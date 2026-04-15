package net.java.slee.resource.diameter.slg.events.avp;

import net.java.slee.resource.diameter.base.events.avp.GroupedAvp;

/**
 * Stub interface for GERANPositioningInfoAvp - GMLC Ethiopia
 */
public interface GERANPositioningInfoAvp extends GroupedAvp {
    boolean hasGERANPositioningData();
    byte[] getGERANPositioningData();
    void setGERANPositioningData(byte[] data);

    boolean hasGERANGANSSPositioningData();
    byte[] getGERANGANSSPositioningData();
    void setGERANGANSSPositioningData(byte[] data);
}
