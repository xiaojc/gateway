package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.autofill.ClientAutofill;
import com.ohayoyo.gateway.client.autofill.GatewayAutofill;
import com.ohayoyo.gateway.client.core.BehaviorClient;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.client.exception.GatewayException;
import com.ohayoyo.gateway.client.restful.builder.RestfulResponseBuilder;
import com.ohayoyo.gateway.client.validator.ClientDefineValidator;
import com.ohayoyo.gateway.client.validator.GatewayDefineValidator;
import com.ohayoyo.gateway.http.core.HttpGateway;
import com.ohayoyo.gateway.http.exception.HttpGatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;

/**
 * @author 蓝明乐
 */
public class RestfulClient extends BehaviorClient {

    public static final Logger LOGGER = LoggerFactory.getLogger(RestfulClient.class);

    private static RestfulClient DEFAULT_RESTFUL_CLIENT = null;

    private static final Object LOCKED = new Object();

    public RestfulClient() {
        this(HttpGateway.DEFAULT_HTTP_GATEWAY);
    }

    public RestfulClient(HttpGateway httpGateway) {
        this(httpGateway, null, null);
    }

    public RestfulClient(HttpGateway httpGateway, GatewayDefineValidator gatewayDefineValidator, GatewayAutofill gatewayAutofill) {
        super(httpGateway, gatewayDefineValidator, gatewayAutofill);
    }

    public static final RestfulClient defaultRestfulClient() {
        if (ObjectUtils.isEmpty(DEFAULT_RESTFUL_CLIENT)) {
            synchronized (LOCKED) {
                if (ObjectUtils.isEmpty(DEFAULT_RESTFUL_CLIENT)) {
                    DEFAULT_RESTFUL_CLIENT = new RestfulClient(HttpGateway.DEFAULT_HTTP_GATEWAY, new ClientDefineValidator(), new ClientAutofill());
                }
            }
        }
        return DEFAULT_RESTFUL_CLIENT;
    }

    @Override
    protected <ResponseBody, RequestBody> void doSession(HttpGateway httpGateway, RestfulResponseBuilder restfulResponseBuilder, Class<ResponseBody> responseBodyClass, GatewayDefine gatewayDefine, GatewayRequest<RequestBody> gatewayRequest) throws GatewayException {

        LOGGER.debug("进行详细会话 .");

        try {
            RequestEntity<RequestBody> requestEntity = this.resolveRequestEntity(gatewayDefine, gatewayRequest);

            LOGGER.debug("反转的请求实体:{} .", requestEntity);

            MediaType customRequestContentType = this.resolveCustomRequestContentType(gatewayDefine);

            LOGGER.debug("反转自定义请求内容类型:{}", customRequestContentType);

            MediaType customResponseContentType = this.resolveCustomResponseContentType(gatewayDefine);

            LOGGER.debug("反转自定义响应内容类型:{}", customResponseContentType);

            LOGGER.debug("HTTP网关进行处理.");

            ResponseEntity<ResponseBody> responseEntity = httpGateway.handler(customRequestContentType, customResponseContentType, responseBodyClass, requestEntity);

            LOGGER.debug("HTTP网关处理完成,结果:{}", requestEntity);

            restfulResponseBuilder.statusCode(responseEntity.getStatusCode().value());
            restfulResponseBuilder.reasonPhrase(responseEntity.getStatusCode().getReasonPhrase());
            restfulResponseBuilder.responseHeaders(responseEntity.getHeaders());
            restfulResponseBuilder.responseBody(responseEntity.getBody());

        } catch (HttpGatewayException ex) {
            throw new GatewayException(ex);
        }
    }

}
