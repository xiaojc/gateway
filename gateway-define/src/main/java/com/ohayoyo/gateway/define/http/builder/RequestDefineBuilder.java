package com.ohayoyo.gateway.define.http.builder;

import com.ohayoyo.gateway.define.http.*;

import java.util.HashSet;
import java.util.Set;

public abstract class RequestDefineBuilder implements DefineBuilder<RequestDefine> {

    protected Set<ProtocolDefine> protocols;

    protected Set<HostDefine> hosts;

    protected PathDefine path;

    protected QueriesDefine queries;

    protected String fragment;

    protected Set<MethodDefine> methods;

    protected HeadersDefine headers;

    protected EntityDefine entity;

    public RequestDefineBuilder entity(EntityDefine entity) {
        this.entity = entity;
        return this;
    }

    public RequestDefineBuilder headers(HeadersDefine headers) {
        this.headers = headers;
        return this;
    }

    public RequestDefineBuilder methods(Set<MethodDefine> methods) {
        if (BuilderUtil.isEmpty(this.methods)) {
            this.methods = new HashSet<MethodDefine>();
        }
        if (BuilderUtil.isNotEmpty(methods)) {
            this.methods.addAll(methods);
        }
        return this;
    }

    public RequestDefineBuilder method(MethodDefine method) {
        if (BuilderUtil.isEmpty(this.methods)) {
            this.methods = new HashSet<MethodDefine>();
        }
        this.methods.add(method);
        return this;
    }

    public RequestDefineBuilder fragment(String fragment) {
        this.fragment = fragment;
        return this;
    }

    public RequestDefineBuilder queries(QueriesDefine queries) {
        this.queries = queries;
        return this;
    }

    public RequestDefineBuilder path(PathDefine path) {
        this.path = path;
        return this;
    }

    public RequestDefineBuilder hosts(Set<HostDefine> hosts) {
        if (BuilderUtil.isEmpty(this.hosts)) {
            this.hosts = new HashSet<HostDefine>();
        }
        if (BuilderUtil.isNotEmpty(hosts)) {
            this.hosts.addAll(hosts);
        }
        return this;
    }

    public RequestDefineBuilder host(HostDefine host) {
        if (BuilderUtil.isEmpty(this.hosts)) {
            this.hosts = new HashSet<HostDefine>();
        }
        this.hosts.add(host);
        return this;
    }

    public RequestDefineBuilder protocols(Set<ProtocolDefine> protocols) {
        if (BuilderUtil.isEmpty(this.protocols)) {
            this.protocols = new HashSet<ProtocolDefine>();
        }
        if (BuilderUtil.isNotEmpty(protocols)) {
            this.protocols.addAll(protocols);
        }
        return this;
    }

    public RequestDefineBuilder protocol(ProtocolDefine protocol) {
        if (BuilderUtil.isEmpty(this.protocols)) {
            this.protocols = new HashSet<ProtocolDefine>();
        }
        this.protocols.add(protocol);
        return this;
    }

}
