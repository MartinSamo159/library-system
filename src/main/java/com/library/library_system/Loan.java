package com.library.library_system;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

//Entita pro vypůjčku knihy. Vazba uživatel - kniha, datum půjčení a vrácení

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @NotNull
    private LocalDate loanDate;

    private LocalDate returnDate;

    public Loan() {}

    public Loan(User user, Book book, LocalDate loanDate) {
        this.user = user;
        this.book = book;
        this.loanDate = loanDate;
    }

    public Long getId() { return id; }
    public User getUser() { return user; }
    public Book getBook() { return book; }
    public LocalDate getLoanDate() { return loanDate; }
    public LocalDate getReturnDate() { return returnDate; }

    public void setUser(User user) { this.user = user; }
    public void setBook(Book book) { this.book = book; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
}
