package com.andriychuk.demo.repository;

import com.andriychuk.demo.entity.CustomUser;
import com.andriychuk.demo.entity.Order;
import com.andriychuk.demo.enums.OrderStatus;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(CustomUser user);
    Order findOrderByUserAndOrderStatus(CustomUser user, OrderStatus orderStatus);
}
