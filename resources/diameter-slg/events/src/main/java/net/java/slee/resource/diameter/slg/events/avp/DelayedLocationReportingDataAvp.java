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

package net.java.slee.resource.diameter.slg.events.avp;

import net.java.slee.resource.diameter.base.events.avp.GroupedAvp;

/**
 * Defines an interface representing the Delayed-Location-Reporting-Data grouped AVP.
 */
public interface DelayedLocationReportingDataAvp extends GroupedAvp {

  /**
   * Returns true if the Event-Reporting-Indicator AVP is present in the message.
   */
  boolean hasEventReportingIndicator();

  /**
   * Returns the value of the Event-Reporting-Indicator AVP, of type Enumerated.
   * 
   * @return the value of the Event-Reporting-Indicator AVP or null if it has not been set
   */
  int getEventReportingIndicator();

  /**
   * Sets the value of the Event-Reporting-Indicator AVP, of type Enumerated.
   * 
   * @throws IllegalStateException if setEventReportingIndicator has already been called
   */
  void setEventReportingIndicator(int eventReportingIndicator);

}
