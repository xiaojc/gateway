package com.ohayoyo.gateway.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.MultiValueMap;

/**
 * @author 蓝明乐
 */
public class RestfulSessionResponse<ResponseBody> implements SessionResponse<ResponseBody> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestfulSessionResponse.class);

    private Integer statusCode;

    private String reasonPhrase;

    private MultiValueMap<String, String> responseHeaders;

    private ResponseBody responseBody;

    @Override
    public Integer getStatusCode() {
        return statusCode;
    }

    @Override
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String getReasonPhrase() {
        return reasonPhrase;
    }

    @Override
    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    @Override
    public MultiValueMap<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    @Override
    public void setResponseHeaders(MultiValueMap<String, String> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    @Override
    public ResponseBody getResponseBody() {
        return responseBody;
    }

    @Override
    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

}
