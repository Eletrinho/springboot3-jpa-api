package com.test.coursespring.services;

import com.test.coursespring.entities.User;
import com.test.coursespring.repositories.UserRepository;
import com.test.coursespring.services.exceptions.DatabaseException;
import com.test.coursespring.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public User findByEmail(String email) {
        Optional<User> obj = repository.findByEmail(email);
        return obj.orElseThrow(() -> new ResourceNotFoundException(email));
    }

    public User insert(User obj) {
        return repository.save(obj);
    }

    public void delete(Long id, User current_user) {
        try {
            if (current_user.getId() != id) {

            } else {
                repository.deleteById(id);
            }
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(User obj, Long id, User current) {
        try {
            User user = repository.getReferenceById(id);
            if (current.getId() != user.getId()) {
                throw new ResourceNotFoundException(current.getEmail());
            }
            user.setEmail(obj.getEmail());
            user.setName(obj.getName());
            user.setPhone(obj.getPhone());
            return repository.save(user);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}
