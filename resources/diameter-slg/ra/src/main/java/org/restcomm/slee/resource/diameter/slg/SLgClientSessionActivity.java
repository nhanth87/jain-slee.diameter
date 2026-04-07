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

import net.java.slee.resource.diameter.base.events.DiameterMessage;
import net.java.slee.resource.diameter.base.events.avp.AvpNotAllowedException;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.slg.SLgAVPFactory;
import net.java.slee.resource.diameter.slg.SLgActivity;
import net.java.slee.resource.diameter.slg.SLgMessageFactory;
import net.java.slee.resource.diameter.slg.events.LocationRequest;
import net.java.slee.resource.diameter.slg.events.ProvideLocationRequest;

import org.jdiameter.api.Answer;
import org.jdiameter.api.EventListener;
import org.jdiameter.api.Request;
import org.jdiameter.api.app.AppSession;
import org.jdiameter.api.app.StateChangeListener;
import org.jdiameter.api.slg.ClientSLgSession;

import org.mobicents.slee.resource.diameter.base.DiameterActivityImpl;
import org.mobicents.slee.resource.diameter.base.events.DiameterMessageImpl;

/**
 * 
 * SLg Client activity created for request/response use cases
 * 
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a> 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @see SLgActivity
 */
public class SLgClientSessionActivity extends DiameterActivityImpl implements SLgActivity, StateChangeListener<AppSession> {

  private static final long serialVersionUID = 1L;

  protected transient ClientSLgSession clientSession = null;
  protected transient SLgAVPFactory slgAvpFactory = null;
  protected transient SLgMessageFactory messageFactory = null;

  public SLgClientSessionActivity(SLgMessageFactory slgMessageFactory, SLgAVPFactory slgAvpFactory, ClientSLgSession session, DiameterIdentity destinationHost, DiameterIdentity destinationRealm) {
    super(slgMessageFactory.getBaseMessageFactory(), slgAvpFactory.getBaseFactory(), null, (EventListener<Request, Answer>) session, destinationHost, destinationRealm);

    setSession(session);
    super.setCurrentWorkingSession(this.clientSession.getSessions().get(0));
    this.slgAvpFactory = slgAvpFactory;
    this.messageFactory = slgMessageFactory;
  }

  public void setSession(ClientSLgSession session) {
    this.clientSession = session;
    this.clientSession.addStateChangeNotification(this);
  }

  public void sendProvideLocationRequest(ProvideLocationRequest message) throws IOException {
    try {
      DiameterMessageImpl msg = (DiameterMessageImpl) message;
      clientSession.sendProvideLocationRequest(new org.jdiameter.common.impl.app.slg.ProvideLocationRequestImpl((Request) msg.getGenericData()));
    }
    catch (org.jdiameter.api.validation.AvpNotAllowedException e) {
      throw new AvpNotAllowedException("Message validation failed.", e, e.getAvpCode(), e.getVendorId());
    }
    catch (Exception e) {
      throw new IOException("Failed to send message, due to: " + e.getLocalizedMessage());
    }
  }

  public void sendLocationRequest(LocationRequest message) throws IOException {
    try {
      DiameterMessageImpl msg = (DiameterMessageImpl) message;
      this.clientSession.sendLocationRequest(new org.jdiameter.common.impl.app.slg.LocationRequestImpl((Request) msg.getGenericData()));
    }
    catch (org.jdiameter.api.validation.AvpNotAllowedException e) {
      throw new AvpNotAllowedException("Message validation failed.", e, e.getAvpCode(), e.getVendorId());
    }
    catch (Exception e) {
      throw new IOException("Failed to send message, due to: " + e);
    }
  }

  public SLgMessageFactory getSLgMessageFactory() {
    return this.messageFactory;
  }

  public SLgAVPFactory getSLgAVPFactory() {
    return this.slgAvpFactory;
  }

  public void setSLgMessageFactory(SLgMessageFactory v) {
    this.messageFactory = v;
  }

  public void setSLgAVPFactory(SLgAVPFactory v) {
    this.slgAvpFactory = v;
  }

  public String getSessionId() {
    return super.getSessionId();
  }

  public void sendMessage(DiameterMessage message) throws IOException {
    super.sendMessage(message);
  }

  /*
   * (non-Javadoc)
   * @see org.jdiameter.api.app.StateChangeListener#stateChanged(java.lang.Object, java.lang.Enum, java.lang.Enum)
   */
  public void stateChanged(AppSession source, Enum oldState, Enum newState) {
    this.stateChanged(oldState, newState);
  }

  public void stateChanged(Enum oldState, Enum newState) {
    // no state changes, its stateless!
  }

  ClientSLgSession getClientSession() {
    return this.clientSession;
  }

  @Override
  public void endActivity() {
    this.clientSession.release();
    this.clientSession.removeStateChangeNotification(this);
    super.endActivity();
  }

}
