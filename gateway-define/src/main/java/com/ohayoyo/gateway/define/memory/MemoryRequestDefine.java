package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.*;

import java.util.Set;

public class MemoryRequestDefine implements RequestDefine {

    private Set<ProtocolDefine> protocols;

    @Deprecated
    private Set<UserDefine> userDefines;

    private Set<HostDefine> hosts;

    private PathDefine path;

    @Deprecated
    private Set<ParameterDefine> parameters;

    private Set<QueryDefine> queries;

    private String fragment;

    private Set<MethodDefine> methods;

    private Set<HeaderDefine> headers;

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
    @Deprecated
    public Set<UserDefine> getUserDefines() {
        return userDefines;
    }

    @Override
    @Deprecated
    public MemoryRequestDefine setUserDefines(Set<UserDefine> userDefines) {
        this.userDefines = userDefines;
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
    @Deprecated
    public Set<ParameterDefine> getParameters() {
        return parameters;
    }

    @Override
    @Deprecated
    public MemoryRequestDefine setParameters(Set<ParameterDefine> parameters) {
        this.parameters = parameters;
        return this;
    }

    @Override
    public Set<QueryDefine> getQueries() {
        return queries;
    }

    @Override
    public MemoryRequestDefine setQueries(Set<QueryDefine> queries) {
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
    public Set<HeaderDefine> getHeaders() {
        return headers;
    }

    @Override
    public MemoryRequestDefine setHeaders(Set<HeaderDefine> headers) {
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
