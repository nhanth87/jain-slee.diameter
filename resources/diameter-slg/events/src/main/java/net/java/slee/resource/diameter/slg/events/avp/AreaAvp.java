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
 * Defines an interface representing the Area grouped AVP type.
 *
 * From the Diameter SLg Reference Point Protocol Details (3GPP TS 29.172):
 * <pre>
 * 7.4.47        Area
 * 
 * The Area AVP is of type Grouped.
 * 
 * AVP format
 * Area ::= <AVP header: 2538 10415>
 *          { Area-Type }
 *          { Area-Identification }
 *          *[AVP]
 * </pre>
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public interface AreaAvp extends GroupedAvp {

  /**
   * Returns true if the Area-Type AVP is present in the message.
   */
  boolean hasAreaType();

  /**
   * Returns the value of the Area-Type AVP, of type Enumerated.
   * 
   * @return the value of the Area-Type AVP or null if it has not been set
   */
  int getAreaType();

  /**
   * Sets the value of the Area-Type AVP, of type Enumerated.
   * 
   * @throws IllegalStateException if setAreaType has already been called
   */
  void setAreaType(int areaType);

  /**
   * Returns true if the Area-Identification AVP is present in the message.
   */
  boolean hasAreaIdentification();

  /**
   * Returns the value of the Area-Identification AVP, of type OctetString.
   * 
   * @return the value of the Area-Identification AVP or null if it has not been set
   */
  byte[] getAreaIdentification();

  /**
   * Sets the value of the Area-Identification AVP, of type OctetString.
   * 
   * @throws IllegalStateException if setAreaIdentification has already been called
   */
  void setAreaIdentification(byte[] areaIdentification);

}
