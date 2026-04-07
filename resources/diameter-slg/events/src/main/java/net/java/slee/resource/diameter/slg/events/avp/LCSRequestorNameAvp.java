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
 * Defines an interface representing the LCS-Requestor-Name grouped AVP type.
 *
 * From the Diameter SLg Reference Point Protocol Details (3GPP TS 29.172):
 * <pre>
 * 7.4.4        LCS-Requestor-Name
 * 
 * The LCS-Requestor-Name AVP is of type Grouped.
 * 
 * AVP format
 * LCS-Requestor-Name ::= <AVP header: 2503 10415>
 *                        [ LCS-Data-Coding-Scheme ]
 *                        [ LCS-Requestor-Id ]
 *                        [ LCS-Requestor-Id-Type ]
 *                        *[AVP]
 * </pre>
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public interface LCSRequestorNameAvp extends GroupedAvp {

  /**
   * Returns true if the LCS-Data-Coding-Scheme AVP is present in the message.
   */
  boolean hasLCSDataCodingScheme();

  /**
   * Returns the value of the LCS-Data-Coding-Scheme AVP, of type UTF8String.
   * 
   * @return the value of the LCS-Data-Coding-Scheme AVP or null if it has not been set
   */
  String getLCSDataCodingScheme();

  /**
   * Sets the value of the LCS-Data-Coding-Scheme AVP, of type UTF8String.
   * 
   * @throws IllegalStateException if setLCSDataCodingScheme has already been called
   */
  void setLCSDataCodingScheme(String lcsDataCodingScheme);

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

  /**
   * Returns true if the LCS-Requestor-Id-Type AVP is present in the message.
   */
  boolean hasLCSRequestorIdType();

  /**
   * Returns the value of the LCS-Requestor-Id-Type AVP, of type Enumerated.
   * 
   * @return the value of the LCS-Requestor-Id-Type AVP or null if it has not been set
   */
  int getLCSRequestorIdType();

  /**
   * Sets the value of the LCS-Requestor-Id-Type AVP, of type Enumerated.
   * 
   * @throws IllegalStateException if setLCSRequestorIdType has already been called
   */
  void setLCSRequestorIdType(int lcsRequestorIdType);

}
