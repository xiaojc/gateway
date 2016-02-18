package com.ohayoyo.gateway.define.memory.builder;

import com.ohayoyo.gateway.define.builder.*;
import com.ohayoyo.gateway.define.http.*;
import com.ohayoyo.gateway.define.memory.http.MemoryRequestDefine;

import java.util.Set;

/**
 * @author 蓝明乐
 */
public class MemoryRequestDefineBuilder extends RequestDefineBuilder {

    protected MemoryRequestDefineBuilder(InterfaceDefineBuilder interfaceDefineBuilder) {
        super(interfaceDefineBuilder);
    }

    @Override
    protected RequestDefine buildDetails(Set<ProtocolDefine> protocols, Set<HostDefine> hosts, PathDefine path, QueriesDefine queries, String fragment, Set<MethodDefine> methods, HeadersDefine headers, EntityDefine entity) {
        return new MemoryRequestDefine()
                .setProtocols(protocols)
                .setHosts(hosts)
                .setPath(path)
                .setQueries(queries)
                .setFragment(fragment)
                .setMethods(methods)
                .setHeaders(headers)
                .setEntity(entity);
    }

    @Override
    protected EntityDefineBuilder<RequestDefineBuilder> entityBuilder() {
        return new MemoryEntityDefineBuilder<RequestDefineBuilder>(this);
    }

    @Override
    protected HeadersDefineBuilder<RequestDefineBuilder> headersBuilder() {
        return new MemoryHeadersDefineBuilder<RequestDefineBuilder>(this);
    }

    @Override
    protected MethodDefineBuilder methodBuilder() {
        return new MemoryMethodDefineBuilder(this);
    }

    @Override
    protected QueriesDefineBuilder queriesBuilder() {
        return new MemoryQueriesDefineBuilder(this);
    }

    @Override
    protected PathDefineBuilder pathBuilder() {
        return new MemoryPathDefineBuilder(this);
    }

    @Override
    protected HostDefineBuilder hostBuilder() {
        return new MemoryHostDefineBuilder(this);
    }

    @Override
    protected ProtocolDefineBuilder protocolBuilder() {
        return new MemoryProtocolDefineBuilder(this);
    }

}
