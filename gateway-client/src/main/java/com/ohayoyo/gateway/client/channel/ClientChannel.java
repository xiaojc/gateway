package com.ohayoyo.gateway.client.channel;

import com.ohayoyo.gateway.client.core.GatewayClient;
import com.ohayoyo.gateway.client.core.GatewayResponse;
import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.define.core.InterfaceDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

/**
 * @author 蓝明乐
 */
public class ClientChannel extends AbstractChannel {

    public static final Logger LOGGER = LoggerFactory.getLogger(ClientChannel.class);

    public ClientChannel(GatewayContainer gatewayContainer) {
        this(gatewayContainer, GatewayClient.DEFAULT_CLIENT);
    }

    public ClientChannel(GatewayContainer gatewayContainer, GatewayClient gatewayClient) {
        super(gatewayContainer, gatewayClient);
    }

    @Override
    protected <Result> Result resolveGatewayResult(Class<Result> responseType, InterfaceDefine interfaceDefine, GatewayResponse<?> gatewayResponse) {
        LOGGER.debug("反转响应网关结果 .");
        Object responseBody = gatewayResponse.getResponseBody();
        LOGGER.debug("网关响应结果:{} .", responseBody);
        if ((!ObjectUtils.isEmpty(responseBody)) && responseBody.getClass().isAssignableFrom(responseType)) {
            LOGGER.debug("直接返回归属这个定义的结果 .");
            return (Result) responseBody;
        }
        return null;
    }

}
