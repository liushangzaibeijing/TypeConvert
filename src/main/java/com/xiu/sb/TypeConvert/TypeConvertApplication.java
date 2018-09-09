package com.xiu.sb.TypeConvert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.miaosu","com.xiu.sb.TypeConvert"})
public class TypeConvertApplication {

	public static void main(String[] args) {
		SpringApplication.run(TypeConvertApplication.class, args);
	}
}
