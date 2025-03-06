package com.flooringmastery.test;

import com.flooringmastery.dao.OrderDao;
import com.flooringmastery.dao.OrderDaoImpl;
import com.flooringmastery.model.Order;
import com.flooringmastery.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
public class OrderServiceTest {
    private OrderService orderService;
    private OrderDao orderDao;

    @BeforeEach
    void setUp() {
        orderDao = new OrderDaoImpl();
        orderService = new OrderService(orderDao);
    }

    @Test
    void testCalculateOrderCosts() {
        Order order = new Order();
        order.setArea(new BigDecimal("100"));
        order.setCostPerSquareFoot(new BigDecimal("3.50"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.15"));
        order.setTaxRate(new BigDecimal("4.45"));

        orderService.addOrder(order);

        assertNotNull(order.getMaterialCost());
        assertNotNull(order.getLaborCost());
        assertNotNull(order.getTax());
        assertNotNull(order.getTotal());
    }
}

