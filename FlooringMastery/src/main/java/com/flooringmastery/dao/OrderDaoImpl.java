package com.flooringmastery.dao;

import com.flooringmastery.model.Order;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderDaoImpl implements OrderDao {
    private List<Order> orders = new ArrayList<>();

    @Override
    public List<Order> getOrdersByDate(String date) {
        List<Order> orderList = new ArrayList<>();
        String fileName = "./SampleFileData/Orders/Orders_" + date + ".txt"; // Construct file path

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            if (scanner.hasNextLine()) {
                // scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts.length == 12) {
                    Order order = new Order();
                    order.setOrderNumber(Integer.parseInt(parts[0]));
                    order.setCustomerName(parts[1]);
                    order.setState(parts[2]);
                    order.setTaxRate(new BigDecimal(parts[3]));
                    order.setProductType(parts[4]);
                    order.setArea(new BigDecimal(parts[5]));
                    order.setCostPerSquareFoot(new BigDecimal(parts[6]));
                    order.setLaborCostPerSquareFoot(new BigDecimal(parts[7]));
                    order.setMaterialCost(new BigDecimal(parts[8]));
                    order.setLaborCost(new BigDecimal(parts[9]));
                    order.setTax(new BigDecimal(parts[10]));
                    order.setTotal(new BigDecimal(parts[11]));

                    orderList.add(order);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Order file not found for date: " + date);
        }

        return orderList;
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