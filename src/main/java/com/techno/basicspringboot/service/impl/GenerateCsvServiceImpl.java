package com.techno.basicspringboot.service.impl;

import com.opencsv.CSVWriter;
import com.techno.basicspringboot.entity.Product;
import com.techno.basicspringboot.repository.ProductRepository;
import com.techno.basicspringboot.service.GenerateCsvService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenerateCsvServiceImpl implements GenerateCsvService {

    @Autowired
    private final ProductRepository productRepository;

    @Override
    public String generateCsvFromEntity() throws IOException {
        try {
            List<Product> products = productRepository.findAll();

            StringWriter csv = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(csv);

            String[] header = {"ID", "Product Name", "Price", "Quantity"};
            csvWriter.writeNext(header);

            for (Product product : products) {
                String[] data = {product.getId().toString(), product.getName(), product.getPrice().toString(), product.getQuantity().toString()};
                csvWriter.writeNext(data);
            }

            csvWriter.close();

            String csvContent = csv.toString();
            return Base64.getEncoder().encodeToString(csvContent.getBytes());

        } catch (Exception e) {
            return "Error while generating csv";
        }

    }
}
