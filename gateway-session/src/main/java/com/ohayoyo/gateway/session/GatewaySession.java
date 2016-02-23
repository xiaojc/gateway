package com.ohayoyo.gateway.session;

import com.ohayoyo.gateway.define.GatewayInterface;

/**
 * @author 蓝明乐
 */
public interface GatewaySession extends GatewayAccessor {

    <ResponseBody, RequestBody> SessionResponse<ResponseBody> session(Class<ResponseBody> responseBodyClass, GatewayInterface gatewayInterface, SessionRequest<RequestBody> sessionRequest) throws GatewaySessionException;

}
