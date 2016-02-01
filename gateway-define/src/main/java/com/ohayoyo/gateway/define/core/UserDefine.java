package com.ohayoyo.gateway.define.core;

import java.io.Serializable;
import java.util.Set;

/**
 * 用户定义
 * <p>
 * 暂时不支持
 * </p>
 */
@Deprecated
public interface UserDefine extends Serializable {

    /**
     * 获取用户名
     *
     * @return 返回用户名
     */
    String getUsername();

    /**
     * 设置用户名
     *
     * @param username 用户名
     * @return 返回用户定义
     */
    UserDefine setUsername(String username);

    /**
     * 获取密码
     *
     * @return 返回密码
     */
    String getPassword();

    /**
     * 设置密码
     *
     * @param password 密码
     * @return 返回用户定义
     */
    UserDefine setPassword(String password);

    /**
     * 获取可选作用域定义集合
     *
     * @return 返回可选作用域定义集合
     */
    Set<String> getOptions();

    /**
     * 设置可选作用域定义集合
     *
     * @param options 可选作用域定义集合
     * @return 返回协议定义
     */
    UserDefine setOptions(Set<String> options);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

}
