package com.charityconnector.auth;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DonorAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        OAuth2Authentication oauthAuthentication = (OAuth2Authentication) authentication;
        MyOAuth2AuthenticationDetails ud = new MyOAuth2AuthenticationDetails(request);
        ud.setCharity(false);

        oauthAuthentication.setDetails(ud);

        // redirect to previous page
        response.sendRedirect("/");
    }
}