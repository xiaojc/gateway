package com.ohayoyo.gateway.mongo.model;

import com.ohayoyo.gateway.define.http.InterfaceDefine;
import com.ohayoyo.spring.audit.mongo.model.MongoAudit;

public class MongoInterface extends MongoAudit implements InterfaceDefine {

    private String key;

    private String description;

    private MongoRequest request;

    private MongoResponse response;

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public MongoInterface setKey(String key) {
        this.key = key;
        return this;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public MongoInterface setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public MongoRequest getRequest() {
        return request;
    }

    public MongoInterface setRequest(MongoRequest request) {
        this.request = request;
        return this;
    }

    @Override
    public MongoResponse getResponse() {
        return response;
    }

    public MongoInterface setResponse(MongoResponse response) {
        this.response = response;
        return this;
    }

}
