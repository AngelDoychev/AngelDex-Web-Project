package com.example.angeldex.security;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class CurrentUser {
    private static final String ANONYMOUS = "anonymous";
    private String username = ANONYMOUS;
    private boolean isAnonymous = true;
    public CurrentUser() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        if (anonymous){
            this.username = ANONYMOUS;
        }
        this.isAnonymous = anonymous;
    }

}
