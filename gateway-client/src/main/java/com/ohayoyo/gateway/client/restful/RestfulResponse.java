package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.core.GatewayResponse;
import org.springframework.util.MultiValueMap;

/**
 * @author 蓝明乐
 */
public class RestfulResponse<ResponseBody> implements GatewayResponse<ResponseBody> {

    private Integer statusCode;

    private String reasonPhrase;

    private MultiValueMap<String, String> responseHeaders;

    private ResponseBody responseBody;

    @Override
    public Integer getStatusCode() {
        return statusCode;
    }

    @Override
    public RestfulResponse<ResponseBody> setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    @Override
    public String getReasonPhrase() {
        return reasonPhrase;
    }

    @Override
    public RestfulResponse<ResponseBody> setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
        return this;
    }

    @Override
    public MultiValueMap<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    @Override
    public RestfulResponse setResponseHeaders(MultiValueMap<String, String> responseHeaders) {
        this.responseHeaders = responseHeaders;
        return this;
    }

    @Override
    public ResponseBody getResponseBody() {
        return responseBody;
    }

    @Override
    public RestfulResponse setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
        return this;
    }

}
