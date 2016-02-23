package com.ohayoyo.gateway.mongo.model;

import com.ohayoyo.gateway.define.GatewayStatus;
import org.springframework.data.mongodb.core.mapping.Field;

public class MongoGatewayStatus implements GatewayStatus {

    @Field("code")
    private Integer code;

    @Field("phrase")
    private String phrase;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getPhrase() {
        return phrase;
    }

    @Override
    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MongoGatewayStatus)) return false;
        MongoGatewayStatus that = (MongoGatewayStatus) o;
        return code != null ? code.equals(that.code) : that.code == null;
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }

}
