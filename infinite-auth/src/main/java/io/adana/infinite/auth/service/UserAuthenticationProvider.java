package io.adana.infinite.auth.service;

import io.adana.infinite.user.api.IUserDetailApi;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * @author admin
 * @version 1.0
 * @description
 * @date 2020/10/22 19:02
 */
public class UserAuthenticationProvider implements AuthenticationProvider {
    @Reference(version = "1.0.0")
    private IUserDetailApi iUserDetailApi;


    public UserAuthenticationProvider(IUserDetailApi iUserDetailApi) {
        this.iUserDetailApi = iUserDetailApi;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        if (iUserDetailApi.verifyUser(name, password)) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("user"));

        }
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
