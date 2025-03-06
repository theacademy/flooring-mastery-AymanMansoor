package com.flooringmastery.test;

import com.flooringmastery.dao.ProductDao;
import com.flooringmastery.dao.ProductDaoImpl;
import com.flooringmastery.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ProductDaoTest {
    private ProductDao productDao;

    @BeforeEach
    void setUp() {
        productDao = new ProductDaoImpl();
    }

    @Test
    void testGetAllProducts() {
        List<Product> products = productDao.getAllProducts();
        assertNotNull(products);
        assertFalse(products.isEmpty());
    }

    @Test
    void testGetProductByType() {
        Product product = productDao.getProductByType("Tile");
        assertNotNull(product);
        assertEquals("Tile", product.getProductType());
        assertEquals(new BigDecimal("3.50"), product.getCostPerSquareFoot());
        assertEquals(new BigDecimal("4.15"), product.getLaborCostPerSquareFoot());
    }
}

