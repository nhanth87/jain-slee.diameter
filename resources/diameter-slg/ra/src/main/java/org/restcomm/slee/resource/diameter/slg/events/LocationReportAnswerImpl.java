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
import net.java.slee.resource.diameter.slg.events.LocationReportAnswer;
import net.java.slee.resource.diameter.slg.events.avp.SLgAvpCodes;

import org.jdiameter.api.Avp;
import org.jdiameter.api.Message;
import org.mobicents.slee.resource.diameter.base.events.DiameterMessageImpl;
import org.mobicents.slee.resource.diameter.base.events.avp.ExperimentalResultAvpImpl;
import org.mobicents.slee.resource.diameter.base.events.avp.FailedAvpImpl;

/**
 * Implementation of the LocationReportAnswer interface.
 *
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class LocationReportAnswerImpl extends DiameterMessageImpl implements LocationReportAnswer {

  private static final long serialVersionUID = 1L;

  protected String longMessageName = "Location-Report-Answer";
  protected String shortMessageName = "LRA";

  public LocationReportAnswerImpl(Message message) {
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

  public boolean hasGMLCAddress() {
    return hasAvp(SLgAvpCodes.GMLC_ADDRESS, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public byte[] getGMLCAddress() {
    return getAvpAsOctetString(SLgAvpCodes.GMLC_ADDRESS, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public void setGMLCAddress(byte[] gmlcAddress) {
    addAvp(SLgAvpCodes.GMLC_ADDRESS, SLgAvpCodes.SLG_VENDOR_ID, gmlcAddress);
  }

  public boolean hasLRAFlags() {
    return hasAvp(SLgAvpCodes.LRA_FLAGS, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public long getLRAFlags() {
    return getAvpAsUnsigned32(SLgAvpCodes.LRA_FLAGS, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public void setLRAFlags(long lraFlags) {
    addAvp(SLgAvpCodes.LRA_FLAGS, SLgAvpCodes.SLG_VENDOR_ID, lraFlags);
  }

  public boolean hasLCSReferenceNumber() {
    return hasAvp(SLgAvpCodes.LCS_REFERENCE_NUMBER, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public byte[] getLCSReferenceNumber() {
    return getAvpAsOctetString(SLgAvpCodes.LCS_REFERENCE_NUMBER, SLgAvpCodes.SLG_VENDOR_ID);
  }

  public void setLCSReferenceNumber(byte[] lcsReferenceNumber) {
    addAvp(SLgAvpCodes.LCS_REFERENCE_NUMBER, SLgAvpCodes.SLG_VENDOR_ID, lcsReferenceNumber);
  }

}
