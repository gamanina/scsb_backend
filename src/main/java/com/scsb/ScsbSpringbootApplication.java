package com.scsb;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.scsb"})
public class ScsbSpringbootApplication extends SpringBootServletInitializer {
	

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) 
	{
		return application.sources(ScsbSpringbootApplication.class);
	}

	public static void main(String[] args) throws Exception 
	{
		SpringApplication.run(ScsbSpringbootApplication.class, args);
		System.out.println("SCSB Project Running............");
	}
}
