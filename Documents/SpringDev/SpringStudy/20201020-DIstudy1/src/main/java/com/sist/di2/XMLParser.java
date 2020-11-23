package com.sist.di2;

import java.util.*;

import org.aspectj.apache.bcel.classfile.Method;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParser extends DefaultHandler{
	Map map=new HashMap();

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		try {
			// app.xml에서 bean태그를 읽어와서 메모리를 할당한다. 
			if(qName.equals("bean"))
			{
				String id=attributes.getValue("id");
				String cls=attributes.getValue("class");
				clsName=Class.forName(cls);
				obj=clsName.newInstance();
				map.put(id, obj;
			}
			if(qName.equals("property"))
			{
				String name=attributes.getValue("name");
				String value=attributes.getValue("value");
				Method[] methods=clsName.getDeclaredMethods();
				for(Method m:methods)
				{
					if(m.getName().equalsIgnoreCase("set"+name))
					{
						if(name.equals("age"))
						{
							m.invoke(obj,Integer.parseInt(value));
						}
						else
						{
							m.invoke(obj,value);
						}
					}
				}
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
