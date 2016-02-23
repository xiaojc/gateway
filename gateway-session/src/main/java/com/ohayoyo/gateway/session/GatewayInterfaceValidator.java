package com.ohayoyo.gateway.session;

import com.ohayoyo.gateway.define.GatewayInterface;

/**
 * @author 蓝明乐
 */
public interface GatewayInterfaceValidator extends GatewayAccessor {

    void validate(GatewayInterface gatewayInterface) throws VerifySessionException;

}
