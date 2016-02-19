package com.ohayoyo.gateway.client.validator;

import com.ohayoyo.gateway.client.core.GatewayContext;
import com.ohayoyo.gateway.client.exception.ValidatorException;
import com.ohayoyo.gateway.define.http.InterfaceDefine;

/**
 * @author 蓝明乐
 */
public interface GatewayDefineValidator {

    GatewayContext getGatewayContext();

    GatewayDefineValidator setGatewayContext(GatewayContext gatewayContext);

    void validate(InterfaceDefine interfaceDefine) throws ValidatorException;

}
