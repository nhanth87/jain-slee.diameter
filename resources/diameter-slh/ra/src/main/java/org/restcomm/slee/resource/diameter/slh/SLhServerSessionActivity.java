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
import java.util.ArrayList;

import net.java.slee.resource.diameter.base.events.DiameterMessage;
import net.java.slee.resource.diameter.base.events.avp.AuthSessionStateType;
import net.java.slee.resource.diameter.base.events.avp.AvpNotAllowedException;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.slh.SLhAVPFactory;
import net.java.slee.resource.diameter.slh.SLhActivity;
import net.java.slee.resource.diameter.slh.SLhMessageFactory;
import net.java.slee.resource.diameter.slh.events.RoutingInfoAnswer;
import net.java.slee.resource.diameter.slh.events.RoutingInfoRequest;

import org.jdiameter.api.Answer;
import org.jdiameter.api.EventListener;
import org.jdiameter.api.Message;
import org.jdiameter.api.Request;
import org.jdiameter.api.app.AppSession;
import org.jdiameter.api.app.StateChangeListener;
import org.jdiameter.api.slh.ServerSLhSession;
import org.jdiameter.api.slh.events.LCSRoutingInfoAnswer;
import org.restcomm.slee.resource.diameter.slh.events.RoutingInfoAnswerImpl;
import org.mobicents.slee.resource.diameter.base.DiameterActivityImpl;
import org.mobicents.slee.resource.diameter.base.events.DiameterMessageImpl;

/**
 * Implementation of stateless SLh Server activity which receives requests. 
 * It ends after response is sent.
 * 
 * @author <a href = "mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 * @author <a href = "mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @see SLhActivity
 */
public class SLhServerSessionActivity extends DiameterActivityImpl implements SLhActivity, StateChangeListener<AppSession> {

  private static final long serialVersionUID = 1L;

  protected transient ServerSLhSession serverSession = null;

  // Factories
  protected transient SLhAVPFactory slhAvpFactory = null;
  protected transient SLhMessageFactory messageFactory = null;

  protected AuthSessionStateType authSessionState;
  protected DiameterIdentity remoteRealm;
  protected DiameterIdentity remoteHost;

  /**
   * Should contain requests, so we can create answer.
   */
  protected transient ArrayList<DiameterMessage> stateMessages = new ArrayList<DiameterMessage>();

  public SLhServerSessionActivity(SLhMessageFactory slhMessageFactory, SLhAVPFactory slhAvpFactory, ServerSLhSession session, DiameterIdentity destinationHost, DiameterIdentity destinationRealm) {
    super(null, null, null, (EventListener<Request, Answer>) session, destinationHost, destinationRealm);

    setSession(session);
    super.setCurrentWorkingSession(this.serverSession.getSessions().get(0));
    this.slhAvpFactory = slhAvpFactory;
    this.messageFactory = slhMessageFactory;
  }

  /**
   * Create a RoutingInfoAnswer containing a Result-Code or Experimental-Result
   * AVP populated with the given value.
   */
  public RoutingInfoAnswer createRoutingInfoAnswer(long resultCode, boolean isExperimentalResult) {
    RoutingInfoRequest req = (RoutingInfoRequest) getSessionMessage(RoutingInfoRequest.commandCode);

    RoutingInfoAnswer answer = null;

    if(req != null) {
      answer = this.messageFactory.createRoutingInfoAnswer(req, resultCode, isExperimentalResult);
      addSessionData(answer);
    }

    return answer;
  }

  /**
   * Create an empty RoutingInfoAnswer that will need to have AVPs set on it
   * before being sent.
   */
  public RoutingInfoAnswer createRoutingInfoAnswer() {
    RoutingInfoRequest req = (RoutingInfoRequest) getSessionMessage(RoutingInfoRequest.commandCode);

    RoutingInfoAnswer answer = null;

    if(req != null) {
      answer = this.messageFactory.createRoutingInfoAnswer(req);
      addSessionData(answer);
    }

    return answer;
  }

  public void sendRoutingInfoAnswer(RoutingInfoAnswer message) throws IOException {
    try {
      DiameterMessageImpl msg = (DiameterMessageImpl) message;
      fetchSessionData(msg, false);
      // Wrap the SLEE RoutingInfoAnswer to implement LCSRoutingInfoAnswer
      LCSRoutingInfoAnswer wrappedAnswer = new LCSRoutingInfoAnswerWrapper(message);
      this.serverSession.sendRoutingInfoAnswer(wrappedAnswer);
      clean(msg);
    }
    catch (org.jdiameter.api.validation.AvpNotAllowedException e) {
      throw new AvpNotAllowedException("Message validation failed.", e, e.getAvpCode(), e.getVendorId());
    }
    catch (Exception e) {
      throw new IOException("Failed to send message, due to: " + e);
    }
  }

  /**
   * Server side should not send RoutingInfoRequest - this is a client side message.
   * This method throws UnsupportedOperationException.
   */
  public void sendRoutingInfoRequest(RoutingInfoRequest message) throws IOException {
    throw new UnsupportedOperationException("Server side cannot send RoutingInfoRequest");
  }

  /**
   * Server side should not send LocationReportRequest - this is a client side message.
   * This method throws UnsupportedOperationException.
   */
  public void sendLocationReportRequest(net.java.slee.resource.diameter.slh.events.LocationReportRequest message) throws IOException {
    throw new UnsupportedOperationException("Server side cannot send LocationReportRequest");
  }

  /**
   * Wrapper class that wraps a SLEE RoutingInfoAnswer and implements LCSRoutingInfoAnswer
   */
  private class LCSRoutingInfoAnswerWrapper implements LCSRoutingInfoAnswer {
    private final RoutingInfoAnswer wrapped;
    private final org.jdiameter.api.Message message;
    
    public LCSRoutingInfoAnswerWrapper(RoutingInfoAnswer wrapped) {
      this.wrapped = wrapped;
      this.message = ((DiameterMessageImpl) wrapped).getGenericData();
    }
    
    @Override
    public org.jdiameter.api.Message getMessage() throws org.jdiameter.api.InternalException {
      return message;
    }
    
    @Override
    public int getCommandCode() {
      return LCSRoutingInfoAnswer.code;
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
    public org.jdiameter.api.Avp getResultCodeAvp() throws org.jdiameter.api.AvpDataException {
      // First try to get Result-Code (AVP code 268)
      org.jdiameter.api.Avp resultCodeAvp = message.getAvps().getAvp(268);
      if (resultCodeAvp != null) {
        return resultCodeAvp;
      }
      // If not present, try Experimental-Result (AVP code 297)
      return message.getAvps().getAvp(297);
    }
  }

  // #########################
  // # StateChangeListener
  // #########################

  public void stateChanged(AppSession arg0, Enum oldState, Enum newState) {
    this.stateChanged(oldState, newState);
  }

  public void stateChanged(Enum oldState, Enum newState) {
    // NOP
  }

  // #########################
  // # DiameterActivityImpl
  // #########################

  public void fetchSessionData(DiameterMessage msg, boolean incoming) {
    if(msg.getHeader().isRequest()) {
      if(incoming) {
        boolean changed = false;
        if(remoteRealm == null ) {
          remoteRealm = msg.getOriginRealm();
          changed = true;
        }
        if(remoteHost == null) {
          changed = true;
          remoteHost = msg.getOriginHost();
        }

        if(this.authSessionState == null) {
          changed = true;
          // Auth-Session-State AVP code is 277
          try {
            this.authSessionState = AuthSessionStateType.fromInt(
              ((DiameterMessageImpl)msg).getGenericData().getAvps().getAvp(277).getInteger32());
          }
          catch (org.jdiameter.api.AvpDataException e) {
            // Ignore if we can't get the auth session state
          }
        }

        stateMessages.add((DiameterMessageImpl) msg);
        if(changed) {
          super.baseListener.update(getActivityHandle(), this);
        }
      }
    }
  }

  @Override
  public void endActivity() {
    this.serverSession.release();
    this.serverSession.removeStateChangeNotification(this);
    super.endActivity();
  }

  // Aux Methods ---------------------------------------------------------

  private void clean(DiameterMessageImpl msg) {
    if(msg.getData() != null) {
      this.stateMessages.remove(msg.removeData());
    }
  }

  private DiameterMessage getSessionMessage(int code) {
    for(int index = 0; index < stateMessages.size(); index++) {
      DiameterMessage msg = stateMessages.get(index);

      if(msg.getCommand().getCode() == code) {
        return msg;
      }
    }

    return null;
  }

  /**
   * Adds current session data to answer, if needed and present.
   */
  private void addSessionData(DiameterMessage slhMessage) {
    // Add any session-specific data if needed
  }

  //  Setters & Getters --------------------------------------------------

  public void setSession(ServerSLhSession session) {
    stateMessages = new ArrayList<DiameterMessage>();
    this.serverSession = session;
    this.serverSession.addStateChangeNotification(this);
  }

  @Override
  public SLhAVPFactory getSLhAVPFactory() {
    return this.slhAvpFactory;
  }

  @Override
  public SLhMessageFactory getSLhMessageFactory() {
    return this.messageFactory;
  }

  public void setSLhAVPFactory(SLhAVPFactory slhAvpFactory) {
    this.slhAvpFactory = slhAvpFactory;
  }

  public void setSLhMessageFactory(SLhMessageFactory messageFactory) {
    this.messageFactory = messageFactory;
  }

}
