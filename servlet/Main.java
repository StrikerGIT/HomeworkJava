
package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "Main", urlPatterns = "/main/*")
public class Main extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("New GET request");
        req.setAttribute("headerText","Main");
        getServletContext().getRequestDispatcher("/header").include(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("New POST request");
        resp.getWriter().printf("<h1>New POST request</h1>");
    }
}
