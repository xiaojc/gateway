package com.ohayoyo.gateway.define;

import java.io.Serializable;
import java.util.Set;

@Deprecated
public interface UserDefine extends Serializable {

    String getUsername();

    UserDefine setUsername(String username);

    String getPassword();

    UserDefine setPassword(String password);

    Set<String> getScopes();

    UserDefine setScopes(Set<String> scopes);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

}
