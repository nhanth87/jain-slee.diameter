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

import java.io.Serializable;
import java.io.StreamCorruptedException;

import net.java.slee.resource.diameter.base.events.avp.Enumerated;

/**
 * AVP representation of SLg-Location-Type AVP.
 * 
 * From the Diameter SLg Reference Point Protocol Details (3GPP TS 29.172):
 * 
 * The SLg-Location-Type AVP is of type Enumerated. The following values are defined:
 * 
 * <pre>
 * CURRENT_LOCATION (0)
 * CURRENT_OR_LAST_KNOWN_LOCATION (1)
 * INITIAL_LOCATION (2)
 * ACTIVATE_DEFERRED_LOCATION (3)
 * CANCEL_DEFERRED_LOCATION (4)
 * NOTIFICATION_VERIFICATION_ONLY (5)
 * </pre>
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class LocationType implements Serializable, Enumerated {

  private static final long serialVersionUID = 1L;

  public static final int _CURRENT_LOCATION = 0;
  public static final int _CURRENT_OR_LAST_KNOWN_LOCATION = 1;
  public static final int _INITIAL_LOCATION = 2;
  public static final int _ACTIVATE_DEFERRED_LOCATION = 3;
  public static final int _CANCEL_DEFERRED_LOCATION = 4;
  public static final int _NOTIFICATION_VERIFICATION_ONLY = 5;

  /**
   * The request is for the current location of the target UE.
   */
  public static final LocationType CURRENT_LOCATION = new LocationType(_CURRENT_LOCATION);

  /**
   * The request is for the current or last known location of the target UE.
   */
  public static final LocationType CURRENT_OR_LAST_KNOWN_LOCATION = new LocationType(_CURRENT_OR_LAST_KNOWN_LOCATION);

  /**
   * The request is for the initial location of the target UE at the beginning of the emergency call.
   */
  public static final LocationType INITIAL_LOCATION = new LocationType(_INITIAL_LOCATION);

  /**
   * The request is to activate deferred location reporting.
   */
  public static final LocationType ACTIVATE_DEFERRED_LOCATION = new LocationType(_ACTIVATE_DEFERRED_LOCATION);

  /**
   * The request is to cancel deferred location reporting.
   */
  public static final LocationType CANCEL_DEFERRED_LOCATION = new LocationType(_CANCEL_DEFERRED_LOCATION);

  /**
   * The request is for notification verification only.
   */
  public static final LocationType NOTIFICATION_VERIFICATION_ONLY = new LocationType(_NOTIFICATION_VERIFICATION_ONLY);

  private LocationType(int value) {
    this.value = value;
  }

  public static LocationType fromInt(int type) {
    switch (type) {
      case _CURRENT_LOCATION:
        return CURRENT_LOCATION;
      case _CURRENT_OR_LAST_KNOWN_LOCATION:
        return CURRENT_OR_LAST_KNOWN_LOCATION;
      case _INITIAL_LOCATION:
        return INITIAL_LOCATION;
      case _ACTIVATE_DEFERRED_LOCATION:
        return ACTIVATE_DEFERRED_LOCATION;
      case _CANCEL_DEFERRED_LOCATION:
        return CANCEL_DEFERRED_LOCATION;
      case _NOTIFICATION_VERIFICATION_ONLY:
        return NOTIFICATION_VERIFICATION_ONLY;
      default:
        throw new IllegalArgumentException("Invalid LocationType value: " + type);
    }
  }

  public int getValue() {
    return value;
  }

  public byte[] byteArrayValue() {
    return new byte[] { (byte) value };
  }

  public String toString() {
    switch (value) {
      case _CURRENT_LOCATION:
        return "CURRENT_LOCATION";
      case _CURRENT_OR_LAST_KNOWN_LOCATION:
        return "CURRENT_OR_LAST_KNOWN_LOCATION";
      case _INITIAL_LOCATION:
        return "INITIAL_LOCATION";
      case _ACTIVATE_DEFERRED_LOCATION:
        return "ACTIVATE_DEFERRED_LOCATION";
      case _CANCEL_DEFERRED_LOCATION:
        return "CANCEL_DEFERRED_LOCATION";
      case _NOTIFICATION_VERIFICATION_ONLY:
        return "NOTIFICATION_VERIFICATION_ONLY";
      default:
        return "<Invalid Value>";
    }
  }

  private Object readResolve() throws StreamCorruptedException {
    try {
      return fromInt(value);
    }
    catch (IllegalArgumentException iae) {
      throw new StreamCorruptedException("Invalid internal state found: " + value);
    }
  }

  private int value;
}