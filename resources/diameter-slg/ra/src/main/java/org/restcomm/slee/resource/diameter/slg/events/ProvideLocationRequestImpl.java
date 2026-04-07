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

import net.java.slee.resource.diameter.base.events.avp.DiameterAvpCodes;
import net.java.slee.resource.diameter.slg.events.avp.SupportedFeaturesAvp;
import net.java.slee.resource.diameter.slg.events.ProvideLocationRequest;
import net.java.slee.resource.diameter.slg.events.avp.AreaEventInfo;
import net.java.slee.resource.diameter.slg.events.avp.LocationType;
import net.java.slee.resource.diameter.slg.events.avp.PeriodicLDRInfo;
import net.java.slee.resource.diameter.slg.events.avp.SLgAvpCodes;
import net.java.slee.resource.diameter.slg.events.avp.VelocityRequested;

import org.jdiameter.api.Avp;
import org.jdiameter.api.Message;
import org.mobicents.slee.resource.diameter.base.events.DiameterMessageImpl;
import org.mobicents.slee.resource.diameter.base.events.avp.ExperimentalResultAvpImpl;
import org.mobicents.slee.resource.diameter.base.events.avp.FailedAvpImpl;

/**
 * Implementation of the ProvideLocationRequest interface.
 *
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class ProvideLocationRequestImpl extends DiameterMessageImpl implements ProvideLocationRequest {

  private static final long serialVersionUID = 1L;

  // MSISDN AVP code (3GPP TS 29.272)
  private static final int MSISDN_AVP = 701;
  // IMEI AVP code (3GPP TS 29.272)
  private static final int IMEI_AVP = 1402;
  // Service-Selection AVP code
  private static final int SERVICE_SELECTION_AVP = 493;

  protected String longMessageName = "Provide-Location-Request";
  protected String shortMessageName = "PLR";

  public ProvideLocationRequestImpl(Message message) {
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

  public boolean hasAuthSessionState() {
    return hasAvp(Avp.AUTH_SESSION_STATE);
  }

  public boolean hasUserName() {
    return hasAvp(Avp.USER_NAME);
  }

  public String getUserName() {
    return getAvpAsUTF8String(Avp.USER_NAME);
  }

  public void setUserName(String userName) {
    addAvp(Avp.USER_NAME, userName);
  }

  public boolean hasMSISDN() {
    return hasAvp(MSISDN_AVP, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public byte[] getMSISDN() {
    return getAvpAsOctetString(MSISDN_AVP, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public void setMSISDN(byte[] msisdn) {
    addAvp(MSISDN_AVP, SLgAvpCodes.SLG_VENDOR_ID, msisdn);
  }

  public boolean hasIMEI() {
    return hasAvp(IMEI_AVP, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public String getIMEI() {
    return getAvpAsUTF8String(IMEI_AVP, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public void setIMEI(String imei) {
    addAvp(IMEI_AVP, SLgAvpCodes.SLG_VENDOR_ID, imei);
  }

  public boolean hasSLgLocationType() {
    return hasAvp(SLgAvpCodes.SLG_LOCATION_TYPE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public LocationType getSLgLocationType() {
    return (LocationType) getAvpAsCustom(SLgAvpCodes.SLG_LOCATION_TYPE, SLgAvpCodes.SLG_VENDOR_ID, LocationType.class);
  }

  public void setSLgLocationType(LocationType locationType) {
    addAvp(SLgAvpCodes.SLG_LOCATION_TYPE, SLgAvpCodes.SLG_VENDOR_ID, locationType.byteArrayValue());
  }

  public boolean hasLCSPriority() {
    return hasAvp(SLgAvpCodes.LCS_PRIORITY, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public long getLCSPriority() {
    return getAvpAsUnsigned32(SLgAvpCodes.LCS_PRIORITY, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public void setLCSPriority(long lcsPriority) {
    addAvp(SLgAvpCodes.LCS_PRIORITY, SLgAvpCodes.SLG_VENDOR_ID, lcsPriority);
  }

  public boolean hasVelocityRequested() {
    return hasAvp(SLgAvpCodes.VELOCITY_REQUESTED, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public VelocityRequested getVelocityRequested() {
    return (VelocityRequested) getAvpAsCustom(SLgAvpCodes.VELOCITY_REQUESTED, SLgAvpCodes.SLG_VENDOR_ID, VelocityRequested.class);
  }

  public void setVelocityRequested(VelocityRequested velocityRequested) {
    addAvp(SLgAvpCodes.VELOCITY_REQUESTED, SLgAvpCodes.SLG_VENDOR_ID, velocityRequested.byteArrayValue());
  }

  public boolean hasLCSServiceTypeID() {
    return hasAvp(SLgAvpCodes.LCS_SERVICE_TYPE_ID, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public long getLCSServiceTypeID() {
    return getAvpAsUnsigned32(SLgAvpCodes.LCS_SERVICE_TYPE_ID, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public void setLCSServiceTypeID(long lcsServiceTypeID) {
    addAvp(SLgAvpCodes.LCS_SERVICE_TYPE_ID, SLgAvpCodes.SLG_VENDOR_ID, lcsServiceTypeID);
  }

  public boolean hasLCSCodeWord() {
    return hasAvp(SLgAvpCodes.LCS_CODEWORD, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public String getLCSCodeWord() {
    return getAvpAsUTF8String(SLgAvpCodes.LCS_CODEWORD, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public void setLCSCodeWord(String lcsCodeWord) {
    addAvp(SLgAvpCodes.LCS_CODEWORD, SLgAvpCodes.SLG_VENDOR_ID, lcsCodeWord);
  }

  public boolean hasServiceSelection() {
    return hasAvp(SERVICE_SELECTION_AVP);
  }

  public String getServiceSelection() {
    return getAvpAsUTF8String(SERVICE_SELECTION_AVP);
  }

  public void setServiceSelection(String serviceSelection) {
    addAvp(SERVICE_SELECTION_AVP, serviceSelection);
  }

  public boolean hasLCSClientExternalID() {
    return hasAvp(SLgAvpCodes.LCS_CLIENT_EXTERNAL_ID, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public String getLCSClientExternalID() {
    return getAvpAsUTF8String(SLgAvpCodes.LCS_CLIENT_EXTERNAL_ID, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public void setLCSClientExternalID(String lcsClientExternalID) {
    addAvp(SLgAvpCodes.LCS_CLIENT_EXTERNAL_ID, SLgAvpCodes.SLG_VENDOR_ID, lcsClientExternalID);
  }

  public boolean hasAreaEventInfo() {
    return hasAvp(SLgAvpCodes.AREA_EVENT_INFO, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public AreaEventInfo getAreaEventInfo() {
    return (AreaEventInfo) getAvpAsCustom(SLgAvpCodes.AREA_EVENT_INFO, SLgAvpCodes.SLG_VENDOR_ID, AreaEventInfo.class);
  }

  public void setAreaEventInfo(AreaEventInfo areaEventInfo) {
    addAvp(SLgAvpCodes.AREA_EVENT_INFO, SLgAvpCodes.SLG_VENDOR_ID, areaEventInfo.byteArrayValue());
  }

  public boolean hasPeriodicLDRInfo() {
    return hasAvp(SLgAvpCodes.PERIODIC_LDR_INFO, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public PeriodicLDRInfo getPeriodicLDRInfo() {
    return (PeriodicLDRInfo) getAvpAsCustom(SLgAvpCodes.PERIODIC_LDR_INFO, SLgAvpCodes.SLG_VENDOR_ID, PeriodicLDRInfo.class);
  }

  public void setPeriodicLDRInfo(PeriodicLDRInfo periodicLDRInfo) {
    addAvp(SLgAvpCodes.PERIODIC_LDR_INFO, SLgAvpCodes.SLG_VENDOR_ID, periodicLDRInfo.byteArrayValue());
  }

  public SupportedFeaturesAvp[] getSupportedFeatureses() {
    return (SupportedFeaturesAvp[]) getAvpsAsCustom(SLgAvpCodes.VENDOR_ID, SLgAvpCodes.SupportedFeaturesAvpImpl.class);
  }

  public void setSupportedFeatures(SupportedFeaturesAvp supportedFeatures) {
    addAvp(SLgAvpCodes.VENDOR_ID, SLgAvpCodes.SLG_VENDOR_ID, supportedFeatures.byteArrayValue());
  }

  public void setSupportedFeatureses(SupportedFeaturesAvp[] supportedFeatureses) {
    for (SupportedFeaturesAvp supportedFeatures : supportedFeatureses) {
      setSupportedFeatures(supportedFeatures);
    }
  }

}
