package com.login.login.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.login.login.Service.UserService;
import com.login.login.model.User;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping(path = "api/v1/users")
@CrossOrigin(origins = "http://localhost:3000")

public class UserController {

    @Autowired
    private final UserService userService;

    @GetMapping("/all")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
public void registerNewUser(@RequestParam("fullname") String fullname,
                             @RequestParam("email") String email,
                             @RequestParam("phone") String phone,
                             @RequestParam("password") String password,
                             @RequestParam("role") String role,
                             @RequestParam("termsAccepted") Boolean termsAccepted) {
    
    // Convert the role string to Role enum (case-insensitive)
    User.Role userRole;
    try {
        userRole = User.Role.valueOf(role.toUpperCase());  // Convert to uppercase for consistency
    } catch (IllegalArgumentException e) {
        throw new IllegalStateException("Invalid role provided: " + role);
    }

    // Create a new User object
    User user = new User();
    user.setFullName(fullname);
    user.setEmail(email);
    user.setPhoneNumber(phone);
    user.setPassword(password);
    user.setRole(userRole);
    user.setTermsAccepted(termsAccepted);

    // Save the user
    userService.addNewUser(user);
}

    
    

    @DeleteMapping("/delete")
    public void deleteUserByEmail(@RequestParam String email) {
        userService.deleteUserByEmail(email);
    }

    @PostMapping("/login")
    public User loginUser(@RequestParam String email, @RequestParam String password) {
        return userService.loginUser(email, password);
    }

    public UserController(final UserService userService) {
        this.userService = userService;
    }
}


