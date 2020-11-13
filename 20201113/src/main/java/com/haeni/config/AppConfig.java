package com.haeni.config;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
// <context:component-scan base-package="com.haeni.*"/>
@ComponentScan(basePackages={"com.haeni.*"})
@EnableWebMvc
// HandlerMapping , HandlerAdapter , HttpRequestHandler => 셋팅
public class AppConfig implements WebMvcConfigurer{
	
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		WebMvcConfigurer.super.configureDefaultServletHandling(configurer);
	}
	
	/*
		<bean id="ds"
			class="org.apache.commons.dbcp.BasicDataSource"
			p:driverClassName="oracle.jdbc.driver.OracleDriver"
			p:url="jdbc:oracle:thin:@localhost:1521:XE"
			p:username="hr"
			p:password="happy"
		/>			
	 */
	@Bean
	public DataSource dataSource()
	{
		BasicDataSource ds= new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("hr");
		ds.setPassword("happy");
		return ds;
	}
	
	/*
		<!-- 마이바티스 설정 -->
		<bean id="ssf"
		class="org.mybatis.spring.SqlSessionFactoryBean"
		p:dataSource-ref="ds"
		/>
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception
	{
		SqlSessionFactoryBean ssf= new SqlSessionFactoryBean();
		ssf.setDataSource(dataSource());
		return ssf.getObject();
	}
	/*
		<bean id="mapper" class="org.mybatis.spring.mapper.MapperFactoryBean"
			p:sqlSessionFactory-ref="ssf"
			p:mapperInterface="com.haeni.dao.MusicMapper"
		/>
	 */
	@Bean
	public MapperFactoryBean mapperFactoryBean() throws Exception
	{
		MapperFactoryBean m = new MapperFactoryBean();
		m.setSqlSessionFactory(sqlSessionFactory());
		m.setMapperInterface(com.haeni.dao.MusicMapper.class);
		return m;
	}
	
	/*
	 <!-- ViewResolver -->
		<bean id="viewResolver"
			class="org.springframework.web.servlet.view.InternalResourceViewResolver"
			p:prefix="/"
			p:suffix=".jsp"
		/>
	 */
	@Bean
	public ViewResolver viewResolver()
	{
		InternalResourceViewResolver r = new InternalResourceViewResolver();
		/*
		 * Class clsName=Class.forName("")
		 * Object obj=clsName.newInstance()
		 */
		r.setPrefix("/");
		r.setSuffix(".jsp");
		return r;
	}
	
}
