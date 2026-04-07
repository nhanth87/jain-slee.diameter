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

package net.java.slee.resource.diameter.slh.events;

import net.java.slee.resource.diameter.base.events.DiameterMessage;

/**
 * Interface representing an LCS Routing Info Answer message.
 * This is an alias/compat interface for RoutingInfoAnswer.
 * 
 * From the Diameter SLh Reference Point Protocol Details (3GPP TS 29.173):
 * 
 * The LCS-Routing-Info-Answer (RIA) command is indicated by the Command-Code 
 * set to 8388620 and the 'R' bit cleared in the Command Flags field.
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 */
public interface LCSRoutingInfoAnswer extends RoutingInfoAnswer {

  // This interface inherits from RoutingInfoAnswer
  // and provides no additional methods.
  // It serves as a compatibility/alias interface.

}
