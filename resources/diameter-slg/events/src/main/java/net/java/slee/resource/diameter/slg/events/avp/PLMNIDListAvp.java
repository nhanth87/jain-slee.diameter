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
 * Defines an interface representing the PLMN-ID-List grouped AVP type.
 *
 * From the Diameter SLg Reference Point Protocol Details (3GPP TS 29.172):
 * <pre>
 * 7.4.68        PLMN-ID-List
 * 
 * The PLMN-ID-List AVP is of type Grouped.
 * 
 * AVP format
 * PLMN-ID-List ::= <AVP header: 2551 10415>
 *                  { Visited-PLMN-Id }
 *                  [ Periodic-Location-Support-Indicator ]
 *                  *[AVP]
 * </pre>
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public interface PLMNIDListAvp extends GroupedAvp {

  /**
   * Returns true if the Visited-PLMN-Id AVP is present in the message.
   */
  boolean hasVisitedPLMNId();

  /**
   * Returns the value of the Visited-PLMN-Id AVP, of type OctetString.
   * 
   * @return the value of the Visited-PLMN-Id AVP or null if it has not been set
   */
  byte[] getVisitedPLMNId();

  /**
   * Sets the value of the Visited-PLMN-Id AVP, of type OctetString.
   * 
   * @throws IllegalStateException if setVisitedPLMNId has already been called
   */
  void setVisitedPLMNId(byte[] visitedPLMNId);

  /**
   * Returns true if the Periodic-Location-Support-Indicator AVP is present in the message.
   */
  boolean hasPeriodicLocationSupportIndicator();

  /**
   * Returns the value of the Periodic-Location-Support-Indicator AVP, of type Enumerated.
   * 
   * @return the value of the Periodic-Location-Support-Indicator AVP or null if it has not been set
   */
  int getPeriodicLocationSupportIndicator();

  /**
   * Sets the value of the Periodic-Location-Support-Indicator AVP, of type Enumerated.
   * 
   * @throws IllegalStateException if setPeriodicLocationSupportIndicator has already been called
   */
  void setPeriodicLocationSupportIndicator(int periodicLocationSupportIndicator);

}
