package com.helpinghands;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.helpinghands")
public class HelpinghandsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpinghandsApplication.class, args);
	}

}
