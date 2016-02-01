package com.ohayoyo.gateway.define.core;

import java.io.Serializable;
import java.util.Set;

/**
 * 协议定义
 */
public interface ProtocolDefine extends Serializable {

    /**
     * HTTP协议名称
     */
    String HTTP_NAME = "http";

    /**
     * HTTPS协议名称
     */
    String HTTPS_NAME = "https";

    /**
     * 获取名称
     *
     * @return 返回名称
     */
    String getName();

    /**
     * 设置名称
     *
     * @param name 名称
     * @return 返回协议定义
     */
    ProtocolDefine setName(String name);

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
    ProtocolDefine setOptions(Set<String> options);

    @Override
    boolean equals(Object o);

    @Override
    int hashCode();

}
