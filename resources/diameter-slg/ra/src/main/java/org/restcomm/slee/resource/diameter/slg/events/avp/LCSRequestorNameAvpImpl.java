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

import net.java.slee.resource.diameter.slg.events.avp.LCSRequestorNameAvp;
import net.java.slee.resource.diameter.slg.events.avp.SLgAvpCodes;

import org.mobicents.slee.resource.diameter.base.events.avp.GroupedAvpImpl;

/**
 * Implementation of AVP: {@link LCSRequestorNameAvp} interface.
 */
public class LCSRequestorNameAvpImpl extends GroupedAvpImpl implements LCSRequestorNameAvp {

  public LCSRequestorNameAvpImpl() {
    super();
  }

  public LCSRequestorNameAvpImpl(int code, long vendorId, int mnd, int prt, byte[] value) {
    super(code, vendorId, mnd, prt, value);
  }

  @Override
  public boolean hasLCSDataCodingScheme() {
    return hasAvp(SLgAvpCodes.LCS_DATA_CODING_SCHEME, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public String getLCSDataCodingScheme() {
    return getAvpAsUTF8String(SLgAvpCodes.LCS_DATA_CODING_SCHEME, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public void setLCSDataCodingScheme(String lcsDataCodingScheme) {
    addAvp(SLgAvpCodes.LCS_DATA_CODING_SCHEME, SLgAvpCodes.SLG_VENDOR_ID, lcsDataCodingScheme);
  }

  @Override
  public boolean hasLCSRequestorId() {
    return hasAvp(SLgAvpCodes.LCS_REQUESTOR_ID, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public String getLCSRequestorId() {
    return getAvpAsUTF8String(SLgAvpCodes.LCS_REQUESTOR_ID, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public void setLCSRequestorId(String lcsRequestorId) {
    addAvp(SLgAvpCodes.LCS_REQUESTOR_ID, SLgAvpCodes.SLG_VENDOR_ID, lcsRequestorId);
  }

  @Override
  public boolean hasLCSRequestorIdType() {
    return hasAvp(SLgAvpCodes.LCS_REQUESTOR_ID_TYPE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public int getLCSRequestorIdType() {
    return getAvpAsInteger32(SLgAvpCodes.LCS_REQUESTOR_ID_TYPE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public void setLCSRequestorIdType(int lcsRequestorIdType) {
    addAvp(SLgAvpCodes.LCS_REQUESTOR_ID_TYPE, SLgAvpCodes.SLG_VENDOR_ID, lcsRequestorIdType);
  }

}
