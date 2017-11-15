package com.charityconnector.controller;

import com.charityconnector.entity.GoogleUser;
import com.google.api.client.extensions.appengine.http.urlfetch.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Controller
@Scope("session")
@RequestMapping("/oauth/google/")
public class OauthGoogleController {

    private static final JacksonFactory jacksonFactory = new JacksonFactory();

    private static final String CLIENT_ID = "373171369886-2a8lv6uajc12cv54e1pdi4eog2kpqd32.apps.googleusercontent.com";

    @Autowired
    GoogleUser user;

    @RequestMapping("/tokenString")
    public ResponseEntity<GoogleIdToken> getGoogleToken(@RequestBody String idTokenString) {
        GoogleIdToken token = verifyGoogleIdToken(idTokenString);
        if (token != null) {
            user.setGoogleIdTokenString(idTokenString);
        }
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    public GoogleIdToken verifyGoogleIdToken(String idTokenString) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new UrlFetchTransport(), jacksonFactory)
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();
        GoogleIdToken idToken = null;

        try {
            idToken = verifier.verify(idTokenString);
        } catch (GeneralSecurityException | IOException e) {
            return idToken;
        }

        if (idToken != null) {
            Payload payload = idToken.getPayload();
        } else {
            return idToken;
        }

        return idToken;
    }
}
