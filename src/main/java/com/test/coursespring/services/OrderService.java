package com.test.coursespring.services;

import com.test.coursespring.entities.Order;
import com.test.coursespring.entities.User;
import com.test.coursespring.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<Order> findAll(User current) {
        List<Order> all = repository.findAll();
        List<Order> res = new ArrayList<>();
        for (Order order : all) {
            if (order.getClient().getId() == current.getId()) {
                res.add(order);
            }
        }
        return res;
    }

    public Order findById(Long id) {
        Optional<Order> obj = repository.findById(id);
        return obj.get();
    }
}
