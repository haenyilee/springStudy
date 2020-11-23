package com.sist.di3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//XML을 대신 사용
@Configuration
public class ApplicationConfig {
   
   @Bean("mem")
   //<bean> XML =>대처하는 프로그램(어노테이션)
   //싱글턴
   public Member memberInfo()
   {
      Member m=new Member("이순신", "서울", "010-0000-0000");
      return m;
   }
}