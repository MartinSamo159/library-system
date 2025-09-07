package com.library.library_system;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDate;
import java.util.List;

// REST API pro seznamn vypůjčených knih, vytvoření výpůjčky a vrácení knihy

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public LoanController(LoanRepository loanRepository, UserRepository userRepository, BookRepository bookRepository) {
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @PostMapping
    public Loan createLoan(@RequestParam Long userId, @RequestParam Long bookId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Uživatel nenalezen"));
        var book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Kniha nenalezena"));
        boolean isBorrowed = loanRepository.existsByBookAndReturnDateIsNull(book);
        if (isBorrowed) {
            throw new RuntimeException("Kniha je aktuálně vypůjčená");
        }

        Loan loan = new Loan(user, book, LocalDate.now());
        return loanRepository.save(loan);
    }

    @PutMapping("/{id}/return")
    public Loan returnLoan(@PathVariable Long id) {
        var loan = loanRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Výpůjčka nenalezena"));

        if (loan.getReturnDate() != null) {
            throw new RuntimeException("Výpůjčka už byla vrácena");
        }

        loan.setReturnDate(LocalDate.now());
        return loanRepository.save(loan);
    }

}

