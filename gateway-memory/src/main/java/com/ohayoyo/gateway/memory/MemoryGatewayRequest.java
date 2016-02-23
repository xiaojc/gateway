package com.ohayoyo.gateway.memory;

import com.ohayoyo.gateway.define.GatewayRequest;

import java.util.Set;

public class MemoryGatewayRequest implements GatewayRequest<MemoryGatewayProtocol, MemoryGatewayHost, MemoryGatewayPath, MemoryGatewayQueries, MemoryGatewayMethod, MemoryGatewayHeaders, MemoryGatewayEntity> {

    private Set<MemoryGatewayProtocol> protocols;

    private Set<MemoryGatewayHost> hosts;

    private MemoryGatewayPath path;

    private MemoryGatewayQueries queries;

    private String fragment;

    private Set<MemoryGatewayMethod> methods;

    private MemoryGatewayHeaders headers;

    private MemoryGatewayEntity entity;

    @Override
    public Set<MemoryGatewayProtocol> getProtocols() {
        return protocols;
    }

    @Override
    public void setProtocols(Set<MemoryGatewayProtocol> protocols) {
        this.protocols = protocols;
    }

    @Override
    public Set<MemoryGatewayHost> getHosts() {
        return hosts;
    }

    @Override
    public void setHosts(Set<MemoryGatewayHost> hosts) {
        this.hosts = hosts;
    }

    @Override
    public MemoryGatewayPath getPath() {
        return path;
    }

    @Override
    public void setPath(MemoryGatewayPath path) {
        this.path = path;
    }

    @Override
    public MemoryGatewayQueries getQueries() {
        return queries;
    }

    @Override
    public void setQueries(MemoryGatewayQueries queries) {
        this.queries = queries;
    }

    @Override
    public String getFragment() {
        return fragment;
    }

    @Override
    public void setFragment(String fragment) {
        this.fragment = fragment;
    }

    @Override
    public Set<MemoryGatewayMethod> getMethods() {
        return methods;
    }

    @Override
    public void setMethods(Set<MemoryGatewayMethod> methods) {
        this.methods = methods;
    }

    @Override
    public MemoryGatewayHeaders getHeaders() {
        return headers;
    }

    @Override
    public void setHeaders(MemoryGatewayHeaders headers) {
        this.headers = headers;
    }

    @Override
    public MemoryGatewayEntity getEntity() {
        return entity;
    }

    @Override
    public void setEntity(MemoryGatewayEntity entity) {
        this.entity = entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemoryGatewayRequest)) return false;
        MemoryGatewayRequest that = (MemoryGatewayRequest) o;
        if (protocols != null ? !protocols.equals(that.protocols) : that.protocols != null) return false;
        if (hosts != null ? !hosts.equals(that.hosts) : that.hosts != null) return false;
        if (path != null ? !path.equals(that.path) : that.path != null) return false;
        if (queries != null ? !queries.equals(that.queries) : that.queries != null) return false;
        if (fragment != null ? !fragment.equals(that.fragment) : that.fragment != null) return false;
        if (methods != null ? !methods.equals(that.methods) : that.methods != null) return false;
        if (headers != null ? !headers.equals(that.headers) : that.headers != null) return false;
        return entity != null ? entity.equals(that.entity) : that.entity == null;
    }

    @Override
    public int hashCode() {
        int result = protocols != null ? protocols.hashCode() : 0;
        result = 31 * result + (hosts != null ? hosts.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        result = 31 * result + (queries != null ? queries.hashCode() : 0);
        result = 31 * result + (fragment != null ? fragment.hashCode() : 0);
        result = 31 * result + (methods != null ? methods.hashCode() : 0);
        result = 31 * result + (headers != null ? headers.hashCode() : 0);
        result = 31 * result + (entity != null ? entity.hashCode() : 0);
        return result;
    }
}
