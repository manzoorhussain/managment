package com.comany.example.managment.managment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class ManagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManagmentApplication.class, args);

		System.out.print("HELLO TESTING");
	}

}
