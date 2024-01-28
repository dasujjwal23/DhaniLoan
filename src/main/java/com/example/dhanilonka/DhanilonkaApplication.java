package com.example.dhanilonka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

//import com.example.dhanilonka.controller.DhaniController;
import com.sample.LazyLoader;
import com.sample.Guide;

@SpringBootApplication
@ComponentScan(basePackages={"com.sample","com.example.dhanilonka.*"})
public class DhanilonkaApplication {
	
	public static void main(String[] args) {
		ConfigurableApplicationContext con=SpringApplication.run(DhanilonkaApplication.class, args);
		Guide help=con.getBean(Guide.class);
		//con.getBean(DhaniController.class);
		con.getBean(LazyLoader.class);
		help.getdata();
	}

	@Bean
	public RestTemplate template()
	{
		return new RestTemplate();
	}	
}
