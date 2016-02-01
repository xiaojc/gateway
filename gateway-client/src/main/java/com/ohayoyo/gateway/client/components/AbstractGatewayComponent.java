package com.ohayoyo.gateway.client.components;

import com.ohayoyo.gateway.client.core.GatewayComponent;
import com.ohayoyo.gateway.client.core.GatewayConfig;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayRequest;

/**
 * 抽象的网关组件
 *
 * @param <Component> 组件
 */
public abstract class AbstractGatewayComponent<Component> implements GatewayComponent<Component> {

    /**
     * 网关配置
     */
    private GatewayConfig gatewayConfig;

    /**
     * 网关定义
     */
    private GatewayDefine gatewayDefine;

    /**
     * 网关请求
     */
    private GatewayRequest gatewayRequest;

    /**
     * 获取网关配置
     *
     * @return 返回网关配置
     */
    @Override
    public GatewayConfig getGatewayConfig() {
        return gatewayConfig;
    }

    /**
     * 设置网关配置
     *
     * @param gatewayConfig 网关配置
     * @return 返回网关组件
     */
    @Override
    public AbstractGatewayComponent<Component> setGatewayConfig(GatewayConfig gatewayConfig) {
        this.gatewayConfig = gatewayConfig;
        return this;
    }

    /**
     * 获取网关定义
     *
     * @return 返回网关定义
     */
    @Override
    public GatewayDefine getGatewayDefine() {
        return gatewayDefine;
    }

    /**
     * 设置网关定义
     *
     * @param gatewayDefine 网关定义
     * @return 返回网关组件
     */
    @Override
    public AbstractGatewayComponent<Component> setGatewayDefine(GatewayDefine gatewayDefine) {
        this.gatewayDefine = gatewayDefine;
        return this;
    }

    /**
     * 获取网关请求
     *
     * @return 返回网关请求
     */
    @Override
    public GatewayRequest getGatewayRequest() {
        return gatewayRequest;
    }

    /**
     * 设置网关请求
     *
     * @param gatewayRequest 网关请求
     * @return 返回网关组件
     */
    @Override
    public AbstractGatewayComponent<Component> setGatewayRequest(GatewayRequest gatewayRequest) {
        this.gatewayRequest = gatewayRequest;
        return this;
    }

}
