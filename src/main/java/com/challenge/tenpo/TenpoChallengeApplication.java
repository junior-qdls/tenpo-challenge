package com.challenge.tenpo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication()
@EnableAspectJAutoProxy
@EnableSwagger2WebMvc
public class TenpoChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TenpoChallengeApplication.class, args);
	}

}
