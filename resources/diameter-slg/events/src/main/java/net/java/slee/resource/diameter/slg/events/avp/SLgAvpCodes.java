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

package net.java.slee.resource.diameter.slg.events.avp;

import net.java.slee.resource.diameter.base.events.avp.DiameterAvpType;

/**
 * Diameter SLg AVP codes constants.
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class SLgAvpCodes {

  private SLgAvpCodes() {}

  /**
   * Vendor-Id for SLg AVPs. It's 3GPP.
   */
  public static final long SLG_VENDOR_ID = 10415L;

  /**
   * AVP Code for SLg-Location-Type AVP. Data type is Enumerated.
   */
  public static final int SLG_LOCATION_TYPE = 2500;
  public static final DiameterAvpType SLG_LOCATION_TYPE_AVP_TYPE = DiameterAvpType.fromString("Enumerated");

  /**
   * AVP Code for LCS-EPS-Client-Name AVP. Data type is Grouped.
   */
  public static final int LCS_EPS_CLIENT_NAME = 2501;
  public static final DiameterAvpType LCS_EPS_CLIENT_NAME_AVP_TYPE = DiameterAvpType.fromString("Grouped");

  /**
   * AVP Code for LCS-Name-String AVP. Data type is UTF8String.
   */
  public static final int LCS_NAME_STRING = 2502;
  public static final DiameterAvpType LCS_NAME_STRING_AVP_TYPE = DiameterAvpType.fromString("UTF8String");

  /**
   * AVP Code for LCS-Format-Indicator AVP. Data type is Enumerated.
   */
  public static final int LCS_FORMAT_INDICATOR = 2503;
  public static final DiameterAvpType LCS_FORMAT_INDICATOR_AVP_TYPE = DiameterAvpType.fromString("Enumerated");

  /**
   * AVP Code for Location-Event AVP. Data type is Enumerated.
   */
  public static final int LOCATION_EVENT = 2513;
  public static final DiameterAvpType LOCATION_EVENT_AVP_TYPE = DiameterAvpType.fromString("Enumerated");

  /**
   * AVP Code for LCS-EPS-Client-Name-String AVP. Data type is UTF8String.
   */
  public static final int LCS_EPS_CLIENT_NAME_STRING = 2502;
  public static final DiameterAvpType LCS_EPS_CLIENT_NAME_STRING_AVP_TYPE = DiameterAvpType.fromString("UTF8String");

  /**
   * AVP Code for LCS-Client-Type AVP. Data type is Enumerated.
   */
  public static final int LCS_CLIENT_TYPE = 1241;
  public static final DiameterAvpType LCS_CLIENT_TYPE_AVP_TYPE = DiameterAvpType.fromString("Enumerated");

  /**
   * AVP Code for LCS-Requestor-Name AVP. Data type is Grouped.
   */
  public static final int LCS_REQUESTOR_NAME = 2503;
  public static final DiameterAvpType LCS_REQUESTOR_NAME_AVP_TYPE = DiameterAvpType.fromString("Grouped");

  /**
   * AVP Code for LCS-Requestor-Name-String AVP. Data type is UTF8String.
   */
  public static final int LCS_REQUESTOR_NAME_STRING = 2504;
  public static final DiameterAvpType LCS_REQUESTOR_NAME_STRING_AVP_TYPE = DiameterAvpType.fromString("UTF8String");

  /**
   * AVP Code for LCS-Priority AVP. Data type is Unsigned32.
   */
  public static final int LCS_PRIORITY = 2505;
  public static final DiameterAvpType LCS_PRIORITY_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for LCS-QoS AVP. Data type is Grouped.
   */
  public static final int LCS_QOS = 2506;
  public static final DiameterAvpType LCS_QOS_AVP_TYPE = DiameterAvpType.fromString("Grouped");

  /**
   * AVP Code for LCS-QoS-Class AVP. Data type is Enumerated.
   */
  public static final int LCS_QOS_CLASS = 2507;
  public static final DiameterAvpType LCS_QOS_CLASS_AVP_TYPE = DiameterAvpType.fromString("Enumerated");

  /**
   * AVP Code for Horizontal-Accuracy AVP. Data type is Unsigned32.
   */
  public static final int HORIZONTAL_ACCURACY = 2508;
  public static final DiameterAvpType HORIZONTAL_ACCURACY_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for Vertical-Accuracy AVP. Data type is Unsigned32.
   */
  public static final int VERTICAL_ACCURACY = 2509;
  public static final DiameterAvpType VERTICAL_ACCURACY_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for Vertical-Requested AVP. Data type is Enumerated.
   */
  public static final int VERTICAL_REQUESTED = 2510;
  public static final DiameterAvpType VERTICAL_REQUESTED_AVP_TYPE = DiameterAvpType.fromString("Enumerated");

  /**
   * AVP Code for Velocity-Requested AVP. Data type is Enumerated.
   */
  public static final int VELOCITY_REQUESTED = 2511;
  public static final DiameterAvpType VELOCITY_REQUESTED_AVP_TYPE = DiameterAvpType.fromString("Enumerated");

  /**
   * AVP Code for LCS-Supported-GAD-Shapes AVP. Data type is Unsigned32.
   */
  public static final int LCS_SUPPORTED_GAD_SHAPES = 2512;
  public static final DiameterAvpType LCS_SUPPORTED_GAD_SHAPES_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for LCS-Service-Type-ID AVP. Data type is Unsigned32.
   */
  public static final int LCS_SERVICE_TYPE_ID = 2513;
  public static final DiameterAvpType LCS_SERVICE_TYPE_ID_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for LCS-Codeword AVP. Data type is UTF8String.
   */
  public static final int LCS_CODEWORD = 2514;
  public static final DiameterAvpType LCS_CODEWORD_AVP_TYPE = DiameterAvpType.fromString("UTF8String");

  /**
   * AVP Code for LCS-Privacy-Invoke-Notify AVP. Data type is Grouped.
   */
  public static final int LCS_PRIVACY_INVOKE_NOTIFY = 2515;
  public static final DiameterAvpType LCS_PRIVACY_INVOKE_NOTIFY_AVP_TYPE = DiameterAvpType.fromString("Grouped");

  /**
   * AVP Code for LCS-Privacy-Check AVP. Data type is Enumerated.
   */
  public static final int LCS_PRIVACY_CHECK = 2516;
  public static final DiameterAvpType LCS_PRIVACY_CHECK_AVP_TYPE = DiameterAvpType.fromString("Enumerated");

  /**
   * AVP Code for Accuracy-Fulfilment-Indicator AVP. Data type is Enumerated.
   */
  public static final int ACCURACY_FULFILMENT_INDICATOR = 2517;
  public static final DiameterAvpType ACCURACY_FULFILMENT_INDICATOR_AVP_TYPE = DiameterAvpType.fromString("Enumerated");

  /**
   * AVP Code for Age-Of-Location-Estimate AVP. Data type is Unsigned32.
   */
  public static final int AGE_OF_LOCATION_ESTIMATE = 2518;
  public static final DiameterAvpType AGE_OF_LOCATION_ESTIMATE_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for Velocity-Estimate AVP. Data type is OctetString.
   */
  public static final int VELOCITY_ESTIMATE = 2519;
  public static final DiameterAvpType VELOCITY_ESTIMATE_AVP_TYPE = DiameterAvpType.fromString("OctetString");

  /**
   * AVP Code for EUTRAN-Positioning-Data AVP. Data type is OctetString.
   */
  public static final int EUTRAN_POSITIONING_DATA = 2520;
  public static final DiameterAvpType EUTRAN_POSITIONING_DATA_AVP_TYPE = DiameterAvpType.fromString("OctetString");

  /**
   * AVP Code for ECGI AVP. Data type is OctetString.
   */
  public static final int ECGI = 2521;
  public static final DiameterAvpType ECGI_AVP_TYPE = DiameterAvpType.fromString("OctetString");

  /**
   * AVP Code for Location-Estimate AVP. Data type is OctetString.
   */
  public static final int LOCATION_ESTIMATE = 1242;
  public static final DiameterAvpType LOCATION_ESTIMATE_AVP_TYPE = DiameterAvpType.fromString("OctetString");

  /**
   * AVP Code for GERAN-Positioning-Info AVP. Data type is Grouped.
   */
  public static final int GERAN_POSITIONING_INFO = 2522;
  public static final DiameterAvpType GERAN_POSITIONING_INFO_AVP_TYPE = DiameterAvpType.fromString("Grouped");

  /**
   * AVP Code for Cell-Global-Identity AVP. Data type is OctetString.
   */
  public static final int CELL_GLOBAL_IDENTITY = 1604;
  public static final DiameterAvpType CELL_GLOBAL_IDENTITY_AVP_TYPE = DiameterAvpType.fromString("OctetString");

  /**
   * AVP Code for GERAN-Positioning-Data AVP. Data type is OctetString.
   */
  public static final int GERAN_POSITIONING_DATA = 2523;
  public static final DiameterAvpType GERAN_POSITIONING_DATA_AVP_TYPE = DiameterAvpType.fromString("OctetString");

  /**
   * AVP Code for GERAN-GANSS-Positioning-Data AVP. Data type is OctetString.
   */
  public static final int GERAN_GANSS_POSITIONING_DATA = 2524;
  public static final DiameterAvpType GERAN_GANSS_POSITIONING_DATA_AVP_TYPE = DiameterAvpType.fromString("OctetString");

  /**
   * AVP Code for UTRAN-Positioning-Info AVP. Data type is Grouped.
   */
  public static final int UTRAN_POSITIONING_INFO = 2524;
  public static final DiameterAvpType UTRAN_POSITIONING_INFO_AVP_TYPE = DiameterAvpType.fromString("Grouped");

  /**
   * AVP Code for UTRAN-Positioning-Data AVP. Data type is OctetString.
   */
  public static final int UTRAN_POSITIONING_DATA = 2525;
  public static final DiameterAvpType UTRAN_POSITIONING_DATA_AVP_TYPE = DiameterAvpType.fromString("OctetString");

  /**
   * AVP Code for UTRAN-GANSS-Positioning-Data AVP. Data type is OctetString.
   */
  public static final int UTRAN_GANSS_POSITIONING_DATA = 2526;
  public static final DiameterAvpType UTRAN_GANSS_POSITIONING_DATA_AVP_TYPE = DiameterAvpType.fromString("OctetString");

  /**
   * AVP Code for UTRAN-Additional-Positioning-Data AVP. Data type is OctetString.
   */
  public static final int UTRAN_ADDITIONAL_POSITIONING_DATA = 2528;
  public static final DiameterAvpType UTRAN_ADDITIONAL_POSITIONING_DATA_AVP_TYPE = DiameterAvpType.fromString("OctetString");

  /**
   * AVP Code for Service-Area-Identity AVP. Data type is OctetString.
   */
  public static final int SERVICE_AREA_IDENTITY = 2527;
  public static final DiameterAvpType SERVICE_AREA_IDENTITY_AVP_TYPE = DiameterAvpType.fromString("OctetString");

  /**
   * AVP Code for Serving-Node AVP. Data type is Grouped.
   */
  public static final int SERVING_NODE = 2401;
  public static final DiameterAvpType SERVING_NODE_AVP_TYPE = DiameterAvpType.fromString("Grouped");

  /**
   * AVP Code for GMLC-Address AVP. Data type is Address.
   */
  public static final int GMLC_ADDRESS = 2405;
  public static final DiameterAvpType GMLC_ADDRESS_AVP_TYPE = DiameterAvpType.fromString("Address");

  /**
   * AVP Code for LCS-Capabilities-Sets AVP. Data type is Unsigned32.
   */
  public static final int LCS_CAPABILITIES_SETS = 2406;
  public static final DiameterAvpType LCS_CAPABILITIES_SETS_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for PLA-Flags AVP. Data type is Unsigned32.
   */
  public static final int PLA_FLAGS = 2528;
  public static final DiameterAvpType PLA_FLAGS_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for PLR-Flags AVP. Data type is Unsigned32.
   */
  public static final int PLR_FLAGS = 2529;
  public static final DiameterAvpType PLR_FLAGS_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for Deferred-Location-Type AVP. Data type is Unsigned32.
   */
  public static final int DEFERRED_LOCATION_TYPE = 1230;
  public static final DiameterAvpType DEFERRED_LOCATION_TYPE_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for Pseudonym-Indicator AVP. Data type is Enumerated.
   */
  public static final int PSEUDONYM_INDICATOR = 2530;
  public static final DiameterAvpType PSEUDONYM_INDICATOR_AVP_TYPE = DiameterAvpType.fromString("Enumerated");

  /**
   * AVP Code for Periodic-Location-Support-Indicator AVP. Data type is Enumerated.
   */
  public static final int PERIODIC_LOCATION_SUPPORT_INDICATOR = 2531;
  public static final DiameterAvpType PERIODIC_LOCATION_SUPPORT_INDICATOR_AVP_TYPE = DiameterAvpType.fromString("Enumerated");

  /**
   * AVP Code for Priority-Indicator AVP. Data type is Enumerated.
   */
  public static final int PRIORITY_INDICATOR = 2532;
  public static final DiameterAvpType PRIORITY_INDICATOR_AVP_TYPE = DiameterAvpType.fromString("Enumerated");

  /**
   * AVP Code for LCS-Client-Dialed-By-MS AVP. Data type is UTF8String.
   */
  public static final int LCS_CLIENT_DIALED_BY_MS = 2533;
  public static final DiameterAvpType LCS_CLIENT_DIALED_BY_MS_AVP_TYPE = DiameterAvpType.fromString("UTF8String");

  /**
   * AVP Code for LCS-Client-External-ID AVP. Data type is UTF8String.
   */
  public static final int LCS_CLIENT_EXTERNAL_ID = 1238;
  public static final DiameterAvpType LCS_CLIENT_EXTERNAL_ID_AVP_TYPE = DiameterAvpType.fromString("UTF8String");

  /**
   * AVP Code for Delayed-Location-Reporting-Data AVP. Data type is Grouped.
   */
  public static final int DELAYED_LOCATION_REPORTING_DATA = 2534;
  public static final DiameterAvpType DELAYED_LOCATION_REPORTING_DATA_AVP_TYPE = DiameterAvpType.fromString("Grouped");

  /**
   * AVP Code for Event-Reporting-Indicator AVP. Data type is Enumerated.
   */
  public static final int EVENT_REPORTING_INDICATOR = 2535;
  public static final DiameterAvpType EVENT_REPORTING_INDICATOR_AVP_TYPE = DiameterAvpType.fromString("Enumerated");

  /**
   * AVP Code for Area-Event-Info AVP. Data type is Grouped.
   */
  public static final int AREA_EVENT_INFO = 2536;
  public static final DiameterAvpType AREA_EVENT_INFO_AVP_TYPE = DiameterAvpType.fromString("Grouped");

  /**
   * AVP Code for Area-Definition AVP. Data type is Grouped.
   */
  public static final int AREA_DEFINITION = 2537;
  public static final DiameterAvpType AREA_DEFINITION_AVP_TYPE = DiameterAvpType.fromString("Grouped");

  /**
   * AVP Code for Area AVP. Data type is Grouped.
   */
  public static final int AREA = 2538;
  public static final DiameterAvpType AREA_AVP_TYPE = DiameterAvpType.fromString("Grouped");

  /**
   * AVP Code for Area-Type AVP. Data type is Enumerated.
   */
  public static final int AREA_TYPE = 2539;
  public static final DiameterAvpType AREA_TYPE_AVP_TYPE = DiameterAvpType.fromString("Enumerated");

  /**
   * AVP Code for Area-Identification AVP. Data type is OctetString.
   */
  public static final int AREA_IDENTIFICATION = 2540;
  public static final DiameterAvpType AREA_IDENTIFICATION_AVP_TYPE = DiameterAvpType.fromString("OctetString");

  /**
   * AVP Code for Occurrence-Info AVP. Data type is Enumerated.
   */
  public static final int OCCURRENCE_INFO = 2541;
  public static final DiameterAvpType OCCURRENCE_INFO_AVP_TYPE = DiameterAvpType.fromString("Enumerated");

  /**
   * AVP Code for Interval-Time AVP. Data type is Unsigned32.
   */
  public static final int INTERVAL_TIME = 2542;
  public static final DiameterAvpType INTERVAL_TIME_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for Periodic-LDR-Info AVP. Data type is Grouped.
   */
  public static final int PERIODIC_LDR_INFO = 2543;
  public static final DiameterAvpType PERIODIC_LDR_INFO_AVP_TYPE = DiameterAvpType.fromString("Grouped");

  /**
   * AVP Code for Reporting-Interval AVP. Data type is Unsigned32.
   */
  public static final int REPORTING_INTERVAL = 2544;
  public static final DiameterAvpType REPORTING_INTERVAL_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for Reporting-Amount AVP. Data type is Unsigned32.
   */
  public static final int REPORTING_AMOUNT = 2545;
  public static final DiameterAvpType REPORTING_AMOUNT_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for Delayed-Location-Reporting-Support-Indicator AVP. Data type is Enumerated.
   */
  public static final int DELAYED_LOCATION_REPORTING_SUPPORT_INDICATOR = 2546;
  public static final DiameterAvpType DELAYED_LOCATION_REPORTING_SUPPORT_INDICATOR_AVP_TYPE = DiameterAvpType.fromString("Enumerated");

  /**
   * AVP Code for MTC-Provider-Info AVP. Data type is Grouped.
   */
  public static final int MTC_PROVIDER_INFO = 2547;
  public static final DiameterAvpType MTC_PROVIDER_INFO_AVP_TYPE = DiameterAvpType.fromString("Grouped");

  /**
   * AVP Code for MTC-Provider-ID AVP. Data type is UTF8String.
   */
  public static final int MTC_PROVIDER_ID = 2548;
  public static final DiameterAvpType MTC_PROVIDER_ID_AVP_TYPE = DiameterAvpType.fromString("UTF8String");

  /**
   * AVP Code for Response-Time AVP. Data type is Enumerated.
   */
  public static final int RESPONSE_TIME = 282;
  public static final DiameterAvpType RESPONSE_TIME_AVP_TYPE = DiameterAvpType.fromString("Enumerated");

  /**
   * AVP Code for LCS-Data-Coding-Scheme AVP. Data type is UTF8String.
   */
  public static final int LCS_DATA_CODING_SCHEME = 1236;
  public static final DiameterAvpType LCS_DATA_CODING_SCHEME_AVP_TYPE = DiameterAvpType.fromString("UTF8String");

  /**
   * AVP Code for LCS-Requestor-Id AVP. Data type is UTF8String.
   */
  public static final int LCS_REQUESTOR_ID = 1237;
  public static final DiameterAvpType LCS_REQUESTOR_ID_AVP_TYPE = DiameterAvpType.fromString("UTF8String");

  /**
   * AVP Code for LCS-Requestor-Id-Type AVP. Data type is Enumerated.
   */
  public static final int LCS_REQUESTOR_ID_TYPE = 1239;
  public static final DiameterAvpType LCS_REQUESTOR_ID_TYPE_AVP_TYPE = DiameterAvpType.fromString("Enumerated");

  /**
   * AVP Code for Visited-PLMN-Id AVP. Data type is OctetString.
   */
  public static final int VISITED_PLMN_ID = 1611;
  public static final DiameterAvpType VISITED_PLMN_ID_AVP_TYPE = DiameterAvpType.fromString("OctetString");

  /**
   * AVP Code for Linear-Distance AVP. Data type is Unsigned32.
   */
  public static final int LINEAR_DISTANCE = 2550;
  public static final DiameterAvpType LINEAR_DISTANCE_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  // Supported-Features AVP codes (shared across 3GPP applications)
  /**
   * AVP Code for Vendor-Id AVP. Data type is Unsigned32.
   */
  public static final int VENDOR_ID = 266;
  public static final DiameterAvpType VENDOR_ID_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for Feature-List-ID AVP. Data type is Unsigned32.
   */
  public static final int FEATURE_LIST_ID = 629;
  public static final DiameterAvpType FEATURE_LIST_ID_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for Feature-List AVP. Data type is Unsigned32.
   */
  public static final int FEATURE_LIST = 630;
  public static final DiameterAvpType FEATURE_LIST_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for Reporting-PLMN-List AVP. Data type is Grouped.
   */
  public static final int REPORTING_PLMN_LIST = 2549;
  public static final DiameterAvpType REPORTING_PLMN_LIST_AVP_TYPE = DiameterAvpType.fromString("Grouped");

  /**
   * AVP Code for PLMN-ID-List AVP. Data type is OctetString.
   */
  public static final int PLMN_ID_LIST = 2544;
  public static final DiameterAvpType PLMN_ID_LIST_AVP_TYPE = DiameterAvpType.fromString("OctetString");

  /**
   * AVP Code for Prioritized-List-Indicator AVP. Data type is Enumerated.
   */
  public static final int PRIORITIZED_LIST_INDICATOR = 2532;
  public static final DiameterAvpType PRIORITIZED_LIST_INDICATOR_AVP_TYPE = DiameterAvpType.fromString("Enumerated");

  /**
   * AVP Code for Maximum-Interval AVP. Data type is Unsigned32.
   */
  public static final int MAXIMUM_INTERVAL = 2543;
  public static final DiameterAvpType MAXIMUM_INTERVAL_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for Sampling-Interval AVP. Data type is Unsigned32.
   */
  public static final int SAMPLING_INTERVAL = 2544;
  public static final DiameterAvpType SAMPLING_INTERVAL_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for Reporting-Duration AVP. Data type is Unsigned32.
   */
  public static final int REPORTING_DURATION = 2545;
  public static final DiameterAvpType REPORTING_DURATION_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for Reporting-Location-Requirements AVP. Data type is Unsigned32.
   */
  public static final int REPORTING_LOCATION_REQUIREMENTS = 2546;
  public static final DiameterAvpType REPORTING_LOCATION_REQUIREMENTS_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for ESMLC-Cell-Info AVP. Data type is Grouped.
   */
  public static final int ESMLC_CELL_INFO = 2551;
  public static final DiameterAvpType ESMLC_CELL_INFO_AVP_TYPE = DiameterAvpType.fromString("Grouped");

  /**
   * AVP Code for Civic-Address AVP. Data type is UTF8String.
   */
  public static final int CIVIC_ADDRESS = 2552;
  public static final DiameterAvpType CIVIC_ADDRESS_AVP_TYPE = DiameterAvpType.fromString("UTF8String");

  /**
   * AVP Code for Barometric-Pressure AVP. Data type is Unsigned32.
   */
  public static final int BAROMETRIC_PRESSURE = 2553;
  public static final DiameterAvpType BAROMETRIC_PRESSURE_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for Cell-Portion-ID AVP. Data type is Integer32.
   */
  public static final int CELL_PORTION_ID = 2554;
  public static final DiameterAvpType CELL_PORTION_ID_AVP_TYPE = DiameterAvpType.fromString("Integer32");

  /**
   * AVP Code for LCS-Reference-Number AVP. Data type is OctetString.
   */
  public static final int LCS_REFERENCE_NUMBER = 2526;
  public static final DiameterAvpType LCS_REFERENCE_NUMBER_AVP_TYPE = DiameterAvpType.fromString("OctetString");

  /**
   * AVP Code for Deferred-MTLR-Data AVP. Data type is Grouped.
   */
  public static final int DEFERRED_MTLR_DATA = 2534;
  public static final DiameterAvpType DEFERRED_MTLR_DATA_AVP_TYPE = DiameterAvpType.fromString("Grouped");

  /**
   * AVP Code for 1xRTT-RCID AVP. Data type is OctetString.
   */
  public static final int RTT_RCID_1X = 2713;
  public static final DiameterAvpType RTT_RCID_1X_AVP_TYPE = DiameterAvpType.fromString("OctetString");

  /**
   * AVP Code for LRR-Flags AVP. Data type is Unsigned32.
   */
  public static final int LRR_FLAGS = 2714;
  public static final DiameterAvpType LRR_FLAGS_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for LRA-Flags AVP. Data type is Unsigned32.
   */
  public static final int LRA_FLAGS = 2715;
  public static final DiameterAvpType LRA_FLAGS_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for AMF-Instance-ID AVP. Data type is OctetString.
   */
  public static final int AMF_INSTANCE_ID = 2712;
  public static final DiameterAvpType AMF_INSTANCE_ID_AVP_TYPE = DiameterAvpType.fromString("OctetString");

}
