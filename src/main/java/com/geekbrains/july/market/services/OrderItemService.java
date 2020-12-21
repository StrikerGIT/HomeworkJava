package com.geekbrains.july.market.services;

import com.geekbrains.july.market.entities.OrderItem;
import com.geekbrains.july.market.entities.dtos.OrderItemDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderItemService {
    public List<OrderItemDto> mapEntityListToDtoList(List<OrderItem> orderItemList) {
        return orderItemList.stream().map(OrderItemDto::new).collect(Collectors.toList());
    }
}
