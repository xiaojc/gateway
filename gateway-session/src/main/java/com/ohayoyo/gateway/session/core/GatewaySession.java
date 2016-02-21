package com.ohayoyo.gateway.session.core;

import com.ohayoyo.gateway.define.http.GatewayInterface;
import com.ohayoyo.gateway.session.exception.GatewaySessionException;

/**
 * @author 蓝明乐
 */
public interface GatewaySession extends GatewayAccessor {

    <ResponseBody, RequestBody> SessionResponse<ResponseBody> session(Class<ResponseBody> responseBodyClass, GatewayInterface gatewayInterface, SessionRequest<RequestBody> sessionRequest) throws GatewaySessionException;

}
