package com.project.E_commerce.service;

import com.project.E_commerce.model.Product;
import com.project.E_commerce.notification.ProductStream;
import com.project.E_commerce.repositories.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock ProductRepo productRepo;
    @Mock ProductStream productStream;

    @InjectMocks
    ProductService productService;

    private Product p;

    @BeforeEach
    void setUp() {
        p = new Product();
        p.setId(1L);
        p.setName("Mock Product");
        p.setDescription("desc");
        p.setPrice(10.0);
        p.setStock(5);
    }

    @Test
    void createProduct_savesAndPublishesNotification() {
        when(productRepo.save(any(Product.class))).thenReturn(p);

        Product saved = productService.saveProduct(p);
        assertThat(saved.getName()).isEqualTo("Mock Product");

        verify(productRepo, times(1)).save(any(Product.class));
        verify(productStream, times(1)).publish(anyString());
    }

    @Test
    void updateProduct_existing_updatesFields() {
        when(productRepo.findById(1L)).thenReturn(Optional.of(p));
        when(productRepo.save(any(Product.class))).thenReturn(p);

        Product updated = new Product();
        updated.setName("Updated");
        updated.setDescription("updated desc");
        updated.setPrice(20.0);
        updated.setStock(2);

        Product res = productService.updateProduct(1L, updated);
        assertThat(res.getName()).isEqualTo("Updated");
        verify(productRepo).save(any());
    }
}
