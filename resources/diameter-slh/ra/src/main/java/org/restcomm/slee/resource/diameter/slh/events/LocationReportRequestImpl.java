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


import net.java.slee.resource.diameter.slh.events.LocationReportRequest;

import org.jdiameter.api.Message;
import org.mobicents.slee.resource.diameter.base.events.DiameterMessageImpl;

/**
 * Implementation of the LocationReportRequest interface.
 *
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class LocationReportRequestImpl extends DiameterMessageImpl implements LocationReportRequest {

  private static final long serialVersionUID = 1L;

  protected String longMessageName = "Location-Report-Request";
  protected String shortMessageName = "LRR";

  // AVP codes
  private static final int AUTH_SESSION_STATE_AVP = 277;
  private static final int USER_NAME_AVP = 1;
  private static final int MSISDN_AVP = 701;
  private static final int IMEI_AVP = 1402;
  private static final long SLH_VENDOR_ID = 10415L;

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

  public boolean hasAuthSessionState() {
    return hasAvp(AUTH_SESSION_STATE_AVP);
  }

  public boolean hasUserName() {
    return hasAvp(USER_NAME_AVP);
  }

  public String getUserName() {
    return getAvpAsUTF8String(USER_NAME_AVP);
  }

  public void setUserName(String userName) {
    addAvp(USER_NAME_AVP, userName);
  }

  public boolean hasMSISDN() {
    return hasAvp(MSISDN_AVP, SLH_VENDOR_ID);
  }

  public byte[] getMSISDN() {
    return getAvpAsOctetString(MSISDN_AVP, SLH_VENDOR_ID);
  }

  public void setMSISDN(byte[] msisdn) {
    addAvp(MSISDN_AVP, SLH_VENDOR_ID, msisdn);
  }

  public boolean hasIMEI() {
    return hasAvp(IMEI_AVP, SLH_VENDOR_ID);
  }

  public String getIMEI() {
    return getAvpAsUTF8String(IMEI_AVP, SLH_VENDOR_ID);
  }

  public void setIMEI(String imei) {
    addAvp(IMEI_AVP, SLH_VENDOR_ID, imei);
  }

}
