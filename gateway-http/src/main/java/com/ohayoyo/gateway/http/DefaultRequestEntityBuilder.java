package com.ohayoyo.gateway.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.util.Assert;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.List;
import java.util.Map;

public class DefaultRequestEntityBuilder<RequestBody> extends AbstractHttpEntityBuilder<RequestBody, RequestEntity<RequestBody>> {

    private URI uri;

    private HttpMethod httpMethod;

    public DefaultRequestEntityBuilder() {
    }

    public static <RequestBody> DefaultRequestEntityBuilder<RequestBody> newInstance() {
        return new DefaultRequestEntityBuilder<RequestBody>();
    }

    public DefaultRequestEntityBuilder(URI uri, HttpMethod httpMethod) {
        Assert.notNull(uri);
        Assert.notNull(httpMethod);
        this.uri = uri;
        this.httpMethod = httpMethod;
    }

    public DefaultRequestEntityBuilder(HttpHeaders headers, URI uri, HttpMethod httpMethod) {
        super(headers);
        Assert.notNull(uri);
        Assert.notNull(httpMethod);
        this.uri = uri;
        this.httpMethod = httpMethod;
    }

    public DefaultRequestEntityBuilder(RequestBody requestBody, URI uri, HttpMethod httpMethod) {
        super(requestBody);
        Assert.notNull(uri);
        Assert.notNull(httpMethod);
        this.uri = uri;
        this.httpMethod = httpMethod;
    }

    public DefaultRequestEntityBuilder(HttpHeaders headers, RequestBody requestBody, URI uri, HttpMethod httpMethod) {
        super(headers, requestBody);
        Assert.notNull(uri);
        Assert.notNull(httpMethod);
        this.uri = uri;
        this.httpMethod = httpMethod;
    }

    public DefaultRequestEntityBuilder<RequestBody> url(URI uri) {
        Assert.notNull(uri);
        this.uri = uri;
        return this;
    }

    public DefaultRequestEntityBuilder<RequestBody> httpMethod(HttpMethod httpMethod) {
        Assert.notNull(httpMethod);
        this.httpMethod = httpMethod;
        return this;
    }

    public DefaultRequestEntityBuilder<RequestBody> httpMethod(String httpMethod) {
        Assert.notNull(httpMethod);
        this.httpMethod = HttpMethod.resolve(httpMethod);
        return this;
    }

    @Override
    public DefaultRequestEntityBuilder<RequestBody> body(RequestBody requestBody) {
        return (DefaultRequestEntityBuilder<RequestBody>) super.body(requestBody);
    }

    @Override
    public DefaultRequestEntityBuilder<RequestBody> headers(HttpHeaders headers) {
        return (DefaultRequestEntityBuilder<RequestBody>) super.headers(headers);
    }

    @Override
    public DefaultRequestEntityBuilder<RequestBody> headers(MultiValueMap<String, String> headers) {
        return (DefaultRequestEntityBuilder<RequestBody>) super.headers(headers);
    }

    @Override
    public DefaultRequestEntityBuilder<RequestBody> headers(Map<String, List<String>> headers) {
        return (DefaultRequestEntityBuilder<RequestBody>) super.headers(headers);
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
