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
 * Defines an interface representing the UTRAN-Positioning-Info grouped AVP type.
 *
 * From the Diameter SLg Reference Point Protocol Details (3GPP TS 29.172):
 * <pre>
 * 7.4.34        UTRAN-Positioning-Info
 * 
 * The UTRAN-Positioning-Info AVP is of type Grouped.
 * 
 * AVP format
 * UTRAN-Positioning-Info ::= <AVP header: 2524 10415>
 *                            [ UTRAN-Positioning-Data ]
 *                            [ UTRAN-GANSS-Positioning-Data ]
 *                            [ Service-Area-Identity ]
 *                            *[AVP]
 * </pre>
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public interface UTRANPositioningInfo extends GroupedAvp {

  /**
   * Returns true if the UTRAN-Positioning-Data AVP is present in the message.
   */
  boolean hasUTRANPositioningData();

  /**
   * Returns the value of the UTRAN-Positioning-Data AVP, of type OctetString.
   * 
   * @return the value of the UTRAN-Positioning-Data AVP or null if it has not been set
   */
  byte[] getUTRANPositioningData();

  /**
   * Sets the value of the UTRAN-Positioning-Data AVP, of type OctetString.
   * 
   * @throws IllegalStateException if setUTRANPositioningData has already been called
   */
  void setUTRANPositioningData(byte[] utranPositioningData);

  /**
   * Returns true if the UTRAN-GANSS-Positioning-Data AVP is present in the message.
   */
  boolean hasUTRANGANSSPositioningData();

  /**
   * Returns the value of the UTRAN-GANSS-Positioning-Data AVP, of type OctetString.
   * 
   * @return the value of the UTRAN-GANSS-Positioning-Data AVP or null if it has not been set
   */
  byte[] getUTRANGANSSPositioningData();

  /**
   * Sets the value of the UTRAN-GANSS-Positioning-Data AVP, of type OctetString.
   * 
   * @throws IllegalStateException if setUTRANGANSSPositioningData has already been called
   */
  void setUTRANGANSSPositioningData(byte[] utranGanssPositioningData);

  /**
   * Returns true if the Service-Area-Identity AVP is present in the message.
   */
  boolean hasServiceAreaIdentity();

  /**
   * Returns the value of the Service-Area-Identity AVP, of type OctetString.
   * 
   * @return the value of the Service-Area-Identity AVP or null if it has not been set
   */
  byte[] getServiceAreaIdentity();

  /**
   * Sets the value of the Service-Area-Identity AVP, of type OctetString.
   * 
   * @throws IllegalStateException if setServiceAreaIdentity has already been called
   */
  void setServiceAreaIdentity(byte[] serviceAreaIdentity);

}
