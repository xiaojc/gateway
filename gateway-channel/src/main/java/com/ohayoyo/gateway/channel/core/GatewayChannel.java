package com.ohayoyo.gateway.channel.core;

import com.ohayoyo.gateway.channel.exception.GatewayChannelException;
import com.ohayoyo.gateway.session.core.GatewayAccessor;
import com.ohayoyo.gateway.session.core.SessionRequest;

/**
 * @author 蓝明乐
 */
public interface GatewayChannel extends GatewayAccessor {

    String channel(String key) throws GatewayChannelException;

    String channel(String key, SessionRequest<Object> sessionRequest) throws GatewayChannelException;

    <Result> Result channel(Class<Result> responseType, String key) throws GatewayChannelException;

    <Result> Result channel(Class<Result> responseType, String key, SessionRequest<Object> sessionRequest) throws GatewayChannelException;

}

