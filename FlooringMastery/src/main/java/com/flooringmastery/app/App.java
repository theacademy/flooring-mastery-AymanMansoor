package com.flooringmastery.app;
import com.flooringmastery.controller.FlooringController;
import com.flooringmastery.dao.OrderDao;
import com.flooringmastery.dao.OrderDaoImpl;
import com.flooringmastery.dao.ProductDao;
import com.flooringmastery.dao.ProductDaoImpl;
import com.flooringmastery.dao.TaxDao;
import com.flooringmastery.dao.TaxDaoImpl;
import com.flooringmastery.service.OrderService;
import com.flooringmastery.view.FlooringView;
import com.flooringmastery.view.UserIO;
import com.flooringmastery.view.UserIOImpl;

public class App {
        public static void main(String[] args) {
            // Initialize input/output handler
            UserIO io = new UserIOImpl();
            FlooringView view = new FlooringView(io);

            // Initialize DAOs
            OrderDao orderDao = new OrderDaoImpl();
            ProductDao productDao = new ProductDaoImpl();
            TaxDao taxDao = new TaxDaoImpl();

            // Initialize service layer
            OrderService orderService = new OrderService(orderDao, productDao, taxDao);

            // Initialize controller and start the program
            FlooringController controller = new FlooringController(view, orderService);
            controller.run();
        }
}
