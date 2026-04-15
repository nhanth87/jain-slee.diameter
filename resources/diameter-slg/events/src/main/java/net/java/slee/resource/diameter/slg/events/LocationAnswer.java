/*
 * JBoss, Home of Professional Open Source
 * Copyright 2011, Red Hat, Inc. and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package net.java.slee.resource.diameter.slg.events;

import net.java.slee.resource.diameter.base.events.DiameterMessage;
import net.java.slee.resource.diameter.base.events.avp.ExperimentalResultAvp;
import net.java.slee.resource.diameter.base.events.avp.FailedAvp;
import net.java.slee.resource.diameter.slg.events.avp.SupportedFeaturesAvp;

/**
 * Defines an interface representing the Location-Answer command.
 * 
 * From the Diameter SLg Reference Point Protocol Details (3GPP TS 29.172)
 * specification:
 * 
 * <pre>
 * 3.2.2        Location-Answer (LgA) Command
 * 
 * The Location-Answer command, indicated by the Command-Code field set 
 * to 8388620 and the 'R' bit cleared in the Command Flags field, is sent 
 * by an MME to a GMLC in response to the Location-Request command.
 * 
 * Message Format
 * &lt; Location-Answer &gt; ::=        &lt; Diameter Header: 8388620, PXY, 16777255 &gt;
 *                                 &lt; Session-Id &gt;
 *                                 [ Result-Code ]
 *                                 [ Experimental-Result ]
 *                                 { Auth-Session-State }
 *                                 { Origin-Host }
 *                                 { Origin-Realm }
 *                                 [ Location-Estimate ]
 *                                 [ Accuracy-Fulfilment-Indicator ]
 *                                 [ Age-Of-Location-Estimate ]
 *                                 [ Velocity-Estimate ]
 *                                 [ EUTRAN-Positioning-Data ]
 *                                 [ ECGI ]
 *                                 [ GERAN-Positioning-Info ]
 *                                 [ Cell-Global-Identity ]
 *                                 [ UTRAN-Positioning-Info ]
 *                                 [ Service-Area-Identity ]
 *                                 [ Serving-Node ]
 *                                 [ PLR-Flags ]
 *                                 [ Deferred-Location-Type ]
 *                                 [ Pseudonym-Indicator ]
 *                                 *[ Supported-Features ]
 *                                 *[ AVP ]
 *                                 *[ Failed-AVP ]
 *                                 *[ Proxy-Info ]
 *                                 *[ Route-Record ]
 * </pre>
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public interface LocationAnswer extends DiameterMessage {

  static final int commandCode = 8388620;

  /**
   * Returns true if the Result-Code AVP is present in the message.
   */
  boolean hasResultCode();

  /**
   * Returns the value of the Result-Code AVP, of type Unsigned32.
   * 
   * @return the value of the Result-Code AVP
   * @throws IllegalStateException if the Result-Code AVP has not been set
   */
  long getResultCode();

  /**
   * Sets the value of the Result-Code AVP, of type Unsigned32.
   * 
   * @throws IllegalStateException if setResultCode has already been called
   */
  void setResultCode(long resultCode);

  /**
   * Returns true if the Experimental-Result AVP is present in the message.
   */
  boolean hasExperimentalResult();

  /**
   * Returns the value of the Experimental-Result AVP, of type Grouped.
   * 
   * @return the value of the Experimental-Result AVP or null if it has not been set
   */
  ExperimentalResultAvp getExperimentalResult();

  /**
   * Sets the value of the Experimental-Result AVP, of type Grouped.
   * 
   * @throws IllegalStateException if setExperimentalResult has already been called
   */
  void setExperimentalResult(ExperimentalResultAvp experimentalResult);

  /**
   * Returns true if the Auth-Session-State AVP is present in the message.
   */
  boolean hasAuthSessionState();

  /**
   * Returns the set of Failed-AVP AVPs.
   */
  FailedAvp[] getFailedAvps();

  /**
   * Sets a single Failed-AVP AVP in the message.
   * 
   * @throws IllegalStateException if setFailedAvp or setFailedAvps has already been called
   */
  void setFailedAvp(FailedAvp failedAvp);

  /**
   * Sets the set of Failed-AVP AVPs.
   * 
   * @throws IllegalStateException if setFailedAvp or setFailedAvps has already been called
   */
  void setFailedAvps(FailedAvp[] failedAvps);

  /**
   * Returns true if the Location-Estimate AVP is present in the message.
   */
  boolean hasLocationEstimate();

  /**
   * Returns the value of the Location-Estimate AVP, of type OctetString.
   * 
   * @return the value of the Location-Estimate AVP or null if it has not been set
   */
  byte[] getLocationEstimate();

  /**
   * Sets the value of the Location-Estimate AVP, of type OctetString.
   * 
   * @throws IllegalStateException if setLocationEstimate has already been called
   */
  void setLocationEstimate(byte[] locationEstimate);

  /**
   * Returns true if the Accuracy-Fulfilment-Indicator AVP is present in the message.
   */
  boolean hasAccuracyFulfilmentIndicator();

  /**
   * Returns the value of the Accuracy-Fulfilment-Indicator AVP, of type Enumerated.
   * 
   * @return the value of the Accuracy-Fulfilment-Indicator AVP or null if it has not been set
   */
  int getAccuracyFulfilmentIndicator();

  /**
   * Sets the value of the Accuracy-Fulfilment-Indicator AVP, of type Enumerated.
   * 
   * @throws IllegalStateException if setAccuracyFulfilmentIndicator has already been called
   */
  void setAccuracyFulfilmentIndicator(int accuracyFulfilmentIndicator);

  /**
   * Returns true if the Age-Of-Location-Estimate AVP is present in the message.
   */
  boolean hasAgeOfLocationEstimate();

  /**
   * Returns the value of the Age-Of-Location-Estimate AVP, of type Unsigned32.
   * 
   * @return the value of the Age-Of-Location-Estimate AVP or null if it has not been set
   */
  long getAgeOfLocationEstimate();

  /**
   * Sets the value of the Age-Of-Location-Estimate AVP, of type Unsigned32.
   * 
   * @throws IllegalStateException if setAgeOfLocationEstimate has already been called
   */
  void setAgeOfLocationEstimate(long ageOfLocationEstimate);

  /**
   * Returns true if the Velocity-Estimate AVP is present in the message.
   */
  boolean hasVelocityEstimate();

  /**
   * Returns the value of the Velocity-Estimate AVP, of type OctetString.
   * 
   * @return the value of the Velocity-Estimate AVP or null if it has not been set
   */
  byte[] getVelocityEstimate();

  /**
   * Sets the value of the Velocity-Estimate AVP, of type OctetString.
   * 
   * @throws IllegalStateException if setVelocityEstimate has already been called
   */
  void setVelocityEstimate(byte[] velocityEstimate);

  /**
   * Returns true if the EUTRAN-Positioning-Data AVP is present in the message.
   */
  boolean hasEUTRANPositioningData();

  /**
   * Returns the value of the EUTRAN-Positioning-Data AVP, of type OctetString.
   * 
   * @return the value of the EUTRAN-Positioning-Data AVP or null if it has not been set
   */
  byte[] getEUTRANPositioningData();

  /**
   * Sets the value of the EUTRAN-Positioning-Data AVP, of type OctetString.
   * 
   * @throws IllegalStateException if setEUTRANPositioningData has already been called
   */
  void setEUTRANPositioningData(byte[] eutranPositioningData);

  /**
   * Returns true if the ECGI AVP is present in the message.
   */
  boolean hasECGI();

  /**
   * Returns the value of the ECGI AVP, of type OctetString.
   * 
   * @return the value of the ECGI AVP or null if it has not been set
   */
  byte[] getECGI();

  /**
   * Sets the value of the ECGI AVP, of type OctetString.
   * 
   * @throws IllegalStateException if setECGI has already been called
   */
  void setECGI(byte[] ecgi);

  /**
   * Returns true if the Service-Area-Identity AVP is present in the message.
   */
  boolean hasServiceAreaIdentity();

  /**
   * Returns the value of the Service-Area-Identity AVP, of type OctetString.
   * 
   * @return the value of the Service-Area-Identity AVP or null if it has not been set
   */
  byte[] getServiceAreaIdentity();

  /**
   * Sets the value of the Service-Area-Identity AVP, of type OctetString.
   * 
   * @throws IllegalStateException if setServiceAreaIdentity has already been called
   */
  void setServiceAreaIdentity(byte[] serviceAreaIdentity);

  /**
   * Returns true if the PLR-Flags AVP is present in the message.
   */
  boolean hasPLRFlags();

  /**
   * Returns the value of the PLR-Flags AVP, of type Unsigned32.
   * 
   * @return the value of the PLR-Flags AVP or null if it has not been set
   */
  long getPLRFlags();

  /**
   * Sets the value of the PLR-Flags AVP, of type Unsigned32.
   * 
   * @throws IllegalStateException if setPLRFlags has already been called
   */
  void setPLRFlags(long plrFlags);

  /**
   * Returns the set of Supported-Features AVPs.
   * 
   * @return the set of Supported-Features AVPs
   */
  SupportedFeaturesAvp[] getSupportedFeatureses();

  /**
   * Sets a single Supported-Features AVP in the message.
   * 
   * @throws IllegalStateException if setSupportedFeatures or setSupportedFeatureses has already been called
   */
  void setSupportedFeatures(SupportedFeaturesAvp supportedFeatures);

  /**
   * Sets the set of Supported-Features AVPs.
   * 
   * @throws IllegalStateException if setSupportedFeatures or setSupportedFeatureses has already been called
   */
  void setSupportedFeatureses(SupportedFeaturesAvp[] supportedFeatureses);

}
