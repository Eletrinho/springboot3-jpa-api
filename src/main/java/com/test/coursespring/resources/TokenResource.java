package com.test.coursespring.resources;

import com.test.coursespring.entities.Token;
import com.test.coursespring.entities.User;
import com.test.coursespring.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/token")
public class TokenResource {

    @Autowired
    private TokenService tokenService;

    @GetMapping
    public ResponseEntity<Token> loginUserAccess(@RequestBody User obj){
        Token token = new Token(tokenService.create_token(obj.getEmail()));
        return ResponseEntity.ok().body(token);
    }
}
