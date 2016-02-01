package com.ohayoyo.gateway.client.core;

/**
 * 网关组件
 *
 * @param <Component> 组件
 */
public interface GatewayComponent<Component> {

    /**
     * 获取组件
     *
     * @return 返回组件
     * @throws GatewayException 抛出网关异常
     */
    Component getComponent() throws GatewayException;

    /**
     * 获取网关配置
     *
     * @return 返回网关配置
     */
    GatewayConfig getGatewayConfig();

    /**
     * 设置网关配置
     *
     * @param gatewayConfig 网关配置
     * @return 返回网关组件
     */
    GatewayComponent<Component> setGatewayConfig(GatewayConfig gatewayConfig);

    /**
     * 获取网关定义
     *
     * @return 返回网关定义
     */
    GatewayDefine getGatewayDefine();

    /**
     * 设置网关定义
     *
     * @param gatewayDefine 网关定义
     * @return 返回网关组件
     */
    GatewayComponent<Component> setGatewayDefine(GatewayDefine gatewayDefine);

    /**
     * 获取网关请求
     *
     * @return 返回网关请求
     */
    GatewayRequest getGatewayRequest();

    /**
     * 设置网关请求
     *
     * @param gatewayRequest 网关请求
     * @return 返回网关组件
     */
    GatewayComponent<Component> setGatewayRequest(GatewayRequest gatewayRequest);

}
