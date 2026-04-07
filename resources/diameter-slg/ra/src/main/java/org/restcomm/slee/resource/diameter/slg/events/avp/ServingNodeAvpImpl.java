package org.restcomm.slee.resource.diameter.slg.events.avp;

import net.java.slee.resource.diameter.slg.events.avp.ServingNodeAvp;
import net.java.slee.resource.diameter.slg.events.avp.SLgAvpCodes;
import org.mobicents.slee.resource.diameter.base.events.avp.GroupedAvpImpl;

/**
 * Stub implementation for ServingNodeAvp - GMLC Ethiopia
 */
public class ServingNodeAvpImpl extends GroupedAvpImpl implements ServingNodeAvp {

    public ServingNodeAvpImpl() {
        super();
    }

    public ServingNodeAvpImpl(int code, long vendorId, int mnd, int prt, byte[] value) {
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

    @Override
    public boolean hasMSCNumber() {
        return false;
    }

    @Override
    public byte[] getMSCNumber() {
        return new byte[0];
    }

    @Override
    public void setMSCNumber(byte[] mscNumber) {
    }

    @Override
    public boolean has3GPPAAAServerName() {
        return false;
    }

    @Override
    public String get3GPPAAAServerName() {
        return "";
    }

    @Override
    public void set3GPPAAAServerName(String aaaServerName) {
    }
}
