package com.org.graphql.config;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.google.common.io.CharStreams;
import com.org.graphql.datafetcher.AllBooksDataFetcher;
import com.org.graphql.datafetcher.BookDataFetcher;
import com.org.graphql.model.Book;
import com.org.graphql.repo.BookRepository;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GraphQLConfiguration {

	@Value("classpath:bookstore.graphqls")
	Resource schemaResource;

	@Autowired
	AllBooksDataFetcher allBooksDataFetcher;

	@Autowired
	BookDataFetcher bookDataFetcher;

	@Autowired
	BookRepository bookRepo;

	GraphQL graphQL;

	@PostConstruct
	public void loadSchema() throws IOException {

		this.loadDataAtStartUp();
		String schemaStr;
		try (final Reader reader = new InputStreamReader(schemaResource.getInputStream())) {
			schemaStr = CharStreams.toString(reader);
		}
		TypeDefinitionRegistry registry = new SchemaParser().parse(schemaStr);
		RuntimeWiring wiring = buildWiring();
		GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
		log.info("GraphQL schema...{}", graphQLSchema);
		this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
	}

	private RuntimeWiring buildWiring() {

		return RuntimeWiring.newRuntimeWiring().type("Query", typeWiring -> typeWiring
				.dataFetcher("allBooks", allBooksDataFetcher).dataFetcher("book", bookDataFetcher)).build();
	}

	private void loadDataAtStartUp() {

		Stream.of(new Book("111", "Book-1", "pub-1", "auth-1", "01/01/2021"),
				new Book("222", "Book-2", "pub-2", "auth-2", "02/01/2021"),
				new Book("333", "Book-3", "pub-3", "auth-3", "03/01/2021"),
				new Book("444", "Book-4", "pub-4", "auth-4", "04/01/2021")).forEach(book -> {
					bookRepo.save(book);
				});
	}

	@Bean
	public GraphQL graphQL() {

		return this.graphQL;
	}

}