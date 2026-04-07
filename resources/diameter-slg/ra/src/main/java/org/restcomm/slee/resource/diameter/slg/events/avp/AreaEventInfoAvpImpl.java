package org.restcomm.slee.resource.diameter.slg.events.avp;

import net.java.slee.resource.diameter.slg.events.avp.AreaDefinitionAvp;
import net.java.slee.resource.diameter.slg.events.avp.AreaEventInfoAvp;
import net.java.slee.resource.diameter.slg.events.avp.SLgAvpCodes;
import org.mobicents.slee.resource.diameter.base.events.avp.GroupedAvpImpl;

public class AreaEventInfoAvpImpl extends GroupedAvpImpl implements AreaEventInfoAvp {

  public AreaEventInfoAvpImpl() {
    super();
  }

  public AreaEventInfoAvpImpl(int code, long vendorId, int mnd, int prt, byte[] value) {
    super(code, vendorId, mnd, prt, value);
  }

  @Override
  public boolean hasAreaDefinition() {
    return hasAvp(SLgAvpCodes.AREA_DEFINITION, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public AreaDefinitionAvp getAreaDefinition() {
    return (AreaDefinitionAvp) getAvpAsCustom(SLgAvpCodes.AREA_DEFINITION, SLgAvpCodes.SLG_VENDOR_ID, AreaDefinitionAvpImpl.class);
  }

  @Override
  public void setAreaDefinition(AreaDefinitionAvp areaDefinition) {
    addAvp(SLgAvpCodes.AREA_DEFINITION, SLgAvpCodes.SLG_VENDOR_ID, areaDefinition);
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
