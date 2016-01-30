package com.ohayoyo.gateway.client.parts;

import com.ohayoyo.gateway.client.core.*;
import com.ohayoyo.gateway.client.restful.RestfulResponse;

public class ResponseWrapperPart extends AbstractGatewayPart<GatewayWrapper> implements GatewayWrapper {

    @Override
    public GatewayWrapper getPart() throws GatewayException {
        return this;
    }

    @Override
    public GatewayResponse wrap(GatewayEntity gatewayEntity, GatewayConfig gatewayConfig, GatewayRequest gatewayRequest, GatewayDefine gatewayDefine) throws GatewayException {
        RestfulResponse restfulResponse = new RestfulResponse();
        return restfulResponse;
    }

}
