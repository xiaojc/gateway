package com.ohayoyo.gateway.client.validator;

import com.ohayoyo.gateway.client.core.AbstractContextAccessor;
import com.ohayoyo.gateway.client.core.GatewayContext;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayResponse;

/**
 * @author 蓝明乐
 */
public class ClientResultValidator extends AbstractContextAccessor implements GatewayResultValidator {

    private GatewayContext gatewayContext;

    @Override
    public void validate(GatewayDefine gatewayDefine, Class<?> responseBodyClass, GatewayResponse<?> gatewayResponse) {

    }

}
