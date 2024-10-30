package com.test.coursespring.resources;

import com.test.coursespring.entities.Token;
import com.test.coursespring.entities.User;
import com.test.coursespring.services.EncoderService;
import com.test.coursespring.services.TokenService;
import com.test.coursespring.services.UserService;
import com.test.coursespring.services.exceptions.UnauthorizedException;
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

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Token> loginUserAccess(@RequestBody User obj) {
        if (!EncoderService.verifyPassword(obj.getPassword(), userService.findByEmail(obj.getEmail()).getPassword())) {
            throw new UnauthorizedException("Dados de login incorretos");
        }
        Token token = new Token(tokenService.create_token(obj.getEmail()));
        return ResponseEntity.ok().body(token);
    }
}
