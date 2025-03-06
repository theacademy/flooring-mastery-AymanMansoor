package com.flooringmastery.dao;

import com.flooringmastery.model.Product;
import java.util.List;

public interface ProductDao {
    List<Product> getAllProducts();
    Product getProductByType(String productType);
}

