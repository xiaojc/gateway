package com.ohayoyo.gateway.channel.core;

import com.ohayoyo.gateway.define.http.GatewayInterface;
import com.ohayoyo.gateway.session.core.GatewaySessionResponse;
import org.springframework.util.ObjectUtils;

@SuppressWarnings("unchecked")
public class ResultTypeGatewayChannel extends AbstractGatewayChannel {

    @Override
    protected <ResponseBody, Result> Class<ResponseBody> resolveResponseType(Class<Result> responseType, GatewayInterface gatewayInterface) {
        return (Class<ResponseBody>) responseType;
    }

    @Override
    protected <Result> Result resolveGatewayResult(Class<Result> responseType, GatewayInterface GatewayInterface, GatewaySessionResponse<?> gatewaySessionResponse) {
        Object responseBody = gatewaySessionResponse.getResponseBody();
        if ((!ObjectUtils.isEmpty(responseBody)) && responseBody.getClass().isAssignableFrom(responseType)) {
            return (Result) responseBody;
        }
        return null;
    }

}
