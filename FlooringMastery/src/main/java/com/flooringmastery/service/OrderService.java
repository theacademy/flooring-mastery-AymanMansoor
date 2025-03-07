package com.flooringmastery.service;

import com.flooringmastery.dao.OrderDao;
import com.flooringmastery.dao.ProductDao;
import com.flooringmastery.dao.TaxDaoImpl;
import com.flooringmastery.model.Order;
import com.flooringmastery.dao.TaxDao;
import com.flooringmastery.model.Product;
import com.flooringmastery.model.Tax;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

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

    public Order addOrder(Order order) {
        Order newOrder = calculateOrderCosts(order);
        orderDao.addOrder(newOrder);
        return newOrder;
    }

    public void editOrder(Order order) {
        calculateOrderCosts(order);
        orderDao.editOrder(order);
    }

    // new
    public boolean removeOrder(int orderNumber, String date) {
        String fileName = "./SampleFileData/Orders/Orders_" + date + ".txt";
        File orderFile = new File(fileName);

        if (!orderFile.exists()) {
            System.out.println("Error: Order file not found for date " + date);
            return false;
        }

        Map<Integer, Order> updatedOrders = new HashMap<>();
        boolean orderFound = false;

        try (Scanner SDR = new Scanner(new BufferedReader(new FileReader(fileName)))) {
            String line ;

            while ((SDR.hasNextLine())) {
                line = SDR.nextLine();
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

                    updatedOrders.put(Integer.parseInt(parts[0]), order);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file: " + fileName);
            return false;
        }

            updatedOrders.remove(orderNumber);


        //if (!orderFound) {
       //     System.out.println("Order number " + orderNumber + " not found.");
        //    return false;
       // }

        // Rewrite the file with the updated order list
        try (PrintWriter writer = new PrintWriter(new FileWriter(orderFile))) {
            List<Order> orders = new ArrayList<>(updatedOrders.values());
             for (Order orderLine : orders) {
                 //private String order (Order order) {
                 String orderText = orderLine.getOrderNumber() + ",";
                 orderText += orderLine.getCustomerName() + ",";
                 orderText += orderLine.getState() + ",";
                 orderText += orderLine.getTaxRate() + ",";
                 orderText += orderLine.getProductType() + ",";
                 orderText += orderLine.getArea() + ",";
                 orderText += orderLine.getCostPerSquareFoot() + ",";
                 orderText += orderLine.getLaborCostPerSquareFoot() + ",";
                 orderText += orderLine.getMaterialCost() + ",";
                 orderText += orderLine.getLaborCost() + ",";
                 orderText += orderLine.getTax() + ",";
                 orderText += orderLine.getTotal() + ",";

                 writer.println(orderText);
                 writer.flush();

            }


        } catch (IOException e) {
            System.out.println("Error writing to file: " + fileName);
            return false;
        }

        return true; // Order was successfully removed
    }


    // Calculations
   private Order calculateOrderCosts(Order order) {
        BigDecimal materialCost = order.getArea().multiply(order.getCostPerSquareFoot());
        materialCost = materialCost.setScale(2, RoundingMode.HALF_UP);

        BigDecimal laborCost = order.getArea().multiply(order.getLaborCostPerSquareFoot());
       laborCost = laborCost.setScale(2, RoundingMode.HALF_UP);

        BigDecimal tax = (materialCost.add(laborCost)).multiply(order.getTaxRate().divide(new BigDecimal("100")));
       tax = tax.setScale(2, RoundingMode.HALF_UP);

        BigDecimal total = materialCost.add(laborCost).add(tax);
       total = total.setScale(2, RoundingMode.HALF_UP);

        order.setMaterialCost(materialCost);
        order.setLaborCost(laborCost);
        order.setTax(tax);
        order.setTotal(total);

        return order;
    }
    public Map<String,Tax> getTaxInfo(){
        return taxDao.getTaxInfo();
    }
}
