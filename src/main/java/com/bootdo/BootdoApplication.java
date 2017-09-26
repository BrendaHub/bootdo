package com.bootdo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.TimeUnit;

@EnableTransactionManagement //添加事物的支持
@MapperScan("com.bootdo.*.dao") //扫描dao目录
@SpringBootApplication
public class BootdoApplication {
	public static void main(String[] args) {
		SpringApplication.run(BootdoApplication.class, args);
		System.out.println("*****************************");
		System.out.println("*****************************");
		System.out.println("*******BootDo启动成功**********");
		System.out.println("*****************************");
		System.out.println("*****************************");
		System.out.println("提交了项目代码到github");
	}

	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer222() {
		return (container -> {
			ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
			ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
			ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
			container.addErrorPages(error401Page, error404Page, error500Page);
			container.setSessionTimeout(1800);// 单位为S
		});
	}

	@Bean
	public EmbeddedServletContainerCustomizer embeddedServletContainerCustomizer(){
		return (container ->{
			container.setSessionTimeout(1, TimeUnit.MINUTES);
		});
	}
}
