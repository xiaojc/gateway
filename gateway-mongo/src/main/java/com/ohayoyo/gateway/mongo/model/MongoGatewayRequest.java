package com.ohayoyo.gateway.mongo.model;

import com.ohayoyo.gateway.define.GatewayRequest;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

public class MongoGatewayRequest implements GatewayRequest<MongoGatewayProtocol, MongoGatewayHost, MongoGatewayPath, MongoGatewayQueries, MongoGatewayMethod, MongoGatewayHeaders, MongoGatewayEntity> {

    @Field("protocols")
    private Set<MongoGatewayProtocol> protocols;

    @Field("hosts")
    private Set<MongoGatewayHost> hosts;

    @Field("path")
    private MongoGatewayPath path;

    @Field("queries")
    private MongoGatewayQueries queries;

    @Field("fragment")
    private String fragment;

    @Field("methods")
    private Set<MongoGatewayMethod> methods;

    @Field("headers")
    private MongoGatewayHeaders headers;

    @Field("entity")
    private MongoGatewayEntity entity;

    @Override
    public Set<MongoGatewayProtocol> getProtocols() {
        return protocols;
    }

    @Override
    public void setProtocols(Set<MongoGatewayProtocol> protocols) {
        this.protocols = protocols;
    }

    @Override
    public Set<MongoGatewayHost> getHosts() {
        return hosts;
    }

    @Override
    public void setHosts(Set<MongoGatewayHost> hosts) {
        this.hosts = hosts;
    }

    @Override
    public MongoGatewayPath getPath() {
        return path;
    }

    @Override
    public void setPath(MongoGatewayPath path) {
        this.path = path;
    }

    @Override
    public MongoGatewayQueries getQueries() {
        return queries;
    }

    @Override
    public void setQueries(MongoGatewayQueries queries) {
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
    public Set<MongoGatewayMethod> getMethods() {
        return methods;
    }

    @Override
    public void setMethods(Set<MongoGatewayMethod> methods) {
        this.methods = methods;
    }

    @Override
    public MongoGatewayHeaders getHeaders() {
        return headers;
    }

    @Override
    public void setHeaders(MongoGatewayHeaders headers) {
        this.headers = headers;
    }

    @Override
    public MongoGatewayEntity getEntity() {
        return entity;
    }

    @Override
    public void setEntity(MongoGatewayEntity entity) {
        this.entity = entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MongoGatewayRequest)) return false;
        MongoGatewayRequest that = (MongoGatewayRequest) o;
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
