package com.ohayoyo.gateway.session;

import org.springframework.util.MultiValueMap;

/**
 * @author 蓝明乐
 */
public interface SessionResponse<ResponseBody> {

    Integer getStatusCode();

    void setStatusCode(Integer statusCode);

    String getReasonPhrase();

    void setReasonPhrase(String reasonPhrase);

    MultiValueMap<String, String> getResponseHeaders();

    void setResponseHeaders(MultiValueMap<String, String> responseHeaders);

    ResponseBody getResponseBody();

    void setResponseBody(ResponseBody responseBody);

}
