package com.ohayoyo.gateway.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.List;
import java.util.Map;

public class GatewayRequestEntityBuilder<RequestBody> extends AbstractHttpEntityBuilder<RequestBody, RequestEntity<RequestBody>> {

    private URI uri;

    private HttpMethod httpMethod;

    public GatewayRequestEntityBuilder() {
    }

    public static <RequestBody> GatewayRequestEntityBuilder<RequestBody> newInstance() {
        return new GatewayRequestEntityBuilder<RequestBody>();
    }

    public GatewayRequestEntityBuilder(URI uri, HttpMethod httpMethod) {
        Assert.notNull(uri);
        Assert.notNull(httpMethod);
        this.uri = uri;
        this.httpMethod = httpMethod;
    }

    public GatewayRequestEntityBuilder(HttpHeaders headers, URI uri, HttpMethod httpMethod) {
        super(headers);
        Assert.notNull(uri);
        Assert.notNull(httpMethod);
        this.uri = uri;
        this.httpMethod = httpMethod;
    }

    public GatewayRequestEntityBuilder(RequestBody requestBody, URI uri, HttpMethod httpMethod) {
        super(requestBody);
        Assert.notNull(uri);
        Assert.notNull(httpMethod);
        this.uri = uri;
        this.httpMethod = httpMethod;
    }

    public GatewayRequestEntityBuilder(HttpHeaders headers, RequestBody requestBody, URI uri, HttpMethod httpMethod) {
        super(headers, requestBody);
        Assert.notNull(uri);
        Assert.notNull(httpMethod);
        this.uri = uri;
        this.httpMethod = httpMethod;
    }

    public GatewayRequestEntityBuilder<RequestBody> url(URI uri) {
        Assert.notNull(uri);
        this.uri = uri;
        return this;
    }

    public GatewayRequestEntityBuilder<RequestBody> httpMethod(HttpMethod httpMethod) {
        Assert.notNull(httpMethod);
        this.httpMethod = httpMethod;
        return this;
    }

    public GatewayRequestEntityBuilder<RequestBody> httpMethod(String httpMethod) {
        Assert.notNull(httpMethod);
        this.httpMethod = HttpMethod.resolve(httpMethod);
        return this;
    }

    @Override
    public GatewayRequestEntityBuilder<RequestBody> body(RequestBody requestBody) {
        return (GatewayRequestEntityBuilder<RequestBody>) super.body(requestBody);
    }

    @Override
    public GatewayRequestEntityBuilder<RequestBody> headers(HttpHeaders headers) {
        return (GatewayRequestEntityBuilder<RequestBody>) super.headers(headers);
    }

    @Override
    public GatewayRequestEntityBuilder<RequestBody> headers(MultiValueMap<String, String> headers) {
        return (GatewayRequestEntityBuilder<RequestBody>) super.headers(headers);
    }

    @Override
    public GatewayRequestEntityBuilder<RequestBody> headers(Map<String, List<String>> headers) {
        return (GatewayRequestEntityBuilder<RequestBody>) super.headers(headers);
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
