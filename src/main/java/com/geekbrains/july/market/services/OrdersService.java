package com.geekbrains.july.market.services;

import com.geekbrains.july.market.entities.Order;
import com.geekbrains.july.market.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrdersService {
    private OrdersRepository ordersRepository;

    @Autowired
    public void setOrdersRepository(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    public Optional<Order> findById(Long id) {
        return ordersRepository.findById(id);
    }

    public Order saveOrder(Order order) {
        return ordersRepository.save(order);
    }
}
