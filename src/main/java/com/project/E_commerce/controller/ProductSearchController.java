package com.project.E_commerce.controller;

import com.project.E_commerce.model.Product;
import com.project.E_commerce.repositories.ProductRepo;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/product/search")
public class ProductSearchController {

    private final ProductRepo productRepo;

    public ProductSearchController(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @GetMapping
    public ResponseEntity<List<Product>> searchByName(@RequestParam String name) {

        String search = name.trim().toLowerCase();

        List<Product> products = productRepo.findAll()
                .stream()
                .filter(p -> {
                    if (p.getName() == null) return false;
                    return p.getName().toLowerCase().contains(search);
                })
                .toList();

        return ResponseEntity.ok(products);
    }



    @GetMapping("/autocomplete")
    public ResponseEntity<List<String>> autoComplete(
            @RequestParam String query
    ) {
        List<String> suggestions = productRepo.findAll()
                .stream()
                .map(Product::getName)
                .filter(name -> name.toLowerCase().startsWith(query.toLowerCase()))
                .limit(10)
                .toList();

        return ResponseEntity.ok(suggestions);
    }
}
