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
 * Defines an interface representing the ESMLC-Cell-Info grouped AVP.
 * 
 * From the Diameter SLg Reference Point Protocol Details (3GPP TS 29.172):
 * 
 * <pre>
 * 7.4.53 ESMLC-Cell-Info
 * 
 * The ESMLC-Cell-Info AVP is of type Grouped and contains the E-CGI and Cell Portion ID.
 * 
 * AVP format:
 * ESMLC-Cell-Info ::= <AVP header: 2551 10415>
 *                     [ ECGI ]
 *                     [ Cell-Portion-ID ]
 * </pre>
 */
public interface ESMLCCellInfoAvp extends GroupedAvp {

  /**
   * Returns true if the ECGI AVP is present in the message.
   */
  boolean hasECGI();

  /**
   * Returns the value of the ECGI AVP, of type OctetString.
   * 
   * @return the value of the ECGI AVP or null if it has not been set
   */
  byte[] getECGI();

  /**
   * Sets the value of the ECGI AVP, of type OctetString.
   * 
   * @throws IllegalStateException if setECGI has already been called
   */
  void setECGI(byte[] ecgi);

  /**
   * Returns true if the Cell-Portion-ID AVP is present in the message.
   */
  boolean hasCellPortionID();

  /**
   * Returns the value of the Cell-Portion-ID AVP, of type Integer32.
   * 
   * @return the value of the Cell-Portion-ID AVP or null if it has not been set
   */
  Long getCellPortionID();

  /**
   * Sets the value of the Cell-Portion-ID AVP, of type Integer32.
   * 
   * @throws IllegalStateException if setCellPortionID has already been called
   */
  void setCellPortionID(Long cellPortionID);

}
