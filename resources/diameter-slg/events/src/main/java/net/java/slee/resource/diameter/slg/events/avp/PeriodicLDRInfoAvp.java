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
 * Defines an interface representing the Periodic-LDR-Information grouped AVP.
 */
public interface PeriodicLDRInfoAvp extends GroupedAvp {

  /**
   * Returns true if the Reporting-Interval AVP is present in the message.
   */
  boolean hasReportingInterval();

  /**
   * Returns the value of the Reporting-Interval AVP, of type Unsigned32.
   * 
   * @return the value of the Reporting-Interval AVP or null if it has not been set
   */
  long getReportingInterval();

  /**
   * Sets the value of the Reporting-Interval AVP, of type Unsigned32.
   * 
   * @throws IllegalStateException if setReportingInterval has already been called
   */
  void setReportingInterval(long reportingInterval);

  /**
   * Returns true if the Reporting-Amount AVP is present in the message.
   */
  boolean hasReportingAmount();

  /**
   * Returns the value of the Reporting-Amount AVP, of type Unsigned32.
   * 
   * @return the value of the Reporting-Amount AVP or null if it has not been set
   */
  long getReportingAmount();

  /**
   * Sets the value of the Reporting-Amount AVP, of type Unsigned32.
   * 
   * @throws IllegalStateException if setReportingAmount has already been called
   */
  void setReportingAmount(long reportingAmount);

}
