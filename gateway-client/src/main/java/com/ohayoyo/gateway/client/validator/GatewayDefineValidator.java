package com.ohayoyo.gateway.client.validator;

import com.ohayoyo.gateway.client.exception.ValidatorException;
import com.ohayoyo.gateway.define.core.InterfaceDefine;

/**
 * @author 蓝明乐
 */
public interface GatewayDefineValidator {

    void validate(InterfaceDefine interfaceDefine) throws ValidatorException;

}
