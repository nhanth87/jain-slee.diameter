package net.java.slee.resource.diameter.slg.events.avp;

/**
 * Stub enum for OccurrenceInfo - GMLC Ethiopia
 */
public interface OccurrenceInfo {
    int _ONE_TIME_EVENT = 0;
    int _MULTIPLE_TIME_EVENT = 1;
    
    static int fromInt(Integer value) {
        return value != null ? value.intValue() : _ONE_TIME_EVENT;
    }
}
