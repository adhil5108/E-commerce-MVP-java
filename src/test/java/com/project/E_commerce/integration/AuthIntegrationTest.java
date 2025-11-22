package com.project.E_commerce.integration;

import com.project.E_commerce.ECommerceApplication;
import com.project.E_commerce.model.User;
import com.project.E_commerce.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = ECommerceApplication.class
)
@ActiveProfiles("test")
class AuthIntegrationTest {

    @Autowired private UserRepository userRepository;
    @Autowired private TestRestTemplate restTemplate;

    @BeforeEach
    void clean() {
        // Clear only test users
        userRepository.deleteAll();
    }

    @Test
    void registerUser_success() {
        // Request body
        Map<String, String> request = Map.of(
                "username", "newuser",
                "password", "newpass123"
        );

        // Expect plain text response -> String.class
        ResponseEntity<String> response =
                restTemplate.postForEntity("/api/auth/register", request, String.class);

        // Assertions
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().toLowerCase()).contains("success");
    }

    @Test
    void loginUser_success() {

        // Seed a test user manually
        User u = new User();
        u.setUsername("loginuser");
        u.setPassword(new BCryptPasswordEncoder().encode("loginpass"));
        u.setRole("USER");
        userRepository.save(u);

        // Login request
        Map<String, String> request = Map.of(
                "username", "loginuser",
                "password", "loginpass"
        );

        // Login returns JSON token → still Map.class
        ResponseEntity<Map> response =
                restTemplate.postForEntity("/api/auth/login", request, Map.class);

        // Assertions
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsKey("token");
        assertThat(response.getBody().get("token")).isNotNull();
    }
}
