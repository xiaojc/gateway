package com.ohayoyo.gateway.session.validator;

import com.ohayoyo.gateway.define.http.GatewayInterface;
import com.ohayoyo.gateway.session.core.AbstractGatewayAccessor;
import com.ohayoyo.gateway.session.core.GatewaySessionResponse;
import com.ohayoyo.gateway.session.exception.VerifySessionException;

/**
 * @author 蓝明乐
 */
public class NullGatewayResultValidator extends AbstractGatewayAccessor implements GatewayResultValidator {

    @Override
    public void validate(GatewayInterface gatewayInterface, Class<?> responseBodyClass, GatewaySessionResponse<?> gatewaySessionResponse) throws VerifySessionException {

    }

}
