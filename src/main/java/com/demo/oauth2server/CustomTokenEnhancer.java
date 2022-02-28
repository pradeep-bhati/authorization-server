package com.demo.oauth2server;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance( OAuth2AccessToken accessToken,  OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<String, Object>();
       // additionalInfo.put("tenantId", authentication.getName());
        additionalInfo.put("tenantId", "1");
        additionalInfo.put("email", "ashimusmani@gmail.com");
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }

}