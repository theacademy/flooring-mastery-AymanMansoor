package com.flooringmastery.service;

import com.flooringmastery.dao.OrderDao;
import com.flooringmastery.model.Order;
import java.math.BigDecimal;
import java.util.List;

public class OrderService {
    private final OrderDao orderDao;

    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public List<Order> getOrdersByDate(String date) {
        return orderDao.getOrdersByDate(date);
    }

    public void addOrder(Order order) {
        calculateOrderCosts(order);
        orderDao.addOrder(order);
    }

    public void editOrder(Order order) {
        calculateOrderCosts(order);
        orderDao.editOrder(order);
    }

    public void removeOrder(int orderNumber, String date) {
        orderDao.removeOrder(orderNumber, date);
    }

    private void calculateOrderCosts(Order order) {
        BigDecimal materialCost = order.getArea().multiply(order.getCostPerSquareFoot());
        BigDecimal laborCost = order.getArea().multiply(order.getLaborCostPerSquareFoot());
        BigDecimal tax = (materialCost.add(laborCost)).multiply(order.getTaxRate().divide(new BigDecimal("100")));
        BigDecimal total = materialCost.add(laborCost).add(tax);

        order.setMaterialCost(materialCost);
        order.setLaborCost(laborCost);
        order.setTax(tax);
        order.setTotal(total);
    }
}
