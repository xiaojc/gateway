package com.ohayoyo.gateway.client.validator;

import com.ohayoyo.gateway.client.core.GatewayContextAccessor;
import com.ohayoyo.gateway.client.exception.ValidatorException;
import com.ohayoyo.gateway.define.http.InterfaceDefine;

/**
 * @author 蓝明乐
 */
public interface GatewayDefineValidator extends GatewayContextAccessor {

    void validate(InterfaceDefine interfaceDefine) throws ValidatorException;

}
