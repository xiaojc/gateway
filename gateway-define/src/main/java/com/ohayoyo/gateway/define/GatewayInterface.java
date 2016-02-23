package com.ohayoyo.gateway.define;

/**
 * @author 蓝明乐
 */
public interface GatewayInterface<Request extends GatewayRequest, Response extends GatewayResponse> {

    String getKey();

    void setKey(String key);

    Request getRequest();

    void setRequest(Request request);

    Response getResponse();

    void setResponse(Response response);

}
