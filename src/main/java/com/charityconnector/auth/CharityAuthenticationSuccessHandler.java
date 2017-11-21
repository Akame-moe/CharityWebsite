package com.charityconnector.auth;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CharityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CharityAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2Authentication oauthAuthentication = (OAuth2Authentication) authentication;
        MyOAuth2AuthenticationDetails ud = new MyOAuth2AuthenticationDetails(request);
        ud.setCharity(true);

        //search if exist

        //if exist redirect to charity page

        //if not create and redirect request to create charity

        oauthAuthentication.setDetails(ud);
        response.sendRedirect("/");

    }
}