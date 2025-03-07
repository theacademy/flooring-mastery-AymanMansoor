package com.flooringmastery.view;
import com.flooringmastery.model.Order;
import com.flooringmastery.model.Tax;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FlooringView {
    private final UserIO io;

    // Constructor that accepts UserIO
    public FlooringView(UserIO io) {
        this.io = io;
    }

    public void displayMessage(String message) {
        io.print(message);
    }

    public int printMenuAndGetSelection() {
        io.print("\n<< Flooring Program >>");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Quit");
        return io.readInt("Enter your choice: ");
    }


    public String getOrderDate() {
        return io.readString("Enter order date (MMDDYYYY): ");
    }

    public Order getNewOrderInfo() {

        String customerName = io.readString("Enter customer name: ");
        String state = io.readString("Enter state: ");

        
        String productType = io.readString("Enter product type: ");
        BigDecimal area = new BigDecimal(io.readString("Enter area (sq ft): "));

        Order order = new Order();
        order.setCustomerName(customerName);
        order.setState(state);
        order.setProductType(productType);
        order.setArea(area);

        return order;
    }

    public void displayOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            io.print("No orders found.");
        } else {
            for (Order order : orders) {
                io.print(order.toString());
            }
        }
    }

    public Order getEditOrderInfo() {
        io.print("Editing an order:");
        return getNewOrderInfo(); // Reuse getNewOrderInfo() for input
    }

    public int getOrderNumber() {
        return io.readInt("Enter order number: ");
    }


}
