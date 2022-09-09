package com.org.graphql.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	@Id
	private String isin;
	private String title;
	private String publisher;
	private String author;
	private String publishedDate;
}