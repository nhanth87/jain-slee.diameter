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

package net.java.slee.resource.diameter.slh.events.avp;

import net.java.slee.resource.diameter.base.events.avp.Address;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.base.events.avp.GroupedAvp;

/**
 * Defines an interface representing the LCS-Routing-Info grouped AVP type.
 *
 * From the Diameter SLh Reference Point Protocol Details (3GPP TS 29.173):
 * <pre>
 * 7.4.1        LCS-Routing-Info
 * 
 * The LCS-Routing-Info AVP is of type Grouped.
 * 
 * AVP format
 * LCS-Routing-Info ::= <AVP header: 2600 10415>
 *                      [ MME-Name ]
 *                      [ MME-Realm ]
 *                      [ SGSN-Name ]
 *                      [ SGSN-Realm ]
 *                      [ MSC-Number ]
 *                      [ TSGSN-Number ]
 *                      [ LCS-Capabilities-Sets ]
 *                      [ GMLC-Address ]
 *                      *[AVP]
 * </pre>
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public interface LCSRoutingInfo extends GroupedAvp {

  /**
   * Returns true if the MME-Name AVP is present in the message.
   */
  boolean hasMMEName();

  /**
   * Returns the value of the MME-Name AVP, of type UTF8String.
   * 
   * @return the value of the MME-Name AVP or null if it has not been set
   */
  String getMMEName();

  /**
   * Sets the value of the MME-Name AVP, of type UTF8String.
   * 
   * @throws IllegalStateException if setMMEName has already been called
   */
  void setMMEName(String mmeName);

  /**
   * Returns true if the MME-Realm AVP is present in the message.
   */
  boolean hasMMERealm();

  /**
   * Returns the value of the MME-Realm AVP, of type DiameterIdentity.
   * 
   * @return the value of the MME-Realm AVP or null if it has not been set
   */
  DiameterIdentity getMMERealm();

  /**
   * Sets the value of the MME-Realm AVP, of type DiameterIdentity.
   * 
   * @throws IllegalStateException if setMMERealm has already been called
   */
  void setMMERealm(DiameterIdentity mmeRealm);

  /**
   * Returns true if the SGSN-Name AVP is present in the message.
   */
  boolean hasSGSNName();

  /**
   * Returns the value of the SGSN-Name AVP, of type UTF8String.
   * 
   * @return the value of the SGSN-Name AVP or null if it has not been set
   */
  String getSGSNName();

  /**
   * Sets the value of the SGSN-Name AVP, of type UTF8String.
   * 
   * @throws IllegalStateException if setSGSNName has already been called
   */
  void setSGSNName(String sgsnName);

  /**
   * Returns true if the SGSN-Realm AVP is present in the message.
   */
  boolean hasSGSNRealm();

  /**
   * Returns the value of the SGSN-Realm AVP, of type DiameterIdentity.
   * 
   * @return the value of the SGSN-Realm AVP or null if it has not been set
   */
  DiameterIdentity getSGSNRealm();

  /**
   * Sets the value of the SGSN-Realm AVP, of type DiameterIdentity.
   * 
   * @throws IllegalStateException if setSGSNRealm has already been called
   */
  void setSGSNRealm(DiameterIdentity sgsnRealm);

  /**
   * Returns true if the MSC-Number AVP is present in the message.
   */
  boolean hasMSCNumber();

  /**
   * Returns the value of the MSC-Number AVP, of type OctetString.
   * 
   * @return the value of the MSC-Number AVP or null if it has not been set
   */
  byte[] getMSCNumber();

  /**
   * Sets the value of the MSC-Number AVP, of type OctetString.
   * 
   * @throws IllegalStateException if setMSCNumber has already been called
   */
  void setMSCNumber(byte[] mscNumber);

  /**
   * Returns true if the TSGSN-Number AVP is present in the message.
   */
  boolean hasTSGSNNumber();

  /**
   * Returns the value of the TSGSN-Number AVP, of type OctetString.
   * 
   * @return the value of the TSGSN-Number AVP or null if it has not been set
   */
  byte[] getTSGSNNumber();

  /**
   * Sets the value of the TSGSN-Number AVP, of type OctetString.
   * 
   * @throws IllegalStateException if setTSGSNNumber has already been called
   */
  void setTSGSNNumber(byte[] tsgsnNumber);

  /**
   * Returns true if the LCS-Capabilities-Sets AVP is present in the message.
   */
  boolean hasLCSCapabilitiesSets();

  /**
   * Returns the value of the LCS-Capabilities-Sets AVP, of type Unsigned32.
   * 
   * @return the value of the LCS-Capabilities-Sets AVP or null if it has not been set
   */
  long getLCSCapabilitiesSets();

  /**
   * Sets the value of the LCS-Capabilities-Sets AVP, of type Unsigned32.
   * 
   * @throws IllegalStateException if setLCSCapabilitiesSets has already been called
   */
  void setLCSCapabilitiesSets(long lcsCapabilitiesSets);

  /**
   * Returns true if the GMLC-Address AVP is present in the message.
   */
  boolean hasGMLCAddress();

  /**
   * Returns the value of the GMLC-Address AVP, of type Address.
   * 
   * @return the value of the GMLC-Address AVP or null if it has not been set
   */
  Address getGMLCAddress();

  /**
   * Sets the value of the GMLC-Address AVP, of type Address.
   * 
   * @throws IllegalStateException if setGMLCAddress has already been called
   */
  void setGMLCAddress(Address gmlcAddress);

}
