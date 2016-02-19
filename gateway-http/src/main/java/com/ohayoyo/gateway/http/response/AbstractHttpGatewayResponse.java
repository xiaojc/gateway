package com.ohayoyo.gateway.http.response;

import com.ohayoyo.gateway.http.converter.HttpGatewayMessageConverters;
import com.ohayoyo.gateway.http.core.HttpGatewayResponse;
import com.ohayoyo.gateway.http.exception.HttpGatewayException;
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
public abstract class AbstractHttpGatewayResponse implements HttpGatewayResponse {

    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractHttpGatewayResponse.class);

    private ResponseErrorHandler responseErrorHandler;

    public AbstractHttpGatewayResponse() {
        this(new DefaultResponseErrorHandler());
    }

    public AbstractHttpGatewayResponse(ResponseErrorHandler responseErrorHandler) {
        Assert.notNull(responseErrorHandler);
        this.responseErrorHandler = responseErrorHandler;
    }

    @Override
    public void responseErrorHandler(ClientHttpResponse clientHttpResponse) throws HttpGatewayException, IOException {
        if (!ObjectUtils.isEmpty(this.responseErrorHandler)) {
            boolean hasError = responseErrorHandler.hasError(clientHttpResponse);
            if (hasError) {
                responseErrorHandler.handleError(clientHttpResponse);
            }
        } else {
            HttpGatewayException.exception("响应错误处理器对象为空.");
        }
    }

    @Override
    public final <ResponseBody> ResponseEntity<ResponseBody> responseHandler(MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, HttpGatewayMessageConverters httpGatewayMessageConverters, ClientHttpResponse clientHttpResponse) throws HttpGatewayException,
            IOException {
        ResponseBody responseBody = this.responseBodyHandler(customResponseContentType, responseBodyClass, httpGatewayMessageConverters, clientHttpResponse);
        HttpStatus httpStatus = this.responseHttpStatusHandler(clientHttpResponse);
        HttpHeaders httpHeaders = this.responseHttpHeadersHandler(customResponseContentType, clientHttpResponse);
        ResponseEntity<ResponseBody> responseEntity = this.responseEntityHandler(httpStatus, httpHeaders, responseBody);
        return responseEntity;
    }

    protected abstract HttpStatus responseHttpStatusHandler(ClientHttpResponse clientHttpResponse) throws HttpGatewayException, IOException;

    protected abstract HttpHeaders responseHttpHeadersHandler(MediaType customResponseContentType, ClientHttpResponse clientHttpResponse) throws HttpGatewayException, IOException;

    protected abstract <ResponseBody> ResponseBody responseBodyHandler(MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, HttpGatewayMessageConverters httpGatewayMessageConverters, ClientHttpResponse clientHttpResponse) throws HttpGatewayException, IOException;

    protected abstract <ResponseBody> ResponseEntity<ResponseBody> responseEntityHandler(HttpStatus httpStatus, HttpHeaders httpHeaders, ResponseBody responseBody) throws HttpGatewayException, IOException;

    @Override
    public <ResponseBody> void responseCallback(MediaType customResponseContentType, ResponseEntity<ResponseBody> responseEntity, ClientHttpResponse clientHttpResponse) throws HttpGatewayException, IOException {
    }

    @Override
    public ResponseErrorHandler getResponseErrorHandler() {
        return responseErrorHandler;
    }

    @Override
    public AbstractHttpGatewayResponse setResponseErrorHandler(ResponseErrorHandler responseErrorHandler) {
        Assert.notNull(responseErrorHandler);
        this.responseErrorHandler = responseErrorHandler;
        return this;
    }
}
