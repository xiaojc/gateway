package com.ohayoyo.gateway.client.parts;

import com.ohayoyo.gateway.client.core.GatewayConfig;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayException;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.client.restful.RestfulEntity;
import com.ohayoyo.gateway.client.utils.ResponseTypeUtil;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.ResponseExtractor;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ResponseExtractorPart extends AbstractGatewayPart<ResponseExtractor> implements ResponseExtractor {

    @Override
    public ResponseExtractor getPart() throws GatewayException {
        return this;
    }

    @Override
    public Object extractData(ClientHttpResponse response) throws IOException {

        this.setClientHttpResponse(response);

        GatewayConfig gatewayConfig = this.getGatewayConfig();
        GatewayRequest gatewayRequest = this.getGatewayRequest();
        GatewayDefine gatewayDefine = this.getGatewayDefine();
        ClientHttpRequest clientHttpRequest = this.getClientHttpRequest();
        ClientHttpResponse clientHttpResponse = this.getClientHttpResponse();

        RestfulEntity restfulEntity = new RestfulEntity();
        Type responseType = ResponseTypeUtil.resolve(gatewayConfig, gatewayRequest, gatewayDefine, clientHttpRequest, clientHttpResponse);
        if (null == responseType) {
            throw new IOException("反转响应类型结果为空对象.");
        }
        try {

            List<HttpMessageConverter<?>> httpMessageConverters = gatewayConfig.getHttpMessageConverters();
            ResponseExtractor<Object> delegateResponseExtractor = new HttpMessageConverterExtractor(responseType, httpMessageConverters);
            Object httpData = delegateResponseExtractor.extractData(clientHttpResponse);

            restfulEntity.setGatewayConfig(gatewayConfig);
            restfulEntity.setGatewayRequest(gatewayRequest);
            restfulEntity.setGatewayDefine(gatewayDefine);
            restfulEntity.setClientHttpRequest(clientHttpRequest);
            restfulEntity.setClientHttpResponse(clientHttpResponse);

            restfulEntity.setHttpStatus(clientHttpResponse.getStatusCode());
            restfulEntity.setHttpHeaders(clientHttpResponse.getHeaders());
            restfulEntity.setHttpData(httpData);

        } catch (IOException ioe) {
            throw ioe;
        }

        return restfulEntity;
    }

}
