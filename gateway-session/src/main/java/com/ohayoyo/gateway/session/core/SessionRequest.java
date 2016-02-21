package com.ohayoyo.gateway.session.core;

import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * @author 蓝明乐
 */
public interface SessionRequest<RequestBody> {

    String getSelect();

    void setSelect(String select);

    Map<String, String> getRequestPathVariables();

    void setRequestPathVariables(Map<String, String> requestPathVariables);

    MultiValueMap<String, String> getRequestQueries();

    void setRequestQueries(MultiValueMap<String, String> requestQueries);

    MultiValueMap<String, String> getRequestHeaders();

    void setRequestHeaders(MultiValueMap<String, String> requestHeaders);

    RequestBody getRequestBody();

    void setRequestBody(RequestBody requestBody);

}
