package ru.geekbrains.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebListener
public class AppBootstrapListener implements ServletContextListener {

    public static final String DB_CONNECTION = "dbConnection";
    public static final String PRODUCT_REPO = "productRepo";

    private static final Logger logger = LoggerFactory.getLogger(AppBootstrapListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Initializing application");

        ServletContext sc = sce.getServletContext();
        String jdbcConnectionString = sc.getInitParameter("jdbcConnectionString");
        String username = sc.getInitParameter("username");
        String password = sc.getInitParameter("password");

        try {
            Connection conn = DriverManager.getConnection(jdbcConnectionString, username, password);
            sc.setAttribute(DB_CONNECTION, conn);

            ProductRepository productRepository = new ProductRepository(conn);
            sc.setAttribute(PRODUCT_REPO, productRepository);

            if (productRepository.findAll().isEmpty()) {
                productRepository.insert(new Product(-1L, "Product1", 100));
                productRepository.insert(new Product(-1L, "Product2", 200));
                productRepository.insert(new Product(-1L, "Product3", 300));
                productRepository.insert(new Product(-1L, "Product4", 300));
                productRepository.insert(new Product(-1L, "Product5", 300));
                productRepository.insert(new Product(-1L, "Product6", 300));
                productRepository.insert(new Product(-1L, "Product7", 300));
                productRepository.insert(new Product(-1L, "Product8", 300));
                productRepository.insert(new Product(-1L, "Product9", 300));
            }
        } catch (SQLException ex) {
            logger.error("", ex);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        Connection conn = (Connection) sc.getAttribute(DB_CONNECTION);
        try {
            if (conn != null && conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            logger.error("", ex);
        }
    }
}
