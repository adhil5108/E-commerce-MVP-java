package com.project.E_commerce.repository;

import com.project.E_commerce.model.Product;
import com.project.E_commerce.repositories.ProductRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepo productRepo;

    @Test
    void whenSave_thenFindById() {
        Product p = new Product();
        p.setName("Test Product");
        p.setDescription("desc");
        p.setPrice(99.0);
        p.setStock(10);
        Product saved = productRepo.save(p);

        Optional<Product> found = productRepo.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getName()).isEqualTo("Test Product");
    }
}
