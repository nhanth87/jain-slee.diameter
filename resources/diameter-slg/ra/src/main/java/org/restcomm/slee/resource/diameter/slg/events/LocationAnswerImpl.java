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

import net.java.slee.resource.diameter.base.events.avp.ExperimentalResultAvp;
import net.java.slee.resource.diameter.base.events.avp.FailedAvp;
import net.java.slee.resource.diameter.slg.events.avp.SupportedFeaturesAvp;
import net.java.slee.resource.diameter.slg.events.LocationAnswer;
import net.java.slee.resource.diameter.slg.events.avp.SLgAvpCodes;

import org.jdiameter.api.Avp;
import org.jdiameter.api.Message;
import org.mobicents.slee.resource.diameter.base.events.DiameterMessageImpl;
import org.mobicents.slee.resource.diameter.base.events.avp.ExperimentalResultAvpImpl;
import org.mobicents.slee.resource.diameter.base.events.avp.FailedAvpImpl;
import org.restcomm.slee.resource.diameter.slg.events.avp.SupportedFeaturesAvpImpl;

/**
 * Implementation of the LocationAnswer interface.
 *
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class LocationAnswerImpl extends DiameterMessageImpl implements LocationAnswer {

  private static final long serialVersionUID = 1L;

  protected String longMessageName = "Location-Answer";
  protected String shortMessageName = "LgA";

  public LocationAnswerImpl(Message message) {
    super(message);
    message.setRequest(false);
  }

  @Override
  public String getLongName() {
    return longMessageName;
  }

  @Override
  public String getShortName() {
    return shortMessageName;
  }

  public boolean hasResultCode() {
    return hasAvp(Avp.RESULT_CODE);
  }

  public long getResultCode() {
    return getAvpAsUnsigned32(Avp.RESULT_CODE);
  }

  public void setResultCode(long resultCode) {
    addAvp(Avp.RESULT_CODE, resultCode);
  }

  public boolean hasExperimentalResult() {
    return hasAvp(Avp.EXPERIMENTAL_RESULT);
  }

  public ExperimentalResultAvp getExperimentalResult() {
    return (ExperimentalResultAvp) getAvpAsCustom(Avp.EXPERIMENTAL_RESULT, ExperimentalResultAvpImpl.class);
  }

  public void setExperimentalResult(ExperimentalResultAvp experimentalResult) {
    addAvp(Avp.EXPERIMENTAL_RESULT, experimentalResult.byteArrayValue());
  }

  public boolean hasAuthSessionState() {
    return hasAvp(Avp.AUTH_SESSION_STATE);
  }

  public FailedAvp[] getFailedAvps() {
    return (FailedAvp[]) getAvpsAsCustom(Avp.FAILED_AVP, FailedAvpImpl.class);
  }

  public void setFailedAvp(FailedAvp failedAvp) {
    addAvp(Avp.FAILED_AVP, failedAvp.byteArrayValue());
  }

  public void setFailedAvps(FailedAvp[] failedAvps) {
    for (FailedAvp failedAvp : failedAvps) {
      setFailedAvp(failedAvp);
    }
  }

  public boolean hasLocationEstimate() {
    return hasAvp(SLgAvpCodes.LOCATION_ESTIMATE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public byte[] getLocationEstimate() {
    return getAvpAsOctetString(SLgAvpCodes.LOCATION_ESTIMATE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public void setLocationEstimate(byte[] locationEstimate) {
    addAvp(SLgAvpCodes.LOCATION_ESTIMATE, SLgAvpCodes.SLG_VENDOR_ID, locationEstimate);
  }

  public boolean hasAccuracyFulfilmentIndicator() {
    return hasAvp(SLgAvpCodes.ACCURACY_FULFILMENT_INDICATOR, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public int getAccuracyFulfilmentIndicator() {
    return getAvpAsInteger32(SLgAvpCodes.ACCURACY_FULFILMENT_INDICATOR, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public void setAccuracyFulfilmentIndicator(int accuracyFulfilmentIndicator) {
    addAvp(SLgAvpCodes.ACCURACY_FULFILMENT_INDICATOR, SLgAvpCodes.SLG_VENDOR_ID, accuracyFulfilmentIndicator);
  }

  public boolean hasAgeOfLocationEstimate() {
    return hasAvp(SLgAvpCodes.AGE_OF_LOCATION_ESTIMATE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public long getAgeOfLocationEstimate() {
    return getAvpAsUnsigned32(SLgAvpCodes.AGE_OF_LOCATION_ESTIMATE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public void setAgeOfLocationEstimate(long ageOfLocationEstimate) {
    addAvp(SLgAvpCodes.AGE_OF_LOCATION_ESTIMATE, SLgAvpCodes.SLG_VENDOR_ID, ageOfLocationEstimate);
  }

  public boolean hasVelocityEstimate() {
    return hasAvp(SLgAvpCodes.VELOCITY_ESTIMATE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public byte[] getVelocityEstimate() {
    return getAvpAsOctetString(SLgAvpCodes.VELOCITY_ESTIMATE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public void setVelocityEstimate(byte[] velocityEstimate) {
    addAvp(SLgAvpCodes.VELOCITY_ESTIMATE, SLgAvpCodes.SLG_VENDOR_ID, velocityEstimate);
  }

  public boolean hasEUTRANPositioningData() {
    return hasAvp(SLgAvpCodes.EUTRAN_POSITIONING_DATA, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public byte[] getEUTRANPositioningData() {
    return getAvpAsOctetString(SLgAvpCodes.EUTRAN_POSITIONING_DATA, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public void setEUTRANPositioningData(byte[] eutranPositioningData) {
    addAvp(SLgAvpCodes.EUTRAN_POSITIONING_DATA, SLgAvpCodes.SLG_VENDOR_ID, eutranPositioningData);
  }

  public boolean hasECGI() {
    return hasAvp(SLgAvpCodes.ECGI, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public byte[] getECGI() {
    return getAvpAsOctetString(SLgAvpCodes.ECGI, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public void setECGI(byte[] ecgi) {
    addAvp(SLgAvpCodes.ECGI, SLgAvpCodes.SLG_VENDOR_ID, ecgi);
  }

  public boolean hasServiceAreaIdentity() {
    return hasAvp(SLgAvpCodes.SERVICE_AREA_IDENTITY, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public byte[] getServiceAreaIdentity() {
    return getAvpAsOctetString(SLgAvpCodes.SERVICE_AREA_IDENTITY, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public void setServiceAreaIdentity(byte[] serviceAreaIdentity) {
    addAvp(SLgAvpCodes.SERVICE_AREA_IDENTITY, SLgAvpCodes.SLG_VENDOR_ID, serviceAreaIdentity);
  }

  public boolean hasPLRFlags() {
    return hasAvp(SLgAvpCodes.PLR_FLAGS, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public long getPLRFlags() {
    return getAvpAsUnsigned32(SLgAvpCodes.PLR_FLAGS, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public void setPLRFlags(long plrFlags) {
    addAvp(SLgAvpCodes.PLR_FLAGS, SLgAvpCodes.SLG_VENDOR_ID, plrFlags);
  }

  public SupportedFeaturesAvp[] getSupportedFeatureses() {
    return (SupportedFeaturesAvp[]) getAvpsAsCustom(SLgAvpCodes.VENDOR_ID, SupportedFeaturesAvpImpl.class);
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
