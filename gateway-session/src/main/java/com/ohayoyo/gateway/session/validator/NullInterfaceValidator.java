package com.ohayoyo.gateway.session.validator;

import com.ohayoyo.gateway.define.http.GatewayInterface;
import com.ohayoyo.gateway.session.core.AbstractGatewayAccessor;
import com.ohayoyo.gateway.session.exception.VerifySessionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 蓝明乐
 */
public class NullInterfaceValidator extends AbstractGatewayAccessor implements GatewayInterfaceValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(NullInterfaceValidator.class);

    @Override
    public void validate(GatewayInterface gatewayInterface) throws VerifySessionException {

    }
}
