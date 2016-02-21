package com.ohayoyo.gateway.session.validator;

import com.ohayoyo.gateway.define.http.GatewayInterface;
import com.ohayoyo.gateway.session.core.AbstractGatewayAccessor;
import com.ohayoyo.gateway.session.core.SessionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 蓝明乐
 */
public class SessionResultValidator extends AbstractGatewayAccessor implements GatewayResultValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionResultValidator.class);

    @Override
    public void validate(GatewayInterface gatewayInterface, Class<?> responseBodyClass, SessionResponse<?> sessionResponse) {

    }

}
