package com.ohayoyo.gateway.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class DDQ implements Serializable {

    @JsonIgnore
    private String service ;

    private String type = "front";

    private String picture = "";

    private String userId = "15814488932";

    private String identityNO = "441622199107020779";

    public String getService() {
        return service;
    }

    public DDQ setService(String service) {
        this.service = service;
        return this;
    }

    public String getType() {
        return type;
    }

    public DDQ setType(String type) {
        this.type = type;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public DDQ setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public DDQ setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getIdentityNO() {
        return identityNO;
    }

    public DDQ setIdentityNO(String identityNO) {
        this.identityNO = identityNO;
        return this;
    }
}
