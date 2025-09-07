package com.library.library_system;

import jakarta.validation.Valid;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

// REST API pro knihy - seznam, vyhledávání, přidání, detail, mazání

@RestController
public class BookController {

    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;

    public BookController(BookRepository bookRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.loanRepository = loanRepository;
    }

    @GetMapping("/books")
    public Page<Book> getAllBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer publicationYear,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) Integer pages,
            @PageableDefault(size = 20, sort = "id") Pageable pageable
    ) {
        Specification<Book> spec = Specification.allOf();

        if (title != null) spec = spec.and(BookSpecification.hasTitle(title));
        if (author != null) spec = spec.and(BookSpecification.hasAuthor(author));
        if (isbn != null) spec = spec.and(BookSpecification.hasIsbn(isbn));
        if (genre != null) spec = spec.and(BookSpecification.hasGenre(genre));
        if (publicationYear != null) spec = spec.and(BookSpecification.hasPublicationYear(publicationYear));
        if (pages != null) spec = spec.and(BookSpecification.hasPages(pages));

        return bookRepository.findAll(spec, pageable);
    }


    @PostMapping("/books")
    public Book addBook(@Valid @RequestBody Book book) {
        return bookRepository.save(book);
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new BookNotFoundException(id));
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        if (loanRepository.existsByBook(book)) {
            throw new RuntimeException("Knihu nelze smazat, protože má záznamy ve výpůjčkách.");
        }

        bookRepository.delete(book);
    }
}
