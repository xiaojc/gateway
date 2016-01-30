package com.ohayoyo.gateway.define.core;

import java.io.Serializable;
import java.util.Set;

public interface UserDefine extends Serializable {

    String getUsername();

    UserDefine setUsername(String username);

    String getPassword();

    UserDefine setPassword(String password);

    Set<String> getOptions();

    UserDefine setOptions(Set<String> options);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

}
