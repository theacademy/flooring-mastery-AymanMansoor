package com.flooringmastery.controller;
import com.flooringmastery.model.Product;
import com.flooringmastery.model.Tax;
import com.flooringmastery.service.OrderService;
import com.flooringmastery.view.FlooringView;
import com.flooringmastery.model.Order;

import javax.swing.text.View;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
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
        String DateString = view.getOrderDate();
        String fileName = "./SampleFileData/Orders/Orders_" + DateString + ".txt"; // Ensure correct directory

        File orderFile = new File(fileName);

        // If file does not exist, create a new one with a header row
        if (!orderFile.exists()) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(orderFile))) {
                writer.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total");
            } catch (IOException e) {
                view.displayMessage("Error creating new order file: " + fileName);
                return;
            }
        }
        Order order = view.getNewOrderInfo();
        Map<String, Tax> taxInfo = orderService.getTaxInfo();
        //Validate State
        if (!taxInfo.containsKey(order.getState())) {
            view.displayMessage("Invalid state. We do not sell in " + order.getState());
            return;
        }
        Tax taxData = taxInfo.get(order.getState());
        order.setTaxRate(taxData.getTaxRate());


    Map<String, Product> productInfo = orderService.getProductInfo();
        // Validate product type
        if (!productInfo.containsKey(order.getProductType())) {
            view.displayMessage("Invalid product type: " + order.getProductType());
            return;
        }
        Product productData = productInfo.get(order.getProductType());
        order.setCostPerSquareFoot(productData.getCostPerSquareFoot());
        order.setLaborCostPerSquareFoot(productData.getLaborCostPerSquareFoot());

        // Validate area
        // if the input is less than 100
        if (order.getArea().compareTo(new java.math.BigDecimal("100")) < 0) {
            view.displayMessage("Invalid area. Minimum order size is 100 sq ft.");
            return;
        }
        //start new order number at 1
        // Generate the next available order number
        int nextOrderNumber = 1; // Default to 1 if no existing orders
        if (orderFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(orderFile))) {
                String line;
                int maxOrderNumber = 0;
                reader.readLine(); // Skip the header row
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    int orderNum = Integer.parseInt(parts[0]); // Read order number from file
                    if (orderNum > maxOrderNumber) {
                        maxOrderNumber = orderNum;
                    }
                }
                nextOrderNumber = maxOrderNumber + 1; // Increment to get next order number
            } catch (IOException | NumberFormatException e) {
                view.displayMessage("Error reading order file to determine order number.");
            }
        }

        order.setOrderNumber(nextOrderNumber); // Assign the new order number

        order = orderService.addOrder(order);

        // Add the info to the new information to the existing order file
        // If the order file already exists
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(orderFile, true))) {
            writer.newLine(); // Ensure new order starts on a new line
            writer.write(order.getOrderNumber() + "," + order.getCustomerName() + "," + order.getState() + "," +
                    order.getTaxRate() + "," + order.getProductType() + "," + order.getArea() + "," +
                    order.getCostPerSquareFoot() + "," + order.getLaborCostPerSquareFoot() + "," +
                    order.getMaterialCost() + "," + order.getLaborCost() + "," + order.getTax() + "," + order.getTotal());
        } catch (IOException e) {
            view.displayMessage("Error writing order to file: " + fileName);
            return;
        }
    
        //orderService.addOrder(order);
        view.displayMessage("Order added successfully.");
    }

    private void editOrder() {
        Order order = view.getEditOrderInfo();
        orderService.editOrder(order);
        view.displayMessage("Order edited successfully.");
    }

    private void removeOrder() {
        int orderNumber = view.getOrderNumber(); // Get order number from user
        String date = view.getOrderDate(); // Get order date from user

        if (orderNumber <= -1 || date.isEmpty()) {
            view.displayMessage("Invalid input. Please enter a valid order number and date.");
            return;
        }

        

        boolean success = orderService.removeOrder(orderNumber, date); // Call service layer

        if (success) {
            view.displayMessage("Order removed successfully.");
        } else {
            view.displayMessage("Order not found or could not be removed.");
        }
    }


}
