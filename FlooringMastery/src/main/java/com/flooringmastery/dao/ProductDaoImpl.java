package com.flooringmastery.dao;

import com.flooringmastery.model.Product;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class ProductDaoImpl implements ProductDao {
    private final String PRODUCT_FILE = "Data/Products.txt";
    private final Map<String, Product> products = new HashMap<>();

    public ProductDaoImpl() {
        loadProducts();
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product getProductByType(String productType) {
        return products.get(productType);
    }

    private void loadProducts() {
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String productType = parts[0];
                    BigDecimal costPerSquareFoot = new BigDecimal(parts[1]);
                    BigDecimal laborCostPerSquareFoot = new BigDecimal(parts[2]);

                    Product product = new Product(productType, costPerSquareFoot, laborCostPerSquareFoot);
                    products.put(productType, product);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Product file not found.");
        }
    }
}
