package com.ohayoyo.gateway.session;

import com.ohayoyo.gateway.define.GatewayInterface;

/**
 * @author 蓝明乐
 */
public interface GatewayResultValidator extends GatewayAccessor {

    void validate(GatewayInterface gatewayInterface, Class<?> responseBodyClass, SessionResponse<?> sessionResponse) throws VerifySessionException;

}
