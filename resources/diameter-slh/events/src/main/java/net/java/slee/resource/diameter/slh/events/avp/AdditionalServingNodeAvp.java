package net.java.slee.resource.diameter.slh.events.avp;

import net.java.slee.resource.diameter.base.events.avp.GroupedAvp;

/**
 * Stub interface for AdditionalServingNodeAvp - GMLC Ethiopia
 */
public interface AdditionalServingNodeAvp extends GroupedAvp {
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
}
