package org.example.controller;

import jakarta.servlet.http.HttpSession;
import org.example.model.Credentials;
import org.example.repository.CredentialsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;

@RestController
@CrossOrigin(origins = "https://motorphpayroll.netlify.app/", allowCredentials = "true")
@RequestMapping("/users")
public class UserController {
    @Autowired
    private CredentialsDAO credentialsDAO;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Credentials credentials, HttpSession session) throws SQLException {
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        int id = credentialsDAO.getAuthenticatedID(username, password);

        if (id != -1) {
            session.setAttribute("userId", id);
            return ResponseEntity.ok(Map.of("message", "Login successful", "userId", id));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUserId(HttpSession session) {
        Object userId = session.getAttribute("userId");
        if (userId != null) {
            return ResponseEntity.ok(userId);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
