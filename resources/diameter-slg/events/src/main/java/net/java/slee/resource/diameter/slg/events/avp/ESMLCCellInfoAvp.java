package net.java.slee.resource.diameter.slg.events.avp;

import net.java.slee.resource.diameter.base.events.avp.GroupedAvp;

/**
 * Stub interface for ESMLCCellInfoAvp - GMLC Ethiopia
 */
public interface ESMLCCellInfoAvp extends GroupedAvp {
    boolean hasECGI();
    byte[] getECGI();
    void setECGI(byte[] ecgi);
}
