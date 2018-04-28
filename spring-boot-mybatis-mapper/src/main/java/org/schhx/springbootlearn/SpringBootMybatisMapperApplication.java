package org.schhx.springbootlearn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan(basePackages = {"org.schhx.springbootlearn.dao"})
public class SpringBootMybatisMapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisMapperApplication.class, args);
	}
}
