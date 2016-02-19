package com.ohayoyo.gateway.client.channel;

import com.ohayoyo.gateway.client.core.GatewayResponse;
import com.ohayoyo.gateway.define.http.InterfaceDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

/**
 * @author 蓝明乐
 */
public class ClientChannel extends AbstractChannel {

    public static final Logger LOGGER = LoggerFactory.getLogger(ClientChannel.class);

    @Override
    protected <Result> Result resolveGatewayResult(Class<Result> responseType, InterfaceDefine interfaceDefine, GatewayResponse<?> gatewayResponse) {
        Object responseBody = gatewayResponse.getResponseBody();
        if ((!ObjectUtils.isEmpty(responseBody)) && responseBody.getClass().isAssignableFrom(responseType)) {
            return (Result) responseBody;
        }
        return null;
    }

}
