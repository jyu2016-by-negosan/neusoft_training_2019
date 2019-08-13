package com.neusoft.ht;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = { "com.neusoft.ht.fee.mapper" })
@MapperScan(basePackages = { "com.neusoft.ht.complain.mapper" })
public class HtApplication {

	public static void main(String[] args) {
		SpringApplication.run(HtApplication.class, args);
	}

}
