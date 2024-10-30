package com.test.coursespring.resources;

import com.test.coursespring.entities.Order;
import com.test.coursespring.services.OrderService;
import com.test.coursespring.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService service;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<List<Order>> findAll(@RequestHeader("Authorization") String token) {
        List<Order> list = service.findAll(tokenService.getCurrentUser(token));
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        Order obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}
