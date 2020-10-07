package com.geekbrains.july.market.repositories;

import com.geekbrains.july.market.entities.Order;
import com.geekbrains.july.market.entities.dtos.OrderItemDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends CrudRepository<Order, Long> {
}
