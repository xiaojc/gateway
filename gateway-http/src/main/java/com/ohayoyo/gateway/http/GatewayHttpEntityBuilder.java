package com.ohayoyo.gateway.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

/**
 * @author 蓝明乐
 */
public abstract class GatewayHttpEntityBuilder<Body, SubHttpEntity extends HttpEntity<Body>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayHttpEntityBuilder.class);

    private HttpHeaders headers;

    private Body body;

    public GatewayHttpEntityBuilder() {
        this(null, null);
    }

    public GatewayHttpEntityBuilder(HttpHeaders headers) {
        this(headers, null);
    }

    public GatewayHttpEntityBuilder(Body body) {
        this(null, body);
    }

    public GatewayHttpEntityBuilder(HttpHeaders headers, Body body) {
        this.headers = headers;
        this.body = body;
    }

    public GatewayHttpEntityBuilder<Body, SubHttpEntity> headers(Map<String, List<String>> headers) {
        if (!CollectionUtils.isEmpty(headers)) {
            MultiValueMap<String, String> thisHeaders = new LinkedMultiValueMap<String, String>(headers);
            this.headers(thisHeaders);
        }
        return this;
    }

    public GatewayHttpEntityBuilder<Body, SubHttpEntity> headers(MultiValueMap<String, String> headers) {
        if (!CollectionUtils.isEmpty(headers)) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.putAll(headers);
            this.headers(httpHeaders);
        }
        return this;
    }

    public GatewayHttpEntityBuilder<Body, SubHttpEntity> headers(HttpHeaders headers) {
        if (ObjectUtils.isEmpty(this.headers)) {
            this.headers = headers;
        } else if (!ObjectUtils.isEmpty(headers)) {
            this.headers.putAll(headers);
        }
        return this;
    }

    public GatewayHttpEntityBuilder<Body, SubHttpEntity> body(Body body) {
        this.body = body;
        return this;
    }

    protected HttpHeaders getHeaders() {
        return headers;
    }

    protected Body getBody() {
        return body;
    }

    public abstract SubHttpEntity build();

}
