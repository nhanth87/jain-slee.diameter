package net.java.slee.resource.diameter.slg.events.avp;

import net.java.slee.resource.diameter.base.events.avp.GroupedAvp;

/**
 * AVP representation of Supported-Features AVP (628).
 * 
 * The Supported-Features AVP is of type Grouped and allows the peer to discover the vendor-specific
 * features supported by the Diameter peer.
 * 
 * @author <a href="mailto:dev@jdiameter">Restcomm jdiameter Team</a>
 */
public interface SupportedFeatures extends GroupedAvp {

    /**
     * Gets the Vendor-Id AVP.
     * @return long value of Vendor-Id
     */
    long getVendorId();

    /**
     * Sets the Vendor-Id AVP.
     * @param vendorId the vendor ID
     */
    void setVendorId(long vendorId);

    /**
     * Gets the Feature-List-ID AVP.
     * @return int value of Feature-List-ID
     */
    int getFeatureListId();

    /**
     * Sets the Feature-List-ID AVP.
     * @param featureListId the feature list ID
     */
    void setFeatureListId(int featureListId);

    /**
     * Gets the Feature-List AVP.
     * @return long value of Feature-List
     */
    long getFeatureList();

    /**
     * Sets the Feature-List AVP.
     * @param featureList the feature list
     */
    void setFeatureList(long featureList);
}
