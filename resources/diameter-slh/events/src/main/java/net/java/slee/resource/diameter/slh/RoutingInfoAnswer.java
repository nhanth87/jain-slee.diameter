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

package net.java.slee.resource.diameter.slh.events;

import net.java.slee.resource.diameter.base.events.DiameterMessage;
import net.java.slee.resource.diameter.base.events.avp.ExperimentalResultAvp;
import net.java.slee.resource.diameter.base.events.avp.FailedAvp;

/**
 * Defines an interface representing the Routing-Info-Answer command.
 * 
 * From the Diameter SLh Reference Point Protocol Details (3GPP TS 29.173)
 * specification:
 * 
 * <pre>
 * 3.2.2        Routing-Info-Answer (RIA) Command
 * 
 * The Routing-Info-Answer command, indicated by the Command-Code field set 
 * to 8388622 and the 'R' bit cleared in the Command Flags field, is sent 
 * by an HSS to a GMLC in response to the Routing-Info-Request command.
 * 
 * Message Format
 * &lt; Routing-Info-Answer &gt; ::=        &lt; Diameter Header: 8388622, PXY, 16777291 &gt;
 *                                     &lt; Session-Id &gt;
 *                                     [ Result-Code ]
 *                                     [ Experimental-Result ]
 *                                     { Auth-Session-State }
 *                                     { Origin-Host }
 *                                     { Origin-Realm }
 *                                     [ User-Name ]
 *                                     [ MSISDN ]
 *                                     [ IMSI ]
 *                                     [ MME-Name ]
 *                                     [ MME-Realm ]
 *                                     [ SGSN-Name ]
 *                                     [ SGSN-Realm ]
 *                                     [ MSC-Number ]
 *                                     [ TSGSN-Number ]
 *                                     [ LCS-Capabilities-Sets ]
 *                                     [ GMLC-Address ]
 *                                     *[ Supported-Features ]
 *                                     *[ AVP ]
 *                                     *[ Failed-AVP ]
 *                                     *[ Proxy-Info ]
 *                                     *[ Route-Record ]
 * </pre>
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public interface RoutingInfoAnswer extends DiameterMessage {

  static final int commandCode = 8388622;

  /**
   * Returns true if the Result-Code AVP is present in the message.
   */
  boolean hasResultCode();

  /**
   * Returns the value of the Result-Code AVP, of type Unsigned32.
   * 
   * @return the value of the Result-Code AVP
   * @throws IllegalStateException if the Result-Code AVP has not been set
   */
  long getResultCode();

  /**
   * Sets the value of the Result-Code AVP, of type Unsigned32.
   * 
   * @throws IllegalStateException if setResultCode has already been called
   */
  void setResultCode(long resultCode);

  /**
   * Returns true if the Experimental-Result AVP is present in the message.
   */
  boolean hasExperimentalResult();

  /**
   * Returns the value of the Experimental-Result AVP, of type Grouped.
   * 
   * @return the value of the Experimental-Result AVP or null if it has not been set
   */
  ExperimentalResultAvp getExperimentalResult();

  /**
   * Sets the value of the Experimental-Result AVP, of type Grouped.
   * 
   * @throws IllegalStateException if setExperimentalResult has already been called
   */
  void setExperimentalResult(ExperimentalResultAvp experimentalResult);

  /**
   * Returns true if the Auth-Session-State AVP is present in the message.
   */
  boolean hasAuthSessionState();

  /**
   * Returns true if the User-Name AVP is present in the message.
   */
  boolean hasUserName();

  /**
   * Returns the value of the User-Name AVP, of type UTF8String.
   * 
   * @return the value of the User-Name AVP or null if it has not been set
   */
  String getUserName();

  /**
   * Sets the value of the User-Name AVP, of type UTF8String.
   * 
   * @throws IllegalStateException if setUserName has already been called
   */
  void setUserName(String userName);

  /**
   * Returns true if the MSISDN AVP is present in the message.
   */
  boolean hasMSISDN();

  /**
   * Returns the value of the MSISDN AVP, of type OctetString.
   * 
   * @return the value of the MSISDN AVP or null if it has not been set
   */
  byte[] getMSISDN();

  /**
   * Sets the value of the MSISDN AVP, of type OctetString.
   * 
   * @throws IllegalStateException if setMSISDN has already been called
   */
  void setMSISDN(byte[] msisdn);

  /**
   * Returns the set of Failed-AVP AVPs.
   */
  FailedAvp[] getFailedAvps();

  /**
   * Sets a single Failed-AVP AVP in the message.
   * 
   * @throws IllegalStateException if setFailedAvp or setFailedAvps has already been called
   */
  void setFailedAvp(FailedAvp failedAvp);

  /**
   * Sets the set of Failed-AVP AVPs.
   * 
   * @throws IllegalStateException if setFailedAvp or setFailedAvps has already been called
   */
  void setFailedAvps(FailedAvp[] failedAvps);

}
