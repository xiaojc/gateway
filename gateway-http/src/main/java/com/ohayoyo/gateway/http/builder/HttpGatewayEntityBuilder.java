package com.ohayoyo.gateway.http.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

/**
 * @author 蓝明乐
 */
public abstract class HttpGatewayEntityBuilder<Body, SubHttpEntity extends HttpEntity<Body>> {

    public static final Logger LOGGER = LoggerFactory.getLogger(HttpGatewayEntityBuilder.class);

    private HttpHeaders headers;

    private Body body;

    public HttpGatewayEntityBuilder() {
        this(null, null);
    }

    public HttpGatewayEntityBuilder(HttpHeaders headers) {
        this(headers, null);
    }

    public HttpGatewayEntityBuilder(Body body) {
        this(null, body);
    }

    public HttpGatewayEntityBuilder(HttpHeaders headers, Body body) {
        this.headers = headers;
        this.body = body;
    }

    public HttpGatewayEntityBuilder<Body, SubHttpEntity> headers(Map<String, List<String>> headers) {
        if (!CollectionUtils.isEmpty(headers)) {
            MultiValueMap<String, String> thisHeaders = new LinkedMultiValueMap<String, String>(headers);
            this.headers(thisHeaders);
        }
        return this;
    }

    public HttpGatewayEntityBuilder<Body, SubHttpEntity> headers(MultiValueMap<String, String> headers) {
        if (!CollectionUtils.isEmpty(headers)) {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.putAll(headers);
            this.headers(httpHeaders);
        }
        return this;
    }

    public HttpGatewayEntityBuilder<Body, SubHttpEntity> headers(HttpHeaders headers) {
        if (null == this.headers) {
            this.headers = headers;
        } else if (null != headers) {
            this.headers.putAll(headers);
        }
        return this;
    }

    public HttpGatewayEntityBuilder<Body, SubHttpEntity> body(Body body) {
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
