package br.com.customerorders.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
// @SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class CustomerordersApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerordersApiApplication.class, args);
	}

}
