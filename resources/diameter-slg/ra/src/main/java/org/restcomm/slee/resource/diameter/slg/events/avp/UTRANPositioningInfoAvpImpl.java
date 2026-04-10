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

package org.restcomm.slee.resource.diameter.slg.events.avp;

import net.java.slee.resource.diameter.slg.events.avp.SLgAvpCodes;
import net.java.slee.resource.diameter.slg.events.avp.UTRANPositioningInfoAvp;

import org.mobicents.slee.resource.diameter.base.events.avp.GroupedAvpImpl;

/**
 * Implementation of AVP: {@link UTRANPositioningInfoAvp} interface.
 */
public class UTRANPositioningInfoAvpImpl extends GroupedAvpImpl implements UTRANPositioningInfoAvp {

  public UTRANPositioningInfoAvpImpl() {
    super();
  }

  public UTRANPositioningInfoAvpImpl(int code, long vendorId, int mnd, int prt, byte[] value) {
    super(code, vendorId, mnd, prt, value);
  }

  @Override
  public boolean hasUTRANPositioningData() {
    return hasAvp(SLgAvpCodes.UTRAN_POSITIONING_DATA, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public byte[] getUTRANPositioningData() {
    return getAvpAsOctetString(SLgAvpCodes.UTRAN_POSITIONING_DATA, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public void setUTRANPositioningData(byte[] data) {
    addAvp(SLgAvpCodes.UTRAN_POSITIONING_DATA, SLgAvpCodes.SLG_VENDOR_ID, data);
  }

  @Override
  public boolean hasUTRANGANSSPositioningData() {
    return hasAvp(SLgAvpCodes.UTRAN_GANSS_POSITIONING_DATA, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public byte[] getUTRANGANSSPositioningData() {
    return getAvpAsOctetString(SLgAvpCodes.UTRAN_GANSS_POSITIONING_DATA, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public void setUTRANGANSSPositioningData(byte[] data) {
    addAvp(SLgAvpCodes.UTRAN_GANSS_POSITIONING_DATA, SLgAvpCodes.SLG_VENDOR_ID, data);
  }

  @Override
  public boolean hasUTRANAdditionalPositioningData() {
    return hasAvp(SLgAvpCodes.UTRAN_ADDITIONAL_POSITIONING_DATA, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public byte[] getUTRANAdditionalPositioningData() {
    return getAvpAsOctetString(SLgAvpCodes.UTRAN_ADDITIONAL_POSITIONING_DATA, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public void setUTRANAdditionalPositioningData(byte[] data) {
    addAvp(SLgAvpCodes.UTRAN_ADDITIONAL_POSITIONING_DATA, SLgAvpCodes.SLG_VENDOR_ID, data);
  }

}
