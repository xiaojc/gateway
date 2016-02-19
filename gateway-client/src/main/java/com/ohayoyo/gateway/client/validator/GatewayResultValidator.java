package com.ohayoyo.gateway.client.validator;

import com.ohayoyo.gateway.client.core.GatewayContextAccessor;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayResponse;
import com.ohayoyo.gateway.client.exception.ValidatorException;

/**
 * @author 蓝明乐
 */
public interface GatewayResultValidator extends GatewayContextAccessor {

    void validate(GatewayDefine gatewayDefine, Class<?> responseBodyClass, GatewayResponse<?> gatewayResponse) throws ValidatorException;

}
