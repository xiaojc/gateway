package com.ohayoyo.gateway.session.validator;

import com.ohayoyo.gateway.define.http.GatewayInterface;
import com.ohayoyo.gateway.session.core.GatewayAccessor;
import com.ohayoyo.gateway.session.core.GatewaySessionResponse;
import com.ohayoyo.gateway.session.exception.VerifySessionException;

/**
 * @author 蓝明乐
 */
public interface GatewayResultValidator extends GatewayAccessor {

    void validate(GatewayInterface gatewayInterface, Class<?> responseBodyClass, GatewaySessionResponse<?> gatewaySessionResponse) throws VerifySessionException;

}
