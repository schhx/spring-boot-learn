package org.schhx.springbootlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class SpringBootMybatisMasterslaveDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisMasterslaveDatasourceApplication.class, args);
	}
}
