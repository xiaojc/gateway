package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.UserDefine;

import java.util.Set;

@Deprecated
public class MemoryUserDefine implements UserDefine {

    private String username;

    private String password;

    private Set<String> options;

    public String getUsername() {
        return username;
    }

    public MemoryUserDefine setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public MemoryUserDefine setPassword(String password) {
        this.password = password;
        return this;
    }

    public Set<String> getOptions() {
        return options;
    }

    public MemoryUserDefine setOptions(Set<String> options) {
        this.options = options;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryUserDefine)) return false;
        MemoryUserDefine that = (MemoryUserDefine) o;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        return password != null ? password.equals(that.password) : that.password == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

}
