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

package net.java.slee.resource.diameter.slh;

import java.io.IOException;

import net.java.slee.resource.diameter.base.events.DiameterMessage;
import net.java.slee.resource.diameter.slh.events.LocationReportRequest;
import net.java.slee.resource.diameter.slh.events.RoutingInfoRequest;

/**
 * 
 * SLh Client activity created for request/response use cases
 * 
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a> 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @see SLhActivity
 */
public interface SLhClientSessionActivity extends SLhActivity {

  /**
   * Sends a Routing-Info-Request message to the HSS.
   * 
   * @param message the RoutingInfoRequest message to send
   * @throws IOException if an error occurs while sending the message
   */
  void sendRoutingInfoRequest(RoutingInfoRequest message) throws IOException;

  /**
   * Sends a Location-Report-Request message to the HSS.
   * 
   * @param message the LocationReportRequest message to send
   * @throws IOException if an error occurs while sending the message
   */
  void sendLocationReportRequest(LocationReportRequest message) throws IOException;

  /**
   * Returns the message factory for this activity.
   * 
   * @return the SLhMessageFactory
   */
  SLhMessageFactory getSLhMessageFactory();

  /**
   * Returns the AVP factory for this activity.
   * 
   * @return the SLhAVPFactory
   */
  SLhAVPFactory getSLhAVPFactory();

  /**
   * Sends a Diameter message.
   * 
   * @param message the message to send
   * @throws IOException if an error occurs while sending the message
   */
  void sendMessage(DiameterMessage message) throws IOException;

}
