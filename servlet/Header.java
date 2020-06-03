
package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Header", urlPatterns = "/header")
public class Header extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>" + req.getAttribute("headerText") +  "</h1>");
        resp.getWriter().println("<p></p>");

        resp.getWriter().println("<a href=\"http://127.0.0.1:8080/first-lesson/main\">Main</a>");
        resp.getWriter().println("<a href=\"http://127.0.0.1:8080/first-lesson/catalog\">Catalog</a>");
        resp.getWriter().println("<a href=\"http://127.0.0.1:8080/first-lesson/order\">Order</a>");
        resp.getWriter().println("<a href=\"http://127.0.0.1:8080/first-lesson/cart\">Cart</a>");
        resp.getWriter().println("<a href=\"http://127.0.0.1:8080/first-lesson/product\">Product</a>");

    }
}
