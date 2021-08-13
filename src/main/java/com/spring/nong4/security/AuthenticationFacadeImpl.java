package com.spring.nong4.security;

import com.spring.nong4.security.model.CustomUserPrincipal;
import com.spring.nong4.user.model.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacadeImpl implements com.spring.nong4.security.IAuthenticationFacade {
    @Override
    public UserEntity getLoginUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomUserPrincipal userDetails = (CustomUserPrincipal)auth.getPrincipal();
        return userDetails.getUser();
    }

    @Override
    public int getLoginUserPk() {
        return getLoginUser().getIuser();
    }
}
