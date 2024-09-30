package com.test.coursespring.services;

import com.test.coursespring.entities.User;
import com.test.coursespring.repositories.UserRepository;
import com.test.coursespring.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public User insert(User obj){
        return repository.save(obj);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public User update(User obj, Long id){
        User user = repository.getReferenceById(id);
        user.setEmail(obj.getEmail());
        user.setName(obj.getName());
        user.setPhone(obj.getPhone());

        return repository.save(user);
    }
}
