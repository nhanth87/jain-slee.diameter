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
import net.java.slee.resource.diameter.slg.events.avp.SupportedFeaturesAvp;

import org.jdiameter.api.Avp;
import org.mobicents.slee.resource.diameter.base.events.avp.GroupedAvpImpl;

/**
 * Implementation of AVP: {@link SupportedFeaturesAvp} interface.
 */
public class SupportedFeaturesAvpImpl extends GroupedAvpImpl implements SupportedFeaturesAvp {

  public SupportedFeaturesAvpImpl() {
    super();
  }

  public SupportedFeaturesAvpImpl(int code, long vendorId, int mnd, int prt, byte[] value) {
    super(code, vendorId, mnd, prt, value);
  }

  @Override
  public long getFeatureList() {
    return getAvpAsUnsigned32(SLgAvpCodes.FEATURE_LIST, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public boolean hasFeatureList() {
    return hasAvp(SLgAvpCodes.FEATURE_LIST, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public void setFeatureList(long featureList) {
    addAvp(SLgAvpCodes.FEATURE_LIST, SLgAvpCodes.SLG_VENDOR_ID, featureList);
  }

  @Override
  public long getFeatureListId() {
    return getAvpAsUnsigned32(SLgAvpCodes.FEATURE_LIST_ID, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public boolean hasFeatureListId() {
    return hasAvp(SLgAvpCodes.FEATURE_LIST_ID, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public void setFeatureListId(long featureListId) {
    addAvp(SLgAvpCodes.FEATURE_LIST_ID, SLgAvpCodes.SLG_VENDOR_ID, featureListId);
  }

  @Override
  public boolean hasVendorId() {
    return hasAvp(Avp.VENDOR_ID);
  }

  @Override
  public long getVendorId() {
    return getAvpAsUnsigned32(Avp.VENDOR_ID);
  }

  @Override
  public void setVendorId(long vendorId) {
    addAvp(Avp.VENDOR_ID, vendorId);
  }

}
