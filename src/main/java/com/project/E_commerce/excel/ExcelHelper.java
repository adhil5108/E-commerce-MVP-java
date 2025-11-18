package com.project.E_commerce.excel;

import com.project.E_commerce.model.Product;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

public class ExcelHelper {

    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static boolean isExcelFile(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }


    public static ByteArrayInputStream productsToExcel(List<Product> products) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Products");

            Row headerRow = sheet.createRow(0);

            String[] headers = {"ID", "Name", "Price", "Stock", "Description"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            int rowIdx = 1;
            for (Product product : products) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(product.getId());
                row.createCell(1).setCellValue(product.getName());
                row.createCell(2).setCellValue(product.getPrice());
                row.createCell(3).setCellValue(product.getStock());
                row.createCell(4).setCellValue(product.getDescription());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());

        } catch (IOException e) {
            throw new RuntimeException("Error while exporting Excel file: " + e.getMessage());
        }
    }


    public static List<Product> excelToProducts(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet("Products");

            List<Product> productList = new ArrayList<>();

            int rowNumber = 0;
            for (Row row : sheet) {
                if (rowNumber == 0) { rowNumber++; continue; }
                Product product = new Product();

                if (row.getCell(1) == null || row.getCell(1).getStringCellValue().trim().isEmpty())
                    throw new RuntimeException("Product name is required at row " + rowNumber);

                product.setName(row.getCell(1).getStringCellValue());
                product.setPrice(row.getCell(2).getNumericCellValue());
                product.setStock((int) row.getCell(3).getNumericCellValue());
                product.setDescription(row.getCell(4).getStringCellValue());

                productList.add(product);
                rowNumber++;
            }

            workbook.close();
            return productList;

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }
    }
}
