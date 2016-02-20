package com.ohayoyo.gateway.http.response;

import com.ohayoyo.gateway.http.converter.GatewayHttpMessageConverters;
import com.ohayoyo.gateway.http.client.GatewayHttpResponse;
import com.ohayoyo.gateway.http.exception.GatewayHttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * @author 蓝明乐
 */
public abstract class AbstractGatewayHttpResponse implements GatewayHttpResponse {

    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractGatewayHttpResponse.class);

    private ResponseErrorHandler responseErrorHandler;

    public AbstractGatewayHttpResponse() {
        this(new DefaultResponseErrorHandler());
    }

    public AbstractGatewayHttpResponse(ResponseErrorHandler responseErrorHandler) {
        Assert.notNull(responseErrorHandler);
        this.responseErrorHandler = responseErrorHandler;
    }

    @Override
    public void responseErrorHandler(ClientHttpResponse clientHttpResponse) throws GatewayHttpException, IOException {
        if (!ObjectUtils.isEmpty(this.responseErrorHandler)) {
            boolean hasError = responseErrorHandler.hasError(clientHttpResponse);
            if (hasError) {
                responseErrorHandler.handleError(clientHttpResponse);
            }
        } else {
            GatewayHttpException.exception("响应错误处理器对象为空.");
        }
    }

    @Override
    public final <ResponseBody> ResponseEntity<ResponseBody> responseHandler(MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, GatewayHttpMessageConverters gatewayHttpMessageConverters, ClientHttpResponse clientHttpResponse) throws GatewayHttpException,
            IOException {
        ResponseBody responseBody = this.responseBodyHandler(customResponseContentType, responseBodyClass, gatewayHttpMessageConverters, clientHttpResponse);
        HttpStatus httpStatus = this.responseHttpStatusHandler(clientHttpResponse);
        HttpHeaders httpHeaders = this.responseHttpHeadersHandler(customResponseContentType, clientHttpResponse);
        ResponseEntity<ResponseBody> responseEntity = this.responseEntityHandler(httpStatus, httpHeaders, responseBody);
        return responseEntity;
    }

    protected abstract HttpStatus responseHttpStatusHandler(ClientHttpResponse clientHttpResponse) throws GatewayHttpException, IOException;

    protected abstract HttpHeaders responseHttpHeadersHandler(MediaType customResponseContentType, ClientHttpResponse clientHttpResponse) throws GatewayHttpException, IOException;

    protected abstract <ResponseBody> ResponseBody responseBodyHandler(MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, GatewayHttpMessageConverters gatewayHttpMessageConverters, ClientHttpResponse clientHttpResponse) throws GatewayHttpException, IOException;

    protected abstract <ResponseBody> ResponseEntity<ResponseBody> responseEntityHandler(HttpStatus httpStatus, HttpHeaders httpHeaders, ResponseBody responseBody) throws GatewayHttpException, IOException;

    protected MediaType resolveCustomResponseContentType(MediaType customResponseContentType, ClientHttpResponse clientHttpResponse) {
        if (!ObjectUtils.isEmpty(customResponseContentType)) {
            return customResponseContentType;
        }
        HttpHeaders httpHeaders = clientHttpResponse.getHeaders();
        MediaType contentType = httpHeaders.getContentType();
        if (ObjectUtils.isEmpty(contentType)) {
            contentType = MediaType.APPLICATION_OCTET_STREAM;
        }
        return contentType;
    }

    @Override
    public <ResponseBody> void responseCallback(MediaType customResponseContentType, ResponseEntity<ResponseBody> responseEntity, ClientHttpResponse clientHttpResponse) throws GatewayHttpException, IOException {
    }

    @Override
    public ResponseErrorHandler getResponseErrorHandler() {
        return responseErrorHandler;
    }

    @Override
    public AbstractGatewayHttpResponse setResponseErrorHandler(ResponseErrorHandler responseErrorHandler) {
        Assert.notNull(responseErrorHandler);
        this.responseErrorHandler = responseErrorHandler;
        return this;
    }

}
