package net.java.slee.resource.diameter.slg.events.avp;

import net.java.slee.resource.diameter.base.events.avp.GroupedAvp;

/**
 * Stub interface for LCSQoSClass - GMLC Ethiopia
 */
public interface LCSQoSClass extends GroupedAvp {
    int _ASSURED = 0;
    int _BEST_EFFORT = 1;
    
    boolean hasLCSQoSClass();
    int getLCSQoSClass();
    int getValue();
    void setLCSQoSClass(int value);
    
    boolean hasHorizontalAccuracy();
    long getHorizontalAccuracy();
    void setHorizontalAccuracy(long value);
    
    boolean hasVerticalAccuracy();
    long getVerticalAccuracy();
    void setVerticalAccuracy(long value);
    
    boolean hasVerticalRequested();
    int getVerticalRequested();
    void setVerticalRequested(int value);
    
    boolean hasResponseTime();
    int getResponseTime();
    void setResponseTime(int value);
}
