package com.org.graphql.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.graphql.model.Book;

public interface BookRepository extends JpaRepository<Book, String> {
}