package com.example.camundabug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.camunda.zeebe.spring.client.annotation.Deployment;

@SpringBootApplication
@Deployment(resources = "classpath*:/bpmn/**/*.bpmn")
public class CamundabugApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamundabugApplication.class, args);
	}

}
