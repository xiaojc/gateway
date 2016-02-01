package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.core.ProtocolDefine;

import java.util.Set;

/**
 * 内存协议定义
 */
public class MemoryProtocolDefine implements ProtocolDefine {

    /**
     * HTTP协议定义对象
     */
    public static final MemoryProtocolDefine HTTP = new MemoryProtocolDefine().setName(HTTP_NAME);

    /**
     * HTTPS协议定义对象
     */
    public static final MemoryProtocolDefine HTTPS = new MemoryProtocolDefine().setName(HTTPS_NAME);

    /**
     * 名称
     */
    private String name;

    /**
     * 可选作用域定义集合
     */
    private Set<String> options;

    /**
     * 获取名称
     *
     * @return 返回名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     * @return 返回协议定义
     */
    public MemoryProtocolDefine setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 获取可选作用域定义集合
     *
     * @return 返回可选作用域定义集合
     */
    public Set<String> getOptions() {
        return options;
    }

    /**
     * 设置可选作用域定义集合
     *
     * @param options 可选作用域定义集合
     * @return 返回协议定义
     */
    public MemoryProtocolDefine setOptions(Set<String> options) {
        this.options = options;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryProtocolDefine)) return false;
        MemoryProtocolDefine that = (MemoryProtocolDefine) o;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
