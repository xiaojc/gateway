package com.ohayoyo.gateway.session.validator;

import com.ohayoyo.gateway.define.http.GatewayInterface;
import com.ohayoyo.gateway.session.core.GatewayAccessor;
import com.ohayoyo.gateway.session.core.SessionRequest;
import com.ohayoyo.gateway.session.exception.VerifySessionException;

/**
 * @author 蓝明乐
 */
public interface GatewayDataValidator extends GatewayAccessor {

    void validate(GatewayInterface gatewayInterface, SessionRequest<?> sessionRequest) throws VerifySessionException;

}
