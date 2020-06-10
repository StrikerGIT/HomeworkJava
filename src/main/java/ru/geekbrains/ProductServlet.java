package ru.geekbrains;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static ru.geekbrains.listener.AppBootstrapListener.PRODUCT_REPO;


@WebServlet(name = "ProductServlet", urlPatterns = {"", "/"})
public class ProductServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(ProductServlet.class);

    private ProductRepository repository;

    @Override
    public void init() throws ServletException {
        repository = (ProductRepository) getServletContext().getAttribute(PRODUCT_REPO);
        if (repository == null) {
            throw new ServletException("Product repository not initialized");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Index product page");

        if (req.getServletPath().equals("/")) {
            try {
                req.setAttribute("products", repository.findAll());
                getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
            } catch (SQLException ex) {
                throw new IllegalStateException(ex);
            }
        } else if (req.getServletPath().equals("/new")) {
            req.setAttribute("product", new Product());
            getServletContext().getRequestDispatcher("/WEB-INF/views/product.jsp").forward(req, resp);
        } else if (req.getServletPath().equals("/edit")) {
            getServletContext().getRequestDispatcher("/WEB-INF/views/product.jsp").forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            repository.insert(new Product(-1L,
                    req.getParameter("title"),
                    Integer.parseInt(req.getParameter("coast"))));
            resp.sendRedirect(getServletContext().getContextPath());
        } catch (SQLException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
