package org.restcomm.slee.resource.diameter.slg.events.avp;

import net.java.slee.resource.diameter.slg.events.avp.AdditionalAreaAvp;
import net.java.slee.resource.diameter.slg.events.avp.SLgAvpCodes;
import org.mobicents.slee.resource.diameter.base.events.avp.GroupedAvpImpl;

public class AdditionalAreaAvpImpl extends GroupedAvpImpl implements AdditionalAreaAvp {

  public AdditionalAreaAvpImpl() {
    super();
  }

  public AdditionalAreaAvpImpl(int code, long vendorId, int mnd, int prt, byte[] value) {
    super(code, vendorId, mnd, prt, value);
  }

  @Override
  public boolean hasAreaIdentification() {
    return hasAvp(SLgAvpCodes.AREA_IDENTIFICATION, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public byte[] getAreaIdentification() {
    return getAvpAsOctetString(SLgAvpCodes.AREA_IDENTIFICATION, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public void setAreaIdentification(byte[] areaIdentification) {
    addAvp(SLgAvpCodes.AREA_IDENTIFICATION, SLgAvpCodes.SLG_VENDOR_ID, areaIdentification);
  }

  @Override
  public boolean hasAreaType() {
    return hasAvp(SLgAvpCodes.AREA_TYPE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public int getAreaType() {
    return getAvpAsInteger32(SLgAvpCodes.AREA_TYPE, SLgAvpCodes.SLG_VENDOR_ID);
  }

  @Override
  public void setAreaType(int areaType) {
    addAvp(SLgAvpCodes.AREA_TYPE, SLgAvpCodes.SLG_VENDOR_ID, areaType);
  }
}
