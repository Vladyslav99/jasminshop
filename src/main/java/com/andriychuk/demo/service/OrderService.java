package com.andriychuk.demo.service;

import com.andriychuk.demo.entity.Order;
import com.andriychuk.demo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }
}
