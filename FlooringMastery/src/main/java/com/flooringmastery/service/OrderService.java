package com.flooringmastery.service;

import com.flooringmastery.dao.OrderDao;
import com.flooringmastery.dao.ProductDao;
import com.flooringmastery.dao.TaxDaoImpl;
import com.flooringmastery.model.Order;
import com.flooringmastery.dao.TaxDao;
import com.flooringmastery.model.Product;
import com.flooringmastery.model.Tax;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class OrderService {
    private final OrderDao orderDao;
    private final ProductDao productDao;
    private final TaxDao taxDao;

    // Updated constructor to accept all three DAOs
    public OrderService(OrderDao orderDao, ProductDao productDao, TaxDao taxDao) {
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.taxDao = taxDao;
    }

   // public Map<String, Tax> getTaxInfo(){
     //   return taxDao.getTaxInfo();}

    public Map <String, Product> getProductInfo(){
     return productDao.getProductInfo();}

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
    public Map<String,Tax> getTaxInfo(){
        return taxDao.getTaxInfo();
    }
}
