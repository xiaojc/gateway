package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.core.BehaviorClient;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.client.exception.GatewayException;
import com.ohayoyo.gateway.client.restful.builder.RestfulResponseBuilder;
import com.ohayoyo.gateway.http.core.HttpGateway;
import com.ohayoyo.gateway.http.exception.HttpGatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

/**
 * @author 蓝明乐
 */
public class RestfulClient extends BehaviorClient {

    public static final Logger LOGGER = LoggerFactory.getLogger(RestfulClient.class);

    @Override
    protected <ResponseBody, RequestBody> void doSession(HttpGateway httpGateway, RestfulResponseBuilder restfulResponseBuilder, Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException {
        try {
            RequestEntity<RequestBody> requestEntity = this.resolveRequestEntity(gatewayDefine, gatewayRequest);
            MediaType customRequestContentType = this.resolveCustomRequestContentType(gatewayDefine);
            MediaType customResponseContentType = this.resolveCustomResponseContentType(gatewayDefine);
            ResponseEntity<ResponseBody> responseEntity = httpGateway.handler(customRequestContentType, customResponseContentType, responseBodyClass, requestEntity);
            restfulResponseBuilder.statusCode(responseEntity.getStatusCode().value());
            restfulResponseBuilder.reasonPhrase(responseEntity.getStatusCode().getReasonPhrase());
            restfulResponseBuilder.responseHeaders(responseEntity.getHeaders());
            restfulResponseBuilder.responseBody(responseEntity.getBody());
        } catch (HttpGatewayException ex) {
            throw new GatewayException(ex);
        }
    }

}
