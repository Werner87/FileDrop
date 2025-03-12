package org.example.filedrop.services;

import org.example.filedrop.models.User;
import org.example.filedrop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(User user) {
        // Sprawdzanie, czy użytkownik już istnieje
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Username already taken");
        }

        // Szyfrowanie hasła
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // Zapis użytkownika do bazy danych
        userRepository.save(user);
    }

    public boolean loginUser(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());

        // Weryfikacja hasła
        if (existingUser != null && passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            return true;
        } else {
            return false;
        }
    }
}

