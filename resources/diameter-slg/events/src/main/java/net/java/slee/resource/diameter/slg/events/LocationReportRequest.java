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
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.slg.events.avp.LCSEPSClientNameAvp;

/**
 * Defines an interface representing the Location-Report-Request command.
 * 
 * From the Diameter SLg Reference Point Protocol Details (3GPP TS 29.172)
 * specification:
 * 
 * <pre>
 * 3.2.5        Location-Report-Request (LRR) Command
 * 
 * The Location-Report-Request command, indicated by the Command-Code field set 
 * to 8388623 and the 'R' bit set in the Command Flags field, is sent by an 
 * MME to a GMLC to report location information.
 * </pre>
 */
public interface LocationReportRequest extends DiameterMessage {

  int commandCode = 8388623;

  // Base Diameter methods
  String getSessionId();
  DiameterIdentity getOriginHost();
  DiameterIdentity getOriginRealm();

  // Location-Event AVP
  boolean hasLocationEvent();
  int getLocationEvent();

  // LCS-EPS-Client-Name AVP
  boolean hasLCSEPSClientName();
  LCSEPSClientNameAvp getLCSEPSClientName();

  // User-Name AVP
  boolean hasUserName();
  String getUserName();

  // MSISDN AVP
  boolean hasMSISDN();
  byte[] getMSISDN();

  // IMEI AVP
  boolean hasIMEI();
  String getIMEI();

  // Location-Estimate AVP
  boolean hasLocationEstimate();
  byte[] getLocationEstimate();

  // Accuracy-Fulfilment-Indicator AVP
  boolean hasAccuracyFulfilmentIndicator();
  int getAccuracyFulfilmentIndicator();

  // Age-Of-Location-Estimate AVP
  boolean hasAgeOfLocationEstimate();
  long getAgeOfLocationEstimate();

  // Velocity-Estimate AVP
  boolean hasVelocityEstimate();
  byte[] getVelocityEstimate();

  // EUTRAN-Positioning-Data AVP
  boolean hasEUTRANPositioningData();
  byte[] getEUTRANPositioningData();

  // ECGI AVP
  boolean hasECGI();
  byte[] getECGI();

  // GERAN-Positioning-Info AVP
  boolean hasGERANPositioningInfo();
  net.java.slee.resource.diameter.slg.events.avp.GERANPositioningInfoAvp getGERANPositioningInfo();

  // UTRAN-Positioning-Info AVP
  boolean hasUTRANPositioningInfo();
  net.java.slee.resource.diameter.slg.events.avp.UTRANPositioningInfoAvp getUTRANPositioningInfo();

  // Civic-Address AVP
  boolean hasCivicAddress();
  String getCivicAddress();

  // Barometric-Pressure AVP
  boolean hasBarometricPressure();
  long getBarometricPressure();

  // Cell-Global-Identity AVP
  boolean hasCellGlobalIdentity();
  byte[] getCellGlobalIdentity();

  // Service-Area-Identity AVP
  boolean hasServiceAreaIdentity();
  byte[] getServiceAreaIdentity();

  // LCS-Service-Type-ID AVP
  boolean hasLCSServiceTypeID();
  long getLCSServiceTypeID();

  // Pseudonym-Indicator AVP
  boolean hasPseudonymIndicator();
  int getPseudonymIndicator();

  // LCS-QoS-Class AVP
  boolean hasLCSQoSClass();
  int getLCSQoSClass();

  // Serving-Node AVP
  boolean hasServingNode();
  net.java.slee.resource.diameter.slg.events.avp.ServingNodeAvp getServingNode();

  // LCS-Reference-Number AVP
  boolean hasLCSReferenceNumber();
  byte[] getLCSReferenceNumber();

  // Deferred-MTLR-Data AVP
  boolean hasDeferredMTLRData();
  net.java.slee.resource.diameter.slg.events.avp.DeferredMTLRDataAvp getDeferredMTLRData();

  // GMLC-Address AVP
  boolean hasGMLCAddress();
  byte[] getGMLCAddress();

  // Reporting-Amount AVP
  boolean hasReportingAmount();
  long getReportingAmount();

  // Periodic-LDR-Information AVP
  boolean hasPeriodicLDRInformation();
  net.java.slee.resource.diameter.slg.events.avp.PeriodicLDRInfoAvp getPeriodicLDRInformation();

  // ESMLC-Cell-Info AVP
  boolean hasESMLCCellInfo();
  net.java.slee.resource.diameter.slg.events.avp.ESMLCCellInfoAvp getESMLCCellInfo();

  // 1xRTT-RCID AVP
  boolean has1xRTTRCID();
  byte[] get1xRTTRCID();

  // Delayed-Location-Reporting-Data AVP
  boolean hasDelayedLocationReportingData();
  net.java.slee.resource.diameter.slg.events.avp.DelayedLocationReportingDataAvp getDelayedLocationReportingData();

  // LRR-Flags AVP
  boolean hasLRRFlags();
  long getLRRFlags();

  // AMF-Instance-ID AVP
  boolean hasAmfInstanceId();
  byte[] getAmfInstanceId();

}
