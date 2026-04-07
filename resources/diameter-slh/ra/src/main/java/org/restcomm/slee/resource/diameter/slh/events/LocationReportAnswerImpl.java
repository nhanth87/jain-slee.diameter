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

package org.restcomm.slee.resource.diameter.slh.events;


import net.java.slee.resource.diameter.base.events.avp.ExperimentalResultAvp;
import net.java.slee.resource.diameter.base.events.avp.FailedAvp;
import net.java.slee.resource.diameter.slh.events.LocationReportAnswer;
import net.java.slee.resource.diameter.slh.events.avp.SLhAvpCodes;

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

  // AVP codes
  private static final int RESULT_CODE_AVP = 268;
  private static final int EXPERIMENTAL_RESULT_AVP = 297;
  private static final int AUTH_SESSION_STATE_AVP = 277;
  private static final int GMLC_ADDRESS_AVP = 2402;
  private static final int FAILED_AVP = 279;
  private static final long SLH_VENDOR_ID = 10415L;

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
    return hasAvp(RESULT_CODE_AVP);
  }

  public long getResultCode() {
    return getAvpAsUnsigned32(RESULT_CODE_AVP);
  }

  public void setResultCode(long resultCode) {
    addAvp(RESULT_CODE_AVP, resultCode);
  }

  public boolean hasExperimentalResult() {
    return hasAvp(EXPERIMENTAL_RESULT_AVP);
  }

  public ExperimentalResultAvp getExperimentalResult() {
    return (ExperimentalResultAvp) getAvpAsCustom(EXPERIMENTAL_RESULT_AVP, ExperimentalResultAvpImpl.class);
  }

  public void setExperimentalResult(ExperimentalResultAvp experimentalResult) {
    addAvp(EXPERIMENTAL_RESULT_AVP, experimentalResult.byteArrayValue());
  }

  public boolean hasAuthSessionState() {
    return hasAvp(AUTH_SESSION_STATE_AVP);
  }

  public boolean hasGMLCAddress() {
    return hasAvp(GMLC_ADDRESS_AVP, SLH_VENDOR_ID);
  }

  public byte[] getGMLCAddress() {
    return getAvpAsOctetString(GMLC_ADDRESS_AVP, SLH_VENDOR_ID);
  }

  public void setGMLCAddress(byte[] gmlcAddress) {
    addAvp(GMLC_ADDRESS_AVP, SLH_VENDOR_ID, gmlcAddress);
  }

  public FailedAvp[] getFailedAvps() {
    return (FailedAvp[]) getAvpsAsCustom(FAILED_AVP, FailedAvpImpl.class);
  }

  public void setFailedAvp(FailedAvp failedAvp) {
    addAvp(FAILED_AVP, failedAvp.byteArrayValue());
  }

  public void setFailedAvps(FailedAvp[] failedAvps) {
    for (FailedAvp failedAvp : failedAvps) {
      setFailedAvp(failedAvp);
    }
  }

}
