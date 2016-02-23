package com.ohayoyo.gateway.channel;

import com.ohayoyo.gateway.session.GatewayAccessor;
import com.ohayoyo.gateway.session.SessionRequest;

/**
 * @author 蓝明乐
 */
public interface GatewayChannel extends GatewayAccessor {

    String channel(String key) throws GatewayChannelException;

    String channel(String key, SessionRequest<Object> sessionRequest) throws GatewayChannelException;

    <Result> Result channel(Class<Result> responseType, String key) throws GatewayChannelException;

    <Result> Result channel(Class<Result> responseType, String key, SessionRequest<Object> sessionRequest) throws GatewayChannelException;

}

