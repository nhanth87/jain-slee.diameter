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
 * Defines an interface representing the LCS-EPS-Client-Name grouped AVP type.
 *
 * From the Diameter SLg Reference Point Protocol Details (3GPP TS 29.172):
 * <pre>
 * 7.4.2        LCS-EPS-Client-Name
 * 
 * The LCS-EPS-Client-Name AVP is of type Grouped.
 * 
 * AVP format
 * LCS-EPS-Client-Name ::= <AVP header: 2501 10415>
 *                         { LCS-Client-Type }
 *                         [ LCS-Requestor-Id ]
 *                         *[AVP]
 * </pre>
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public interface LCSEPSClientNameAvp extends GroupedAvp {

  /**
   * Returns true if the LCS-Client-Type AVP is present in the message.
   */
  boolean hasLCSClientType();

  /**
   * Returns the value of the LCS-Client-Type AVP, of type Enumerated.
   * 
   * @return the value of the LCS-Client-Type AVP or null if it has not been set
   */
  int getLCSClientType();

  /**
   * Sets the value of the LCS-Client-Type AVP, of type Enumerated.
   * 
   * @throws IllegalStateException if setLCSClientType has already been called
   */
  void setLCSClientType(int lcsClientType);

  /**
   * Returns true if the LCS-Requestor-Id AVP is present in the message.
   */
  boolean hasLCSRequestorId();

  /**
   * Returns the value of the LCS-Requestor-Id AVP, of type UTF8String.
   * 
   * @return the value of the LCS-Requestor-Id AVP or null if it has not been set
   */
  String getLCSRequestorId();

  /**
   * Sets the value of the LCS-Requestor-Id AVP, of type UTF8String.
   * 
   * @throws IllegalStateException if setLCSRequestorId has already been called
   */
  void setLCSRequestorId(String lcsRequestorId);

}
