package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.GatewayResponse;
import org.springframework.util.MultiValueMap;

public class RestfulGatewayResponse<ResponseBody> implements GatewayResponse<ResponseBody> {

    private Integer statusCode;

    private String reasonPhrase;

    private MultiValueMap<String, String> responseHeaders;

    private ResponseBody responseBody;

    @Override
    public Integer getStatusCode() {
        return statusCode;
    }

    @Override
    public RestfulGatewayResponse<ResponseBody> setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    @Override
    public String getReasonPhrase() {
        return reasonPhrase;
    }

    @Override
    public RestfulGatewayResponse<ResponseBody> setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
        return this;
    }

    @Override
    public MultiValueMap<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    @Override
    public RestfulGatewayResponse setResponseHeaders(MultiValueMap<String, String> responseHeaders) {
        this.responseHeaders = responseHeaders;
        return this;
    }

    @Override
    public ResponseBody getResponseBody() {
        return responseBody;
    }

    @Override
    public RestfulGatewayResponse setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
        return this;
    }

}
