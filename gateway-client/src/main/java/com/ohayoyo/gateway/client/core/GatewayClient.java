package com.ohayoyo.gateway.client.core;

/**
 * 网关客户端
 */
public interface GatewayClient {

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
     * @return 返回网关客户端
     */
    GatewayClient setGatewayConfig(GatewayConfig gatewayConfig);

    /**
     * 获取网关执行器
     *
     * @return 返回网关执行器
     */
    GatewayExecutor getGatewayExecutor();

    /**
     * 设置网关执行器
     *
     * @param gatewayExecutor 网关执行器
     * @return 返回网关客户端
     */
    GatewayClient setGatewayExecutor(GatewayExecutor gatewayExecutor);

    /**
     * 通过网关客户端进行一次会话
     *
     * @param gatewayDefine  网关定义
     * @param gatewayRequest 网关请求
     * @return 返回网关响应
     * @throws GatewayException 抛出网关异常
     */
    GatewayResponse session(GatewayDefine gatewayDefine, GatewayRequest gatewayRequest) throws GatewayException;

    /**
     * 通过网关客户端进行一次会话
     *
     * @param responseType   响应实体类型
     * @param gatewayDefine  网关定义
     * @param gatewayRequest 网关请求
     * @param <T>            响应实体类型
     * @return 返回网关响应
     * @throws GatewayException 抛出网关异常
     */
    <T> GatewayResponse session(Class<T> responseType, GatewayDefine gatewayDefine, GatewayRequest gatewayRequest) throws GatewayException;

}
