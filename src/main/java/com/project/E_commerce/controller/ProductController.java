package com.project.E_commerce.controller;

import com.project.E_commerce.dto.ProductDTO;
import com.project.E_commerce.model.Product;
import com.project.E_commerce.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/v1/products")

public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> get(){
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getbyid(@PathVariable Long id ){
        return productService.getProductById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Product post(@Valid @RequestBody ProductDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());

        return productService.saveProduct(product);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Product put(@PathVariable Long id,@Valid @RequestBody ProductDTO productDTO){
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStock(productDTO.getStock());

        return productService.updateProduct(id, product);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        productService.deleteProduct(id);
        return "product with id : "+id+" deleted succesfully";
    }

}
