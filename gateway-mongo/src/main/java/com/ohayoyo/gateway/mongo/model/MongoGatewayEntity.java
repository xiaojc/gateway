package com.ohayoyo.gateway.mongo.model;

import com.ohayoyo.gateway.define.GatewayEntity;
import org.springframework.data.mongodb.core.mapping.Field;

public class MongoGatewayEntity implements GatewayEntity {

    @Field("type")
    private String type;

    @Field("data")
    private Object data;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public Object getData() {
        return data;
    }

    @Override
    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MongoGatewayEntity)) return false;
        MongoGatewayEntity that = (MongoGatewayEntity) o;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        return data != null ? data.equals(that.data) : that.data == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }
}
