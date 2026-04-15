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

package net.java.slee.resource.diameter.slh.events.avp;

import net.java.slee.resource.diameter.base.events.avp.DiameterAvpType;

/**
 * Diameter SLh AVP codes constants.
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class SLhAvpCodes {

  private SLhAvpCodes() {}

  /**
   * Vendor-Id for SLh AVPs. It's 3GPP.
   */
  public static final long SLH_VENDOR_ID = 10415L;

  /**
   * AVP Code for LCS-Routing-Info AVP. Data type is Grouped.
   */
  public static final int LCS_ROUTING_INFO = 2600;
  public static final DiameterAvpType LCS_ROUTING_INFO_AVP_TYPE = DiameterAvpType.fromString("Grouped");

  /**
   * AVP Code for LCS-Routing-Info-Flags AVP. Data type is Unsigned32.
   */
  public static final int LCS_ROUTING_INFO_FLAGS = 2601;
  public static final DiameterAvpType LCS_ROUTING_INFO_FLAGS_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for MME-Name AVP. Data type is UTF8String.
   */
  public static final int MME_NAME = 2402;
  public static final DiameterAvpType MME_NAME_AVP_TYPE = DiameterAvpType.fromString("UTF8String");

  /**
   * AVP Code for MME-Realm AVP. Data type is DiameterIdentity.
   */
  public static final int MME_REALM = 2403;
  public static final DiameterAvpType MME_REALM_AVP_TYPE = DiameterAvpType.fromString("DiameterIdentity");

  /**
   * AVP Code for SGSN-Name AVP. Data type is UTF8String.
   */
  public static final int SGSN_NAME = 2404;
  public static final DiameterAvpType SGSN_NAME_AVP_TYPE = DiameterAvpType.fromString("UTF8String");

  /**
   * AVP Code for SGSN-Realm AVP. Data type is DiameterIdentity.
   */
  public static final int SGSN_REALM = 2405;
  public static final DiameterAvpType SGSN_REALM_AVP_TYPE = DiameterAvpType.fromString("DiameterIdentity");

  /**
   * AVP Code for MSC-Number AVP. Data type is OctetString.
   */
  public static final int MSC_NUMBER = 2406;
  public static final DiameterAvpType MSC_NUMBER_AVP_TYPE = DiameterAvpType.fromString("OctetString");

  /**
   * AVP Code for TSGSN-Number AVP. Data type is OctetString.
   */
  public static final int TSGSN_NUMBER = 2407;
  public static final DiameterAvpType TSGSN_NUMBER_AVP_TYPE = DiameterAvpType.fromString("OctetString");

  /**
   * AVP Code for LCS-Capabilities-Sets AVP. Data type is Unsigned32.
   */
  public static final int LCS_CAPABILITIES_SETS = 2408;
  public static final DiameterAvpType LCS_CAPABILITIES_SETS_AVP_TYPE = DiameterAvpType.fromString("Unsigned32");

  /**
   * AVP Code for GMLC-Address AVP. Data type is Address.
   */
  public static final int GMLC_ADDRESS = 2409;
  public static final DiameterAvpType GMLC_ADDRESS_AVP_TYPE = DiameterAvpType.fromString("Address");

  /**
   * AVP Code for GMLC-Number AVP. Data type is OctetString.
   */
  public static final int GMLC_NUMBER = 2410;
  public static final DiameterAvpType GMLC_NUMBER_AVP_TYPE = DiameterAvpType.fromString("OctetString");

  /**
   * AVP Code for IMSI AVP. Data type is UTF8String.
   */
  public static final int IMSI = 1;
  public static final DiameterAvpType IMSI_AVP_TYPE = DiameterAvpType.fromString("UTF8String");

}
