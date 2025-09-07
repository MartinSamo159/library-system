package com.library.library_system;

import org.springframework.data.jpa.repository.JpaRepository;

//Repository pro výpůjčky, crud a kontroly

public interface LoanRepository extends JpaRepository<Loan, Long> {
    boolean existsByBookAndReturnDateIsNull(Book book);
    boolean existsByBook(Book book);
    boolean existsByUser(User user);
}

