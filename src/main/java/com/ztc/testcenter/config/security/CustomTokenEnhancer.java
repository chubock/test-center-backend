package com.ztc.testcenter.config.security;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yubar on 9/30/17.
 */

public class CustomTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        ApplicationUserDetails userDetails = (ApplicationUserDetails) authentication.getUserAuthentication().getPrincipal();
        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put("firstName", userDetails.getUser().getFirstName());
        additionalInfo.put("lastName", userDetails.getUser().getLastName());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }

}
