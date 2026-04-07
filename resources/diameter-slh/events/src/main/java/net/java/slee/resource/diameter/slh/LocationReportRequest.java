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

/**
 * Defines an interface representing the Location-Report-Request command.
 * 
 * From the Diameter SLh Reference Point Protocol Details (3GPP TS 29.173)
 * specification:
 * 
 * <pre>
 * 3.2.3        Location-Report-Request (LRR) Command
 * 
 * The Location-Report-Request command, indicated by the Command-Code field set 
 * to 8388621 and the 'R' bit set in the Command Flags field, is sent by an 
 * MME to an HSS to report the location of a target UE.
 * 
 * Message Format
 * &lt; Location-Report-Request &gt; ::=   &lt; Diameter Header: 8388621, REQ, PXY, 16777291 &gt;
 *                                    &lt; Session-Id &gt;
 *                                    { Auth-Session-State }
 *                                    { Origin-Host }
 *                                    { Origin-Realm }
 *                                    { Destination-Host }
 *                                    { Destination-Realm }
 *                                    { User-Name }
 *                                    [ MSISDN ]
 *                                    [ IMEI ]
 *                                    [ Location-Estimate ]
 *                                    [ Accuracy-Fulfilment-Indicator ]
 *                                    [ Age-Of-Location-Estimate ]
 *                                    [ T4-Location-Information ]
 *                                    [ Service-Area-Identity ]
 *                                    [ Serving-Node ]
 *                                    [ LRR-Flags ]
 *                                    *[ Supported-Features ]
 *                                    *[ AVP ]
 *                                    *[ Proxy-Info ]
 *                                    *[ Route-Record ]
 * </pre>
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public interface LocationReportRequest extends DiameterMessage {

  static final int commandCode = 8388621;

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
   * Returns true if the IMEI AVP is present in the message.
   */
  boolean hasIMEI();

  /**
   * Returns the value of the IMEI AVP, of type UTF8String.
   * 
   * @return the value of the IMEI AVP or null if it has not been set
   */
  String getIMEI();

  /**
   * Sets the value of the IMEI AVP, of type UTF8String.
   * 
   * @throws IllegalStateException if setIMEI has already been called
   */
  void setIMEI(String imei);

}
