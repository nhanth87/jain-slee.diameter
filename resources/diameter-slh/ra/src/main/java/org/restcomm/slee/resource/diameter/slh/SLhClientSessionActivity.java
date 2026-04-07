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

import net.java.slee.resource.diameter.base.events.DiameterMessage;
import net.java.slee.resource.diameter.base.events.avp.AvpNotAllowedException;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.slh.SLhAVPFactory;
import net.java.slee.resource.diameter.slh.SLhActivity;
import net.java.slee.resource.diameter.slh.SLhMessageFactory;
import net.java.slee.resource.diameter.slh.events.RoutingInfoRequest;
import net.java.slee.resource.diameter.slh.events.LocationReportRequest;

import org.jdiameter.api.Answer;
import org.jdiameter.api.EventListener;
import org.jdiameter.api.Message;
import org.jdiameter.api.Request;
import org.jdiameter.api.app.AppSession;
import org.jdiameter.api.app.StateChangeListener;
import org.jdiameter.api.slh.ClientSLhSession;
import org.jdiameter.api.slh.events.LCSRoutingInfoRequest;

import org.mobicents.slee.resource.diameter.base.DiameterActivityImpl;
import org.mobicents.slee.resource.diameter.base.events.DiameterMessageImpl;

/**
 * 
 * SLh Client activity created for request/response use cases
 * 
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a> 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @see SLhActivity
 */
public class SLhClientSessionActivity extends DiameterActivityImpl implements SLhActivity, StateChangeListener<AppSession> {

  private static final long serialVersionUID = 1L;

  protected transient ClientSLhSession clientSession = null;
  protected transient SLhAVPFactory slhAvpFactory = null;
  protected transient SLhMessageFactory messageFactory = null;

  public SLhClientSessionActivity(SLhMessageFactory slhMessageFactory, SLhAVPFactory slhAvpFactory, ClientSLhSession session, DiameterIdentity destinationHost, DiameterIdentity destinationRealm) {
    super(slhMessageFactory.getBaseMessageFactory(), slhAvpFactory.getBaseFactory(), null, (EventListener<Request, Answer>) session, destinationHost, destinationRealm);

    setSession(session);
    super.setCurrentWorkingSession(this.clientSession.getSessions().get(0));
    this.slhAvpFactory = slhAvpFactory;
    this.messageFactory = slhMessageFactory;
  }

  public void setSession(ClientSLhSession session) {
    this.clientSession = session;
    this.clientSession.addStateChangeNotification(this);
  }

  public void sendRoutingInfoRequest(RoutingInfoRequest message) throws IOException {
    try {
      // Wrap the SLEE RoutingInfoRequest to implement LCSRoutingInfoRequest
      LCSRoutingInfoRequest wrappedRequest = new LCSRoutingInfoRequestWrapper(message);
      clientSession.sendRoutingInfoRequest(wrappedRequest);
    }
    catch (org.jdiameter.api.validation.AvpNotAllowedException e) {
      throw new AvpNotAllowedException("Message validation failed.", e, e.getAvpCode(), e.getVendorId());
    }
    catch (Exception e) {
      throw new IOException("Failed to send message, due to: " + e.getLocalizedMessage());
    }
  }

  public void sendLocationReportRequest(LocationReportRequest message) throws IOException {
    try {
      // Wrap the SLEE LocationReportRequest to implement org.jdiameter.api.slh.events.LocationReportRequest
      org.jdiameter.api.slh.events.LocationReportRequest wrappedRequest = new LocationReportRequestWrapper(message);
      clientSession.sendLocationReportRequest(wrappedRequest);
    }
    catch (org.jdiameter.api.validation.AvpNotAllowedException e) {
      throw new AvpNotAllowedException("Message validation failed.", e, e.getAvpCode(), e.getVendorId());
    }
    catch (Exception e) {
      throw new IOException("Failed to send message, due to: " + e.getLocalizedMessage());
    }
  }

  /**
   * Wrapper class that wraps a SLEE RoutingInfoRequest and implements LCSRoutingInfoRequest
   */
  private class LCSRoutingInfoRequestWrapper implements LCSRoutingInfoRequest {
    private final RoutingInfoRequest wrapped;
    private final org.jdiameter.api.Message message;
    
    public LCSRoutingInfoRequestWrapper(RoutingInfoRequest wrapped) {
      this.wrapped = wrapped;
      this.message = ((DiameterMessageImpl) wrapped).getGenericData();
    }
    
    @Override
    public org.jdiameter.api.Message getMessage() throws org.jdiameter.api.InternalException {
      return message;
    }
    
    @Override
    public int getCommandCode() {
      return LCSRoutingInfoRequest.code;
    }
    
    // getShortName and getLongName are not part of AppEvent interface
    
    @Override
    public String getOriginHost() throws org.jdiameter.api.AvpDataException {
      org.jdiameter.api.Avp avp = message.getAvps().getAvp(264); // Origin-Host AVP code
      return avp != null ? avp.getUTF8String() : null;
    }
    
    @Override
    public String getOriginRealm() throws org.jdiameter.api.AvpDataException {
      org.jdiameter.api.Avp avp = message.getAvps().getAvp(296); // Origin-Realm AVP code
      return avp != null ? avp.getUTF8String() : null;
    }
    
    @Override
    public String getDestinationHost() throws org.jdiameter.api.AvpDataException {
      org.jdiameter.api.Avp avp = message.getAvps().getAvp(293); // Destination-Host AVP code
      return avp != null ? avp.getUTF8String() : null;
    }
    
    @Override
    public String getDestinationRealm() throws org.jdiameter.api.AvpDataException {
      org.jdiameter.api.Avp avp = message.getAvps().getAvp(283); // Destination-Realm AVP code
      return avp != null ? avp.getUTF8String() : null;
    }
  }

  /**
   * Wrapper class that wraps a SLEE LocationReportRequest and implements org.jdiameter.api.slh.events.LocationReportRequest
   */
  private class LocationReportRequestWrapper implements org.jdiameter.api.slh.events.LocationReportRequest {
    private final net.java.slee.resource.diameter.slh.events.LocationReportRequest wrapped;
    private final org.jdiameter.api.Message message;
    
    public LocationReportRequestWrapper(net.java.slee.resource.diameter.slh.events.LocationReportRequest wrapped) {
      this.wrapped = wrapped;
      this.message = ((DiameterMessageImpl) wrapped).getGenericData();
    }
    
    @Override
    public org.jdiameter.api.Message getMessage() throws org.jdiameter.api.InternalException {
      return message;
    }
    
    @Override
    public int getCommandCode() {
      return org.jdiameter.api.slh.events.LocationReportRequest.code;
    }
    
    // getShortName and getLongName are not part of AppEvent interface
    
    @Override
    public String getOriginHost() throws org.jdiameter.api.AvpDataException {
      org.jdiameter.api.Avp avp = message.getAvps().getAvp(264); // Origin-Host AVP code
      return avp != null ? avp.getUTF8String() : null;
    }
    
    @Override
    public String getOriginRealm() throws org.jdiameter.api.AvpDataException {
      org.jdiameter.api.Avp avp = message.getAvps().getAvp(296); // Origin-Realm AVP code
      return avp != null ? avp.getUTF8String() : null;
    }
    
    @Override
    public String getDestinationHost() throws org.jdiameter.api.AvpDataException {
      org.jdiameter.api.Avp avp = message.getAvps().getAvp(293); // Destination-Host AVP code
      return avp != null ? avp.getUTF8String() : null;
    }
    
    @Override
    public String getDestinationRealm() throws org.jdiameter.api.AvpDataException {
      org.jdiameter.api.Avp avp = message.getAvps().getAvp(283); // Destination-Realm AVP code
      return avp != null ? avp.getUTF8String() : null;
    }
  }

  public SLhMessageFactory getSLhMessageFactory() {
    return this.messageFactory;
  }

  public SLhAVPFactory getSLhAVPFactory() {
    return this.slhAvpFactory;
  }

  public void setSLhMessageFactory(SLhMessageFactory v) {
    this.messageFactory = v;
  }

  public void setSLhAVPFactory(SLhAVPFactory v) {
    this.slhAvpFactory = v;
  }

  public String getSessionId() {
    return super.getSessionId();
  }

  public void sendMessage(DiameterMessage message) throws IOException {
    super.sendMessage(message);
  }

  public void stateChanged(AppSession source, Enum oldState, Enum newState) {
    this.stateChanged(oldState, newState);
  }

  public void stateChanged(Enum oldState, Enum newState) {
    // no state changes, its stateless!
  }

  ClientSLhSession getClientSession() {
    return this.clientSession;
  }

  @Override
  public void endActivity() {
    this.clientSession.release();
    this.clientSession.removeStateChangeNotification(this);
    super.endActivity();
  }

}
