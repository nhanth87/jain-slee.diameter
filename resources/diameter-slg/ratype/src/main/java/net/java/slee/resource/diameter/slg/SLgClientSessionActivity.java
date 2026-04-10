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

import net.java.slee.resource.diameter.base.events.DiameterHeader;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.slg.events.LocationReportAnswer;

/**
 * The SLgClientSessionActivity is used by a Diameter SLg Client (e.g., a GMLC) to send
 * Provide-Location-Request messages and receive Provide-Location-Answer messages.
 */
public interface SLgClientSessionActivity extends SLgActivity {

  /**
   * Sends a ProvideLocationRequest to the MME.
   * 
   * @param message the ProvideLocationRequest to send
   */
  void sendProvideLocationRequest(net.java.slee.resource.diameter.slg.events.ProvideLocationRequest message);

  /**
   * Get the destination host for this session.
   * 
   * @return the destination host
   */
  DiameterIdentity getDestinationHost();

  /**
   * Get the destination realm for this session.
   * 
   * @return the destination realm
   */
  DiameterIdentity getDestinationRealm();

  /**
   * Creates a new Location-Report-Answer message.
   * 
   * @param header the Diameter header to use for creating the answer
   * @return a LocationReportAnswer object
   */
  LocationReportAnswer createLocationReportAnswer(DiameterHeader header);

  /**
   * Sends the Location-Report-Answer message.
   * 
   * @param message the LocationReportAnswer to send
   */
  void sendLocationReportAnswer(LocationReportAnswer message);

}
