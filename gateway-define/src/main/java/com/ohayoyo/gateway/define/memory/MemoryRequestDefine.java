package com.ohayoyo.gateway.define.memory;

import com.ohayoyo.gateway.define.core.*;

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

    public MemoryRequestDefine() {
    }

    public Set<ProtocolDefine> getProtocols() {
        return protocols;
    }

    public MemoryRequestDefine setProtocols(Set<ProtocolDefine> protocols) {
        this.protocols = protocols;
        return this;
    }

    @Deprecated
    public Set<UserDefine> getUserDefines() {
        return userDefines;
    }

    @Deprecated
    public MemoryRequestDefine setUserDefines(Set<UserDefine> userDefines) {
        this.userDefines = userDefines;
        return this;
    }

    public Set<HostDefine> getHosts() {
        return hosts;
    }

    public MemoryRequestDefine setHosts(Set<HostDefine> hosts) {
        this.hosts = hosts;
        return this;
    }

    public PathDefine getPath() {
        return path;
    }

    public MemoryRequestDefine setPath(PathDefine path) {
        this.path = path;
        return this;
    }

    @Deprecated
    public Set<ParameterDefine> getParameters() {
        return parameters;
    }

    @Deprecated
    public MemoryRequestDefine setParameters(Set<ParameterDefine> parameters) {
        this.parameters = parameters;
        return this;
    }

    public Set<QueryDefine> getQueries() {
        return queries;
    }

    public MemoryRequestDefine setQueries(Set<QueryDefine> queries) {
        this.queries = queries;
        return this;
    }

    public String getFragment() {
        return fragment;
    }

    public MemoryRequestDefine setFragment(String fragment) {
        this.fragment = fragment;
        return this;
    }

    public Set<MethodDefine> getMethods() {
        return methods;
    }

    public MemoryRequestDefine setMethods(Set<MethodDefine> methods) {
        this.methods = methods;
        return this;
    }

    public Set<HeaderDefine> getHeaders() {
        return headers;
    }

    public MemoryRequestDefine setHeaders(Set<HeaderDefine> headers) {
        this.headers = headers;
        return this;
    }

    public EntityDefine getEntity() {
        return entity;
    }

    public MemoryRequestDefine setEntity(EntityDefine entity) {
        this.entity = entity;
        return this;
    }

}
