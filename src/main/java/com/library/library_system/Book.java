package com.library.library_system;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

//Validace vstupů a údaje o knize

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{book.title.notblank}")
    private String title;

    @NotBlank(message = "{book.author.notblank}")
    private String author;

    @NotNull(message = "{book.publicationYear.notnull}")
    @Positive(message = "{book.publicationYear.positive}")
    @Max(value = 2025, message = "{book.publicationYear.max}")
    private Integer publicationYear;

    @NotBlank(message = "{book.isbn.notblank}")
    @Pattern(
        regexp = "^(\\d{10}|\\d{13}|[0-9\\-]{13,17})$",
        message = "{book.isbn.invalid}"
    )
    private String isbn;

    private String genre;

    @NotNull(message = "{book.pages.notnull}")
    @Min(value = 1, message = "{book.pages.min}")
    private Integer pages;

    public Book() {}

    public Book(String title, String author, Integer publicationYear, String isbn, String genre, Integer pages) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.genre = genre;
        this.pages = pages;
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public Integer getPublicationYear() { return publicationYear; }
    public String getIsbn() { return isbn; }
    public String getGenre() { return genre; }
    public Integer getPages() { return pages; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPublicationYear(Integer publicationYear) { this.publicationYear = publicationYear; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setGenre(String genre) { this.genre = genre; }
    public void setPages(Integer pages) { this.pages = pages; }
}