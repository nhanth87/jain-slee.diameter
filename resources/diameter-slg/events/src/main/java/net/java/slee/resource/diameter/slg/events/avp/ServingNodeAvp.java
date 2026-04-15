package net.java.slee.resource.diameter.slg.events.avp;

import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.base.events.avp.GroupedAvp;

/**
 * Stub interface for ServingNodeAvp - GMLC Ethiopia
 */
public interface ServingNodeAvp extends GroupedAvp {
    boolean hasSGSNNumber();
    byte[] getSGSNNumber();
    void setSGSNNumber(byte[] sgsnNumber);
    
    boolean hasSGSNName();
    DiameterIdentity getSGSNName();
    void setSGSNName(DiameterIdentity sgsnName);
    
    boolean hasSGSNRealm();
    DiameterIdentity getSGSNRealm();
    void setSGSNRealm(DiameterIdentity sgsnRealm);
    
    boolean hasMMEName();
    DiameterIdentity getMMEName();
    void setMMEName(DiameterIdentity mmeName);
    
    boolean hasMMERealm();
    DiameterIdentity getMMERealm();
    void setMMERealm(DiameterIdentity mmeRealm);
    
    boolean hasMSCNumber();
    byte[] getMSCNumber();
    void setMSCNumber(byte[] mscNumber);
    
    boolean has3GPPAAAServerName();
    DiameterIdentity get3GPPAAAServerName();
    void set3GPPAAAServerName(DiameterIdentity aaaServerName);

    boolean hasLcsCapabilitiesSets();
    long getLcsCapabilitiesSets();
    void setLcsCapabilitiesSets(long lcsCapabilitiesSets);

    boolean hasGMLCAddress();
    DiameterIdentity getGMLCAddress();
    void setGMLCAddress(DiameterIdentity gmlcAddress);
}
