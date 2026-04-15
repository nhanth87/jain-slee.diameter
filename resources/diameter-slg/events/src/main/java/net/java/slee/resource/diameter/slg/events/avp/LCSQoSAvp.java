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
 * Defines an interface representing the LCS-QoS grouped AVP type.
 *
 * From the Diameter SLg Reference Point Protocol Details (3GPP TS 29.172):
 * <pre>
 * 7.4.8        LCS-QoS
 * 
 * The LCS-QoS AVP is of type Grouped.
 * 
 * AVP format
 * LCS-QoS ::= <AVP header: 2506 10415>
 *             [ LCS-QoS-Class ]
 *             [ Horizontal-Accuracy ]
 *             [ Vertical-Accuracy ]
 *             [ Vertical-Requested ]
 *             [ Response-Time ]
 *             *[AVP]
 * </pre>
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public interface LCSQoSAvp extends GroupedAvp {

  /**
   * Returns true if the LCS-QoS-Class AVP is present in the message.
   */
  boolean hasLCSQoSClass();

  /**
   * Returns the value of the LCS-QoS-Class AVP, of type Enumerated.
   * 
   * @return the value of the LCS-QoS-Class AVP or null if it has not been set
   */
  int getLCSQoSClass();

  /**
   * Sets the value of the LCS-QoS-Class AVP, of type Enumerated.
   * 
   * @throws IllegalStateException if setLCSQoSClass has already been called
   */
  void setLCSQoSClass(int lcsQoSClass);

  /**
   * Returns true if the Horizontal-Accuracy AVP is present in the message.
   */
  boolean hasHorizontalAccuracy();

  /**
   * Returns the value of the Horizontal-Accuracy AVP, of type Unsigned32.
   * 
   * @return the value of the Horizontal-Accuracy AVP or null if it has not been set
   */
  long getHorizontalAccuracy();

  /**
   * Sets the value of the Horizontal-Accuracy AVP, of type Unsigned32.
   * 
   * @throws IllegalStateException if setHorizontalAccuracy has already been called
   */
  void setHorizontalAccuracy(long horizontalAccuracy);

  /**
   * Returns true if the Vertical-Accuracy AVP is present in the message.
   */
  boolean hasVerticalAccuracy();

  /**
   * Returns the value of the Vertical-Accuracy AVP, of type Unsigned32.
   * 
   * @return the value of the Vertical-Accuracy AVP or null if it has not been set
   */
  long getVerticalAccuracy();

  /**
   * Sets the value of the Vertical-Accuracy AVP, of type Unsigned32.
   * 
   * @throws IllegalStateException if setVerticalAccuracy has already been called
   */
  void setVerticalAccuracy(long verticalAccuracy);

  /**
   * Returns true if the Vertical-Requested AVP is present in the message.
   */
  boolean hasVerticalRequested();

  /**
   * Returns the value of the Vertical-Requested AVP, of type Enumerated.
   * 
   * @return the value of the Vertical-Requested AVP or null if it has not been set
   */
  int getVerticalRequested();

  /**
   * Sets the value of the Vertical-Requested AVP, of type Enumerated.
   * 
   * @throws IllegalStateException if setVerticalRequested has already been called
   */
  void setVerticalRequested(int verticalRequested);

  /**
   * Returns true if the Response-Time AVP is present in the message.
   */
  boolean hasResponseTime();

  /**
   * Returns the value of the Response-Time AVP, of type Enumerated.
   * 
   * @return the value of the Response-Time AVP or null if it has not been set
   */
  int getResponseTime();

  /**
   * Sets the value of the Response-Time AVP, of type Enumerated.
   * 
   * @throws IllegalStateException if setResponseTime has already been called
   */
  void setResponseTime(int responseTime);

}
