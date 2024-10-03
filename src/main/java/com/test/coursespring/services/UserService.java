package com.test.coursespring.services;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.test.coursespring.entities.User;
import com.test.coursespring.repositories.UserRepository;
import com.test.coursespring.services.exceptions.DatabaseException;
import com.test.coursespring.services.exceptions.ResourceNotFoundException;
import com.test.coursespring.services.exceptions.UnauthorizedException;
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
        return obj.orElseThrow(() -> new UnauthorizedException("Dados incorretos"));
    }

    public User insert(User obj) {
        obj.setPassword(EncoderService.getPasswordHash(obj.getPassword()));
        return repository.save(obj);
    }

    public void delete(Long id, User current_user) {
        try {
            if (current_user.getId() != id) {
                throw new UnauthorizedException("Dados incorretos");
            } else {
                repository.deleteById(id);
            }
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        } catch (TokenExpiredException e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }

    public User update(User obj, Long id, User current) {
        try {
            User user = repository.getReferenceById(id);
            if (current.getId() != user.getId()) {
                throw new UnauthorizedException("Dados incorretos");
            }
            user.setEmail(obj.getEmail());
            user.setName(obj.getName());
            user.setPhone(obj.getPhone());
            return repository.save(user);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        } catch (TokenExpiredException e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }
}
