package com.ohayoyo.gateway.session.validator;

import com.ohayoyo.gateway.define.http.GatewayInterface;
import com.ohayoyo.gateway.session.core.AbstractGatewayAccessor;
import com.ohayoyo.gateway.session.core.SessionRequest;
import com.ohayoyo.gateway.session.exception.VerifySessionException;
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
