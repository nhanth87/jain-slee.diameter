package org.restcomm.slee.resource.diameter.slh.events.avp;

import net.java.slee.resource.diameter.slh.events.avp.AdditionalServingNodeAvp;
import org.mobicents.slee.resource.diameter.base.events.avp.GroupedAvpImpl;

/**
 * Stub implementation for AdditionalServingNodeAvp - GMLC Ethiopia
 */
public class AdditionalServingNodeAvpImpl extends GroupedAvpImpl implements AdditionalServingNodeAvp {

    public AdditionalServingNodeAvpImpl() {
        super();
    }

    public AdditionalServingNodeAvpImpl(int code, long vendorId, int mnd, int prt, byte[] value) {
        super(code, vendorId, mnd, prt, value);
    }

    @Override
    public boolean hasSGSNNumber() {
        return false;
    }

    @Override
    public byte[] getSGSNNumber() {
        return new byte[0];
    }

    @Override
    public void setSGSNNumber(byte[] sgsnNumber) {
    }

    @Override
    public boolean hasSGSNName() {
        return false;
    }

    @Override
    public String getSGSNName() {
        return "";
    }

    @Override
    public void setSGSNName(String sgsnName) {
    }

    @Override
    public boolean hasSGSNRealm() {
        return false;
    }

    @Override
    public String getSGSNRealm() {
        return "";
    }

    @Override
    public void setSGSNRealm(String sgsnRealm) {
    }

    @Override
    public boolean hasMMEName() {
        return false;
    }

    @Override
    public String getMMEName() {
        return "";
    }

    @Override
    public void setMMEName(String mmeName) {
    }

    @Override
    public boolean hasMMERealm() {
        return false;
    }

    @Override
    public String getMMERealm() {
        return "";
    }

    @Override
    public void setMMERealm(String mmeRealm) {
    }
}
