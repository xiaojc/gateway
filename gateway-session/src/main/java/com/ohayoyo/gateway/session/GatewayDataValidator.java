package com.ohayoyo.gateway.session;

import com.ohayoyo.gateway.define.GatewayInterface;

/**
 * @author 蓝明乐
 */
public interface GatewayDataValidator extends GatewayAccessor {

    void validate(GatewayInterface gatewayInterface, SessionRequest<?> sessionRequest) throws VerifySessionException;

}
