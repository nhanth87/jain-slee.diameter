package org.restcomm.slee.resource.diameter.slg.events.avp;

import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.slg.events.avp.ServingNodeAvp;
import net.java.slee.resource.diameter.slg.events.avp.SLgAvpCodes;
import org.mobicents.slee.resource.diameter.base.events.avp.GroupedAvpImpl;

import org.jdiameter.api.Avp;

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
        return hasAvp(Avp.SGSN_NAME);
    }

    @Override
    public DiameterIdentity getSGSNName() {
        String value = getAvpAsUTF8String(Avp.SGSN_NAME);
        return value != null ? new DiameterIdentity(value) : null;
    }

    @Override
    public void setSGSNName(DiameterIdentity sgsnName) {
        addAvp(Avp.SGSN_NAME, sgsnName.toString());
    }

    @Override
    public boolean hasSGSNRealm() {
        return hasAvp(Avp.SGSN_REALM);
    }

    @Override
    public DiameterIdentity getSGSNRealm() {
        String value = getAvpAsUTF8String(Avp.SGSN_REALM);
        return value != null ? new DiameterIdentity(value) : null;
    }

    @Override
    public void setSGSNRealm(DiameterIdentity sgsnRealm) {
        addAvp(Avp.SGSN_REALM, sgsnRealm.toString());
    }

    @Override
    public boolean hasMMEName() {
        return hasAvp(Avp.MME_NAME);
    }

    @Override
    public DiameterIdentity getMMEName() {
        String value = getAvpAsUTF8String(Avp.MME_NAME);
        return value != null ? new DiameterIdentity(value) : null;
    }

    @Override
    public void setMMEName(DiameterIdentity mmeName) {
        addAvp(Avp.MME_NAME, mmeName.toString());
    }

    @Override
    public boolean hasMMERealm() {
        return hasAvp(Avp.MME_REALM);
    }

    @Override
    public DiameterIdentity getMMERealm() {
        String value = getAvpAsUTF8String(Avp.MME_REALM);
        return value != null ? new DiameterIdentity(value) : null;
    }

    @Override
    public void setMMERealm(DiameterIdentity mmeRealm) {
        addAvp(Avp.MME_REALM, mmeRealm.toString());
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
        return hasAvp(Avp.TGPP_AAA_SERVER_NAME);
    }

    @Override
    public DiameterIdentity get3GPPAAAServerName() {
        String value = getAvpAsUTF8String(Avp.TGPP_AAA_SERVER_NAME);
        return value != null ? new DiameterIdentity(value) : null;
    }

    @Override
    public void set3GPPAAAServerName(DiameterIdentity aaaServerName) {
        addAvp(Avp.TGPP_AAA_SERVER_NAME, aaaServerName.toString());
    }

    @Override
    public boolean hasLcsCapabilitiesSets() {
        return hasAvp(SLgAvpCodes.LCS_CAPABILITIES_SETS, SLgAvpCodes.SLG_VENDOR_ID);
    }

    @Override
    public long getLcsCapabilitiesSets() {
        return getAvpAsUnsigned32(SLgAvpCodes.LCS_CAPABILITIES_SETS, SLgAvpCodes.SLG_VENDOR_ID);
    }

    @Override
    public void setLcsCapabilitiesSets(long lcsCapabilitiesSets) {
        addAvp(SLgAvpCodes.LCS_CAPABILITIES_SETS, SLgAvpCodes.SLG_VENDOR_ID, lcsCapabilitiesSets);
    }

    @Override
    public boolean hasGMLCAddress() {
        return hasAvp(SLgAvpCodes.GMLC_ADDRESS, SLgAvpCodes.SLG_VENDOR_ID);
    }

    @Override
    public DiameterIdentity getGMLCAddress() {
        String value = getAvpAsUTF8String(SLgAvpCodes.GMLC_ADDRESS, SLgAvpCodes.SLG_VENDOR_ID);
        return value != null ? new DiameterIdentity(value) : null;
    }

    @Override
    public void setGMLCAddress(DiameterIdentity gmlcAddress) {
        addAvp(SLgAvpCodes.GMLC_ADDRESS, SLgAvpCodes.SLG_VENDOR_ID, gmlcAddress.toString());
    }
}
