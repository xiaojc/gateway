package com.ohayoyo.gateway.define.builder;

import com.ohayoyo.gateway.define.core.*;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public abstract class RequestDefineBuilder extends AbstractThenDefineBuilder<RequestDefine, InterfaceDefineBuilder> {

    private Set<ProtocolDefine> protocols;

    private Set<HostDefine> hosts;

    private PathDefine path;

    private QueriesDefine queries;

    private String fragment;

    private Set<MethodDefine> methods;

    private HeadersDefine headers;

    private EntityDefine entity;

    private EntityDefineBuilder<RequestDefineBuilder> entityDefineBuilder;

    private HeadersDefineBuilder<RequestDefineBuilder> headersDefineBuilder;

    private Set<MethodDefineBuilder> methodDefineBuilders = new HashSet<MethodDefineBuilder>();

    private QueriesDefineBuilder queriesDefineBuilder;

    private PathDefineBuilder pathDefineBuilder;

    private Set<HostDefineBuilder> hostDefineBuilders = new HashSet<HostDefineBuilder>();

    private Set<ProtocolDefineBuilder> protocolDefineBuilders = new HashSet<ProtocolDefineBuilder>();

    @Override
    public final RequestDefine build() {
        if (!ObjectUtils.isEmpty(entityDefineBuilder)) {
            entity(entityDefineBuilder.build());
        }
        if (!ObjectUtils.isEmpty(headersDefineBuilder)) {
            headers(headersDefineBuilder.build());
        }
        if (!CollectionUtils.isEmpty(methodDefineBuilders)) {
            for (MethodDefineBuilder methodDefineBuilder : methodDefineBuilders) {
                method(methodDefineBuilder.build());
            }
        }
        if (!ObjectUtils.isEmpty(queriesDefineBuilder)) {
            queries(queriesDefineBuilder.build());
        }
        if (!ObjectUtils.isEmpty(pathDefineBuilder)) {
            path(pathDefineBuilder.build());
        }
        if (!CollectionUtils.isEmpty(hostDefineBuilders)) {
            for (HostDefineBuilder hostDefineBuilder : hostDefineBuilders) {
                host(hostDefineBuilder.build());
            }
        }
        if (!CollectionUtils.isEmpty(protocolDefineBuilders)) {
            for (ProtocolDefineBuilder protocolDefineBuilder : protocolDefineBuilders) {
                protocol(protocolDefineBuilder.build());
            }
        }
        return this.buildDetails(protocols, hosts, path, queries, fragment, methods, headers, entity);
    }

    protected abstract RequestDefine buildDetails(Set<ProtocolDefine> protocols, Set<HostDefine> hosts, PathDefine path, QueriesDefine queries, String fragment, Set<MethodDefine> methods, HeadersDefine headers, EntityDefine entity);

    protected RequestDefineBuilder(InterfaceDefineBuilder interfaceDefineBuilder) {
        super(interfaceDefineBuilder);
    }

    public EntityDefineBuilder<RequestDefineBuilder> entity() {
        entityDefineBuilder = this.entityBuilder();
        return entityDefineBuilder;
    }

    protected abstract EntityDefineBuilder<RequestDefineBuilder> entityBuilder();

    public HeadersDefineBuilder<RequestDefineBuilder> headers() {
        headersDefineBuilder = this.headersBuilder();
        return headersDefineBuilder;
    }

    protected abstract HeadersDefineBuilder<RequestDefineBuilder> headersBuilder();

    public MethodDefineBuilder method() {
        MethodDefineBuilder methodDefineBuilder = this.methodBuilder();
        methodDefineBuilders.add(methodDefineBuilder);
        return methodDefineBuilder;
    }

    protected abstract MethodDefineBuilder methodBuilder();

    public QueriesDefineBuilder queries() {
        queriesDefineBuilder = this.queriesBuilder();
        return queriesDefineBuilder;
    }

    protected abstract QueriesDefineBuilder queriesBuilder();

    public PathDefineBuilder path() {
        pathDefineBuilder = this.pathBuilder();
        return pathDefineBuilder;
    }

    protected abstract PathDefineBuilder pathBuilder();

    public HostDefineBuilder host() {
        HostDefineBuilder hostDefineBuilder = this.hostBuilder();
        hostDefineBuilders.add(hostDefineBuilder);
        return hostDefineBuilder;
    }

    protected abstract HostDefineBuilder hostBuilder();

    public ProtocolDefineBuilder protocol() {
        ProtocolDefineBuilder protocolDefineBuilder = this.protocolBuilder();
        this.protocolDefineBuilders.add(protocolDefineBuilder);
        return protocolDefineBuilder;
    }

    protected abstract ProtocolDefineBuilder protocolBuilder();

    public RequestDefineBuilder entity(EntityDefine entity) {
        this.entity = entity;
        return this;
    }

    public RequestDefineBuilder headers(HeadersDefine headers) {
        this.headers = headers;
        return this;
    }

    public RequestDefineBuilder methods(Set<MethodDefine> methods) {
        if (CollectionUtils.isEmpty(this.methods)) {
            this.methods = new HashSet<MethodDefine>();
        }
        if (!CollectionUtils.isEmpty(methods)) {
            this.methods.addAll(methods);
        }
        return this;
    }

    public RequestDefineBuilder method(MethodDefine method) {
        if (CollectionUtils.isEmpty(this.methods)) {
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
        if (CollectionUtils.isEmpty(this.hosts)) {
            this.hosts = new HashSet<HostDefine>();
        }
        if (!CollectionUtils.isEmpty(hosts)) {
            this.hosts.addAll(hosts);
        }
        return this;
    }

    public RequestDefineBuilder host(HostDefine host) {
        if (CollectionUtils.isEmpty(this.hosts)) {
            this.hosts = new HashSet<HostDefine>();
        }
        this.hosts.add(host);
        return this;
    }

    public RequestDefineBuilder protocols(Set<ProtocolDefine> protocols) {
        if (CollectionUtils.isEmpty(this.protocols)) {
            this.protocols = new HashSet<ProtocolDefine>();
        }
        if (!CollectionUtils.isEmpty(protocols)) {
            this.protocols.addAll(protocols);
        }
        return this;
    }

    public RequestDefineBuilder protocol(ProtocolDefine protocol) {
        if (CollectionUtils.isEmpty(this.protocols)) {
            this.protocols = new HashSet<ProtocolDefine>();
        }
        this.protocols.add(protocol);
        return this;
    }

}
