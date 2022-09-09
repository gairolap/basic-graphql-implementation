package com.org.graphql.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import graphql.ExecutionResult;
import graphql.GraphQL;

@RequestMapping("/graphql/books")
@RestController
public class GraphQLController {

	@Autowired
	GraphQL graphQL;

	@PostMapping
	public ResponseEntity<Object> getAll(@RequestBody String query) {

		ExecutionResult result = graphQL.execute(query);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}

}