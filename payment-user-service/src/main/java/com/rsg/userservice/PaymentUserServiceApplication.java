package com.rsg.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.rsg.userservice.repository")
public class PaymentUserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentUserServiceApplication.class, args);
	}

}
