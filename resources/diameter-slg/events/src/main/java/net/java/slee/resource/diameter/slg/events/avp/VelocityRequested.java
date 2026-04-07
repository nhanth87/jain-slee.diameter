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
 * AVP representation of Velocity-Requested AVP.
 * 
 * From the Diameter SLg Reference Point Protocol Details (3GPP TS 29.172):
 * 
 * The Velocity-Requested AVP is of type Enumerated. The following values are defined:
 * 
 * <pre>
 * VELOCITY_IS_NOT_REQUESTED (0)
 * VELOCITY_IS_REQUESTED (1)
 * </pre>
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public class VelocityRequested implements Serializable, Enumerated {

  private static final long serialVersionUID = 1L;

  public static final int _VELOCITY_IS_NOT_REQUESTED = 0;
  public static final int _VELOCITY_IS_REQUESTED = 1;

  /**
   * Velocity is not requested.
   */
  public static final VelocityRequested VELOCITY_IS_NOT_REQUESTED = new VelocityRequested(_VELOCITY_IS_NOT_REQUESTED);

  /**
   * Velocity is requested.
   */
  public static final VelocityRequested VELOCITY_IS_REQUESTED = new VelocityRequested(_VELOCITY_IS_REQUESTED);

  private VelocityRequested(int value) {
    this.value = value;
  }

  public static VelocityRequested fromInt(int type) {
    switch (type) {
      case _VELOCITY_IS_NOT_REQUESTED:
        return VELOCITY_IS_NOT_REQUESTED;
      case _VELOCITY_IS_REQUESTED:
        return VELOCITY_IS_REQUESTED;
      default:
        throw new IllegalArgumentException("Invalid VelocityRequested value: " + type);
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
      case _VELOCITY_IS_NOT_REQUESTED:
        return "VELOCITY_IS_NOT_REQUESTED";
      case _VELOCITY_IS_REQUESTED:
        return "VELOCITY_IS_REQUESTED";
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