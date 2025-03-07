package com.flooringmastery.dao;

import com.flooringmastery.model.Product;
import java.util.List;
import java.util.Map;

public interface ProductDao {
    List<Product> getAllProducts();
    Product getProductByType(String productType);
    Map<String,Product> getProductInfo ();
}

