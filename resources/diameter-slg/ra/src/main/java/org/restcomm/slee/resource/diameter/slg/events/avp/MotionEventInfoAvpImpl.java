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

import net.java.slee.resource.diameter.slg.events.avp.MotionEventInfoAvp;
import net.java.slee.resource.diameter.slg.events.avp.SLgAvpCodes;

import org.mobicents.slee.resource.diameter.base.events.avp.GroupedAvpImpl;

/**
 * Implementation of AVP: {@link MotionEventInfoAvp} interface.
 */
public class MotionEventInfoAvpImpl extends GroupedAvpImpl implements MotionEventInfoAvp {

  public MotionEventInfoAvpImpl() {
    super();
  }

  public MotionEventInfoAvpImpl(int code, long vendorId, int mnd, int prt, byte[] value) {
    super(code, vendorId, mnd, prt, value);
  }

  @Override
  public boolean hasLinearDistance() {
    return hasAvp(SLgAvpCodes.LINEAR_DISTANCE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public long getLinearDistance() {
    return getAvpAsUnsigned32(SLgAvpCodes.LINEAR_DISTANCE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public void setLinearDistance(long linearDistance) {
    addAvp(SLgAvpCodes.LINEAR_DISTANCE, SLgAvpCodes.SLG_VENDOR_ID, linearDistance);
  }

  @Override
  public boolean hasOccurrenceInfo() {
    return hasAvp(SLgAvpCodes.OCCURRENCE_INFO, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public int getOccurrenceInfo() {
    return getAvpAsInteger32(SLgAvpCodes.OCCURRENCE_INFO, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public void setOccurrenceInfo(int occurrenceInfo) {
    addAvp(SLgAvpCodes.OCCURRENCE_INFO, SLgAvpCodes.SLG_VENDOR_ID, occurrenceInfo);
  }

  @Override
  public boolean hasIntervalTime() {
    return hasAvp(SLgAvpCodes.INTERVAL_TIME, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public long getIntervalTime() {
    return getAvpAsUnsigned32(SLgAvpCodes.INTERVAL_TIME, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public void setIntervalTime(long intervalTime) {
    addAvp(SLgAvpCodes.INTERVAL_TIME, SLgAvpCodes.SLG_VENDOR_ID, intervalTime);
  }

}
