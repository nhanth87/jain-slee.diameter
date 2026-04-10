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

package net.java.slee.resource.diameter.slg;

import java.io.IOException;

import net.java.slee.resource.diameter.Validator;
import net.java.slee.resource.diameter.base.CreateActivityException;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.slg.events.LocationAnswer;
import net.java.slee.resource.diameter.slg.events.LocationRequest;
import net.java.slee.resource.diameter.slg.events.ProvideLocationAnswer;
import net.java.slee.resource.diameter.slg.events.ProvideLocationRequest;

/**
 * The SLgProvider is used by a Diameter SLg Client (e.g., a GMLC) in an EPS network to create and send
 * requests to a Diameter SLg Server (e.g., an MME).
 *
 * @author Open Cloud
 */
public interface SLgProvider {

  /**
   * Get access to the SLg Diameter message factory.
   *
   * @return message factory for SLg Diameter
   */
  SLgMessageFactory getSLgMessageFactory();

  /**
   * Get access to the SLg Diameter AVP factory.
   *
   * @return AVP factory for SLg Diameter
   */
  SLgAVPFactory getSLgAVPFactory();

  /**
   * Create a new SLg activity to send and receive Diameter SLg messages.
   * @throws CreateActivityException if the RA could not create the activity for any reason
   * @return an SLgActivity
   */
  SLgActivity createSLgActivity() throws CreateActivityException;

  /**
   * Create a new SLg client session activity to send and receive Diameter SLg messages.
   * @param destinationHost the destination host
   * @param destinationRealm the destination realm
   * @return an SLgClientSessionActivity
   * @throws CreateActivityException if the RA could not create the activity for any reason
   */
  SLgClientSessionActivity createSLgClientSessionActivity(DiameterIdentity destinationHost, DiameterIdentity destinationRealm) throws CreateActivityException;

  /**
   * Create a new SLg client session activity to send and receive Diameter SLg messages.
   * @param destinationHost the destination host
   * @param destinationRealm the destination realm
   * @param sessionId optional session ID
   * @return an SLgClientSessionActivity
   * @throws CreateActivityException if the RA could not create the activity for any reason
   */
  SLgClientSessionActivity createSLgClientSessionActivity(DiameterIdentity destinationHost, DiameterIdentity destinationRealm, String sessionId) throws CreateActivityException;

  /**
   * Sends a synchronous ProvideLocationRequest which will block until an answer is received from the peer.
   *
   * @param message created using the MessageFactory
   * @return answer received from MME
   * @throws IOException if there was a problem sending the request
   */
  ProvideLocationAnswer provideLocationRequest(ProvideLocationRequest message) throws IOException;

  /**
   * Sends a synchronous LocationRequest which will block until an answer is received from the peer.
   *
   * @param message created using the MessageFactory
   * @return answer received from MME
   * @throws IOException if there was a problem sending the request
   */
  LocationAnswer locationRequest(LocationRequest message) throws IOException;

  /**
   * Return the number of peers this Diameter resource adaptor is connected to.
   * 
   * @return connected peer count
   */
  int getPeerCount();

  /**
   * Returns array containing identities of connected peers
   * 
   * @return array of DiameterIdentity
   */
  DiameterIdentity[] getConnectedPeers();

  /**
   * Returns the validator used to validate AVPs
   * 
   * @return Validator instance
   */
  Validator getValidator();

}
