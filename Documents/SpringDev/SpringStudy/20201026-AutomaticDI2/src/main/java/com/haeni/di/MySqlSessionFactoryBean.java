package com.haeni.di;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

/*
<bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
     p:dataSource-ref="ds"
     p:configLocation="classpath:Config.xml"
   />
 */

@Component
public class MySqlSessionFactoryBean extends SqlSessionFactoryBean {

	@Autowired
	public void setDataSource(DataSource dataSource) {
		// TODO Auto-generated method stub
		super.setDataSource(dataSource);
	}
	public MySqlSessionFactoryBean()
	{
		try {
			Resource res = new ClassPathResource("Config.xml");
			setConfigLocation(res);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
