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

package org.mobicents.slee.resource.diameter.sh.events.avp.userdata;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.bind.annotation.XmlAnyElement;

import net.java.slee.resource.diameter.sh.events.avp.userdata.CSLocationInformation;
import net.java.slee.resource.diameter.sh.events.avp.userdata.PSLocationInformation;
import net.java.slee.resource.diameter.sh.events.avp.userdata.PublicIdentity;
import net.java.slee.resource.diameter.sh.events.avp.userdata.ShData;
import net.java.slee.resource.diameter.sh.events.avp.userdata.ShDataExtension;
import net.java.slee.resource.diameter.sh.events.avp.userdata.ShIMSData;


/**
 * <p>Java class for tSh-Data complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tSh-Data">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PublicIdentifiers" type="{}tPublicIdentity" minOccurs="0"/>
 *         &lt;element name="RepositoryData" type="{}tTransparentData" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="Sh-IMS-Data" type="{}tShIMSData" minOccurs="0"/>
 *         &lt;element name="CSLocationInformation" type="{}tCSLocationInformation" minOccurs="0"/>
 *         &lt;element name="PSLocationInformation" type="{}tPSLocationInformation" minOccurs="0"/>
 *         &lt;element name="CSUserState" type="{}tCSUserState" minOccurs="0"/>
 *         &lt;element name="PSUserState" type="{}tPSUserState" minOccurs="0"/>
 *         &lt;element name="Extension" type="{}tSh-Data-Extension" minOccurs="0"/>
 *         &lt;any processContents='lax' namespace='##other' maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 */
@JacksonXmlRootElement
public class TShData implements ShData {

    @JacksonXmlProperty(localName = "PublicIdentifiers")
    protected TPublicIdentity publicIdentifiers;
    @JacksonXmlProperty(localName = "RepositoryData")
    protected List<TTransparentData> repositoryData;
    @JacksonXmlProperty(localName = "Sh-IMS-Data")
    protected TShIMSData shIMSData;
    @JacksonXmlProperty(localName = "CSLocationInformation")
    protected TCSLocationInformation csLocationInformation;
    @JacksonXmlProperty(localName = "PSLocationInformation")
    protected TPSLocationInformation psLocationInformation;
    @JacksonXmlProperty(localName = "CSUserState")
    protected Short csUserState;
    @JacksonXmlProperty(localName = "PSUserState")
    protected Short psUserState;
    @JacksonXmlProperty(localName = "Extension")
    protected TShDataExtension extension;
    @XmlAnyElement(lax = true)
    protected List<Object> any;

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.ShData#getPublicIdentifiers()
     */
    public PublicIdentity getPublicIdentifiers() {
        return publicIdentifiers;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.ShData#setPublicIdentifiers(org.mobicents.slee.resource.diameter.sh.events.avp.userdata.PublicIdentity)
     */
    public void setPublicIdentifiers(PublicIdentity value) {
        this.publicIdentifiers = (TPublicIdentity) value;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.ShData#getRepositoryData()
     */
    public List<TTransparentData> getRepositoryData() {
        if (repositoryData == null) {
            repositoryData = new ArrayList<TTransparentData>();
        }
        return this.repositoryData;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.ShData#getShIMSData()
     */
    public ShIMSData getShIMSData() {
        return shIMSData;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.ShData#setShIMSData(org.mobicents.slee.resource.diameter.sh.events.avp.userdata.TShIMSData)
     */
    public void setShIMSData(ShIMSData value) {
        this.shIMSData = (TShIMSData) value;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.ShData#getCSLocationInformation()
     */
    public CSLocationInformation getCSLocationInformation() {
        return csLocationInformation;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.ShData#setCSLocationInformation(org.mobicents.slee.resource.diameter.sh.events.avp.userdata.CSLocationInformation)
     */
    public void setCSLocationInformation(CSLocationInformation value) {
        this.csLocationInformation = (TCSLocationInformation) value;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.ShData#getPSLocationInformation()
     */
    public PSLocationInformation getPSLocationInformation() {
        return psLocationInformation;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.ShData#setPSLocationInformation(org.mobicents.slee.resource.diameter.sh.events.avp.userdata.PSLocationInformation)
     */
    public void setPSLocationInformation(PSLocationInformation value) {
        this.psLocationInformation = (TPSLocationInformation) value;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.ShData#getCSUserState()
     */
    public Short getCSUserState() {
        return csUserState;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.ShData#setCSUserState(java.lang.Short)
     */
    public void setCSUserState(Short value) {
        this.csUserState = value;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.ShData#getPSUserState()
     */
    public Short getPSUserState() {
        return psUserState;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.ShData#setPSUserState(java.lang.Short)
     */
    public void setPSUserState(Short value) {
        this.psUserState = value;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.ShData#getExtension()
     */
    public ShDataExtension getExtension() {
        return extension;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.ShData#setExtension(org.mobicents.slee.resource.diameter.sh.events.avp.userdata.TShDataExtension)
     */
    public void setExtension(ShDataExtension value) {
        this.extension = (TShDataExtension) value;
    }

    /* (non-Javadoc)
     * @see org.mobicents.slee.resource.diameter.sh.events.avp.userdata.ShData#getAny()
     */
    public List<Object> getAny() {
        if (any == null) {
            any = new ArrayList<Object>();
        }
        return this.any;
    }

    @Override
    public boolean equals(Object obj) {
      if(obj != null && obj.getClass().equals(this.getClass())) {
        try {
          XmlMapper xmlMapper = new XmlMapper();
          ByteArrayOutputStream baosThis = new ByteArrayOutputStream();
          xmlMapper.writeValue(baosThis, this);
          ByteArrayOutputStream baosOther = new ByteArrayOutputStream();
          xmlMapper.writeValue(baosOther, obj);
          return Arrays.equals(baosThis.toByteArray(), baosOther.toByteArray());
        }
        catch (Exception e) {
          return false;
        }
      }

      return false;
    }
}
