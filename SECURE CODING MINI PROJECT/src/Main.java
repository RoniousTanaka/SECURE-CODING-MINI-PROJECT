package Ronious.tanaka.taka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import javax.persistence.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Main class for the Spring Boot Application
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

// User Model (Entity)
@Entity
class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    // Default constructor
    public User() {}

    // Constructor with fields
    public User(String username, String password, String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

// Repository Interface for User (Data Layer)
@Repository
interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}

// Service Layer for Business Logic
@Service
class UserService {

    @Autowired
    private UserRepository userRepository;

    // Method to check if login is valid
    public Optional<User> login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            return Optional.of(user);
        }
        return Optional.empty();
    }

    // Method to register a user
    public User register(User user) {
        return userRepository.save(user);
    }

    // Method to check if username already exists
    public boolean userExists(String username) {
        return userRepository.findByUsername(username) != null;
    }
}

// REST Controller for handling requests
@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
class UserController {

    @Autowired
    private UserService userService;

    // Endpoint for logging in
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        return userService.login(user.getUsername(), user.getPassword())
                .map(u -> ResponseEntity.ok("Login successful!"))
                .orElse(ResponseEntity.status(401).body("Invalid credentials"));
    }

    // Endpoint for registering
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (userService.userExists(user.getUsername())) {
            return ResponseEntity.status(409).body("Username already exists!");
        }
        userService.register(user);
        return ResponseEntity.ok("User registered successfully!");
    }
}
