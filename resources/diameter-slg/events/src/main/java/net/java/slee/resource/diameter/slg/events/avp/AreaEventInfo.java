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
 * Defines an interface representing the Area-Event-Info grouped AVP type.
 *
 * From the Diameter SLg Reference Point Protocol Details (3GPP TS 29.172):
 * <pre>
 * 7.4.45        Area-Event-Info
 * 
 * The Area-Event-Info AVP is of type Grouped.
 * 
 * AVP format
 * Area-Event-Info ::= <AVP header: 2536 10415>
 *                     { Area-Definition }
 *                     [ Occurrence-Info ]
 *                     [ Interval-Time ]
 *                     *[AVP]
 * </pre>
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public interface AreaEventInfo extends GroupedAvp {

  /**
   * Returns true if the Area-Definition AVP is present in the message.
   */
  boolean hasAreaDefinition();

  /**
   * Returns the value of the Area-Definition AVP, of type Grouped.
   * 
   * @return the value of the Area-Definition AVP or null if it has not been set
   */
  GroupedAvp getAreaDefinition();

  /**
   * Sets the value of the Area-Definition AVP, of type Grouped.
   * 
   * @throws IllegalStateException if setAreaDefinition has already been called
   */
  void setAreaDefinition(GroupedAvp areaDefinition);

  /**
   * Returns true if the Occurrence-Info AVP is present in the message.
   */
  boolean hasOccurrenceInfo();

  /**
   * Returns the value of the Occurrence-Info AVP, of type Enumerated.
   * 
   * @return the value of the Occurrence-Info AVP or null if it has not been set
   */
  int getOccurrenceInfo();

  /**
   * Sets the value of the Occurrence-Info AVP, of type Enumerated.
   * 
   * @throws IllegalStateException if setOccurrenceInfo has already been called
   */
  void setOccurrenceInfo(int occurrenceInfo);

  /**
   * Returns true if the Interval-Time AVP is present in the message.
   */
  boolean hasIntervalTime();

  /**
   * Returns the value of the Interval-Time AVP, of type Unsigned32.
   * 
   * @return the value of the Interval-Time AVP or null if it has not been set
   */
  long getIntervalTime();

  /**
   * Sets the value of the Interval-Time AVP, of type Unsigned32.
   * 
   * @throws IllegalStateException if setIntervalTime has already been called
   */
  void setIntervalTime(long intervalTime);

}
