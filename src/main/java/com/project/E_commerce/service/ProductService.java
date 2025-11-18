package com.project.E_commerce.service;

import com.project.E_commerce.model.Product;
import com.project.E_commerce.notification.ProductStream;
import com.project.E_commerce.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepo productRepo;
    private final ProductStream productStream;

    @Autowired
    public ProductService(ProductRepo productRepo, ProductStream productStream) {
        this.productRepo = productRepo;
        this.productStream = productStream;
    }

    public Product saveProduct(Product product) {
        Product saved = productRepo.save(product);


        String message = "New product added: " + saved.getName();
        productStream.publish(message);

        return saved;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProductById(Long id) {
        return productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = getProductById(id);
        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setPrice(updatedProduct.getPrice());
        existing.setStock(updatedProduct.getStock());

        return productRepo.save(existing);
    }

    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }
}
