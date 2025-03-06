package com.flooringmastery.dao;

import com.flooringmastery.model.Order;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private List<Order> orders = new ArrayList<>();

    @Override
    public List<Order> getOrdersByDate(String date) {
        return new ArrayList<>();
    }

    @Override
    public void addOrder(Order order) {
        orders.add(order);
    }

    @Override
    public void editOrder(Order order) {
        // Logic to update an order (to be implemented)
    }

    @Override
    public void removeOrder(int orderNumber, String date) {
        // Logic to remove an order (to be implemented)
    }

    @Override
    public void saveOrders() {
        // Logic to save orders to a file (to be implemented)
    }
}