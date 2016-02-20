package com.ohayoyo.gateway.channel.core;

import com.ohayoyo.gateway.channel.exception.ChannelException;
import com.ohayoyo.gateway.session.core.GatewayAccessor;
import com.ohayoyo.gateway.session.core.GatewaySessionRequest;

/**
 * @author 蓝明乐
 */
public interface GatewayChannel extends GatewayAccessor {

    String channel(String key) throws ChannelException;

    String channel(String key, GatewaySessionRequest<Object> gatewaySessionRequest) throws ChannelException;

    <Result> Result channel(Class<Result> responseType, String key) throws ChannelException;

    <Result> Result channel(Class<Result> responseType, String key, GatewaySessionRequest<Object> gatewaySessionRequest) throws ChannelException;

}

