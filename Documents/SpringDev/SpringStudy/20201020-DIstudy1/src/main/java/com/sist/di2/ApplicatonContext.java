package com.sist.di2;

import java.io.*;
import java.util.*;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.springframework.context.ApplicationContext;

public class ApplicatonContext {
	private Map map=new HashMap();
	public ApplicatonContext(String path)
	{
		try {
			SAXParserFactory spf=SAXParserFactory.newInstance();
			SAXParser sp=spf.newSAXParser();
			XMLParser xp=new XMLParser();
			sp.parse(new File(path), dh);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	public Object getBean(String Key)
	{
		return map.get(Key);
	}

}
