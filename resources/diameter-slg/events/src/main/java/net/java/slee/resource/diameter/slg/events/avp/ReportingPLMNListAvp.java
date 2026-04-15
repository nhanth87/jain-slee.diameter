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
 * Defines an interface representing the Reporting-PLMN-List grouped AVP type.
 *
 * From the Diameter SLg Reference Point Protocol Details (3GPP TS 29.172):
 * <pre>
 * 7.4.44        Reporting-PLMN-List
 * 
 * The Reporting-PLMN-List AVP is of type Grouped.
 * 
 * AVP format
 * Reporting-PLMN-List ::= <AVP header: 2549 10415>
 *                         [ Prioritized-List-Indicator ]
 *                         1*{ PLMN-ID-List }
 *                         *[AVP]
 * </pre>
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public interface ReportingPLMNListAvp extends GroupedAvp {

  /**
   * Returns true if the Prioritized-List-Indicator AVP is present in the message.
   */
  boolean hasPrioritizedListIndicator();

  /**
   * Returns the value of the Prioritized-List-Indicator AVP, of type Enumerated.
   * 
   * @return the value of the Prioritized-List-Indicator AVP or null if it has not been set
   */
  int getPrioritizedListIndicator();

  /**
   * Sets the value of the Prioritized-List-Indicator AVP, of type Enumerated.
   * 
   * @throws IllegalStateException if setPrioritizedListIndicator has already been called
   */
  void setPrioritizedListIndicator(int prioritizedListIndicator);

  /**
   * Returns true if the PLMN-ID-List AVP is present in the message.
   */
  boolean hasPLMNIDList();

  /**
   * Returns the set of PLMN-ID-List AVPs.
   * 
   * @return the set of PLMN-ID-List AVPs or null if it has not been set
   */
  PLMNIDListAvp[] getPLMNIDLists();

  /**
   * Sets a single PLMN-ID-List AVP in the message, of type Grouped.
   * 
   * @throws IllegalStateException if setPLMNIDList has already been called
   */
  void setPLMNIDList(PLMNIDListAvp plmnIdList);

  /**
   * Sets the set of PLMN-ID-List AVPs in the message, of type Grouped.
   * 
   * @throws IllegalStateException if setPLMNIDLists has already been called
   */
  void setPLMNIDLists(PLMNIDListAvp[] plmnIdLists);

}
