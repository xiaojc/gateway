package com.ohayoyo.gateway.define;

import java.io.Serializable;
import java.util.Map;

/**
 * 引用定义
 */
public interface ReferenceDefine extends Serializable {

    /**
     * 获取引用
     *
     * @return 返回引用
     */
    String getReference();

    /**
     * 设置引用
     *
     * @param reference 引用
     * @return 返回引用定义
     */
    ReferenceDefine setReference(String reference);

    /**
     * 获取映射集合
     *
     * @return 返回映射集合
     */
    Map<String, Object> getMapping();

    /**
     * 设置映射集合
     *
     * @param mapping 映射集合
     * @return 返回引用定义
     */
    ReferenceDefine setMapping(Map<String, Object> mapping);

}
