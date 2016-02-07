package com.ohayoyo.gateway.http;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

public abstract class AbstractHttpEntityBuilder<Body, SubHttpEntity extends HttpEntity<Body>> {

    private HttpHeaders headers;

    private Body body;

    public AbstractHttpEntityBuilder() {
        this(null, null);
    }

    public AbstractHttpEntityBuilder(HttpHeaders headers) {
        this(headers, null);
    }

    public AbstractHttpEntityBuilder(Body body) {
        this(null, body);
    }

    public AbstractHttpEntityBuilder(HttpHeaders headers, Body body) {
        this.headers = headers;
        this.body = body;
    }

    public AbstractHttpEntityBuilder<Body, SubHttpEntity> headers(Map<String, List<String>> headers) {
        if (!CollectionUtils.isEmpty(headers)) {
            MultiValueMap<String, String> thisHeaders = new LinkedMultiValueMap<String, String>(headers);
            this.headers(thisHeaders);
        }
        return this;
    }

    public AbstractHttpEntityBuilder<Body, SubHttpEntity> headers(MultiValueMap<String, String> headers) {
        if (!CollectionUtils.isEmpty(headers)) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.putAll(headers);
            this.headers(httpHeaders);
        }
        return this;
    }

    public AbstractHttpEntityBuilder<Body, SubHttpEntity> headers(HttpHeaders headers) {
        if (null == this.headers) {
            this.headers = headers;
        } else if (null != headers) {
            this.headers.putAll(headers);
        }
        return this;
    }

    public AbstractHttpEntityBuilder<Body, SubHttpEntity> body(Body body) {
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
