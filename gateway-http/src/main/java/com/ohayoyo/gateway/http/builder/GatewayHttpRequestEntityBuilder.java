package com.ohayoyo.gateway.http.builder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.List;
import java.util.Map;

/**
 * @author 蓝明乐
 */
public class GatewayHttpRequestEntityBuilder<RequestBody> extends GatewayHttpEntityBuilder<RequestBody, RequestEntity<RequestBody>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayHttpRequestEntityBuilder.class);

    private URI uri;

    private HttpMethod httpMethod;

    public GatewayHttpRequestEntityBuilder() {
    }

    public static <RequestBody> GatewayHttpRequestEntityBuilder<RequestBody> newInstance() {
        return new GatewayHttpRequestEntityBuilder<RequestBody>();
    }

    public GatewayHttpRequestEntityBuilder(URI uri, HttpMethod httpMethod) {
        Assert.notNull(uri);
        Assert.notNull(httpMethod);
        this.uri = uri;
        this.httpMethod = httpMethod;
    }

    public GatewayHttpRequestEntityBuilder(HttpHeaders headers, URI uri, HttpMethod httpMethod) {
        super(headers);
        Assert.notNull(uri);
        Assert.notNull(httpMethod);
        this.uri = uri;
        this.httpMethod = httpMethod;
    }

    public GatewayHttpRequestEntityBuilder(RequestBody requestBody, URI uri, HttpMethod httpMethod) {
        super(requestBody);
        Assert.notNull(uri);
        Assert.notNull(httpMethod);
        this.uri = uri;
        this.httpMethod = httpMethod;
    }

    public GatewayHttpRequestEntityBuilder(HttpHeaders headers, RequestBody requestBody, URI uri, HttpMethod httpMethod) {
        super(headers, requestBody);
        Assert.notNull(uri);
        Assert.notNull(httpMethod);
        this.uri = uri;
        this.httpMethod = httpMethod;
    }

    public GatewayHttpRequestEntityBuilder<RequestBody> url(URI uri) {
        Assert.notNull(uri);
        this.uri = uri;
        return this;
    }

    public GatewayHttpRequestEntityBuilder<RequestBody> httpMethod(HttpMethod httpMethod) {
        Assert.notNull(httpMethod);
        this.httpMethod = httpMethod;
        return this;
    }

    public GatewayHttpRequestEntityBuilder<RequestBody> httpMethod(String httpMethod) {
        Assert.notNull(httpMethod);
        this.httpMethod = HttpMethod.resolve(httpMethod);
        return this;
    }

    @Override
    public GatewayHttpRequestEntityBuilder<RequestBody> body(RequestBody requestBody) {
        return (GatewayHttpRequestEntityBuilder<RequestBody>) super.body(requestBody);
    }

    @Override
    public GatewayHttpRequestEntityBuilder<RequestBody> headers(HttpHeaders headers) {
        return (GatewayHttpRequestEntityBuilder<RequestBody>) super.headers(headers);
    }

    @Override
    public GatewayHttpRequestEntityBuilder<RequestBody> headers(MultiValueMap<String, String> headers) {
        return (GatewayHttpRequestEntityBuilder<RequestBody>) super.headers(headers);
    }

    @Override
    public GatewayHttpRequestEntityBuilder<RequestBody> headers(Map<String, List<String>> headers) {
        return (GatewayHttpRequestEntityBuilder<RequestBody>) super.headers(headers);
    }

    protected URI getUri() {
        return uri;
    }

    protected HttpMethod getHttpMethod() {
        return httpMethod;
    }

    @Override
    public RequestEntity<RequestBody> build() {
        URI uri = this.getUri();
        HttpMethod httpMethod = this.getHttpMethod();
        HttpHeaders httpHeaders = this.getHeaders();
        RequestBody requestBody = this.getBody();
        RequestEntity<RequestBody> requestEntity = new RequestEntity<RequestBody>(requestBody, httpHeaders, httpMethod, uri);
        return requestEntity;
    }

}
