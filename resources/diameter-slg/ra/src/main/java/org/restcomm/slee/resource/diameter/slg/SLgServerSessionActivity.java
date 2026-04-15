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
import java.util.ArrayList;

import net.java.slee.resource.diameter.base.events.DiameterMessage;
import net.java.slee.resource.diameter.base.events.avp.AuthSessionStateType;
import net.java.slee.resource.diameter.base.events.avp.AvpNotAllowedException;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.slg.SLgAVPFactory;
import net.java.slee.resource.diameter.slg.SLgActivity;
import net.java.slee.resource.diameter.slg.SLgMessageFactory;
import net.java.slee.resource.diameter.slg.events.LocationAnswer;
import net.java.slee.resource.diameter.slg.events.LocationRequest;
import net.java.slee.resource.diameter.slg.events.ProvideLocationAnswer;
import net.java.slee.resource.diameter.slg.events.ProvideLocationRequest;

import org.jdiameter.api.Answer;
import org.jdiameter.api.EventListener;
import org.jdiameter.api.Request;
import org.jdiameter.api.app.AppSession;
import org.jdiameter.api.app.StateChangeListener;
import org.jdiameter.api.slg.ServerSLgSession;
import org.restcomm.slee.resource.diameter.slg.events.LocationAnswerImpl;
import org.restcomm.slee.resource.diameter.slg.events.LocationRequestImpl;
import org.restcomm.slee.resource.diameter.slg.events.ProvideLocationAnswerImpl;
import org.restcomm.slee.resource.diameter.slg.events.ProvideLocationRequestImpl;
import org.mobicents.slee.resource.diameter.base.DiameterActivityImpl;
import org.mobicents.slee.resource.diameter.base.events.DiameterMessageImpl;

/**
 * Implementation of stateless SLg Server activity which receives requests. 
 * It ends after response is sent.
 * 
 * @author <a href = "mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 * @author <a href = "mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @see SLgActivity
 */
public class SLgServerSessionActivity extends DiameterActivityImpl implements SLgActivity, StateChangeListener<AppSession> {

  private static final long serialVersionUID = 1L;

  protected transient ServerSLgSession serverSession = null;

  // Factories
  protected transient SLgAVPFactory slgAvpFactory = null;
  protected transient SLgMessageFactory messageFactory = null;

  protected AuthSessionStateType authSessionState;
  protected DiameterIdentity remoteRealm;
  protected DiameterIdentity remoteHost;

  /**
   * Should contain requests, so we can create answer.
   */
  protected transient ArrayList<DiameterMessage> stateMessages = new ArrayList<DiameterMessage>();

  public SLgServerSessionActivity(SLgMessageFactory slgMessageFactory, SLgAVPFactory slgAvpFactory, ServerSLgSession session, DiameterIdentity destinationHost, DiameterIdentity destinationRealm) {
    super(null, null, null, (EventListener<Request, Answer>) session, destinationHost, destinationRealm);

    setSession(session);
    super.setCurrentWorkingSession(this.serverSession.getSessions().get(0));
    this.slgAvpFactory = slgAvpFactory;
    this.messageFactory = slgMessageFactory;
  }

  /**
   * Create a ProvideLocationAnswer containing a Result-Code or Experimental-Result
   * AVP populated with the given value.
   */
  public ProvideLocationAnswer createProvideLocationAnswer(long resultCode, boolean isExperimentalResult) {
    ProvideLocationRequest req = (ProvideLocationRequest) getSessionMessage(ProvideLocationRequest.commandCode);

    ProvideLocationAnswer answer = null;

    if(req != null) {
      answer = this.messageFactory.createProvideLocationAnswer(req, resultCode, isExperimentalResult);
      addSessionData(answer);
    }

    return answer;
  }

  /**
   * Create an empty ProvideLocationAnswer that will need to have AVPs set on it
   * before being sent.
   */
  public ProvideLocationAnswer createProvideLocationAnswer() {
    ProvideLocationRequest req = (ProvideLocationRequest) getSessionMessage(ProvideLocationRequest.commandCode);

    ProvideLocationAnswer answer = null;

    if(req != null) {
      answer = this.messageFactory.createProvideLocationAnswer(req);
      addSessionData(answer);
    }

    return answer;
  }

  /**
   * Create a LocationAnswer containing a Result-Code or Experimental-Result
   * AVP populated with the given value.
   */
  public LocationAnswer createLocationAnswer(long resultCode, boolean isExperimentalResult) {
    LocationRequest req = (LocationRequest) getSessionMessage(LocationRequest.commandCode);

    LocationAnswer answer = null;

    if(req != null) {
      answer = this.messageFactory.createLocationAnswer(req, resultCode, isExperimentalResult);
      addSessionData(answer);
    }

    return answer;
  }

  /**
   * Create an empty LocationAnswer that will need to have AVPs set on it
   * before being sent.
   */
  public LocationAnswer createLocationAnswer() {
    LocationRequest req = (LocationRequest) getSessionMessage(LocationRequest.commandCode);

    LocationAnswer answer = null;

    if(req != null) {
      answer = this.messageFactory.createLocationAnswer(req);
      addSessionData(answer);
    }

    return answer;
  }

  public void sendProvideLocationAnswer(ProvideLocationAnswer message) throws IOException {
    try {
      DiameterMessageImpl msg = (DiameterMessageImpl) message;
      fetchSessionData(msg, false);
      this.serverSession.sendProvideLocationAnswer(new org.jdiameter.common.impl.app.slg.ProvideLocationAnswerImpl((Answer) msg.getGenericData()));
      clean(msg);
    }
    catch (org.jdiameter.api.validation.AvpNotAllowedException e) {
      throw new AvpNotAllowedException("Message validation failed.", e, e.getAvpCode(), e.getVendorId());
    }
    catch (Exception e) {
      throw new IOException("Failed to send message, due to: " + e);
    }
  }

  public void sendLocationAnswer(LocationAnswer message) throws IOException {
    try {
      DiameterMessageImpl msg = (DiameterMessageImpl) message;
      fetchSessionData(msg, false);
      this.serverSession.sendLocationAnswer(new org.jdiameter.common.impl.app.slg.LocationAnswerImpl((Answer) msg.getGenericData()));
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
   * Server side should not send ProvideLocationRequest - this is a client side message.
   * This method throws UnsupportedOperationException.
   */
  public void sendProvideLocationRequest(ProvideLocationRequest message) throws IOException {
    throw new UnsupportedOperationException("Server side cannot send ProvideLocationRequest");
  }

  /**
   * Server side should not send LocationRequest - this is a client side message.
   * This method throws UnsupportedOperationException.
   */
  public void sendLocationRequest(LocationRequest message) throws IOException {
    throw new UnsupportedOperationException("Server side cannot send LocationRequest");
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
  private void addSessionData(DiameterMessage slgMessage) {
    // Add any session-specific data if needed
  }

  //  Setters & Getters --------------------------------------------------

  public void setSession(ServerSLgSession session) {
    stateMessages = new ArrayList<DiameterMessage>();
    this.serverSession = session;
    this.serverSession.addStateChangeNotification(this);
  }

  @Override
  public SLgAVPFactory getSLgAVPFactory() {
    return this.slgAvpFactory;
  }

  @Override
  public SLgMessageFactory getSLgMessageFactory() {
    return this.messageFactory;
  }

  public void setSLgAVPFactory(SLgAVPFactory slgAvpFactory) {
    this.slgAvpFactory = slgAvpFactory;
  }

  public void setSLgMessageFactory(SLgMessageFactory messageFactory) {
    this.messageFactory = messageFactory;
  }

}
