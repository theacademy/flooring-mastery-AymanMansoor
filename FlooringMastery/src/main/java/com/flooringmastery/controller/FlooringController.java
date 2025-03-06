package com.flooringmastery.controller;
import com.flooringmastery.service.OrderService;
import com.flooringmastery.view.FlooringView;
import com.flooringmastery.model.Order;

import java.util.List;

public class FlooringController {
    private final FlooringView view;
    private final OrderService orderService;

    public FlooringController(FlooringView view, OrderService orderService) {
        this.view = view;
        this.orderService = orderService;
    }

    public void run() {
        boolean running = true;
        while (running) {
            int choice = view.printMenuAndGetSelection();
            switch (choice) {
                case 1 -> displayOrders();
                case 2 -> addOrder();
                case 3 -> editOrder();
                case 4 -> removeOrder();
                case 5 -> running = false;
                default -> view.displayMessage("Invalid selection. Try again.");
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
