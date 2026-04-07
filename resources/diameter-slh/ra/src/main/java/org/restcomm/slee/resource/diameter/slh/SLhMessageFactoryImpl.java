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

package org.restcomm.slee.resource.diameter.slh;

import net.java.slee.resource.diameter.base.events.DiameterHeader;
import net.java.slee.resource.diameter.base.events.DiameterMessage;
import net.java.slee.resource.diameter.base.events.avp.DiameterAvp;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.slh.SLhMessageFactory;
import net.java.slee.resource.diameter.slh.events.LocationReportAnswer;
import net.java.slee.resource.diameter.slh.events.LocationReportRequest;
import net.java.slee.resource.diameter.slh.events.RoutingInfoAnswer;
import net.java.slee.resource.diameter.slh.events.RoutingInfoRequest;

import org.apache.log4j.Logger;
import org.jdiameter.api.ApplicationId;
import org.jdiameter.api.InternalException;
import org.jdiameter.api.Message;
import org.jdiameter.api.Session;
import org.jdiameter.api.Stack;
import org.mobicents.slee.resource.diameter.base.DiameterMessageFactoryImpl;
import org.mobicents.slee.resource.diameter.base.events.ExtensionDiameterMessageImpl;
import org.restcomm.slee.resource.diameter.slh.events.LocationReportAnswerImpl;
import org.restcomm.slee.resource.diameter.slh.events.LocationReportRequestImpl;
import org.restcomm.slee.resource.diameter.slh.events.RoutingInfoAnswerImpl;
import org.restcomm.slee.resource.diameter.slh.events.RoutingInfoRequestImpl;

/**
 * SLh Message Factory Implementation
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 */
public class SLhMessageFactoryImpl extends DiameterMessageFactoryImpl implements SLhMessageFactory {

  private static Logger logger = Logger.getLogger(SLhMessageFactoryImpl.class);

  private DiameterAvp[] EMPTY_AVP_ARRAY = new DiameterAvp[]{};

  // SLh: Vendor-Specific-Application-Id with 3GPP vendor (10415) and SLh auth app id (16777291)
  private ApplicationId slhAppId = ApplicationId.createByAuthAppId(_SLH_VENDOR_ID, _SLH_APP_ID);

  /**
   * @param stack
   */
  public SLhMessageFactoryImpl(Stack stack) {
    super(stack);
  }

  /**
   * @param session
   * @param stack
   */
  public SLhMessageFactoryImpl(Session session, Stack stack) {
    super(session, stack);
  }

  public void setApplicationId(long vendorId, long applicationId) {
    this.slhAppId = ApplicationId.createByAuthAppId(vendorId, applicationId);
  }

  public ApplicationId getApplicationId() {
    return this.slhAppId;
  }

  /**
   * Creates a SLh Message with specified command-code and avps filled. If a header is present an answer will be created, if not
   * it will generate a request.
   *
   * @param diameterHeader
   * @param avps
   * @param _commandCode
   * @param appId
   * @return
   * @throws InternalException
   */
  DiameterMessage createSlhMessage(DiameterHeader diameterHeader, DiameterAvp[] avps, int _commandCode, ApplicationId appId) throws InternalException {

    boolean creatingRequest = diameterHeader == null;
    Message msg = null;

    if (!creatingRequest) {
      Message raw = createMessage(diameterHeader, avps, 0, appId);
      raw.setProxiable(diameterHeader.isProxiable());
      raw.setRequest(false);
      raw.setReTransmitted(false); // just in case. answers never have T flag set
      msg = raw;
    }
    else {
      Message raw = createMessage(diameterHeader, avps, _commandCode, appId);
      raw.setProxiable(true);
      raw.setRequest(true);
      msg = raw;
    }

    int commandCode = creatingRequest ? _commandCode : diameterHeader.getCommandCode();
    DiameterMessage diamMessage = null;

    switch (commandCode) {
      case 8388622: // RoutingInfoRequest.COMMAND_CODE
        diamMessage = creatingRequest ? new RoutingInfoRequestImpl(msg) : new RoutingInfoAnswerImpl(msg);
        break;
      case 8388623: // LocationReportRequest.COMMAND_CODE
        diamMessage = creatingRequest ? new LocationReportRequestImpl(msg) : new LocationReportAnswerImpl(msg);
        break;
      default:
        diamMessage = new ExtensionDiameterMessageImpl(msg);
    }

    // Finally, add Origin-Host and Origin-Realm, if not present.
    if (!diamMessage.hasSessionId() && session != null) {
      diamMessage.setSessionId(session.getSessionId());
    }

    return diamMessage;
  }

  // Routing Info Messages ----------------------------------------------

  public RoutingInfoRequest createRoutingInfoRequest() {
    try {
      return (RoutingInfoRequest) createSlhMessage(null, EMPTY_AVP_ARRAY, 8388622, slhAppId);
    }
    catch (InternalException e) {
      logger.error("Failed to create Routing-Info-Request", e);
    }

    return null;
  }

  public RoutingInfoAnswer createRoutingInfoAnswer(RoutingInfoRequest request) {
    try {
      return (RoutingInfoAnswer) createSlhMessage(request.getHeader(), EMPTY_AVP_ARRAY, 8388622, slhAppId);
    }
    catch (InternalException e) {
      logger.error("Failed to create Routing-Info-Answer", e);
    }

    return null;
  }

  public RoutingInfoAnswer createRoutingInfoAnswer(RoutingInfoRequest request, long resultCode, boolean isExperimentalResult) {
    try {
      RoutingInfoAnswer answer = createRoutingInfoAnswer(request);

      if (isExperimentalResult) {
        // For experimental result, just set the experimental result code
        // The implementation should handle this in a way compatible with the base
        answer.setExperimentalResult(null);
      }
      else {
        answer.setResultCode(resultCode);
      }

      return answer;
    }
    catch (Exception e) {
      logger.error("Failed to create Routing-Info-Answer with result code", e);
    }

    return null;
  }

  // Location Report Messages ----------------------------------------------

  public LocationReportRequest createLocationReportRequest() {
    try {
      return (LocationReportRequest) createSlhMessage(null, EMPTY_AVP_ARRAY, 8388623, slhAppId);
    }
    catch (InternalException e) {
      logger.error("Failed to create Location-Report-Request", e);
    }

    return null;
  }

  public LocationReportAnswer createLocationReportAnswer(LocationReportRequest request) {
    try {
      return (LocationReportAnswer) createSlhMessage(request.getHeader(), EMPTY_AVP_ARRAY, 8388623, slhAppId);
    }
    catch (InternalException e) {
      logger.error("Failed to create Location-Report-Answer", e);
    }

    return null;
  }

  public LocationReportAnswer createLocationReportAnswer(LocationReportRequest request, long resultCode, boolean isExperimentalResult) {
    try {
      LocationReportAnswer answer = createLocationReportAnswer(request);

      if (isExperimentalResult) {
        // For experimental result, just set the experimental result code
        // The implementation should handle this in a way compatible with the base
        answer.setExperimentalResult(null);
      }
      else {
        answer.setResultCode(resultCode);
      }

      return answer;
    }
    catch (Exception e) {
      logger.error("Failed to create Location-Report-Answer with result code", e);
    }

    return null;
  }

  // Base Message Factory -----------------------------------------------

  public net.java.slee.resource.diameter.base.DiameterMessageFactory getBaseMessageFactory() {
    return this;
  }

}
