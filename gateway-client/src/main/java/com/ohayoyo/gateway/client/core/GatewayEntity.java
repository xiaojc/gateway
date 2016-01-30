package com.ohayoyo.gateway.client.core;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

public interface GatewayEntity extends GatewayScope, Serializable {

    HttpStatus getHttpStatus();

    HttpHeaders getHttpHeaders();

    Object getHttpData();

}
