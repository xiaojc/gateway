package com.ohayoyo.gateway.client.parts;

import com.ohayoyo.gateway.client.core.*;
import com.ohayoyo.gateway.client.restful.RestfulResponse;

public class ResponseWrappingPart extends AbstractGatewayPart<GatewayWrapping> implements GatewayWrapping {

    @Override
    public GatewayWrapping getPart() throws GatewayException {
        return this;
    }

    @Override
    public GatewayResponse wrap(GatewayEntity gatewayEntity, GatewayConfig gatewayConfig) {
        RestfulResponse restfulResponse = new RestfulResponse();

        return restfulResponse;
    }

}
