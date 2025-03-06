package com.flooringmastery.view;
import com.flooringmastery.model.Order;
import java.util.List;
import java.util.Scanner;

public class FlooringView {
    private final Scanner scanner = new Scanner(System.in);

    public int printMenuAndGetSelection() {
        System.out.println("\n<< Flooring Program >>");
        System.out.println("1. Display Orders");
        System.out.println("2. Add an Order");
        System.out.println("3. Edit an Order");
        System.out.println("4. Remove an Order");
        System.out.println("5. Quit");
        System.out.print("Enter your choice: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public String getOrderDate() {
        System.out.print("Enter order date (MMDDYYYY): ");
        return scanner.nextLine();
    }

    public int getOrderNumber() {
        System.out.print("Enter order number: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public Order getNewOrderInfo() {
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter state: ");
        String state = scanner.nextLine();
        System.out.print("Enter product type: ");
        String productType = scanner.nextLine();
        System.out.print("Enter area (sq ft): ");
        String area = scanner.nextLine();

        Order order = new Order();
        order.setCustomerName(customerName);
        order.setState(state);
        order.setProductType(productType);
        order.setArea(new java.math.BigDecimal(area));
        return order;
    }

    public Order getEditOrderInfo() {
        System.out.println("Editing an order: ");
        return getNewOrderInfo();
    }

    public void displayOrders(List<Order> orders) {
        if (orders.isEmpty()) {
            System.out.println("No orders found for this date.");
        } else {
            for (Order order : orders) {
                System.out.println(order);
            }
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
}
