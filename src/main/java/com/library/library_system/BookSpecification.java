package com.library.library_system;

import org.springframework.data.jpa.domain.Specification;

//Specifikace pro filtrování knih podle různých atributů

public class BookSpecification {

    public static Specification<Book> hasTitle(String title) {
        return (root, query, builder) ->
                builder.like(builder.lower(root.get("title")), "%" + title.toLowerCase() + "%");
    }

    public static Specification<Book> hasAuthor(String author) {
        return (root, query, builder) ->
                builder.like(builder.lower(root.get("author")), "%" + author.toLowerCase() + "%");
    }

    public static Specification<Book> hasIsbn(String isbn) {
        return (root, query, builder) ->
                builder.like(builder.lower(root.get("isbn")), "%" + isbn.toLowerCase() + "%");
    }

    public static Specification<Book> hasGenre(String genre) {
        return (root, query, builder) ->
                builder.like(builder.lower(root.get("genre")), "%" + genre.toLowerCase() + "%");
    }

    public static Specification<Book> hasPublicationYear(Integer year) {
        return (root, query, builder) ->
                builder.equal(root.get("publicationYear"), year);
    }

    public static Specification<Book> hasPages(Integer pages) {
        return (root, query, builder) ->
                builder.equal(root.get("pages"), pages);
    }
}

