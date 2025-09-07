package com.library.library_system;

//Vlastní vyjímka pro knihu která nebyla nalezena

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("Kniha s ID " + id + " nebyla nalezena.");
    }
}
