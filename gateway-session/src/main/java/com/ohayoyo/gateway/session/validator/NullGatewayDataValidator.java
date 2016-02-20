package com.ohayoyo.gateway.session.validator;

import com.ohayoyo.gateway.define.http.GatewayInterface;
import com.ohayoyo.gateway.session.core.AbstractGatewayAccessor;
import com.ohayoyo.gateway.session.core.GatewaySessionRequest;
import com.ohayoyo.gateway.session.exception.VerifySessionException;

/**
 * @author 蓝明乐
 */
public class NullGatewayDataValidator extends AbstractGatewayAccessor implements GatewayDataValidator {

    @Override
    public void validate(GatewayInterface gatewayInterface, GatewaySessionRequest<?> gatewaySessionRequest) throws VerifySessionException {

    }

}
