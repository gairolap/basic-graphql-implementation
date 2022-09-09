package com.org.graphql.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.org.graphql.repo")
@EntityScan("com.org.graphql.model")
@ComponentScan(basePackages = { "com.org.graphql" })
public class GraphQLApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphQLApplication.class, args);
	}

}