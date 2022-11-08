package com.erp.buymanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
//default main class
public class BuyManageApplication extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
		return application.sources(ServletInitializer.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(BuyManageApplication.class, args);
	}
}

//Using for com.erp.buymanage.config.SeeeionListener (external WAS)
//public class BuyManageApplication extends SpringBootServletInitializer {
//	public static void main(String[] args) {
//		SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(BuyManageApplication.class);
//		SpringApplication springApplication = springApplicationBuilder.build();
//		springApplication.run(args);
//	}
//}
