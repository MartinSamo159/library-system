package com.library.library_system;

import org.springframework.data.jpa.repository.JpaRepository;

//Repository pro uživatele a CRUD operace

public interface UserRepository extends JpaRepository<User, Long> {
}
