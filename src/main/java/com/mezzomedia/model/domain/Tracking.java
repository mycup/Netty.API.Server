package com.mezzomedia.model.domain;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@XmlRootElement(name="Tracking")
public class Tracking {
	
	@JacksonXmlProperty(isAttribute=true)
	private String event;
	
	@JacksonXmlText
	private String value;
}
