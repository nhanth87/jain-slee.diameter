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

package net.java.slee.resource.diameter.slg;

import net.java.slee.resource.diameter.base.DiameterMessageFactory;
import net.java.slee.resource.diameter.slg.events.LocationAnswer;
import net.java.slee.resource.diameter.slg.events.LocationRequest;
import net.java.slee.resource.diameter.slg.events.ProvideLocationAnswer;
import net.java.slee.resource.diameter.slg.events.ProvideLocationRequest;

/**
 * The SLg message factory interface used to create Diameter SLg messages.
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public interface SLgMessageFactory {

  /**
   * variable equal to SLg application ID
   */
  public static final int _SLG_APP_ID = 16777255;
  
  /**
   * ID of SLg application vendor - 3GPP
   */
  public static final int _SLG_VENDOR_ID = 10415;

  /**
   * Get a factory to create AVPs and messages defined by Diameter Base.
   * 
   * @return base Diameter message factory
   */
  DiameterMessageFactory getBaseMessageFactory();

  /**
   * Create an empty ProvideLocationRequest that will need to have AVPs set on it before being sent.
   * 
   * @return a ProvideLocationRequest object
   */
  ProvideLocationRequest createProvideLocationRequest();

  /**
   * Create a ProvideLocationAnswer containing a Result-Code or Experimental-Result AVP populated with the given value.
   * If <code>isExperimentalResultCode</code> is <code>true</code>, the <code>resultCode</code> parameter will be set
   * in a {@link org.mobicents.slee.resource.diameter.base.types.ExperimentalResultAvp} AVP, if it is <code>false</code> it 
   * will be sent as a standard Result-Code AVP.
   * 
   * @param request the corresponding request
   * @param resultCode the result code value
   * @param isExperimentalResultCode whether the result code is experimental
   * @return a ProvideLocationAnswer object
   */
  ProvideLocationAnswer createProvideLocationAnswer(ProvideLocationRequest request, long resultCode, boolean isExperimentalResultCode);

  /**
   * Create an empty ProvideLocationAnswer that will need to have AVPs set on it before being sent.
   * 
   * @param request the corresponding request
   * @return a ProvideLocationAnswer object
   */
  ProvideLocationAnswer createProvideLocationAnswer(ProvideLocationRequest request);

  /**
   * Create an empty LocationRequest that will need to have AVPs set on it before being sent.
   * 
   * @return a LocationRequest object
   */
  LocationRequest createLocationRequest();

  /**
   * Create a LocationAnswer containing a Result-Code or Experimental-Result AVP populated with the given value.
   * If <code>isExperimentalResultCode</code> is <code>true</code>, the <code>resultCode</code> parameter will be set
   * in a {@link org.mobicents.slee.resource.diameter.base.types.ExperimentalResultAvp} AVP, if it is <code>false</code> it 
   * will be sent as a standard Result-Code AVP.
   * 
   * @param request the corresponding request
   * @param resultCode the result code value
   * @param isExperimentalResultCode whether the result code is experimental
   * @return a LocationAnswer object
   */
  LocationAnswer createLocationAnswer(LocationRequest request, long resultCode, boolean isExperimentalResultCode);

  /**
   * Create an empty LocationAnswer that will need to have AVPs set on it before being sent.
   * 
   * @param request the corresponding request
   * @return a LocationAnswer object
   */
  LocationAnswer createLocationAnswer(LocationRequest request);

}
