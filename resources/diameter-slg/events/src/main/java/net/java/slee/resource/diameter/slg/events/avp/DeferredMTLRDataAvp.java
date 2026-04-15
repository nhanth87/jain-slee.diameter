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

import net.java.slee.resource.diameter.base.events.avp.GroupedAvp;

/**
 * Defines an interface representing the Deferred-MTLR-Data grouped AVP.
 */
public interface DeferredMTLRDataAvp extends GroupedAvp {

  /**
   * Returns true if the Deferred-Location-Type AVP is present in the message.
   */
  boolean hasDeferredLocationType();

  /**
   * Returns the value of the Deferred-Location-Type AVP, of type Unsigned32.
   * 
   * @return the value of the Deferred-Location-Type AVP or null if it has not been set
   */
  long getDeferredLocationType();

  /**
   * Sets the value of the Deferred-Location-Type AVP, of type Unsigned32.
   * 
   * @throws IllegalStateException if setDeferredLocationType has already been called
   */
  void setDeferredLocationType(long deferredLocationType);

  /**
   * Returns true if the Termination-Cause AVP is present in the message.
   */
  boolean hasTerminationCause();

  /**
   * Returns the value of the Termination-Cause AVP, of type Unsigned32.
   * 
   * @return the value of the Termination-Cause AVP or null if it has not been set
   */
  long getTerminationCause();

  /**
   * Sets the value of the Termination-Cause AVP, of type Unsigned32.
   * 
   * @throws IllegalStateException if setTerminationCause has already been called
   */
  void setTerminationCause(long terminationCause);

}
