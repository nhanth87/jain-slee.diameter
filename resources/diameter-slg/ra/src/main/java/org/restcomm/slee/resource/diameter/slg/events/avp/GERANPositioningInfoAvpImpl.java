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

import net.java.slee.resource.diameter.slg.events.avp.GERANPositioningInfoAvp;
import net.java.slee.resource.diameter.slg.events.avp.SLgAvpCodes;

import org.mobicents.slee.resource.diameter.base.events.avp.GroupedAvpImpl;

/**
 * Implementation of AVP: {@link GERANPositioningInfoAvp} interface.
 */
public class GERANPositioningInfoAvpImpl extends GroupedAvpImpl implements GERANPositioningInfoAvp {

  public GERANPositioningInfoAvpImpl() {
    super();
  }

  public GERANPositioningInfoAvpImpl(int code, long vendorId, int mnd, int prt, byte[] value) {
    super(code, vendorId, mnd, prt, value);
  }

  @Override
  public boolean hasGERANPositioningData() {
    return hasAvp(SLgAvpCodes.GERAN_POSITIONING_DATA, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public byte[] getGERANPositioningData() {
    return getAvpAsOctetString(SLgAvpCodes.GERAN_POSITIONING_DATA, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public void setGERANPositioningData(byte[] data) {
    addAvp(SLgAvpCodes.GERAN_POSITIONING_DATA, SLgAvpCodes.SLG_VENDOR_ID, data);
  }

  @Override
  public boolean hasGERANGANSSPositioningData() {
    return hasAvp(SLgAvpCodes.GERAN_GANSS_POSITIONING_DATA, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public byte[] getGERANGANSSPositioningData() {
    return getAvpAsOctetString(SLgAvpCodes.GERAN_GANSS_POSITIONING_DATA, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public void setGERANGANSSPositioningData(byte[] data) {
    addAvp(SLgAvpCodes.GERAN_GANSS_POSITIONING_DATA, SLgAvpCodes.SLG_VENDOR_ID, data);
  }

}
