package com.ohayoyo.gateway.client.core;

/**
 * 网关包装器
 *
 * @param <T> 响应实体类型
 */
public interface GatewayWrapper<T> extends GatewayComponent<GatewayWrapper<T>> {

    /**
     * 包装网关响应
     *
     * @return 返回网关响应
     * @throws GatewayException 抛出网关异常
     */
    GatewayResponse<T> wrapGatewayResponse() throws GatewayException;

}
