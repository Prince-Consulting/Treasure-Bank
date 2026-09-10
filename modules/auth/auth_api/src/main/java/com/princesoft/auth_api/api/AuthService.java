package com.princesoft.auth_api.api;

public interface AuthService {

    boolean authenticate(String username, String password);

}
