package com.ohayoyo.gateway.define.memory.http;

import com.ohayoyo.gateway.define.core.*;

import java.util.Set;

/**
 * @author 蓝明乐
 */
public class MemoryRequestDefine implements RequestDefine {

    private Set<ProtocolDefine> protocols;

    private Set<HostDefine> hosts;

    private PathDefine path;

    private QueriesDefine queries;

    private String fragment;

    private Set<MethodDefine> methods;

    private HeadersDefine headers;

    private EntityDefine entity;

    @Override
    public Set<ProtocolDefine> getProtocols() {
        return protocols;
    }

    @Override
    public MemoryRequestDefine setProtocols(Set<ProtocolDefine> protocols) {
        this.protocols = protocols;
        return this;
    }

    @Override
    public Set<HostDefine> getHosts() {
        return hosts;
    }

    @Override
    public MemoryRequestDefine setHosts(Set<HostDefine> hosts) {
        this.hosts = hosts;
        return this;
    }

    @Override
    public PathDefine getPath() {
        return path;
    }

    @Override
    public MemoryRequestDefine setPath(PathDefine path) {
        this.path = path;
        return this;
    }

    @Override
    public QueriesDefine getQueries() {
        return queries;
    }

    @Override
    public MemoryRequestDefine setQueries(QueriesDefine queries) {
        this.queries = queries;
        return this;
    }

    @Override
    public String getFragment() {
        return fragment;
    }

    @Override
    public MemoryRequestDefine setFragment(String fragment) {
        this.fragment = fragment;
        return this;
    }

    @Override
    public Set<MethodDefine> getMethods() {
        return methods;
    }

    @Override
    public MemoryRequestDefine setMethods(Set<MethodDefine> methods) {
        this.methods = methods;
        return this;
    }

    @Override
    public HeadersDefine getHeaders() {
        return headers;
    }

    @Override
    public MemoryRequestDefine setHeaders(HeadersDefine headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public EntityDefine getEntity() {
        return entity;
    }

    @Override
    public MemoryRequestDefine setEntity(EntityDefine entity) {
        this.entity = entity;
        return this;
    }

}
