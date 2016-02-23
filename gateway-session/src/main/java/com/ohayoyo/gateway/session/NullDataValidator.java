package com.ohayoyo.gateway.session;

import com.ohayoyo.gateway.define.GatewayInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 蓝明乐
 */
public class NullDataValidator extends AbstractGatewayAccessor implements GatewayDataValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(NullDataValidator.class);

    @Override
    public void validate(GatewayInterface gatewayInterface, SessionRequest<?> sessionRequest) throws VerifySessionException {

    }

}
