package com.ohayoyo.gateway.client.validator;

import com.ohayoyo.gateway.client.core.GatewayContextAccessor;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.client.exception.ValidatorException;

/**
 * @author 蓝明乐
 */
public interface GatewayDataValidator extends GatewayContextAccessor {

    void validate(GatewayDefine gatewayDefine, GatewayRequest<?> gatewayRequest) throws ValidatorException;

}
