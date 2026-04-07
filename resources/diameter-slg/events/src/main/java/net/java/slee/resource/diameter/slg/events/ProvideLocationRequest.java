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

package net.java.slee.resource.diameter.slg.events;

import net.java.slee.resource.diameter.base.events.DiameterMessage;
import net.java.slee.resource.diameter.slg.events.avp.SupportedFeaturesAvp;
import net.java.slee.resource.diameter.slg.events.avp.AreaEventInfo;
import net.java.slee.resource.diameter.slg.events.avp.LocationType;
import net.java.slee.resource.diameter.slg.events.avp.PeriodicLDRInfo;
import net.java.slee.resource.diameter.slg.events.avp.VelocityRequested;

/**
 * Defines an interface representing the Provide-Location-Request command.
 * 
 * From the Diameter SLg Reference Point Protocol Details (3GPP TS 29.172)
 * specification:
 * 
 * <pre>
 * 3.2.3        Provide-Location-Request (PLR) Command
 * 
 * The Provide-Location-Request command, indicated by the Command-Code field set 
 * to 8388622 and the 'R' bit set in the Command Flags field, is sent by a GMLC 
 * to an MME to request location information for a target UE.
 * 
 * Message Format
 * &lt; Provide-Location-Request &gt; ::=   &lt; Diameter Header: 8388622, REQ, PXY, 16777255 &gt;
 *                                     &lt; Session-Id &gt;
 *                                     { Auth-Session-State }
 *                                     { Origin-Host }
 *                                     { Origin-Realm }
 *                                     { Destination-Host }
 *                                     { Destination-Realm }
 *                                     { SLg-Location-Type }
 *                                     [ User-Name ]
 *                                     [ MSISDN ]
 *                                     [ IMEI ]
 *                                     [ LCS-EPS-Client-Name ]
 *                                     [ LCS-Client-Type ]
 *                                     [ LCS-Requestor-Name ]
 *                                     [ LCS-Priority ]
 *                                     [ LCS-QoS ]
 *                                     [ Velocity-Requested ]
 *                                     [ LCS-Supported-GAD-Shapes ]
 *                                     [ LCS-Service-Type-ID ]
 *                                     [ LCS-Codeword ]
 *                                     [ LCS-Privacy-Invoke-Notify ]
 *                                     [ Service-Selection ]
 *                                     [ LCS-Client-Dialed-By-MS ]
 *                                     [ LCS-Client-External-ID ]
 *                                     [ LCS-Client-Name-String ]
 *                                     [ Periodic-Location-Support-Indicator ]
 *                                     [ Reporting-Interval ]
 *                                     [ Reporting-Amount ]
 *                                     [ Event-Reporting-Indicator ]
 *                                     [ Delayed-Location-Reporting-Support-Indicator ]
 *                                     [ Priority-Indicator ]
 *                                     [ MTC-Provider-Info ]
 *                                     *[ Supported-Features ]
 *                                     *[ AVP ]
 *                                     *[ Proxy-Info ]
 *                                     *[ Route-Record ]
 * </pre>
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public interface ProvideLocationRequest extends DiameterMessage {

  static final int commandCode = 8388622;

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

  /**
   * Returns true if the SLg-Location-Type AVP is present in the message.
   */
  boolean hasSLgLocationType();

  /**
   * Returns the value of the SLg-Location-Type AVP, of type Enumerated.
   * 
   * @return the value of the SLg-Location-Type AVP or null if it has not been set
   */
  LocationType getSLgLocationType();

  /**
   * Sets the value of the SLg-Location-Type AVP, of type Enumerated.
   * 
   * @throws IllegalStateException if setSLgLocationType has already been called
   */
  void setSLgLocationType(LocationType locationType);

  /**
   * Returns true if the LCS-Priority AVP is present in the message.
   */
  boolean hasLCSPriority();

  /**
   * Returns the value of the LCS-Priority AVP, of type Unsigned32.
   * 
   * @return the value of the LCS-Priority AVP or null if it has not been set
   */
  long getLCSPriority();

  /**
   * Sets the value of the LCS-Priority AVP, of type Unsigned32.
   * 
   * @throws IllegalStateException if setLCSPriority has already been called
   */
  void setLCSPriority(long lcsPriority);

  /**
   * Returns true if the Velocity-Requested AVP is present in the message.
   */
  boolean hasVelocityRequested();

  /**
   * Returns the value of the Velocity-Requested AVP, of type Enumerated.
   * 
   * @return the value of the Velocity-Requested AVP or null if it has not been set
   */
  VelocityRequested getVelocityRequested();

  /**
   * Sets the value of the Velocity-Requested AVP, of type Enumerated.
   * 
   * @throws IllegalStateException if setVelocityRequested has already been called
   */
  void setVelocityRequested(VelocityRequested velocityRequested);

  /**
   * Returns true if the LCS-Service-Type-ID AVP is present in the message.
   */
  boolean hasLCSServiceTypeID();

  /**
   * Returns the value of the LCS-Service-Type-ID AVP, of type Unsigned32.
   * 
   * @return the value of the LCS-Service-Type-ID AVP or null if it has not been set
   */
  long getLCSServiceTypeID();

  /**
   * Sets the value of the LCS-Service-Type-ID AVP, of type Unsigned32.
   * 
   * @throws IllegalStateException if setLCSServiceTypeID has already been called
   */
  void setLCSServiceTypeID(long lcsServiceTypeID);

  /**
   * Returns true if the LCS-Codeword AVP is present in the message.
   */
  boolean hasLCSCodeWord();

  /**
   * Returns the value of the LCS-Codeword AVP, of type UTF8String.
   * 
   * @return the value of the LCS-Codeword AVP or null if it has not been set
   */
  String getLCSCodeWord();

  /**
   * Sets the value of the LCS-Codeword AVP, of type UTF8String.
   * 
   * @throws IllegalStateException if setLCSCodeWord has already been called
   */
  void setLCSCodeWord(String lcsCodeWord);

  /**
   * Returns true if the Service-Selection AVP is present in the message.
   */
  boolean hasServiceSelection();

  /**
   * Returns the value of the Service-Selection AVP, of type UTF8String.
   * 
   * @return the value of the Service-Selection AVP or null if it has not been set
   */
  String getServiceSelection();

  /**
   * Sets the value of the Service-Selection AVP, of type UTF8String.
   * 
   * @throws IllegalStateException if setServiceSelection has already been called
   */
  void setServiceSelection(String serviceSelection);

  /**
   * Returns true if the LCS-Client-External-ID AVP is present in the message.
   */
  boolean hasLCSClientExternalID();

  /**
   * Returns the value of the LCS-Client-External-ID AVP, of type UTF8String.
   * 
   * @return the value of the LCS-Client-External-ID AVP or null if it has not been set
   */
  String getLCSClientExternalID();

  /**
   * Sets the value of the LCS-Client-External-ID AVP, of type UTF8String.
   * 
   * @throws IllegalStateException if setLCSClientExternalID has already been called
   */
  void setLCSClientExternalID(String lcsClientExternalID);

  /**
   * Returns true if the Area-Event-Info AVP is present in the message.
   */
  boolean hasAreaEventInfo();

  /**
   * Returns the value of the Area-Event-Info AVP, of type Grouped.
   * 
   * @return the value of the Area-Event-Info AVP or null if it has not been set
   */
  AreaEventInfo getAreaEventInfo();

  /**
   * Sets the value of the Area-Event-Info AVP, of type Grouped.
   * 
   * @throws IllegalStateException if setAreaEventInfo has already been called
   */
  void setAreaEventInfo(AreaEventInfo areaEventInfo);

  /**
   * Returns true if the Periodic-LDR-Info AVP is present in the message.
   */
  boolean hasPeriodicLDRInfo();

  /**
   * Returns the value of the Periodic-LDR-Info AVP, of type Grouped.
   * 
   * @return the value of the Periodic-LDR-Info AVP or null if it has not been set
   */
  PeriodicLDRInfo getPeriodicLDRInfo();

  /**
   * Sets the value of the Periodic-LDR-Info AVP, of type Grouped.
   * 
   * @throws IllegalStateException if setPeriodicLDRInfo has already been called
   */
  void setPeriodicLDRInfo(PeriodicLDRInfo periodicLDRInfo);

  /**
   * Returns the set of Supported-Features AVPs.
   * 
   * @return the set of Supported-Features AVPs
   */
  SupportedFeaturesAvp[] getSupportedFeatureses();

  /**
   * Sets a single Supported-Features AVP in the message.
   * 
   * @throws IllegalStateException if setSupportedFeatures or setSupportedFeatureses has already been called
   */
  void setSupportedFeatures(SupportedFeaturesAvp supportedFeatures);

  /**
   * Sets the set of Supported-Features AVPs.
   * 
   * @throws IllegalStateException if setSupportedFeatures or setSupportedFeatureses has already been called
   */
  void setSupportedFeatureses(SupportedFeaturesAvp[] supportedFeatureses);

}
