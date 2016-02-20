package com.ohayoyo.gateway.session.validator;

import com.ohayoyo.gateway.define.http.GatewayInterface;
import com.ohayoyo.gateway.session.core.AbstractGatewayAccessor;
import com.ohayoyo.gateway.session.core.GatewayContext;
import com.ohayoyo.gateway.session.core.GatewaySessionResponse;

/**
 * @author 蓝明乐
 */
public class SessionGatewayResultValidator extends AbstractGatewayAccessor implements GatewayResultValidator {

    private GatewayContext gatewayContext;

    @Override
    public void validate(GatewayInterface gatewayInterface, Class<?> responseBodyClass, GatewaySessionResponse<?> gatewaySessionResponse) {

    }

}
