package org.interswitch.onboardingservice.RestControllers;


import org.interswitch.onboardingservice.Entities.User;
import org.interswitch.onboardingservice.Repositories.UserRepository;
import org.interswitch.onboardingservice.ServiceImpls.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserRestController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        user.setPassword(passwordService.encodePassword(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    // Optional: add a test endpoint to verify authentication
    @GetMapping("/test")
    public ResponseEntity<String> testAuth() {
        return ResponseEntity.ok("You are authenticated!");
    }
}