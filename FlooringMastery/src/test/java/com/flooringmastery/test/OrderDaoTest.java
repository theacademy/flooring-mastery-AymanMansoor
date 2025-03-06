package com.flooringmastery.test;
import com.flooringmastery.dao.OrderDao;
import com.flooringmastery.dao.OrderDaoImpl;
import com.flooringmastery.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class OrderDaoTest {
    private OrderDao orderDao;

    @BeforeEach
    void setUp() {
        orderDao = new OrderDaoImpl();
    }

    @Test
    void testAddAndGetOrders() {
        Order order = new Order();
        order.setOrderNumber(1);
        order.setCustomerName("Test Customer");
        order.setState("TX");
        order.setTaxRate(new BigDecimal("4.45"));
        order.setProductType("Tile");
        order.setArea(new BigDecimal("100"));
        order.setCostPerSquareFoot(new BigDecimal("3.50"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.15"));

        orderDao.addOrder(order);
        List<Order> orders = orderDao.getOrdersByDate("06012013");
        assertFalse(orders.isEmpty());
        assertEquals(1, orders.get(0).getOrderNumber());
    }
}
