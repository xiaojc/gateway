package com.ohayoyo.gateway.session;

import com.ohayoyo.gateway.define.GatewayInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 蓝明乐
 */
public class NullResultValidator extends AbstractGatewayAccessor implements GatewayResultValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(NullResultValidator.class);

    @Override
    public void validate(GatewayInterface gatewayInterface, Class<?> responseBodyClass, SessionResponse<?> sessionResponse) throws VerifySessionException {

    }

}
