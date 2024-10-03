package com.test.coursespring.services;

import com.auth0.jwt.HeaderParams;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.test.coursespring.entities.User;
import com.test.coursespring.services.exceptions.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService implements Serializable {

    @Autowired
    private UserService userService;

    public String create_token(String userEmail) {
        try {
            Map<String, Object> head = new HashMap<>();
            head.put(HeaderParams.ALGORITHM, "HS256");
            head.put(HeaderParams.TYPE, "JWT");
            Instant exp = Instant.now().plus(30, ChronoUnit.MINUTES);
            return JWT.create()
                    .withHeader(head)
                    .withClaim("sub", userEmail)
                    .withExpiresAt(exp)
                    .sign(Algorithm.HMAC256("chave"));
        } catch (JWTDecodeException e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }

    public User getCurrentUser(String token) {
        try {
            DecodedJWT decode = JWT.decode(token);
            String email = decode.getClaim("sub").asString();
            Instant expirationTime = decode.getExpiresAt().toInstant();
            if (expirationTime.isBefore(Instant.now())) {
                throw new UnauthorizedException("Token expirado, faça o login novamente");
            }
            return userService.findByEmail(email);
        } catch (JWTDecodeException e) {
            throw new UnauthorizedException(e.getMessage());
        }
    }
}
