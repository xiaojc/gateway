package com.ohayoyo.gateway.mongo.core;

import com.ohayoyo.gateway.define.core.GatewayObject;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

public class MongoGatewayObject implements GatewayObject<MongoGatewayField> {

    @Field("fields")
    private Set<MongoGatewayField> fields;

    @Override
    public Set<MongoGatewayField> getFields() {
        return fields;
    }

    @Override
    public void setFields(Set<MongoGatewayField> fields) {
        this.fields = fields;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MongoGatewayObject)) return false;
        MongoGatewayObject that = (MongoGatewayObject) o;
        return fields != null ? fields.equals(that.fields) : that.fields == null;
    }

    @Override
    public int hashCode() {
        return fields != null ? fields.hashCode() : 0;
    }

}
