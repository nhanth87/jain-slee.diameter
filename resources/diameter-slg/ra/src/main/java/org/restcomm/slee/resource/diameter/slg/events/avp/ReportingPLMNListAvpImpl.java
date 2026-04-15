package org.restcomm.slee.resource.diameter.slg.events.avp;

import net.java.slee.resource.diameter.slg.events.avp.PLMNIDListAvp;
import net.java.slee.resource.diameter.slg.events.avp.ReportingPLMNListAvp;
import net.java.slee.resource.diameter.slg.events.avp.SLgAvpCodes;
import org.mobicents.slee.resource.diameter.base.events.avp.GroupedAvpImpl;

public class ReportingPLMNListAvpImpl extends GroupedAvpImpl implements ReportingPLMNListAvp {

  public ReportingPLMNListAvpImpl() {
    super();
  }

  public ReportingPLMNListAvpImpl(int code, long vendorId, int mnd, int prt, byte[] value) {
    super(code, vendorId, mnd, prt, value);
  }

  @Override
  public boolean hasPrioritizedListIndicator() {
    return hasAvp(SLgAvpCodes.PRIORITIZED_LIST_INDICATOR, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public int getPrioritizedListIndicator() {
    return getAvpAsInteger32(SLgAvpCodes.PRIORITIZED_LIST_INDICATOR, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public void setPrioritizedListIndicator(int prioritizedListIndicator) {
    addAvp(SLgAvpCodes.PRIORITIZED_LIST_INDICATOR, SLgAvpCodes.SLG_VENDOR_ID, prioritizedListIndicator);
  }

  @Override
  public boolean hasPLMNIDList() {
    return hasAvp(SLgAvpCodes.PLMN_ID_LIST, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public PLMNIDListAvp[] getPLMNIDLists() {
    return (PLMNIDListAvp[]) getAvpsAsCustom(SLgAvpCodes.PLMN_ID_LIST, SLgAvpCodes.SLG_VENDOR_ID, PLMNIDListAvpImpl.class);
  }

  @Override
  public void setPLMNIDList(PLMNIDListAvp plmnIdList) {
    addAvp(SLgAvpCodes.PLMN_ID_LIST, SLgAvpCodes.SLG_VENDOR_ID, plmnIdList);
  }

  @Override
  public void setPLMNIDLists(PLMNIDListAvp[] plmnIdLists) {
    for (PLMNIDListAvp plmnIdList : plmnIdLists) {
      addAvp(SLgAvpCodes.PLMN_ID_LIST, SLgAvpCodes.SLG_VENDOR_ID, plmnIdList);
    }
  }
}
