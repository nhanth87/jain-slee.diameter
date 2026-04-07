package net.java.slee.resource.diameter.slg.events.avp;

import net.java.slee.resource.diameter.base.events.avp.GroupedAvp;

/**
 * Stub interface for ServingNodeAvp - GMLC Ethiopia
 */
public interface ServingNodeAvp extends GroupedAvp {
    boolean hasSGSNNumber();
    byte[] getSGSNNumber();
    void setSGSNNumber(byte[] sgsnNumber);
    
    boolean hasSGSNName();
    String getSGSNName();
    void setSGSNName(String sgsnName);
    
    boolean hasSGSNRealm();
    String getSGSNRealm();
    void setSGSNRealm(String sgsnRealm);
    
    boolean hasMMEName();
    String getMMEName();
    void setMMEName(String mmeName);
    
    boolean hasMMERealm();
    String getMMERealm();
    void setMMERealm(String mmeRealm);
    
    boolean hasMSCNumber();
    byte[] getMSCNumber();
    void setMSCNumber(byte[] mscNumber);
    
    boolean has3GPPAAAServerName();
    String get3GPPAAAServerName();
    void set3GPPAAAServerName(String aaaServerName);
}
