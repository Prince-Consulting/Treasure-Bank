package com.princesoft.auth_internal.impl;

import com.princesoft.auth_api.api.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public boolean authenticate(String username, String password) {
        return false;
    }

}
