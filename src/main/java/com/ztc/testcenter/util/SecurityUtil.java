package com.ztc.testcenter.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

/**
 * Created by yubar on 10/1/17.
 */
public class SecurityUtil {

    public static String getUsername(Authentication authentication) {
        return (String) ((OAuth2Authentication)authentication).getUserAuthentication().getPrincipal();
    }

}
