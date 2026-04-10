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

  @Override
  public boolean hasMaximumInterval() {
    return hasAvp(SLgAvpCodes.MAXIMUM_INTERVAL, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public long getMaximumInterval() {
    return getAvpAsUnsigned32(SLgAvpCodes.MAXIMUM_INTERVAL, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public void setMaximumInterval(long maximumInterval) {
    addAvp(SLgAvpCodes.MAXIMUM_INTERVAL, SLgAvpCodes.SLG_VENDOR_ID, maximumInterval);
  }

  @Override
  public boolean hasSamplingInterval() {
    return hasAvp(SLgAvpCodes.SAMPLING_INTERVAL, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public long getSamplingInterval() {
    return getAvpAsUnsigned32(SLgAvpCodes.SAMPLING_INTERVAL, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public void setSamplingInterval(long samplingInterval) {
    addAvp(SLgAvpCodes.SAMPLING_INTERVAL, SLgAvpCodes.SLG_VENDOR_ID, samplingInterval);
  }

  @Override
  public boolean hasReportDuration() {
    return hasAvp(SLgAvpCodes.REPORTING_DURATION, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public long getReportDuration() {
    return getAvpAsUnsigned32(SLgAvpCodes.REPORTING_DURATION, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public void setReportDuration(long reportDuration) {
    addAvp(SLgAvpCodes.REPORTING_DURATION, SLgAvpCodes.SLG_VENDOR_ID, reportDuration);
  }

  @Override
  public boolean hasReportingLocationRequirements() {
    return hasAvp(SLgAvpCodes.REPORTING_LOCATION_REQUIREMENTS, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public long getReportingLocationRequirements() {
    return getAvpAsUnsigned32(SLgAvpCodes.REPORTING_LOCATION_REQUIREMENTS, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public void setReportingLocationRequirements(long reportingLocationRequirements) {
    addAvp(SLgAvpCodes.REPORTING_LOCATION_REQUIREMENTS, SLgAvpCodes.SLG_VENDOR_ID, reportingLocationRequirements);
  }
}
