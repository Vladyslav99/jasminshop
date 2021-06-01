package com.andriychuk.demo.service;

import com.andriychuk.demo.entity.CustomUser;
import com.andriychuk.demo.entity.Order;
import com.andriychuk.demo.entity.Product;
import com.andriychuk.demo.enums.OrderStatus;
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
        Order notDoneOrder = orderRepository.findOrderByUserAndOrderStatus(order.getUser(), OrderStatus.CREATED);
        if (notDoneOrder != null) {
            List<Product> list = notDoneOrder.getProductList();
            list.add(order.getProductList().get(0));
            notDoneOrder.setProductList(list);
            notDoneOrder.setAddress(order.getAddress());
            return orderRepository.save(notDoneOrder);
        } else {
            return orderRepository.save(order);
        }
    }

    public Order findByUserAndStatusCreated(CustomUser user) {
        return orderRepository.findOrderByUserAndOrderStatus(user, OrderStatus.CREATED);
    }

    public Order saveCompletely(CustomUser user) {
       Order notDoneOrder = orderRepository.findOrderByUserAndOrderStatus(user, OrderStatus.CREATED);
       notDoneOrder.setOrderStatus(OrderStatus.NOT_DONE);
       notDoneOrder.setAddress(user.getShippingAddress());
       return orderRepository.save(notDoneOrder);
    }

    public void delete(Order order) {
        orderRepository.delete(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findAllByUser(CustomUser user) {
        return orderRepository.findAllByUser(user);
    }
}
