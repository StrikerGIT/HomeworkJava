package com.geekbrains.july.market.controllers;

import com.geekbrains.july.market.beans.Cart;
import com.geekbrains.july.market.entities.Order;
import com.geekbrains.july.market.entities.Product;
import com.geekbrains.july.market.entities.User;
import com.geekbrains.july.market.entities.dtos.OrderItemDto;
import com.geekbrains.july.market.exceptions.ResourceNotFoundException;
import com.geekbrains.july.market.services.OrderItemService;
import com.geekbrains.july.market.services.OrdersService;
import com.geekbrains.july.market.services.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrdersController {
    private UsersService usersService;
    private OrdersService ordersService;
    private OrderItemService orderItemService;
    private Cart cart;

    @PostMapping("/confirm")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> confirmOrder(Principal principal, @RequestParam String address) {
        User user = usersService.findByPhone(principal.getName()).get();
        Order order = new Order(user, cart, user.getPhone(), address);
        order = ordersService.saveOrder(order);
        return new ResponseEntity<> (order.getId(), HttpStatus.OK); //order.getId();
    }

    @GetMapping("/find/{orderId}")
    public List<OrderItemDto>findById(@PathVariable Long orderId) {
        Order order = ordersService.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Unable to find order (id = " + orderId + " ). Order not found"));
        return orderItemService.mapEntityListToDtoList(order.getItems());
    }
}
