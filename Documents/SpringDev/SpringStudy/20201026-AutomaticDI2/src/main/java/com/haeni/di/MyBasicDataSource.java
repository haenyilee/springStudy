package com.haeni.di;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.stereotype.Component;

/*
<bean id="ds" class="org.apache.commons.dbcp.BasicDataSource"
     p:driverClassName="oracle.jdbc.driver.OracleDriver"
     p:url="jdbc:oracle:thin:@211.238.142.181:1521:XE"
     p:username="hr"
     p:password="happy"
   />
 */

@Component
public class MyBasicDataSource extends BasicDataSource{
	public MyBasicDataSource()
	{
		setDriverClassName("oracle.jdbc.driver.OracleDriver");
		setUrl("jdbc:oracle:thin:@211.238.142.183:1521:XE");
		setUsername("hr");
		setPassword("happy");
	}
}
