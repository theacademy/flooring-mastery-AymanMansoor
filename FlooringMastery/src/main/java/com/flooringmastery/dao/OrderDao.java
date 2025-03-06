package com.flooringmastery.dao;

import com.flooringmastery.model.Order;
import java.util.List;

public interface OrderDao {
    List<Order> getOrdersByDate(String date);

    void addOrder(Order order);

    void editOrder(Order order);

    void removeOrder(int orderNumber, String date);

    void saveOrders();
}

