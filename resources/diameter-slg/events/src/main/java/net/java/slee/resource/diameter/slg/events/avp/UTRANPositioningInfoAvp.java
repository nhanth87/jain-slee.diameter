package net.java.slee.resource.diameter.slg.events.avp;

import net.java.slee.resource.diameter.base.events.avp.GroupedAvp;

/**
 * Stub interface for UTRANPositioningInfoAvp - GMLC Ethiopia
 */
public interface UTRANPositioningInfoAvp extends GroupedAvp {
    boolean hasUTRANPositioningData();
    byte[] getUTRANPositioningData();
    void setUTRANPositioningData(byte[] data);
    
    boolean hasUTRANGANSSPositioningData();
    byte[] getUTRANGANSSPositioningData();
    void setUTRANGANSSPositioningData(byte[] data);

    boolean hasUTRANAdditionalPositioningData();
    byte[] getUTRANAdditionalPositioningData();
    void setUTRANAdditionalPositioningData(byte[] data);
}
