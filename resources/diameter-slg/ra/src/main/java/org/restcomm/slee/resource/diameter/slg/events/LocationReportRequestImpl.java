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

package org.restcomm.slee.resource.diameter.slg.events;

import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.slg.events.LocationReportRequest;
import net.java.slee.resource.diameter.slg.events.avp.*;

import org.jdiameter.api.Avp;
import org.jdiameter.api.Message;
import org.mobicents.slee.resource.diameter.base.events.DiameterMessageImpl;
import org.restcomm.slee.resource.diameter.slg.events.avp.*;

/**
 * Implementation of the LocationReportRequest interface.
 */
public class LocationReportRequestImpl extends DiameterMessageImpl implements LocationReportRequest {

  private static final long serialVersionUID = 1L;

  protected String longMessageName = "Location-Report-Request";
  protected String shortMessageName = "LRR";

  public LocationReportRequestImpl(Message message) {
    super(message);
    message.setRequest(true);
  }

  @Override
  public String getLongName() {
    return longMessageName;
  }

  @Override
  public String getShortName() {
    return shortMessageName;
  }

  @Override
  public String getSessionId() {
    return getAvpAsUTF8String(Avp.SESSION_ID);
  }

  @Override
  public DiameterIdentity getOriginHost() {
    byte[] value = getAvpAsOctetString(Avp.ORIGIN_HOST);
    return value != null ? new DiameterIdentity(new String(value)) : null;
  }

  @Override
  public DiameterIdentity getOriginRealm() {
    byte[] value = getAvpAsOctetString(Avp.ORIGIN_REALM);
    return value != null ? new DiameterIdentity(new String(value)) : null;
  }

  @Override
  public boolean hasLocationEvent() {
    return hasAvp(SLgAvpCodes.LOCATION_EVENT, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public int getLocationEvent() {
    return getAvpAsInteger32(SLgAvpCodes.LOCATION_EVENT, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public boolean hasLCSEPSClientName() {
    return hasAvp(SLgAvpCodes.LCS_EPS_CLIENT_NAME, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public LCSEPSClientNameAvp getLCSEPSClientName() {
    return (LCSEPSClientNameAvp) getAvpAsCustom(SLgAvpCodes.LCS_EPS_CLIENT_NAME, SLgAvpCodes.SLG_VENDOR_ID, LCSEPSClientNameAvpImpl.class);
  }

  @Override
  public boolean hasUserName() {
    return hasAvp(Avp.USER_NAME);
  }

  @Override
  public String getUserName() {
    return getAvpAsUTF8String(Avp.USER_NAME);
  }

  @Override
  public boolean hasMSISDN() {
    return hasAvp(Avp.MSISDN);
  }

  @Override
  public byte[] getMSISDN() {
    return getAvpAsOctetString(Avp.MSISDN);
  }

  @Override
  public boolean hasIMEI() {
    return hasAvp(Avp.TGPP_IMEI);
  }

  @Override
  public String getIMEI() {
    return getAvpAsUTF8String(Avp.TGPP_IMEI);
  }

  @Override
  public boolean hasLocationEstimate() {
    return hasAvp(SLgAvpCodes.LOCATION_ESTIMATE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public byte[] getLocationEstimate() {
    return getAvpAsOctetString(SLgAvpCodes.LOCATION_ESTIMATE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public boolean hasAccuracyFulfilmentIndicator() {
    return hasAvp(SLgAvpCodes.ACCURACY_FULFILMENT_INDICATOR, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public int getAccuracyFulfilmentIndicator() {
    return getAvpAsInteger32(SLgAvpCodes.ACCURACY_FULFILMENT_INDICATOR, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public boolean hasAgeOfLocationEstimate() {
    return hasAvp(SLgAvpCodes.AGE_OF_LOCATION_ESTIMATE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public long getAgeOfLocationEstimate() {
    return getAvpAsUnsigned32(SLgAvpCodes.AGE_OF_LOCATION_ESTIMATE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public boolean hasVelocityEstimate() {
    return hasAvp(SLgAvpCodes.VELOCITY_ESTIMATE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public byte[] getVelocityEstimate() {
    return getAvpAsOctetString(SLgAvpCodes.VELOCITY_ESTIMATE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public boolean hasEUTRANPositioningData() {
    return hasAvp(SLgAvpCodes.EUTRAN_POSITIONING_DATA, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public byte[] getEUTRANPositioningData() {
    return getAvpAsOctetString(SLgAvpCodes.EUTRAN_POSITIONING_DATA, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public boolean hasECGI() {
    return hasAvp(SLgAvpCodes.ECGI, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public byte[] getECGI() {
    return getAvpAsOctetString(SLgAvpCodes.ECGI, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public boolean hasGERANPositioningInfo() {
    return hasAvp(SLgAvpCodes.GERAN_POSITIONING_INFO, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public GERANPositioningInfoAvp getGERANPositioningInfo() {
    return (GERANPositioningInfoAvp) getAvpAsCustom(SLgAvpCodes.GERAN_POSITIONING_INFO, SLgAvpCodes.SLG_VENDOR_ID, GERANPositioningInfoAvpImpl.class);
  }

  @Override
  public boolean hasUTRANPositioningInfo() {
    return hasAvp(SLgAvpCodes.UTRAN_POSITIONING_INFO, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public UTRANPositioningInfoAvp getUTRANPositioningInfo() {
    return (UTRANPositioningInfoAvp) getAvpAsCustom(SLgAvpCodes.UTRAN_POSITIONING_INFO, SLgAvpCodes.SLG_VENDOR_ID, UTRANPositioningInfoAvpImpl.class);
  }

  @Override
  public boolean hasCivicAddress() {
    return hasAvp(SLgAvpCodes.CIVIC_ADDRESS, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public String getCivicAddress() {
    return getAvpAsUTF8String(SLgAvpCodes.CIVIC_ADDRESS, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public boolean hasBarometricPressure() {
    return hasAvp(SLgAvpCodes.BAROMETRIC_PRESSURE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public long getBarometricPressure() {
    return getAvpAsUnsigned32(SLgAvpCodes.BAROMETRIC_PRESSURE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public boolean hasCellGlobalIdentity() {
    return hasAvp(SLgAvpCodes.CELL_GLOBAL_IDENTITY, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public byte[] getCellGlobalIdentity() {
    return getAvpAsOctetString(SLgAvpCodes.CELL_GLOBAL_IDENTITY, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public boolean hasServiceAreaIdentity() {
    return hasAvp(SLgAvpCodes.SERVICE_AREA_IDENTITY, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public byte[] getServiceAreaIdentity() {
    return getAvpAsOctetString(SLgAvpCodes.SERVICE_AREA_IDENTITY, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public boolean hasLCSServiceTypeID() {
    return hasAvp(SLgAvpCodes.LCS_SERVICE_TYPE_ID, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public long getLCSServiceTypeID() {
    return getAvpAsUnsigned32(SLgAvpCodes.LCS_SERVICE_TYPE_ID, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public boolean hasPseudonymIndicator() {
    return hasAvp(SLgAvpCodes.PSEUDONYM_INDICATOR, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public int getPseudonymIndicator() {
    return getAvpAsInteger32(SLgAvpCodes.PSEUDONYM_INDICATOR, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public boolean hasLCSQoSClass() {
    return hasAvp(SLgAvpCodes.LCS_QOS_CLASS, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public int getLCSQoSClass() {
    return getAvpAsInteger32(SLgAvpCodes.LCS_QOS_CLASS, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public boolean hasServingNode() {
    return hasAvp(SLgAvpCodes.SERVING_NODE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public ServingNodeAvp getServingNode() {
    return (ServingNodeAvp) getAvpAsCustom(SLgAvpCodes.SERVING_NODE, SLgAvpCodes.SLG_VENDOR_ID, ServingNodeAvpImpl.class);
  }

  @Override
  public boolean hasLCSReferenceNumber() {
    return hasAvp(SLgAvpCodes.LCS_REFERENCE_NUMBER, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public byte[] getLCSReferenceNumber() {
    return getAvpAsOctetString(SLgAvpCodes.LCS_REFERENCE_NUMBER, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public boolean hasDeferredMTLRData() {
    return hasAvp(SLgAvpCodes.DEFERRED_MTLR_DATA, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public DeferredMTLRDataAvp getDeferredMTLRData() {
    return (DeferredMTLRDataAvp) getAvpAsCustom(SLgAvpCodes.DEFERRED_MTLR_DATA, SLgAvpCodes.SLG_VENDOR_ID, DeferredMTLRDataAvpImpl.class);
  }

  @Override
  public boolean hasGMLCAddress() {
    return hasAvp(SLgAvpCodes.GMLC_ADDRESS, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public byte[] getGMLCAddress() {
    return getAvpAsOctetString(SLgAvpCodes.GMLC_ADDRESS, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public boolean hasReportingAmount() {
    return hasAvp(SLgAvpCodes.REPORTING_AMOUNT, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public long getReportingAmount() {
    return getAvpAsUnsigned32(SLgAvpCodes.REPORTING_AMOUNT, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public boolean hasPeriodicLDRInformation() {
    return hasAvp(SLgAvpCodes.PERIODIC_LDR_INFO, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public PeriodicLDRInfoAvp getPeriodicLDRInformation() {
    return (PeriodicLDRInfoAvp) getAvpAsCustom(SLgAvpCodes.PERIODIC_LDR_INFO, SLgAvpCodes.SLG_VENDOR_ID, PeriodicLDRInfoAvpImpl.class);
  }

  @Override
  public boolean hasESMLCCellInfo() {
    return hasAvp(SLgAvpCodes.ESMLC_CELL_INFO, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public ESMLCCellInfoAvp getESMLCCellInfo() {
    return (ESMLCCellInfoAvp) getAvpAsCustom(SLgAvpCodes.ESMLC_CELL_INFO, SLgAvpCodes.SLG_VENDOR_ID, ESMLCCellInfoAvpImpl.class);
  }

  @Override
  public boolean has1xRTTRCID() {
    return hasAvp(SLgAvpCodes.RTT_RCID_1X, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public byte[] get1xRTTRCID() {
    return getAvpAsOctetString(SLgAvpCodes.RTT_RCID_1X, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public boolean hasDelayedLocationReportingData() {
    return hasAvp(SLgAvpCodes.DELAYED_LOCATION_REPORTING_DATA, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public DelayedLocationReportingDataAvp getDelayedLocationReportingData() {
    return (DelayedLocationReportingDataAvp) getAvpAsCustom(SLgAvpCodes.DELAYED_LOCATION_REPORTING_DATA, SLgAvpCodes.SLG_VENDOR_ID, DelayedLocationReportingDataAvpImpl.class);
  }

  @Override
  public boolean hasLRRFlags() {
    return hasAvp(SLgAvpCodes.LRR_FLAGS, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public long getLRRFlags() {
    return getAvpAsUnsigned32(SLgAvpCodes.LRR_FLAGS, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public boolean hasAmfInstanceId() {
    return hasAvp(SLgAvpCodes.AMF_INSTANCE_ID, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public byte[] getAmfInstanceId() {
    return getAvpAsOctetString(SLgAvpCodes.AMF_INSTANCE_ID, SLgAvpCodes.SLG_VENDOR_ID);
  }

}
