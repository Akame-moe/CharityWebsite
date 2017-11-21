package com.charityconnector.auth;

import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

public class MyOAuth2AuthenticationDetails extends OAuth2AuthenticationDetails {

    private boolean isCharity;
    private int charityId;

    /**
     * Records the access token value and remote address and will also set the session Id if a session already exists
     * (it won't create one).
     *
     * @param request that the authentication request was received from
     */
    public MyOAuth2AuthenticationDetails(HttpServletRequest request) {
        super(request);
    }

    public boolean isCharity() {
        return isCharity;
    }

    public void setCharity(boolean charity) {
        isCharity = charity;
    }

    public int getCharityId() {
        return charityId;
    }

    public void setCharityId(int charityId) {
        this.charityId = charityId;
    }
}
