package com.ohayoyo.gateway.session.restful;

import com.ohayoyo.gateway.session.core.GatewaySessionRequest;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * @author 蓝明乐
 */
public class RestfulGatewaySessionRequest<RequestBody> implements GatewaySessionRequest<RequestBody> {

    private String select;

    private Map<String, String> requestPathVariables;

    private MultiValueMap<String, String> requestQueries;

    private MultiValueMap<String, String> requestHeaders;

    private RequestBody requestBody;

    @Override
    public String getSelect() {
        return select;
    }

    @Override
    public void setSelect(String select) {
        this.select = select;
    }

    @Override
    public Map<String, String> getRequestPathVariables() {
        return requestPathVariables;
    }

    @Override
    public void setRequestPathVariables(Map<String, String> requestPathVariables) {
        this.requestPathVariables = requestPathVariables;
    }

    @Override
    public MultiValueMap<String, String> getRequestQueries() {
        return requestQueries;
    }

    @Override
    public void setRequestQueries(MultiValueMap<String, String> requestQueries) {
        this.requestQueries = requestQueries;
    }

    @Override
    public MultiValueMap<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    @Override
    public void setRequestHeaders(MultiValueMap<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    @Override
    public RequestBody getRequestBody() {
        return requestBody;
    }

    @Override
    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

}
