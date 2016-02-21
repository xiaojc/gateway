package com.ohayoyo.gateway.mongo.http;

import com.ohayoyo.gateway.define.http.GatewayProtocol;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

public class MongoGatewayProtocol implements GatewayProtocol {

    @Field("name")
    private String name;

    @Field("scopes")
    private Set<String> scopes;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Set<String> getScopes() {
        return scopes;
    }

    @Override
    public void setScopes(Set<String> scopes) {
        this.scopes = scopes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MongoGatewayProtocol)) return false;
        MongoGatewayProtocol that = (MongoGatewayProtocol) o;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
