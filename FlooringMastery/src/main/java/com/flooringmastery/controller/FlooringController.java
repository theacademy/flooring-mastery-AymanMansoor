package com.flooringmastery.controller;
import com.flooringmastery.model.Product;
import com.flooringmastery.model.Tax;
import com.flooringmastery.service.OrderService;
import com.flooringmastery.view.FlooringView;
import com.flooringmastery.model.Order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlooringController {
    private final FlooringView view;
    private final OrderService orderService;
    private Map<String,Tax> taxInfo = new HashMap<>();
    private Map<String, Product> productInfo = new HashMap<>();

    public FlooringController(FlooringView view, OrderService orderService) {
        this.view = view;
        this.orderService = orderService;
    }

    public void run() {
        boolean running = true;

        while (running) {
            int choice = view.printMenuAndGetSelection();
            switch (choice) {
                case 1 : displayOrders();
                break;
                case 2 : addOrder();
                break;
                case 3 : editOrder();
                break;
                case 4 : removeOrder();
                break;
                case 5 : running = false;
                break;
                default : view.displayMessage("Invalid selection. Try again.");

            }
        }
    }

    private void displayOrders() {
        String date = view.getOrderDate();
        List<Order> orders = orderService.getOrdersByDate(date);
        view.displayOrders(orders);
    }

    private void addOrder() {
        Order order = view.getNewOrderInfo();
        Map<String, Tax> taxInfo = orderService.getTaxInfo();
        Tax taxData = taxInfo.get(order.getState());
        order.setTaxRate(taxData.getTaxRate());


    Map<String, Product> productInfo = orderService.getProductInfo();
    Product productData = productInfo.get(order.getProductType());
    order.setCostPerSquareFoot(productData.getCostPerSquareFoot());
    order.setLaborCostPerSquareFoot(productData.getLaborCostPerSquareFoot());

    
        orderService.addOrder(order);
        view.displayMessage("Order added successfully.");
    }

    private void editOrder() {
        Order order = view.getEditOrderInfo();
        orderService.editOrder(order);
        view.displayMessage("Order edited successfully.");
    }

    private void removeOrder() {
        int orderNumber = view.getOrderNumber();
        String date = view.getOrderDate();
        orderService.removeOrder(orderNumber, date);
        view.displayMessage("Order removed successfully.");
    }
}
