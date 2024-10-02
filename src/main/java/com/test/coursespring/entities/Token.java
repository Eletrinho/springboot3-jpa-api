package com.test.coursespring.entities;

import java.io.Serializable;

public class Token implements Serializable {
    private String access_token;
    private String token_type = "Bearer";

    public Token() {
    }

    public Token(String access_token) {
        this.access_token = access_token;
    }

    public Token(String access_token, String token_type) {
        this.access_token = access_token;
        this.token_type = token_type;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }
}
