package com.test.coursespring.resources;

import com.test.coursespring.entities.Token;
import com.test.coursespring.entities.User;
import com.test.coursespring.services.TokenService;
import com.test.coursespring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(service.insert(obj));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> deleteById(@PathVariable Long id, @RequestHeader("Authorization") String token) {
        User current_user = tokenService.getCurrentUser(token);
        service.delete(id, current_user);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id,
                                       @RequestBody User obj,
                                       @RequestHeader("Authorization") String token) {
        User current_user = tokenService.getCurrentUser(token);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(service.update(obj, id, current_user));
    }
}
