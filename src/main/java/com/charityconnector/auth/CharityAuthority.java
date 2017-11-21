package com.charityconnector.auth;

import org.springframework.security.core.GrantedAuthority;

public class CharityAuthority implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return "ROLE_CHARITY";
    }
}
