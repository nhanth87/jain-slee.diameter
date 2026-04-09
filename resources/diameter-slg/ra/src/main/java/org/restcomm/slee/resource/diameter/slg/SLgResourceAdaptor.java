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

package org.restcomm.slee.resource.diameter.slg;

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
import net.java.slee.resource.diameter.slg.SLgAVPFactory;
import net.java.slee.resource.diameter.slg.SLgActivity;
import net.java.slee.resource.diameter.slg.SLgMessageFactory;
import net.java.slee.resource.diameter.slg.SLgProvider;
import net.java.slee.resource.diameter.slg.events.LocationAnswer;
import net.java.slee.resource.diameter.slg.events.LocationRequest;
import net.java.slee.resource.diameter.slg.events.ProvideLocationAnswer;
import net.java.slee.resource.diameter.slg.events.ProvideLocationRequest;

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
import org.jdiameter.api.slg.ClientSLgSession;
import org.jdiameter.api.slg.ServerSLgSession;
import org.restcomm.slee.resource.diameter.slg.events.LocationAnswerImpl;
import org.restcomm.slee.resource.diameter.slg.events.LocationRequestImpl;
import org.restcomm.slee.resource.diameter.slg.events.ProvideLocationAnswerImpl;
import org.restcomm.slee.resource.diameter.slg.events.ProvideLocationRequestImpl;
import org.jdiameter.client.api.ISessionFactory;
import org.jdiameter.client.impl.app.slg.SLgClientSessionImpl;
import org.jdiameter.server.impl.app.slg.SLgServerSessionImpl;
import org.jdiameter.common.impl.app.AppAnswerEventImpl;
import org.jdiameter.common.impl.app.AppRequestEventImpl;
import org.jdiameter.common.impl.app.slg.SLgSessionFactoryImpl;
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
 * Mobicents Diameter SLg Resource Adaptor
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class SLgResourceAdaptor implements ResourceAdaptor, DiameterListener, DiameterRAInterface {

  private static final long serialVersionUID = 1L;

  // Config Properties Names ---------------------------------------------

  private static final String AUTH_APPLICATION_IDS = "authApplicationIds";

  // Config Properties Values --------------------------------------------

  private List<ApplicationId> authApplicationIds;

  /**
   * caches the eventIDs, avoiding lookup in container
   */
  public final EventIDCache eventIdCache = new EventIDCache();

  /**
   * tells the RA if an event with a specified ID should be filtered or not
   */
  private final EventIDFilter eventIDFilter = new EventIDFilter();

  private ResourceAdaptorContext raContext;
  private transient SleeEndpoint sleeEndpoint = null;
  private Tracer tracer;
  private DiameterBaseMarshaler marshaler = new DiameterBaseMarshaler();

  // Diameter Specific Properties ----------------------------------------

  private Stack stack;
  private SessionFactory sessionFactory = null;
  private long messageTimeout = 5000;

  private long activityRemoveDelay = 30000;

  private ObjectName diameterMultiplexerObjectName = null;
  private DiameterStackMultiplexerMBean diameterMux = null;

  private DiameterAvpFactory baseAvpFactory = null;
  private SLgAVPFactory slgAvpFactory = null;

  private transient EventLookupFacility eventLookup = null;

  private transient DiameterActivityManagement activities = null;

  private transient SLgProviderImpl raProvider = null;

  private SLgMessageFactory slgMessageFactory;

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

  public SLgResourceAdaptor() {
    // Initialize any default values.
  }

  // Lifecycle methods ---------------------------------------------------

  public void setResourceAdaptorContext(ResourceAdaptorContext context) {
    this.raContext = context;
    this.tracer = context.getTracer("SLgResourceAdaptor");
    this.sleeEndpoint = context.getSleeEndpoint();
    this.eventLookup = context.getEventLookupFacility();
    this.raProvider = new SLgProviderImpl(this);
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
      tracer.fine("Diameter SLg RA :: raActive.");
    }

    try {
      if(tracer.isInfoEnabled()) {
        tracer.info("Activating Diameter SLg RA Entity");
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

      // Initialize stack
      initStack();

      // Initialize activities mgmt
      initActivitiesMgmt();

      // Initialize factories
      this.baseAvpFactory = new DiameterAvpFactoryImpl();
      this.slgAvpFactory = new SLgAVPFactoryImpl(baseAvpFactory);

      this.slgMessageFactory = new SLgMessageFactoryImpl(stack);
      
      // Set the first configured Application-Id as default for message factory
      ApplicationId firstAppId = authApplicationIds.get(0);
      ((SLgMessageFactoryImpl)this.slgMessageFactory).setApplicationId(firstAppId.getVendorId(), firstAppId.getAuthAppId());

      // Setup session factories
      this.sessionFactory = this.stack.getSessionFactory();

      ((ISessionFactory) sessionFactory).registerAppFacory(ClientSLgSession.class, new SLgClientSessionFactory(this, this.sessionFactory));
      ((ISessionFactory) sessionFactory).registerAppFacory(ServerSLgSession.class, new SLgServerSessionFactory(this, this.sessionFactory));
    }
    catch (Exception e) {
      tracer.severe("Error Activating Diameter SLg RA Entity", e);
    }
  }

  public void raStopping() {
    if(tracer.isFineEnabled()) {
      tracer.fine("Diameter SLg RA :: raStopping.");
    }

    try {
      diameterMux.unregisterListener(this);
    }
    catch (Exception e) {
      tracer.severe("Failed to unregister SLg RA from Diameter Mux.", e);
    }

    if(tracer.isInfoEnabled()) {
      tracer.info("Diameter SLg RA :: entityDeactivating completed.");
    }
  }

  public void raInactive() {
    if(tracer.isFineEnabled()) {
      tracer.fine("Diameter SLg RA :: entityDeactivated.");
    }

    activities = null;

    if(tracer.isInfoEnabled()) {
      tracer.info("Diameter SLg RA :: INACTIVE completed.");
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
    // TODO Auto-generated method stub
  }

  public void raConfigurationUpdate(ConfigProperties properties) {
    // this ra does not support config update while entity is active    
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
    tracer.info("Diameter SLg RA :: queryLiveness :: handle[" + handle + "].");
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
      tracer.fine("Diameter SLg RA :: getActivity :: handle[" + handle + "].");
    }
    if(!(handle instanceof DiameterActivityHandle)) {
      return null;
    }
    return this.activities.get((DiameterActivityHandle) handle);
  }

  public ActivityHandle getActivityHandle(Object activity) {
    if(tracer.isFineEnabled()) {
      tracer.fine("Diameter SLg RA :: getActivityHandle :: activity[" + activity + "].");
    }

    if (!(activity instanceof DiameterActivity)) {
      return null;
    }

    DiameterActivityImpl inActivity = (DiameterActivityImpl) activity;
    return inActivity.getActivityHandle();
  }

  public void administrativeRemove(ActivityHandle handle) {
    // TODO what to do here?
  }

  public void eventProcessingFailed(ActivityHandle handle, FireableEventType eventType, Object event, Address address, ReceivableService service, int flags, FailureReason reason) {
    if(tracer.isInfoEnabled()) {
      tracer.info("Diameter SLg RA :: eventProcessingFailed :: handle[" + handle + "], eventType[" + eventType + "], event[" + event + "], address[" + address + "], flags[" + flags + "], reason[" + reason + "].");
    }
    if(!(handle instanceof DiameterActivityHandle)) {
      return;
    }

    processAfterEventDelivery(handle, eventType, event, address, service, flags);
  }

  public void eventProcessingSuccessful(ActivityHandle handle, FireableEventType eventType, Object event, Address address, ReceivableService service, int flags) {
    if(tracer.isInfoEnabled()) {
      tracer.info("Diameter SLg RA :: eventProcessingSuccessful :: handle[" + handle + "], eventType[" + eventType + "], event[" + event + "], address[" + address + "], flags[" + flags + "].");
    }
    if(!(handle instanceof DiameterActivityHandle)) {
      return;
    }

    processAfterEventDelivery(handle, eventType, event, address, service, flags);
  }

  public void eventUnreferenced(ActivityHandle handle, FireableEventType eventType, Object event, Address address, ReceivableService service, int flags) {
    if(tracer.isFineEnabled()) {
      tracer.fine("Diameter SLg RA :: eventUnreferenced :: handle[" + handle + "], eventType[" + eventType + "], event[" + event + "], address[" + address + "], service[" + service + "], flags[" + flags + "].");
    }
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
    tracer.info("Diameter SLg RA :: activityEnded :: handle[" + handle + ".");
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
    if(tracer.isFineEnabled()) {
      tracer.fine("Diameter SLg RA :: activityUnreferenced :: handle[" + handle + "].");
    }
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
      if (tracer.isFineEnabled()) {
        tracer.fine("Firing event " + event + " on handle " + handle);
      }
      try {
        DiameterActivity activity = (DiameterActivity) getActivity(handle);
        if(activity instanceof SLgServerSessionActivity)
        {
          ((SLgServerSessionActivity)activity).fetchSessionData((DiameterMessage)event, true);
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

  /**
   * Create Event object from a JDiameter message (request or answer)
   */
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
    case net.java.slee.resource.diameter.slg.events.ProvideLocationRequest.commandCode:
      return isRequest ? new ProvideLocationRequestImpl(message) : new ProvideLocationAnswerImpl(message);
    case net.java.slee.resource.diameter.slg.events.LocationRequest.commandCode:
      return isRequest ? new LocationRequestImpl(message) : new LocationAnswerImpl(message);

    default:
      return new ExtensionDiameterMessageImpl(message);
    }
  }

  public void sessionDestroyed(String sessionId, ClientSLgSession session) {
    try {
      this.sleeEndpoint.endActivity(getActivityHandle(sessionId));
    }
    catch (Exception e) {
      tracer.severe( "Failed to end activity with handle[" + getActivityHandle(sessionId) );
    }
  }

  public void sessionDestroyed(String sessionId, ServerSLgSession session) {
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
      tracer.info("Diameter SLg RA :: Successfully initialized stack.");
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
    if(tracer.isFineEnabled()) {
      tracer.fine("Diameter SLg RA :: receivedSuccessMessage :: " + "Request[" + req + "], Answer[" + ans + "].");
    }

    try {
      if(tracer.isInfoEnabled()) {
        tracer.info("Received Message Result-Code: " + ans.getResultCode().getUnsigned32());
      }
    }
    catch (AvpDataException ignore) {
    }
  }

  public void timeoutExpired(Request req) {
    if(tracer.isInfoEnabled()) {
      tracer.info("Diameter SLg RA :: timeoutExpired :: " + "Request[" + req + "].");
    }

    try {
      ((DiameterActivity) getActivity(getActivityHandle(req.getSessionId()))).endActivity();
    }
    catch (Exception e) {
      tracer.severe("Failure processing timeout message.", e);
    }
  }

  // Provider Implementation ---------------------------------------------

  class SLgProviderImpl implements SLgProvider {
    private SLgResourceAdaptor ra = null;
    private Validator validator = new ValidatorImpl();

    private final Set<Integer> requestCodes = ConcurrentHashMap.newKeySet();

    public SLgProviderImpl(SLgResourceAdaptor slgResourceAdaptor) {
      this.ra = slgResourceAdaptor;

      requestCodes.add(net.java.slee.resource.diameter.slg.events.ProvideLocationRequest.commandCode);
      requestCodes.add(net.java.slee.resource.diameter.slg.events.LocationRequest.commandCode);
    }

    public DiameterActivity createActivity(Request request) throws CreateActivityException {
      if(!requestCodes.contains(request.getCommandCode())) {
        throw new CreateActivityException("Cant create activity for unknown command code: " + request.getCommandCode());
      }

      String sessionId = request.getSessionId();
      ApplicationId appId = ApplicationId.createByAuthAppId(10415, 16777255);

      try {
        SLgServerSessionImpl session = ((ISessionFactory) sessionFactory).getNewAppSession(sessionId, appId, ServerSLgSession.class, new Object[]{request});
        sessionCreated(session);
        session.processRequest(request);
      }
      catch(Exception e) {
        throw new CreateActivityException(e);
      }

      return (DiameterActivity) getActivity(getActivityHandle(sessionId));
    }

    private void sessionCreated(SLgServerSessionImpl session) {
      SLgServerSessionActivity activity = new SLgServerSessionActivity(slgMessageFactory, slgAvpFactory, session, null, null);
      activity.setSessionListener(SLgResourceAdaptor.this);
      activityCreated(activity, false);
    }

    public SLgMessageFactory getSLgMessageFactory() {
      return slgMessageFactory;
    }

    public SLgAVPFactory getSLgAVPFactory() {
      return slgAvpFactory;
    }

    public SLgActivity createSLgActivity() throws CreateActivityException {
      try {
        ClientSLgSession session = ((ISessionFactory) sessionFactory).getNewAppSession(null, ApplicationId.createByAuthAppId(10415, 16777255), ClientSLgSession.class, new Object[]{});
        SLgClientSessionActivity activity = new SLgClientSessionActivity(slgMessageFactory, slgAvpFactory, session, null, null);
        activityCreated(activity, false);
        return activity;
      }
      catch (Exception e) {
        throw new CreateActivityException(e);
      }
    }

    public ProvideLocationAnswer provideLocationRequest(ProvideLocationRequest message) throws IOException {
      try {
        SLgActivity activity = createSLgActivity();
        ((SLgClientSessionActivity) activity).sendProvideLocationRequest(message);
        // FIXME: This should wait for the answer and return it
        return null;
      }
      catch (Exception e) {
        throw new IOException("Failed to send ProvideLocationRequest", e);
      }
    }

    public LocationAnswer locationRequest(LocationRequest message) throws IOException {
      try {
        SLgActivity activity = createSLgActivity();
        ((SLgClientSessionActivity) activity).sendLocationRequest(message);
        // FIXME: This should wait for the answer and return it
        return null;
      }
      catch (Exception e) {
        throw new IOException("Failed to send LocationRequest", e);
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

  private class SLgClientSessionFactory extends SLgSessionFactoryImpl {

    SLgResourceAdaptor ra = null;

    public SLgClientSessionFactory(SLgResourceAdaptor ra, SessionFactory sf) {
      super(sf);
      this.ra = ra;
    }

    public AppSession getNewSession(String sessionId, Class<? extends AppSession> aClass, ApplicationId applicationId, Object[] args) {
      try {
        if (aClass == ClientSLgSession.class) {
          SLgClientSessionImpl clientSession = (SLgClientSessionImpl) super.getNewSession(sessionId, aClass, applicationId, args);
          return clientSession;
        }
        else {
          throw new IllegalArgumentException("Wrong session class!!["+aClass+"]. Supported["+ClientSLgSession.class+"]");
        }
      }
      catch (Exception e) {
        tracer.severe("Failure to obtain new SLg Client Session.", e);
      }

      return null;
    }

    public void doOtherEvent(AppSession appSession, AppRequestEvent request, AppAnswerEvent answer) {
      if(tracer.isFineEnabled()) {
        tracer.fine("Diameter SLg RA :: doOtherEvent :: appSession[" + appSession + "], Request[" + request + "], Answer[" + answer + "]");
      }

      try {
        this.ra.fireEvent(appSession.getSessions().get(0).getSessionId(), answer != null ? answer.getMessage() : request.getMessage());
      }
      catch (org.jdiameter.api.InternalException e) {
        tracer.severe("Failed to fire event", e);
      }
    }

    public void doProvideLocationAnswerEvent(ClientSLgSession appSession, org.jdiameter.api.slg.events.ProvideLocationRequest request, org.jdiameter.api.slg.events.ProvideLocationAnswer answer) {
      if(tracer.isFineEnabled()) {
        tracer.fine("doProvideLocationAnswerEvent :: appSession[" + appSession + "], request[" + request + "], answer[" + answer + "]");
      }

      try {
        this.ra.fireEvent(appSession.getSessions().get(0).getSessionId(), answer.getMessage());
      }
      catch (org.jdiameter.api.InternalException e) {
        tracer.severe("Failed to fire ProvideLocationAnswer event", e);
      }
    }

    public void doLocationAnswerEvent(ClientSLgSession appSession, org.jdiameter.api.slg.events.LocationRequest request, org.jdiameter.api.slg.events.LocationAnswer answer) {
      if(tracer.isFineEnabled()) {
        tracer.fine("doLocationAnswerEvent :: appSession[" + appSession + "], request[" + request + "], answer[" + answer + "]");
      }

      try {
        this.ra.fireEvent(appSession.getSessions().get(0).getSessionId(), answer.getMessage());
      }
      catch (org.jdiameter.api.InternalException e) {
        tracer.severe("Failed to fire LocationAnswer event", e);
      }
    }

    public void stateChanged(Enum oldState, Enum newState) {
      if(tracer.isInfoEnabled()) {
        tracer.info("Diameter SLg ClientSessionFactory :: stateChanged :: oldState[" + oldState + "], newState[" + newState + "]");
      }
    }

    public void stateChanged(AppSession source, Enum oldState, Enum newState) {
      if(tracer.isInfoEnabled()) {
        tracer.info("Diameter SLg ClientSessionFactory :: stateChanged :: source["+ source +"] :: oldState[" + oldState + "], newState[" + newState + "]");
      }
    }

    public org.jdiameter.api.slg.events.ProvideLocationAnswer createProvideLocationAnswer(Answer answer) {
      return new org.jdiameter.common.impl.app.slg.ProvideLocationAnswerImpl(answer);
    }

    public org.jdiameter.api.slg.events.ProvideLocationRequest createProvideLocationRequest(Request request) {
      return new org.jdiameter.common.impl.app.slg.ProvideLocationRequestImpl(request);
    }

    public org.jdiameter.api.slg.events.LocationAnswer createLocationAnswer(Answer answer) {
      return new org.jdiameter.common.impl.app.slg.LocationAnswerImpl(answer);
    }

    public org.jdiameter.api.slg.events.LocationRequest createLocationRequest(Request request) {
      return new org.jdiameter.common.impl.app.slg.LocationRequestImpl(request);
    }

    public long getApplicationId() {
      return authApplicationIds.get(0).getAuthAppId();
    }

    public long getMessageTimeout() {
      return this.ra.messageTimeout;
    }
  }

  // Server Session Factory -------------------------------------------

  private class SLgServerSessionFactory extends SLgSessionFactoryImpl {

    SLgResourceAdaptor ra = null;

    public SLgServerSessionFactory(SLgResourceAdaptor ra, SessionFactory sf) {
      super(sf);
      this.ra = ra;
    }

    public AppSession getNewSession(String sessionId, Class<? extends AppSession> aClass, ApplicationId applicationId, Object[] args) {
      try {
        if (aClass == ServerSLgSession.class) {
          SLgServerSessionImpl serverSession = (SLgServerSessionImpl) super.getNewSession(sessionId, aClass, applicationId, args);
          return serverSession;
        }
        else {
          throw new IllegalArgumentException("Wrong session class!!["+aClass+"]. Supported["+ServerSLgSession.class+"]");
        }
      }
      catch (Exception e) {
        tracer.severe("Failure to obtain new SLg Server Session.", e);
      }

      return null;
    }

    public void doOtherEvent(AppSession appSession, AppRequestEvent request, AppAnswerEvent answer) {
      if(tracer.isFineEnabled()) {
        tracer.fine("Diameter SLg RA :: doOtherEvent :: appSession[" + appSession + "], Request[" + request + "], Answer[" + answer + "]");
      }

      try {
        this.ra.fireEvent(appSession.getSessions().get(0).getSessionId(), answer != null ? answer.getMessage() : request.getMessage());
      }
      catch (org.jdiameter.api.InternalException e) {
        tracer.severe("Failed to fire event", e);
      }
    }

    public void doProvideLocationRequestEvent(ServerSLgSession appSession, org.jdiameter.api.slg.events.ProvideLocationRequest request) {
      if(tracer.isFineEnabled()) {
        tracer.fine("doProvideLocationRequestEvent :: appSession[" + appSession + "], request[" + request + "]");
      }

      try {
        this.ra.fireEvent(appSession.getSessions().get(0).getSessionId(), request.getMessage());
      }
      catch (org.jdiameter.api.InternalException e) {
        tracer.severe("Failed to fire ProvideLocationRequest event", e);
      }
    }

    public void doLocationRequestEvent(ServerSLgSession appSession, org.jdiameter.api.slg.events.LocationRequest request) {
      if(tracer.isFineEnabled()) {
        tracer.fine("doLocationRequestEvent :: appSession[" + appSession + "], request[" + request + "]");
      }

      try {
        this.ra.fireEvent(appSession.getSessions().get(0).getSessionId(), request.getMessage());
      }
      catch (org.jdiameter.api.InternalException e) {
        tracer.severe("Failed to fire LocationRequest event", e);
      }
    }

    public void stateChanged(Enum oldState, Enum newState) {
      if(tracer.isInfoEnabled()) {
        tracer.info("Diameter SLg ServerSessionFactory :: stateChanged :: oldState[" + oldState + "], newState[" + newState + "]");
      }
    }

    public void stateChanged(AppSession source, Enum oldState, Enum newState) {
      if(tracer.isInfoEnabled()) {
        tracer.info("Diameter SLg ServerSessionFactory :: stateChanged :: source["+ source +"] :: oldState[" + oldState + "], newState[" + newState + "]");
      }
    }

    public org.jdiameter.api.slg.events.ProvideLocationAnswer createProvideLocationAnswer(Answer answer) {
      return new org.jdiameter.common.impl.app.slg.ProvideLocationAnswerImpl(answer);
    }

    public org.jdiameter.api.slg.events.ProvideLocationRequest createProvideLocationRequest(Request request) {
      return new org.jdiameter.common.impl.app.slg.ProvideLocationRequestImpl(request);
    }

    public org.jdiameter.api.slg.events.LocationAnswer createLocationAnswer(Answer answer) {
      return new org.jdiameter.common.impl.app.slg.LocationAnswerImpl(answer);
    }

    public org.jdiameter.api.slg.events.LocationRequest createLocationRequest(Request request) {
      return new org.jdiameter.common.impl.app.slg.LocationRequestImpl(request);
    }

    public long getApplicationId() {
      return authApplicationIds.get(0).getAuthAppId();
    }

    public long getMessageTimeout() {
      return this.ra.messageTimeout;
    }
  }

}
