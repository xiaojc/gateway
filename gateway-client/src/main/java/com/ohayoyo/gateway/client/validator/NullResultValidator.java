package com.ohayoyo.gateway.client.validator;

import com.ohayoyo.gateway.client.core.AbstractContextAccessor;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayResponse;
import com.ohayoyo.gateway.client.exception.ValidatorException;

/**
 * @author 蓝明乐
 */
public class NullResultValidator extends AbstractContextAccessor implements GatewayResultValidator {

    @Override
    public void validate(GatewayDefine gatewayDefine, Class<?> responseBodyClass, GatewayResponse<?> gatewayResponse) throws ValidatorException {

    }

}
