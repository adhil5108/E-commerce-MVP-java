package com.project.E_commerce.service;

import com.project.E_commerce.excel.ExcelHelper;
import com.project.E_commerce.model.Product;
import com.project.E_commerce.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private ProductRepo productRepository;

    public ByteArrayInputStream exportProducts() {
        List<Product> products = productRepository.findAll();
        return ExcelHelper.productsToExcel(products);
    }

    public void importProducts(MultipartFile file) {

        if (!ExcelHelper.isExcelFile(file)) {
            throw new RuntimeException("Invalid file format. Please upload an Excel (.xlsx) file.");
        }

        try {
            List<Product> products = ExcelHelper.excelToProducts(file.getInputStream());
            productRepository.saveAll(products);

        } catch (IOException e) {
            throw new RuntimeException("Failed to import Excel file: " + e.getMessage());
        }
    }
}

