package com.org.graphql.datafetcher;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.graphql.model.Book;
import com.org.graphql.repo.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class AllBooksDataFetcher implements DataFetcher<List<Book>> {

	@Autowired
	BookRepository bookRepo;

	@Override
	public List<Book> get(DataFetchingEnvironment environment) {

		return bookRepo.findAll();
	}

}