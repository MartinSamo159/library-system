package com.library.library_system;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;
import java.util.List;

//REST API pro uživatele, seznam, přídání a mazání

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final LoanRepository loanRepository;


    public UserController(UserRepository userRepository, LoanRepository loanRepository) {
        this.userRepository = userRepository;
        this.loanRepository = loanRepository;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User addUser(@Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Uživatel s ID " + id + " nebyl nalezen."));

        boolean hasLoans = loanRepository.existsByUser(user);
        if (hasLoans) {
            throw new RuntimeException("Uživatel nelze smazat, protože má záznamy ve výpůjčkách.");
        }

        userRepository.delete(user);
    }
}
