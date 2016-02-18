package com.ohayoyo.gateway.client.channel;

import com.ohayoyo.gateway.client.core.GatewayClient;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.client.core.GatewayResponse;
import com.ohayoyo.gateway.client.exception.ChannelException;
import com.ohayoyo.gateway.client.exception.GatewayException;
import com.ohayoyo.gateway.client.restful.RestfulDefine;
import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.define.core.InterfaceDefine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * @author 蓝明乐
 */
public abstract class AbstractChannel implements GatewayChannel {

    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractChannel.class);

    private GatewayContainer gatewayContainer;

    private GatewayClient gatewayClient;

    public AbstractChannel(GatewayContainer gatewayContainer, GatewayClient gatewayClient) {
        Assert.notNull(gatewayContainer);
        Assert.notNull(gatewayClient);
        this.gatewayContainer = gatewayContainer;
        this.gatewayClient = gatewayClient;
    }

    @Override
    public GatewayContainer getGatewayContainer() {
        return gatewayContainer;
    }

    @Override
    public AbstractChannel setGatewayContainer(GatewayContainer gatewayContainer) {
        this.gatewayContainer = gatewayContainer;
        return this;
    }

    @Override
    public GatewayClient getGatewayClient() {
        return gatewayClient;
    }

    @Override
    public AbstractChannel setGatewayClient(GatewayClient gatewayClient) {
        this.gatewayClient = gatewayClient;
        return this;
    }

    @Override
    public String channel(String interfaceDefineKey) throws GatewayException {
        return channel(interfaceDefineKey, null);
    }

    @Override
    public String channel(String interfaceDefineKey, GatewayRequest<Object> gatewayRequest) throws GatewayException {
        return channel(String.class, interfaceDefineKey, gatewayRequest);
    }

    @Override
    public <Result> Result channel(Class<Result> responseType, String interfaceDefineKey) throws GatewayException {
        return channel(responseType, interfaceDefineKey, null);
    }

    @Override
    public <Result> Result channel(Class<Result> responseType, String interfaceDefineKey, GatewayRequest<Object> gatewayRequest) throws GatewayException {
        Assert.notNull(responseType);
        Assert.notNull(interfaceDefineKey);
        Result result = null;
        InterfaceDefine interfaceDefine = this.gatewayContainer.query(interfaceDefineKey);
        LOGGER.debug("根据接口键:{},向网关容器获取接口定义:{} .", interfaceDefineKey, interfaceDefine);
        if (!ObjectUtils.isEmpty(interfaceDefine)) {
            LOGGER.debug("目标响应类型:{} .", responseType);
            Class<?> responseBodyClass = this.resolveResponseType(responseType, interfaceDefine);
            LOGGER.debug("反转响应类型:{} .", responseBodyClass);
            GatewayDefine gatewayDefine = this.resolveGatewayDefine(interfaceDefine);
            LOGGER.debug("开始调用网关客户端进行会话  .");
            GatewayResponse<?> gatewayResponse = this.gatewayClient.session(responseBodyClass, gatewayDefine, gatewayRequest);
            LOGGER.debug("结束调用网关客户端进行会话 .");
            result = this.resolveGatewayResult(responseType, interfaceDefine, gatewayResponse);
            LOGGER.debug("反转网关结果 :{} .", result);
        } else {
            LOGGER.debug("不存在接口键:{}对应的接口定义,抛出异常 .", interfaceDefineKey);
            ChannelException.exception("根据给定的接口定义键,没有找合适的接口定义,这个键是:%s", interfaceDefineKey);
        }
        return result;
    }

    protected <ResponseBody, Result> Class<ResponseBody> resolveResponseType(Class<Result> responseType, InterfaceDefine interfaceDefine) {
        return (Class<ResponseBody>) responseType;
    }

    protected GatewayDefine resolveGatewayDefine(InterfaceDefine interfaceDefine) {
        return new RestfulDefine(interfaceDefine);
    }

    protected abstract <Result> Result resolveGatewayResult(Class<Result> responseType, InterfaceDefine interfaceDefine, GatewayResponse<?> gatewayResponse);

}
