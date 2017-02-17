package com.imperialm.imiservices.security;

import com.imperialm.imiservices.dto.UserDetailsImpl;
import com.imperialm.imiservices.entities.User;

public final class JwtUserFactory {

    private JwtUserFactory() {
    }

    public static UserDetailsImpl create(User user) {
        return new UserDetailsImpl(user);
    }
}
