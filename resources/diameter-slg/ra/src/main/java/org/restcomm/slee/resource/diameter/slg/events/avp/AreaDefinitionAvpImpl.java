package org.restcomm.slee.resource.diameter.slg.events.avp;

import net.java.slee.resource.diameter.slg.events.avp.AreaAvp;
import net.java.slee.resource.diameter.slg.events.avp.AreaDefinitionAvp;
import net.java.slee.resource.diameter.slg.events.avp.SLgAvpCodes;
import org.mobicents.slee.resource.diameter.base.events.avp.GroupedAvpImpl;

public class AreaDefinitionAvpImpl extends GroupedAvpImpl implements AreaDefinitionAvp {

  public AreaDefinitionAvpImpl() {
    super();
  }

  public AreaDefinitionAvpImpl(int code, long vendorId, int mnd, int prt, byte[] value) {
    super(code, vendorId, mnd, prt, value);
  }

  @Override
  public boolean hasArea() {
    return hasAvp(SLgAvpCodes.AREA, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public AreaAvp[] getAreas() {
    return (AreaAvp[]) getAvpsAsCustom(SLgAvpCodes.AREA, SLgAvpCodes.SLG_VENDOR_ID, AreaAvpImpl.class);
  }

  @Override
  public void setArea(AreaAvp area) {
    addAvp(SLgAvpCodes.AREA, SLgAvpCodes.SLG_VENDOR_ID, area);
  }

  @Override
  public void setAreas(AreaAvp[] areas) {
    for (AreaAvp area : areas) {
      addAvp(SLgAvpCodes.AREA, SLgAvpCodes.SLG_VENDOR_ID, area);
    }
  }
}
