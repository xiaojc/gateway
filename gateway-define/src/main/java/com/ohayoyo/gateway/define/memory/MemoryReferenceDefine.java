package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.core.ReferenceDefine;

import java.util.Map;

/**
 * 内存引用定义
 */
public class MemoryReferenceDefine implements ReferenceDefine {

    /**
     * 引用
     */
    private String reference;

    /**
     * 映射集合
     */
    private Map<String, Object> mapping;

    /**
     * 获取引用
     *
     * @return 返回引用
     */
    @Override
    public String getReference() {
        return reference;
    }

    /**
     * 设置引用
     *
     * @param reference 引用
     * @return 返回引用定义
     */
    @Override
    public MemoryReferenceDefine setReference(String reference) {
        this.reference = reference;
        return this;
    }

    /**
     * 获取映射集合
     *
     * @return 返回映射集合
     */
    @Override
    public Map<String, Object> getMapping() {
        return mapping;
    }

    /**
     * 设置映射集合
     *
     * @param mapping 映射集合
     * @return 返回引用定义
     */
    @Override
    public MemoryReferenceDefine setMapping(Map<String, Object> mapping) {
        this.mapping = mapping;
        return this;
    }

}
