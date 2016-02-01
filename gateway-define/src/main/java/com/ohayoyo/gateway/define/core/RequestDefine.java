package com.ohayoyo.gateway.define.core;

import java.io.Serializable;
import java.util.Set;

/**
 * 请求定义
 * <p>
 * 设置参照
 * <ul>
 * <li><a href="https://www.w3.org/TR/url/">标准</a></li>
 * <li><a href="http://baike.baidu.com/link?url=ia3S_WOT5YEHwRI2ZQ4d2_HqqKvAeNZHKmTskZBn3GlbNBMr2fAv9ZCjJfH3ZVfWrQnC9XiF4GN4NOG5rjlDca">百度</a></li>
 * </ul>
 * </p>
 */
public interface RequestDefine extends Serializable {

    /**
     * 获取协议定义集合
     *
     * @return 返回协议定义集合
     */
    Set<ProtocolDefine> getProtocols();

    /**
     * 设置协议定义集合
     *
     * @param protocols 协议定义集合
     * @return 返回请求定义
     */
    RequestDefine setProtocols(Set<ProtocolDefine> protocols);

    /**
     * 获取用户定义集合
     * <p>
     * 暂时不支持
     * </p>
     *
     * @return 返回用户定义集合
     */
    @Deprecated
    Set<UserDefine> getUserDefines();

    /**
     * 设置用户定义集合
     *
     * @param userDefines 用户定义集合
     * @return 返回请求定义
     */
    @Deprecated
    RequestDefine setUserDefines(Set<UserDefine> userDefines);

    /**
     * 获取主机定义集合
     *
     * @return 返回主机定义集合
     */
    Set<HostDefine> getHosts();

    /**
     * 设置主机定义集合
     *
     * @param hosts 主机定义集合
     * @return 返回请求定义
     */
    RequestDefine setHosts(Set<HostDefine> hosts);

    /**
     * 获取路径值定义
     *
     * @return 返回路径值定义
     */
    PathDefine getPath();

    /**
     * 设置路径值定义
     *
     * @param path 路径值定义
     * @return 返回请求定义
     */
    RequestDefine setPath(PathDefine path);

    /**
     * 获取参数定义集合
     *
     * @return 参数定义集合
     */
    @Deprecated
    Set<ParameterDefine> getParameters();

    /**
     * 设置参数定义集合
     *
     * @param parameters 参数定义集合
     * @return 返回请求定义
     */
    @Deprecated
    RequestDefine setParameters(Set<ParameterDefine> parameters);

    /**
     * 获取查询定义集合
     *
     * @return 返回查询定义集合
     */
    Set<QueryDefine> getQueries();

    /**
     * 设置查询定义集合
     *
     * @param queries 查询定义集合
     * @return 返回请求定义
     */
    RequestDefine setQueries(Set<QueryDefine> queries);

    /**
     * 获取片段
     *
     * @return 返回片段
     */
    String getFragment();

    /**
     * 设置片段
     *
     * @param fragment 片段
     * @return 返回请求定义
     */
    RequestDefine setFragment(String fragment);

    /**
     * 获取方法定义集合
     *
     * @return 返回方法定义集合
     */
    Set<MethodDefine> getMethods();

    /**
     * 设置方法定义集合
     *
     * @param methods 方法定义集合
     * @return 返回请求定义
     */
    RequestDefine setMethods(Set<MethodDefine> methods);

    /**
     * 获取头定义集合
     *
     * @return 返回头定义集合
     */
    Set<HeaderDefine> getHeaders();

    /**
     * 设置头定义集合
     *
     * @param headers 头定义集合
     * @return 返回请求定义
     */
    RequestDefine setHeaders(Set<HeaderDefine> headers);

    /**
     * 获取实体定义
     *
     * @return 返回实体定义
     */
    EntityDefine getEntity();

    /**
     * 设置实体定义
     *
     * @param entity 实体定义
     * @return 返回请求定义
     */
    RequestDefine setEntity(EntityDefine entity);

}
