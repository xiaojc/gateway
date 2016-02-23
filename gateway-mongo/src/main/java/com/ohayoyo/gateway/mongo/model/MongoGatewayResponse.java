package com.ohayoyo.gateway.mongo.model;

import com.ohayoyo.gateway.define.GatewayResponse;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

public class MongoGatewayResponse implements GatewayResponse<MongoGatewayStatus, MongoGatewayHeaders, MongoGatewayEntity> {

    @Field("statuses")
    private Set<MongoGatewayStatus> statuses;

    @Field("headers")
    private MongoGatewayHeaders headers;

    @Field("entity")
    private MongoGatewayEntity entity;

    @Override
    public Set<MongoGatewayStatus> getStatuses() {
        return statuses;
    }

    @Override
    public void setStatuses(Set<MongoGatewayStatus> statuses) {
        this.statuses = statuses;
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
        if (!(o instanceof MongoGatewayResponse)) return false;
        MongoGatewayResponse that = (MongoGatewayResponse) o;
        if (statuses != null ? !statuses.equals(that.statuses) : that.statuses != null) return false;
        if (headers != null ? !headers.equals(that.headers) : that.headers != null) return false;
        return entity != null ? entity.equals(that.entity) : that.entity == null;
    }

    @Override
    public int hashCode() {
        int result = statuses != null ? statuses.hashCode() : 0;
        result = 31 * result + (headers != null ? headers.hashCode() : 0);
        result = 31 * result + (entity != null ? entity.hashCode() : 0);
        return result;
    }

}
