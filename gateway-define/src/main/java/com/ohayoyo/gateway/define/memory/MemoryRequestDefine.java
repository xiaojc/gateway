package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.*;

import java.util.Set;

/**
 * 内存请求定义
 */
public class MemoryRequestDefine implements RequestDefine {

    /**
     * 协议定义集合
     */
    private Set<ProtocolDefine> protocols;

    /**
     * 用户定义集合
     */
    @Deprecated
    private Set<UserDefine> userDefines;

    /**
     * 主机定义集合
     */
    private Set<HostDefine> hosts;

    /**
     * 路径值定义
     */
    private PathDefine path;

    /**
     * 参数定义集合
     */
    @Deprecated
    private Set<ParameterDefine> parameters;

    /**
     * 查询定义集合
     */
    private Set<QueryDefine> queries;

    /**
     * 片段
     */
    private String fragment;

    /**
     * 方法定义集合
     */
    private Set<MethodDefine> methods;

    /**
     * 头定义集合
     */
    private Set<HeaderDefine> headers;

    /**
     * 实体定义
     */
    private EntityDefine entity;

    /**
     * 获取协议定义集合
     *
     * @return 返回协议定义集合
     */
    @Override
    public Set<ProtocolDefine> getProtocols() {
        return protocols;
    }

    /**
     * 设置协议定义集合
     *
     * @param protocols 协议定义集合
     * @return 返回请求定义
     */
    @Override
    public MemoryRequestDefine setProtocols(Set<ProtocolDefine> protocols) {
        this.protocols = protocols;
        return this;
    }

    /**
     * 获取用户定义集合
     * <p>
     * 暂时不支持
     * </p>
     *
     * @return 返回用户定义集合
     */
    @Override
    @Deprecated
    public Set<UserDefine> getUserDefines() {
        return userDefines;
    }

    /**
     * 设置用户定义集合
     *
     * @param userDefines 用户定义集合
     * @return 返回请求定义
     */
    @Override
    @Deprecated
    public MemoryRequestDefine setUserDefines(Set<UserDefine> userDefines) {
        this.userDefines = userDefines;
        return this;
    }

    /**
     * 获取主机定义集合
     *
     * @return 返回主机定义集合
     */
    @Override
    public Set<HostDefine> getHosts() {
        return hosts;
    }

    /**
     * 设置主机定义集合
     *
     * @param hosts 主机定义集合
     * @return 返回请求定义
     */
    @Override
    public MemoryRequestDefine setHosts(Set<HostDefine> hosts) {
        this.hosts = hosts;
        return this;
    }

    /**
     * 获取路径值定义
     *
     * @return 返回路径值定义
     */
    @Override
    public PathDefine getPath() {
        return path;
    }

    /**
     * 设置路径值定义
     *
     * @param path 路径值定义
     * @return 返回请求定义
     */
    @Override
    public MemoryRequestDefine setPath(PathDefine path) {
        this.path = path;
        return this;
    }

    /**
     * 获取参数定义集合
     *
     * @return 参数定义集合
     */
    @Override
    @Deprecated
    public Set<ParameterDefine> getParameters() {
        return parameters;
    }

    /**
     * 设置参数定义集合
     *
     * @param parameters 参数定义集合
     * @return 返回请求定义
     */
    @Override
    @Deprecated
    public MemoryRequestDefine setParameters(Set<ParameterDefine> parameters) {
        this.parameters = parameters;
        return this;
    }

    /**
     * 获取查询定义集合
     *
     * @return 返回查询定义集合
     */
    @Override
    public Set<QueryDefine> getQueries() {
        return queries;
    }

    /**
     * 设置查询定义集合
     *
     * @param queries 查询定义集合
     * @return 返回请求定义
     */
    @Override
    public MemoryRequestDefine setQueries(Set<QueryDefine> queries) {
        this.queries = queries;
        return this;
    }

    /**
     * 获取片段
     *
     * @return 返回片段
     */
    @Override
    public String getFragment() {
        return fragment;
    }

    /**
     * 设置片段
     *
     * @param fragment 片段
     * @return 返回请求定义
     */
    @Override
    public MemoryRequestDefine setFragment(String fragment) {
        this.fragment = fragment;
        return this;
    }

    /**
     * 获取方法定义集合
     *
     * @return 返回方法定义集合
     */
    @Override
    public Set<MethodDefine> getMethods() {
        return methods;
    }

    /**
     * 设置方法定义集合
     *
     * @param methods 方法定义集合
     * @return 返回请求定义
     */
    @Override
    public MemoryRequestDefine setMethods(Set<MethodDefine> methods) {
        this.methods = methods;
        return this;
    }

    /**
     * 获取头定义集合
     *
     * @return 返回头定义集合
     */
    @Override
    public Set<HeaderDefine> getHeaders() {
        return headers;
    }

    /**
     * 设置头定义集合
     *
     * @param headers 头定义集合
     * @return 返回请求定义
     */
    @Override
    public MemoryRequestDefine setHeaders(Set<HeaderDefine> headers) {
        this.headers = headers;
        return this;
    }

    /**
     * 获取实体定义
     *
     * @return 返回实体定义
     */
    @Override
    public EntityDefine getEntity() {
        return entity;
    }

    /**
     * 设置实体定义
     *
     * @param entity 实体定义
     * @return 返回请求定义
     */
    @Override
    public MemoryRequestDefine setEntity(EntityDefine entity) {
        this.entity = entity;
        return this;
    }

}
