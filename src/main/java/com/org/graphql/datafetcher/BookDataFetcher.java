package com.org.graphql.datafetcher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.org.graphql.model.Book;
import com.org.graphql.repo.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class BookDataFetcher implements DataFetcher<Book> {

	@Autowired
	BookRepository bookRepo;

	@Override
	public Book get(DataFetchingEnvironment environment) {

		String isin = environment.getArgument("id");
		return bookRepo.getOne(isin);
	}

}
