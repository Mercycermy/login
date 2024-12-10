package com.login.login.Service;

import com.login.login.model.User;
import com.login.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // Constructor
    public UserService(final UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    // Retrieve all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Retrieve a user by email and password
    public User getUserByEmailAndPassword(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (!bCryptPasswordEncoder.matches(password, user.getPassword())) { // Check if the password matches
                throw new IllegalStateException("Incorrect password for email: " + email);
            }
            return user;
        } else {
            throw new IllegalStateException("No user found with email: " + email);
        }
    }

    // Register a new user
    public void addNewUser(User user) {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());
        if (userOptional.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword())); // Encrypt the password before saving
        userRepository.save(user);
    }

    // Delete a user by email
    public void deleteUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new IllegalStateException("User with email: " + email + " does not exist");
        }
        userRepository.deleteById(userOptional.get().getId());
    }

    // Login a user
    public User loginUser(String email, String password) {
        return getUserByEmailAndPassword(email, password); // Reuse existing method
    }
}
