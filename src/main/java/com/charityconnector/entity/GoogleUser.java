package com.charityconnector.entity;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class GoogleUser {
    private String googleIdTokenString;

    public String getGoogleIdTokenString() {
        return googleIdTokenString;
    }

    public void setGoogleIdTokenString(String googleIdTokenString) {
        this.googleIdTokenString = googleIdTokenString;
    }
}
