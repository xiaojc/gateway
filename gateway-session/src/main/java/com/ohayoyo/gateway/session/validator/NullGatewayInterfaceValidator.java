package com.ohayoyo.gateway.session.validator;

import com.ohayoyo.gateway.define.http.GatewayInterface;
import com.ohayoyo.gateway.session.core.AbstractGatewayAccessor;
import com.ohayoyo.gateway.session.exception.VerifySessionException;

/**
 * @author 蓝明乐
 */
public class NullGatewayInterfaceValidator extends AbstractGatewayAccessor implements GatewayInterfaceValidator {

    @Override
    public void validate(GatewayInterface gatewayInterface) throws VerifySessionException {

    }
}
