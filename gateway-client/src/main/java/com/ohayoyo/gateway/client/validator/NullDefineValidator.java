package com.ohayoyo.gateway.client.validator;

import com.ohayoyo.gateway.client.core.AbstractContextAccessor;
import com.ohayoyo.gateway.client.exception.ValidatorException;
import com.ohayoyo.gateway.define.http.InterfaceDefine;

/**
 * @author 蓝明乐
 */
public class NullDefineValidator extends AbstractContextAccessor implements GatewayDefineValidator {

    @Override
    public void validate(InterfaceDefine interfaceDefine) throws ValidatorException {

    }

}
