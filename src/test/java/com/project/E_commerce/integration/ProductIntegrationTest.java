package com.project.E_commerce.integration;

import com.project.E_commerce.ECommerceApplication;
import com.project.E_commerce.model.Product;
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
class ProductIntegrationTest {

    @Autowired private UserRepository userRepository;
    @Autowired private TestRestTemplate restTemplate;

    @BeforeEach
    void seedAdmin() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(new BCryptPasswordEncoder().encode("admin123"));
            admin.setRole("ADMIN");
            userRepository.save(admin);
        }
    }

    @Test
    void fullProductCrud_withAuth() {


        var login = Map.of("username","admin","password","admin123");
        ResponseEntity<Map> loginResp = restTemplate.postForEntity("/api/auth/login", login, Map.class);
        assertThat(loginResp.getStatusCode()).isEqualTo(HttpStatus.OK);

        String token = (String) loginResp.getBody().get("token");

        Product p = new Product();
        p.setName("IT Product");
        p.setDescription("it desc");
        p.setPrice(123.0);
        p.setStock(10);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<Product> createResp =
                restTemplate.postForEntity("/api/v1/products", new HttpEntity<>(p, headers), Product.class);

        assertThat(createResp.getStatusCode()).isEqualTo(HttpStatus.OK);
        Product created = createResp.getBody();
        assertThat(created).isNotNull();

        ResponseEntity<Product> getResp =
                restTemplate.exchange("/api/v1/products/" + created.getId(),
                        HttpMethod.GET, new HttpEntity<>(headers), Product.class);

        assertThat(getResp.getStatusCode()).isEqualTo(HttpStatus.OK);


        ResponseEntity<String> deleteResp =
                restTemplate.exchange("/api/v1/products/" + created.getId(),
                        HttpMethod.DELETE, new HttpEntity<>(headers), String.class);

        assertThat(deleteResp.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
