package com.apiserver;

import com.apiserver.server.AdvertisementServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.test.util.EnvironmentTestUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.StandardEnvironment;

import java.util.Arrays;

/**
 * 
 * <pre>
 * Class Name  : Application.java
 * Description : AD서버 스타트 파일  SpringBoot configuration  
 * Modification Information
 *
 *    수정일　　　 　　  		수정자　　　     			  수정내용
 *    ────────────   ─────────   ───────────────────────────────
 *    2018. 2. 19.          skan               최초생성
 * </pre>
 *
 * @author skan
 * @since 2018. 2. 19.
 * @version 
 *
 * Copyright (C) 2018 by CJENM|Mezzimedia.Inc. All right reserved.
 */
@SpringBootApplication
public class SpringAdverApplication {
	
	private final static Logger logger = LoggerFactory.getLogger(SpringAdverApplication.class); 
	
	@Autowired
	private ApplicationContext context;
	
	public static void main (String[] args) throws Exception {

		//AnnotationConfigApplicationContext;
		// SpringBoot run 
		SpringApplication application = new SpringApplication(SpringAdverApplication.class);
		ConfigurableEnvironment environment = new StandardEnvironment();
		environment.setDefaultProfiles("dev");
		application.setEnvironment(environment);
		//application.printBanner();
		
		
		ConfigurableApplicationContext context = application.run(args);
		AdvertisementServer advertisementServer = context.getBean(AdvertisementServer.class);
		logger.info("Netty Ad Server - START !!!!!!!!!");
		advertisementServer.start();
		
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}



}
