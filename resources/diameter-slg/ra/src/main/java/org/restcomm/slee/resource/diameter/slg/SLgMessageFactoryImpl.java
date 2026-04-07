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

package org.restcomm.slee.resource.diameter.slg;

import net.java.slee.resource.diameter.base.events.DiameterHeader;
import net.java.slee.resource.diameter.base.events.DiameterMessage;
import net.java.slee.resource.diameter.base.events.avp.DiameterAvp;
import net.java.slee.resource.diameter.base.events.avp.DiameterIdentity;
import net.java.slee.resource.diameter.slg.SLgMessageFactory;
import net.java.slee.resource.diameter.slg.events.LocationAnswer;
import net.java.slee.resource.diameter.slg.events.LocationRequest;
import net.java.slee.resource.diameter.slg.events.ProvideLocationAnswer;
import net.java.slee.resource.diameter.slg.events.ProvideLocationRequest;

import org.apache.log4j.Logger;
import org.jdiameter.api.ApplicationId;
import org.jdiameter.api.InternalException;
import org.jdiameter.api.Message;
import org.jdiameter.api.Session;
import org.jdiameter.api.Stack;
import org.mobicents.slee.resource.diameter.base.DiameterMessageFactoryImpl;
import org.mobicents.slee.resource.diameter.base.events.ExtensionDiameterMessageImpl;
import org.mobicents.slee.resource.diameter.base.events.avp.ExperimentalResultAvpImpl;
import org.restcomm.slee.resource.diameter.slg.events.LocationAnswerImpl;
import org.restcomm.slee.resource.diameter.slg.events.LocationRequestImpl;
import org.restcomm.slee.resource.diameter.slg.events.ProvideLocationAnswerImpl;
import org.restcomm.slee.resource.diameter.slg.events.ProvideLocationRequestImpl;

/**
 * SLg Message Factory Implementation
 *
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 */
public class SLgMessageFactoryImpl extends DiameterMessageFactoryImpl implements SLgMessageFactory {

  private static Logger logger = Logger.getLogger(SLgMessageFactoryImpl.class);

  private DiameterAvp[] EMPTY_AVP_ARRAY = new DiameterAvp[]{};

  // SLg: Vendor-Specific-Application-Id with 3GPP vendor (10415) and SLg auth app id (16777255)
  private ApplicationId slgAppId = ApplicationId.createByAuthAppId(_SLG_VENDOR_ID, _SLG_APP_ID);

  /**
   * @param stack
   */
  public SLgMessageFactoryImpl(Stack stack) {
    super(stack);
  }

  /**
   * @param session
   * @param stack
   */
  public SLgMessageFactoryImpl(Session session, Stack stack) {
    super(session, stack);
  }

  public void setApplicationId(long vendorId, long applicationId) {
    this.slgAppId = ApplicationId.createByAuthAppId(vendorId, applicationId);
  }

  public ApplicationId getApplicationId() {
    return this.slgAppId;
  }

  /**
   * Creates a SLg Message with specified command-code and avps filled. If a header is present an answer will be created, if not
   * it will generate a request.
   *
   * @param diameterHeader
   * @param avps
   * @param _commandCode
   * @param appId
   * @return
   * @throws InternalException
   */
  DiameterMessage createSlgMessage(DiameterHeader diameterHeader, DiameterAvp[] avps, int _commandCode, ApplicationId appId) throws InternalException {

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
      case ProvideLocationRequest.commandCode:
        diamMessage = creatingRequest ? new ProvideLocationRequestImpl(msg) : new ProvideLocationAnswerImpl(msg);
        break;
      case LocationRequest.commandCode:
        diamMessage = creatingRequest ? new LocationRequestImpl(msg) : new LocationAnswerImpl(msg);
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

  // Provide Location Messages -----------------------------------------

  public ProvideLocationRequest createProvideLocationRequest() {
    try {
      return (ProvideLocationRequest) createSlgMessage(null, EMPTY_AVP_ARRAY, ProvideLocationRequest.commandCode, slgAppId);
    }
    catch (InternalException e) {
      logger.error("Failed to create Provide-Location-Request", e);
    }

    return null;
  }

  public ProvideLocationRequest createProvideLocationRequest(DiameterIdentity destinationHost, DiameterIdentity destinationRealm) {
    try {
      ProvideLocationRequest request = createProvideLocationRequest();
      if (destinationHost != null) {
        request.setDestinationHost(destinationHost);
      }
      if (destinationRealm != null) {
        request.setDestinationRealm(destinationRealm);
      }
      return request;
    }
    catch (InternalException e) {
      logger.error("Failed to create Provide-Location-Request with destination", e);
    }

    return null;
  }

  public ProvideLocationAnswer createProvideLocationAnswer(ProvideLocationRequest request) {
    try {
      return (ProvideLocationAnswer) createSlgMessage(request.getHeader(), EMPTY_AVP_ARRAY, ProvideLocationRequest.commandCode, slgAppId);
    }
    catch (InternalException e) {
      logger.error("Failed to create Provide-Location-Answer", e);
    }

    return null;
  }

  public ProvideLocationAnswer createProvideLocationAnswer(ProvideLocationRequest request, long resultCode, boolean isExperimentalResult) {
    try {
      ProvideLocationAnswer answer = createProvideLocationAnswer(request);
      if (isExperimentalResult) {
        ExperimentalResultAvpImpl experimentalResult = new ExperimentalResultAvpImpl();
        experimentalResult.setExperimentalResultCode(resultCode);
        experimentalResult.setVendorIdAVP(_3GPP_VENDOR_ID);
        answer.setExperimentalResult(experimentalResult);
      }
      else {
        answer.setResultCode(resultCode);
      }

      return answer;
    }
    catch (InternalException e) {
      logger.error("Failed to create Provide-Location-Answer with result code", e);
    }
    return null;
  }

  // Location Messages ------------------------------------------------

  public LocationRequest createLocationRequest() {
    try {
      return (LocationRequest) createSlgMessage(null, EMPTY_AVP_ARRAY, LocationRequest.commandCode, slgAppId);
    }
    catch (InternalException e) {
      logger.error("Failed to create Location-Request", e);
    }

    return null;
  }

  public LocationRequest createLocationRequest(DiameterIdentity destinationHost, DiameterIdentity destinationRealm) {
    try {
      LocationRequest request = createLocationRequest();
      if (destinationHost != null) {
        request.setDestinationHost(destinationHost);
      }
      if (destinationRealm != null) {
        request.setDestinationRealm(destinationRealm);
      }
      return request;
    }
    catch (InternalException e) {
      logger.error("Failed to create Location-Request with destination", e);
    }

    return null;
  }

  public LocationAnswer createLocationAnswer(LocationRequest request) {
    try {
      return (LocationAnswer) createSlgMessage(request.getHeader(), EMPTY_AVP_ARRAY, LocationRequest.commandCode, slgAppId);
    }
    catch (InternalException e) {
      logger.error("Failed to create Location-Answer", e);
    }

    return null;
  }

  public LocationAnswer createLocationAnswer(LocationRequest request, long resultCode, boolean isExperimentalResult) {
    try {
      LocationAnswer answer = createLocationAnswer(request);
      if (isExperimentalResult) {
        ExperimentalResultAvpImpl experimentalResult = new ExperimentalResultAvpImpl();
        experimentalResult.setExperimentalResultCode(resultCode);
        experimentalResult.setVendorIdAVP(_3GPP_VENDOR_ID);
        answer.setExperimentalResult(experimentalResult);
      }
      else {
        answer.setResultCode(resultCode);
      }

      return answer;
    }
    catch (InternalException e) {
      logger.error("Failed to create Location-Answer with result code", e);
    }

    return null;
  }

  // Base Message Factory ---------------------------------------------

  public net.java.slee.resource.diameter.base.DiameterMessageFactory getBaseMessageFactory() {
    return this;
  }

}