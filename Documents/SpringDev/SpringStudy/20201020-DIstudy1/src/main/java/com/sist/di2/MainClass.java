package com.sist.di2;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Locale;
import java.util.Map;

import com.sist.di.Sawon;

public class MainClass {

	public static void main(String[] args) {
		ApplicationContext app=
				new ApplicationContext("/Users/haeni/Documents/SpringDev/SpringStudy/20201020-DIstudy1/src/main/java/app.xml");
		Sawon sa=(Sawon)app.getBean("sa");
		sa.init();
		sa.print();
	}

}
