package controller;

import persist.Order;
import persist.OrderRepository;

import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class OrderController implements Serializable {

    @Inject
    OrderRepository orderRepository;

    private Order order;

    private List<Order> orders;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String createOrder() {
        this.order = new Order();
        return "/order.xhtml";
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public String editOrder(Order order) {
        this.order = order;
        return "/order.xhtml";
    }
    public void deleteOrder(Order order) {
        orderRepository.delete(order.getId());
    }

    public String saveOrder() {
        if (order.getId() == null){
            orderRepository.insert(order);
        } else {
            orderRepository.update(order);
        }
        return "/orders.xhtml";
        //?faces-redirect=true
    }

    public void preloadOrders(ComponentSystemEvent cse){
        this.orders = orderRepository.findAll();
    }
}
