/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.restcomm.slee.resource.diameter.slh;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;

import javax.management.ObjectName;
import javax.slee.Address;
import javax.slee.facilities.EventLookupFacility;
import javax.slee.facilities.Tracer;
import javax.slee.resource.ActivityFlags;
import javax.slee.resource.ActivityHandle;
import javax.slee.resource.ConfigProperties;
import javax.slee.resource.EventFlags;
import javax.slee.resource.FailureReason;
import javax.slee.resource.FireableEventType;
import javax.slee.resource.InvalidConfigurationException;
import javax.slee.resource.Marshaler;
import javax.slee.resource.ReceivableService;
import javax.slee.resource.ResourceAdaptor;
import javax.slee.resource.ResourceAdaptorContext;
import javax.slee.resource.SleeEndpoint;

import net.java.slee.resource.diameter.Validator;
import net.java.slee.resource.diameter.base.CreateActivityException;
import net.java.slee.resource.diameter.base.DiameterActivity;
import net.java.slee.resource.diameter.base.DiameterAvpFactory;
import net.java.slee.resource.diameter.base.events.DiameterMessage;
import net.java.slee.resource.diameter.base.events.avp.AvpNotAllowedException;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.slh.SLhAVPFactory;
import net.java.slee.resource.diameter.slh.SLhActivity;
import net.java.slee.resource.diameter.slh.SLhMessageFactory;
import net.java.slee.resource.diameter.slh.SLhProvider;
import net.java.slee.resource.diameter.slh.events.LocationReportAnswer;
import net.java.slee.resource.diameter.slh.events.LocationReportRequest;
import net.java.slee.resource.diameter.slh.events.RoutingInfoAnswer;
import net.java.slee.resource.diameter.slh.events.RoutingInfoRequest;

import org.jboss.mx.util.MBeanServerLocator;
import org.jdiameter.api.Answer;
import org.jdiameter.api.ApplicationId;
import org.jdiameter.api.AvpDataException;
import org.jdiameter.api.Message;
import org.jdiameter.api.Peer;
import org.jdiameter.api.PeerTable;
import org.jdiameter.api.Request;
import org.jdiameter.api.Session;
import org.jdiameter.api.SessionFactory;
import org.jdiameter.api.Stack;
import org.jdiameter.api.app.AppAnswerEvent;
import org.jdiameter.api.app.AppRequestEvent;
import org.jdiameter.api.app.AppSession;
import org.jdiameter.api.slh.ClientSLhSession;
import org.jdiameter.api.slh.ServerSLhSession;
import org.jdiameter.client.api.ISessionFactory;
import org.jdiameter.common.impl.app.AppAnswerEventImpl;
import org.jdiameter.common.impl.app.AppRequestEventImpl;
import org.jdiameter.client.impl.app.slh.SLhClientSessionImpl;
import org.jdiameter.server.impl.app.slh.SLhServerSessionImpl;
import org.restcomm.slee.resource.diameter.slh.events.RoutingInfoAnswerImpl;
import org.restcomm.slee.resource.diameter.slh.events.RoutingInfoRequestImpl;
import org.mobicents.diameter.stack.DiameterListener;
import org.mobicents.diameter.stack.DiameterStackMultiplexerMBean;
import org.mobicents.slee.resource.diameter.DiameterActivityManagement;
import org.mobicents.slee.resource.diameter.LocalDiameterActivityManagement;
import org.mobicents.slee.resource.diameter.ValidatorImpl;
import org.mobicents.slee.resource.diameter.base.DiameterActivityHandle;
import org.mobicents.slee.resource.diameter.base.DiameterActivityImpl;
import org.mobicents.slee.resource.diameter.base.DiameterAvpFactoryImpl;
import org.mobicents.slee.resource.diameter.base.DiameterBaseMarshaler;
import org.mobicents.slee.resource.diameter.base.EventIDFilter;
import org.mobicents.slee.resource.diameter.base.events.DiameterMessageImpl;
import org.mobicents.slee.resource.diameter.base.events.ErrorAnswerImpl;
import org.mobicents.slee.resource.diameter.base.events.ExtensionDiameterMessageImpl;
import org.mobicents.slee.resource.diameter.base.handlers.DiameterRAInterface;

/**
 * Mobicents Diameter SLh Resource Adaptor
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class SLhResourceAdaptor implements ResourceAdaptor, DiameterListener, DiameterRAInterface {

  private static final long serialVersionUID = 1L;

  private static final String AUTH_APPLICATION_IDS = "authApplicationIds";

  private List<ApplicationId> authApplicationIds;
  public final EventIDCache eventIdCache = new EventIDCache();
  private final EventIDFilter eventIDFilter = new EventIDFilter();

  private ResourceAdaptorContext raContext;
  private transient SleeEndpoint sleeEndpoint = null;
  private Tracer tracer;
  private DiameterBaseMarshaler marshaler = new DiameterBaseMarshaler();

  private Stack stack;
  private SessionFactory sessionFactory = null;
  private long messageTimeout = 5000;
  private long activityRemoveDelay = 30000;

  private ObjectName diameterMultiplexerObjectName = null;
  private DiameterStackMultiplexerMBean diameterMux = null;

  private DiameterAvpFactory baseAvpFactory = null;
  private SLhAVPFactory slhAvpFactory = null;

  private transient EventLookupFacility eventLookup = null;
  private transient DiameterActivityManagement activities = null;
  private transient SLhProviderImpl raProvider = null;
  private SLhMessageFactory slhMessageFactory;

  private static final int EVENT_FLAGS = getEventFlags();

  private static int getEventFlags() {
    int eventFlags = EventFlags.REQUEST_EVENT_UNREFERENCED_CALLBACK;
    eventFlags = EventFlags.setRequestProcessingFailedCallback(eventFlags);
    eventFlags = EventFlags.setRequestProcessingSuccessfulCallback(eventFlags);
    return eventFlags;
  }

  private static final int DEFAULT_ACTIVITY_FLAGS = ActivityFlags.setRequestSleeActivityGCCallback(ActivityFlags.REQUEST_ENDED_CALLBACK);
  private static final int MARSHALABLE_ACTIVITY_FLAGS = ActivityFlags.setSleeMayMarshal(DEFAULT_ACTIVITY_FLAGS);

  private static final Object[] EMPTY_OBJECT_ARRAY = new Object[]{};
  private static final String[] EMPTY_STRING_ARRAY = new String[]{};

  public SLhResourceAdaptor() {
  }

  // Lifecycle methods ---------------------------------------------------

  public void setResourceAdaptorContext(ResourceAdaptorContext context) {
    this.raContext = context;
    this.tracer = context.getTracer("SLhResourceAdaptor");
    this.sleeEndpoint = context.getSleeEndpoint();
    this.eventLookup = context.getEventLookupFacility();
    this.raProvider = new SLhProviderImpl(this);
  }

  public void unsetResourceAdaptorContext() {
    this.raContext = null;
    this.tracer = null;
    this.sleeEndpoint = null;
    this.eventLookup = null;
    this.raProvider = null;
  }

  public void raActive() {
    if(tracer.isFineEnabled()) {
      tracer.fine("Diameter SLh RA :: raActive.");
    }

    try {
      if(tracer.isInfoEnabled()) {
        tracer.info("Activating Diameter SLh RA Entity");
      }

      this.diameterMultiplexerObjectName = new ObjectName("diameter.mobicents:service=DiameterStackMultiplexer");

      Object object = null;

      if (ManagementFactory.getPlatformMBeanServer().isRegistered(this.diameterMultiplexerObjectName)) {
        object = ManagementFactory.getPlatformMBeanServer().invoke(this.diameterMultiplexerObjectName, "getMultiplexerMBean", new Object[]{}, new String[]{});
        if (tracer.isInfoEnabled()) {
          tracer.info("Trying to get via Platform MBeanServer: " + this.diameterMultiplexerObjectName + ", object: " + object);
        }
      } else {
        object = MBeanServerLocator.locateJBoss().invoke(this.diameterMultiplexerObjectName, "getMultiplexerMBean", new Object[]{}, new String[]{});
        if (tracer.isInfoEnabled()) {
          tracer.info("Trying to get via JBoss MBeanServer: " + this.diameterMultiplexerObjectName + ", object: " + object);
        }
      }

      if (object != null && object instanceof DiameterStackMultiplexerMBean) {
        this.diameterMux = (DiameterStackMultiplexerMBean) object;
      }

      initStack();
      initActivitiesMgmt();

      this.baseAvpFactory = new DiameterAvpFactoryImpl();
      this.slhAvpFactory = new SLhAVPFactoryImpl(baseAvpFactory);

      this.slhMessageFactory = new SLhMessageFactoryImpl(stack);
      
      ApplicationId firstAppId = authApplicationIds.get(0);
      ((SLhMessageFactoryImpl)this.slhMessageFactory).setApplicationId(firstAppId.getVendorId(), firstAppId.getAuthAppId());

      this.sessionFactory = this.stack.getSessionFactory();

      ((ISessionFactory) sessionFactory).registerAppFacory(ClientSLhSession.class, new SLhClientSessionFactory(this, this.sessionFactory));
      ((ISessionFactory) sessionFactory).registerAppFacory(ServerSLhSession.class, new SLhServerSessionFactory(this, this.sessionFactory));
    }
    catch (Exception e) {
      tracer.severe("Error Activating Diameter SLh RA Entity", e);
    }
  }

  public void raStopping() {
    if(tracer.isFineEnabled()) {
      tracer.fine("Diameter SLh RA :: raStopping.");
    }

    try {
      diameterMux.unregisterListener(this);
    }
    catch (Exception e) {
      tracer.severe("Failed to unregister SLh RA from Diameter Mux.", e);
    }

    if(tracer.isInfoEnabled()) {
      tracer.info("Diameter SLh RA :: entityDeactivating completed.");
    }
  }

  public void raInactive() {
    if(tracer.isFineEnabled()) {
      tracer.fine("Diameter SLh RA :: entityDeactivated.");
    }

    activities = null;

    if(tracer.isInfoEnabled()) {
      tracer.info("Diameter SLh RA :: INACTIVE completed.");
    }
  }

  public void raConfigure(ConfigProperties properties) {
    parseApplicationIds((String) properties.getProperty(AUTH_APPLICATION_IDS).getValue());
  }

  private void parseApplicationIds(String appIdsStr) {
    if(appIdsStr != null) {
      appIdsStr = appIdsStr.replaceAll(" ", "");
      String[] appIdsStrings  = appIdsStr.split(",");
      List<ApplicationId> appIds = new ArrayList<ApplicationId>();

      for(String appId : appIdsStrings) {
        String[] vendorAndAppId = appId.split(":");
        appIds.add(ApplicationId.createByAuthAppId(Long.valueOf(vendorAndAppId[0]), Long.valueOf(vendorAndAppId[1]))); 
      }

      authApplicationIds = appIds;
    }
  }

  public void raUnconfigure() {
    this.activities = null;
    this.raContext = null;
    this.eventLookup = null;
    this.raProvider = null;
    this.sleeEndpoint = null;
    this.stack = null;
  }

  public void raVerifyConfiguration(ConfigProperties properties) throws InvalidConfigurationException {
  }

  public void raConfigurationUpdate(ConfigProperties properties) {
  }

  public Object getResourceAdaptorInterface(String className) {
    return raProvider;
  }

  public Marshaler getMarshaler() {
    return this.marshaler;
  }

  public void serviceActive(ReceivableService serviceInfo) {
    eventIDFilter.serviceActive(serviceInfo);   
  }

  public void serviceStopping(ReceivableService serviceInfo) {
    eventIDFilter.serviceStopping(serviceInfo);
  }

  public void serviceInactive(ReceivableService serviceInfo) {
    eventIDFilter.serviceInactive(serviceInfo); 
  }

  public void queryLiveness(ActivityHandle handle) {
    tracer.info("Diameter SLh RA :: queryLiveness :: handle[" + handle + "].");
    if(!(handle instanceof DiameterActivityHandle)) {
      return;
    }

    DiameterActivityImpl activity = (DiameterActivityImpl) activities.get((DiameterActivityHandle) handle);

    if (activity != null && !activity.isValid()) {
      try {
        sleeEndpoint.endActivity(handle);
      }
      catch (Exception e) {
        tracer.severe("Failure ending non-live activity.", e);
      }
    }
  }

  public Object getActivity(ActivityHandle handle) {
    if(tracer.isFineEnabled()) {
      tracer.fine("Diameter SLh RA :: getActivity :: handle[" + handle + "].");
    }
    if(!(handle instanceof DiameterActivityHandle)) {
      return null;
    }
    return this.activities.get((DiameterActivityHandle) handle);
  }

  public ActivityHandle getActivityHandle(Object activity) {
    if(tracer.isFineEnabled()) {
      tracer.fine("Diameter SLh RA :: getActivityHandle :: activity[" + activity + "].");
    }

    if (!(activity instanceof DiameterActivity)) {
      return null;
    }

    DiameterActivityImpl inActivity = (DiameterActivityImpl) activity;
    return inActivity.getActivityHandle();
  }

  public void administrativeRemove(ActivityHandle handle) {
  }

  public void eventProcessingFailed(ActivityHandle handle, FireableEventType eventType, Object event, Address address, ReceivableService service, int flags, FailureReason reason) {
    if(!(handle instanceof DiameterActivityHandle)) {
      return;
    }
    processAfterEventDelivery(handle, eventType, event, address, service, flags);
  }

  public void eventProcessingSuccessful(ActivityHandle handle, FireableEventType eventType, Object event, Address address, ReceivableService service, int flags) {
    if(!(handle instanceof DiameterActivityHandle)) {
      return;
    }
    processAfterEventDelivery(handle, eventType, event, address, service, flags);
  }

  public void eventUnreferenced(ActivityHandle handle, FireableEventType eventType, Object event, Address address, ReceivableService service, int flags) {
    if(!(handle instanceof DiameterActivityHandle)) {
      return;
    }
    processAfterEventDelivery(handle, eventType, event, address, service, flags);
  }

  private void processAfterEventDelivery(ActivityHandle handle, FireableEventType eventType, Object event, Address address, ReceivableService service, int flags) {
    DiameterActivityImpl activity = (DiameterActivityImpl) getActivity(handle);
    if (activity != null) {
      synchronized (activity) {
        if (activity.isTerminateAfterProcessing()) {
          activity.endActivity();
        }
      }
    }
  }

  public void activityEnded(ActivityHandle handle) {
    if(!(handle instanceof DiameterActivityHandle)) {
      return;
    }
    if(this.activities != null) {
      synchronized (this.activities) {
        this.activities.remove((DiameterActivityHandle) handle);
      }
    }
  }

  public void activityUnreferenced(ActivityHandle handle) {
    if(handle instanceof DiameterActivityHandle) {
      this.endActivity((DiameterActivityHandle) handle);
    }
  }

  public boolean fireEvent(Object event, ActivityHandle handle, FireableEventType eventID, Address address, boolean useFiltering, boolean transacted) {

    if (useFiltering && eventIDFilter.filterEvent(eventID)) {
      if (tracer.isFineEnabled()) {
        tracer.fine("Event " + eventID + " filtered");
      }
    }
    else if (eventID == null) {
      tracer.severe("Event ID for " + eventID + " is unknown, unable to fire.");
    }
    else {
      try {
        DiameterActivity activity = (DiameterActivity) getActivity(handle);
        if(activity instanceof SLhServerSessionActivity)
        {
          ((SLhServerSessionActivity)activity).fetchSessionData((DiameterMessage)event, true);
        }
        this.raContext.getSleeEndpoint().fireEvent(handle, eventID, event, address, null, EVENT_FLAGS);
        return true;
      }
      catch (Exception e) {
        tracer.severe("Error firing event.", e);
      }
    }

    return false;
  }

  public void fireEvent(String sessionId, Message message) {
    DiameterMessage event = (DiameterMessage) createEvent(message);
    FireableEventType eventId = eventIdCache.getEventId(eventLookup, message);
    this.fireEvent(event, getActivityHandle(sessionId), eventId, null, true, message.isRequest());
  }

  public void endActivity(DiameterActivityHandle handle) {
    sleeEndpoint.endActivity(handle);
  }

  public void startActivityRemoveTimer(DiameterActivityHandle handle) {
    try {
      this.activities.startActivityRemoveTimer(handle);
    }
    catch (Exception e) {
      tracer.warning("Failed to start activity remove timer.", e);
    }
  }

  public void stopActivityRemoveTimer(DiameterActivityHandle handle) {
    try {
      this.activities.stopActivityRemoveTimer(handle);
    }
    catch (Exception e) {
      tracer.warning("Failed to stop activity remove timer.", e);
    }
  }

  public void update(DiameterActivityHandle handle, DiameterActivity activity) {
    activities.update(handle, activity);
  }

  public ApplicationId[] getSupportedApplications() {
    return null;
  }

  private DiameterMessage createEvent(Message message) {
    if (message == null) {
      throw new NullPointerException("Message argument cannot be null while creating event.");
    }

    int commandCode = message.getCommandCode();

    if (message.isError()) {
      return new ErrorAnswerImpl(message);
    }

    boolean isRequest = message.isRequest();

    switch (commandCode) {
    case net.java.slee.resource.diameter.slh.events.RoutingInfoRequest.commandCode:
      return isRequest ? new org.restcomm.slee.resource.diameter.slh.events.RoutingInfoRequestImpl(message) : new org.restcomm.slee.resource.diameter.slh.events.RoutingInfoAnswerImpl(message);
    default:
      return new ExtensionDiameterMessageImpl(message);
    }
  }

  public void sessionDestroyed(String sessionId, ClientSLhSession session) {
    try {
      this.sleeEndpoint.endActivity(getActivityHandle(sessionId));
    }
    catch (Exception e) {
      tracer.severe( "Failed to end activity with handle[" + getActivityHandle(sessionId) );
    }
  }

  public void sessionDestroyed(String sessionId, ServerSLhSession session) {
    try {
      this.sleeEndpoint.endActivity(getActivityHandle(sessionId));
    }
    catch (Exception e) {
      tracer.severe( "Failed to end activity with handle[" + getActivityHandle(sessionId) );
    }
  }

  private void activityCreated(DiameterActivity ac, boolean suspended) {
    try {
      DiameterActivityImpl activity = (DiameterActivityImpl) ac;

      if (suspended) {
        sleeEndpoint.startActivitySuspended(activity.getActivityHandle(), activity, MARSHALABLE_ACTIVITY_FLAGS);
      }
      else {
        sleeEndpoint.startActivity(activity.getActivityHandle(), activity, MARSHALABLE_ACTIVITY_FLAGS);
      }

      activities.put(activity.getActivityHandle(), activity);

      if(tracer.isInfoEnabled()) {
        tracer.info("Activity started [" + activity.getActivityHandle() + "]");
      }
    }
    catch (Exception e) {
      tracer.severe("Error creating/starting activity.", e);
    }
  }

  public ResourceAdaptorContext getRaContext() {
    return raContext;
  }

  private synchronized void initStack() throws Exception {
    this.diameterMux.registerListener(this, (ApplicationId[]) authApplicationIds.toArray(new ApplicationId[authApplicationIds.size()]));
    this.stack = this.diameterMux.getStack();
    this.messageTimeout = stack.getMetaData().getConfiguration().getLongValue(
      org.jdiameter.client.impl.helpers.Parameters.MessageTimeOut.ordinal(), 
      (Long) org.jdiameter.client.impl.helpers.Parameters.MessageTimeOut.defValue());

    if(tracer.isInfoEnabled()) {
      tracer.info("Diameter SLh RA :: Successfully initialized stack.");
    }
  }

  private void initActivitiesMgmt() {
    this.activities = new LocalDiameterActivityManagement(this.raContext, activityRemoveDelay);
  }

  protected DiameterActivityHandle getActivityHandle(String sessionId) {
    return new DiameterActivityHandle(sessionId);
  }

  public Answer processRequest(Request request) {
    try {
      raProvider.createActivity(request);
    }
    catch (Throwable e) {
      tracer.severe(e.getMessage(), e);
    }
    return null;
  }

  public void receivedSuccessMessage(Request req, Answer ans) {
    try {
      if(tracer.isInfoEnabled()) {
        tracer.info("Received Message Result-Code: " + ans.getResultCode().getUnsigned32());
      }
    }
    catch (AvpDataException ignore) {
    }
  }

  public void timeoutExpired(Request req) {
    try {
      ((DiameterActivity) getActivity(getActivityHandle(req.getSessionId()))).endActivity();
    }
    catch (Exception e) {
      tracer.severe("Failure processing timeout message.", e);
    }
  }

  // Provider Implementation ---------------------------------------------

  class SLhProviderImpl implements SLhProvider {
    private SLhResourceAdaptor ra = null;
    private Validator validator = new ValidatorImpl();

    private final Set<Integer> requestCodes = ConcurrentHashMap.newKeySet();

    public SLhProviderImpl(SLhResourceAdaptor slhResourceAdaptor) {
      this.ra = slhResourceAdaptor;
      requestCodes.add(net.java.slee.resource.diameter.slh.events.RoutingInfoRequest.commandCode);
    }

    public DiameterActivity createActivity(Request request) throws CreateActivityException {
      if(!requestCodes.contains(request.getCommandCode())) {
        throw new CreateActivityException("Cant create activity for unknown command code: " + request.getCommandCode());
      }

      String sessionId = request.getSessionId();
      ApplicationId appId = ApplicationId.createByAuthAppId(10415, 16777291);

      try {
        SLhServerSessionImpl session = ((ISessionFactory) sessionFactory).getNewAppSession(sessionId, appId, ServerSLhSession.class, new Object[]{request});
        sessionCreated(session);
        session.processRequest(request);
      }
      catch(Exception e) {
        throw new CreateActivityException(e);
      }

      return (DiameterActivity) getActivity(getActivityHandle(sessionId));
    }

    private void sessionCreated(SLhServerSessionImpl session) {
      SLhServerSessionActivity activity = new SLhServerSessionActivity(slhMessageFactory, slhAvpFactory, session, null, null);
      activity.setSessionListener(SLhResourceAdaptor.this);
      activityCreated(activity, false);
    }

    public SLhMessageFactory getSLhMessageFactory() {
      return slhMessageFactory;
    }

    public SLhAVPFactory getSLhAVPFactory() {
      return slhAvpFactory;
    }

    public SLhActivity createSLhActivity() throws CreateActivityException {
      try {
        ClientSLhSession session = ((ISessionFactory) sessionFactory).getNewAppSession(null, ApplicationId.createByAuthAppId(10415, 16777291), ClientSLhSession.class, new Object[]{});
        SLhClientSessionActivity activity = new SLhClientSessionActivity(slhMessageFactory, slhAvpFactory, session, null, null);
        activityCreated(activity, false);
        return activity;
      }
      catch (Exception e) {
        throw new CreateActivityException(e);
      }
    }

    public RoutingInfoAnswer routingInfoRequest(RoutingInfoRequest message) throws IOException {
      try {
        SLhActivity activity = createSLhActivity();
        ((SLhClientSessionActivity) activity).sendRoutingInfoRequest(message);
        // FIXME: This should wait for the answer and return it
        return null;
      }
      catch (Exception e) {
        throw new IOException("Failed to send RoutingInfoRequest", e);
      }
    }

    public LocationReportAnswer locationReportRequest(LocationReportRequest message) throws IOException {
      try {
        SLhActivity activity = createSLhActivity();
        ((SLhClientSessionActivity) activity).sendLocationReportRequest(message);
        // FIXME: This should wait for the answer and return it
        return null;
      }
      catch (Exception e) {
        throw new IOException("Failed to send LocationReportRequest", e);
      }
    }

    public DiameterIdentity[] getConnectedPeers() {
      return ra.getConnectedPeers();
    }

    public int getPeerCount() {
      return ra.getConnectedPeers().length;
    }

    public Validator getValidator() {
      return this.validator;
    }
  }

  public DiameterIdentity[] getConnectedPeers() {
    if (stack != null) {
      try {
        List<Peer> peers = stack.unwrap(PeerTable.class).getPeerTable();
        DiameterIdentity[] result = new DiameterIdentity[peers.size()];
        int i = 0;

        for (Peer peer : peers) {
          DiameterIdentity identity = new DiameterIdentity(peer.getUri().toString());
          result[i++] = identity;
        }

        return result;
      }
      catch (Exception e) {
        tracer.severe("Failure getting peer list.", e);
      }
    }

    return null;
  }

  public int getPeerCount() {
    return getConnectedPeers().length;
  }

  public long getMessageTimeout() {
    return this.messageTimeout; 
  }

  // Client Session Factory -------------------------------------------

  private class SLhClientSessionFactory extends org.jdiameter.common.impl.app.slh.SLhSessionFactoryImpl {

    SLhResourceAdaptor ra = null;

    public SLhClientSessionFactory(SLhResourceAdaptor ra, SessionFactory sf) {
      super(sf);
      this.ra = ra;
    }

    public AppSession getNewSession(String sessionId, Class<? extends AppSession> aClass, ApplicationId applicationId, Object[] args) {
      try {
        if (aClass == ClientSLhSession.class) {
          SLhClientSessionImpl clientSession = (SLhClientSessionImpl) super.getNewSession(sessionId, aClass, applicationId, args);
          return clientSession;
        }
        else {
          throw new IllegalArgumentException("Wrong session class!!["+aClass+"]. Supported["+ClientSLhSession.class+"]");
        }
      }
      catch (Exception e) {
        tracer.severe("Failure to obtain new SLh Client Session.", e);
      }

      return null;
    }

    public void doOtherEvent(AppSession appSession, AppRequestEvent request, AppAnswerEvent answer) {
      try {
        this.ra.fireEvent(appSession.getSessions().get(0).getSessionId(), answer != null ? answer.getMessage() : request.getMessage());
      }
      catch (org.jdiameter.api.InternalException e) {
        tracer.severe("Failed to fire event", e);
      }
    }

    public void doRoutingInfoAnswerEvent(ClientSLhSession appSession, org.jdiameter.api.slh.events.LCSRoutingInfoRequest request, org.jdiameter.api.slh.events.LCSRoutingInfoAnswer answer) {
      try {
        this.ra.fireEvent(appSession.getSessions().get(0).getSessionId(), answer.getMessage());
      }
      catch (org.jdiameter.api.InternalException e) {
        tracer.severe("Failed to fire RoutingInfoAnswer event", e);
      }
    }

    public void stateChanged(Enum oldState, Enum newState) {
    }

    public void stateChanged(AppSession source, Enum oldState, Enum newState) {
    }

    public org.jdiameter.api.slh.events.LCSRoutingInfoAnswer createRoutingInfoAnswer(Answer answer) {
      return new org.jdiameter.common.impl.app.slh.LCSRoutingInfoAnswerImpl(answer);
    }

    public org.jdiameter.api.slh.events.LCSRoutingInfoRequest createRoutingInfoRequest(Request request) {
      return new org.jdiameter.common.impl.app.slh.LCSRoutingInfoRequestImpl(request);
    }

    public long getApplicationId() {
      return authApplicationIds.get(0).getAuthAppId();
    }

    public long getMessageTimeout() {
      return this.ra.messageTimeout;
    }
  }

  // Server Session Factory -------------------------------------------

  private class SLhServerSessionFactory extends org.jdiameter.common.impl.app.slh.SLhSessionFactoryImpl {

    SLhResourceAdaptor ra = null;

    public SLhServerSessionFactory(SLhResourceAdaptor ra, SessionFactory sf) {
      super(sf);
      this.ra = ra;
    }

    public AppSession getNewSession(String sessionId, Class<? extends AppSession> aClass, ApplicationId applicationId, Object[] args) {
      try {
        if (aClass == ServerSLhSession.class) {
          SLhServerSessionImpl serverSession = (SLhServerSessionImpl) super.getNewSession(sessionId, aClass, applicationId, args);
          return serverSession;
        }
        else {
          throw new IllegalArgumentException("Wrong session class!!["+aClass+"]. Supported["+ServerSLhSession.class+"]");
        }
      }
      catch (Exception e) {
        tracer.severe("Failure to obtain new SLh Server Session.", e);
      }

      return null;
    }

    public void doOtherEvent(AppSession appSession, AppRequestEvent request, AppAnswerEvent answer) {
      try {
        this.ra.fireEvent(appSession.getSessions().get(0).getSessionId(), answer != null ? answer.getMessage() : request.getMessage());
      }
      catch (org.jdiameter.api.InternalException e) {
        tracer.severe("Failed to fire event", e);
      }
    }

    public void doRoutingInfoRequestEvent(ServerSLhSession appSession, org.jdiameter.api.slh.events.LCSRoutingInfoRequest request) {
      try {
        this.ra.fireEvent(appSession.getSessions().get(0).getSessionId(), request.getMessage());
      }
      catch (org.jdiameter.api.InternalException e) {
        tracer.severe("Failed to fire RoutingInfoRequest event", e);
      }
    }

    public void stateChanged(Enum oldState, Enum newState) {
    }

    public void stateChanged(AppSession source, Enum oldState, Enum newState) {
    }

    public org.jdiameter.api.slh.events.LCSRoutingInfoAnswer createRoutingInfoAnswer(Answer answer) {
      return new org.jdiameter.common.impl.app.slh.LCSRoutingInfoAnswerImpl(answer);
    }

    public org.jdiameter.api.slh.events.LCSRoutingInfoRequest createRoutingInfoRequest(Request request) {
      return new org.jdiameter.common.impl.app.slh.LCSRoutingInfoRequestImpl(request);
    }

    public long getApplicationId() {
      return authApplicationIds.get(0).getAuthAppId();
    }

    public long getMessageTimeout() {
      return this.ra.messageTimeout;
    }
  }

}
