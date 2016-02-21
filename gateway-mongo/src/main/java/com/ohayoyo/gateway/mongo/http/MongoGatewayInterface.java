package com.ohayoyo.gateway.mongo.http;

import com.ohayoyo.gateway.define.http.GatewayInterface;
import com.ohayoyo.spring.audit.mongo.model.MongoAudit;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "GatewayInterface")
public class MongoGatewayInterface extends MongoAudit implements GatewayInterface<MongoGatewayRequest, MongoGatewayResponse> {

    @Field("key")
    @Indexed(unique = true)
    private String key;

    @Field("request")
    private MongoGatewayRequest request;

    @Field("response")
    private MongoGatewayResponse response;

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public MongoGatewayRequest getRequest() {
        return request;
    }

    @Override
    public void setRequest(MongoGatewayRequest request) {
        this.request = request;
    }

    @Override
    public MongoGatewayResponse getResponse() {
        return response;
    }

    @Override
    public void setResponse(MongoGatewayResponse response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MongoGatewayInterface)) return false;
        MongoGatewayInterface that = (MongoGatewayInterface) o;
        return key != null ? key.equals(that.key) : that.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }
}
