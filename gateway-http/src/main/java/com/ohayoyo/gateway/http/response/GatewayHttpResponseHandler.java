package com.ohayoyo.gateway.http.response;

import com.ohayoyo.gateway.http.builder.GatewayHttpResponseEntityBuilder;
import com.ohayoyo.gateway.http.converter.GatewayHttpMessageConverters;
import com.ohayoyo.gateway.http.exception.GatewayHttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

/**
 * @author 蓝明乐
 */
public class GatewayHttpResponseHandler extends AbstractGatewayHttpResponse {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayHttpResponseHandler.class);

    @Override
    @SuppressWarnings("unchecked")
    protected <ResponseBody> ResponseBody responseBodyHandler(MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, GatewayHttpMessageConverters gatewayHttpMessageConverters, ClientHttpResponse clientHttpResponse) throws
            GatewayHttpException, IOException {
        GatewayHttpResponseWrapper gatewayHttpResponseWrapper = new GatewayHttpResponseWrapper(clientHttpResponse);
        if (gatewayHttpResponseWrapper.hasMessageBody() && (!gatewayHttpResponseWrapper.hasEmptyMessageBody())) {
            MediaType contentType = resolveCustomResponseContentType(customResponseContentType, gatewayHttpResponseWrapper);
            for (HttpMessageConverter<?> httpMessageConverter : gatewayHttpMessageConverters) {
                if ((httpMessageConverter instanceof GenericHttpMessageConverter) && (!ObjectUtils.isEmpty(responseBodyClass))) {
                    GenericHttpMessageConverter<?> genericHttpMessageConverter = (GenericHttpMessageConverter<?>) httpMessageConverter;
                    if (genericHttpMessageConverter.canRead(responseBodyClass, responseBodyClass, contentType)) {
                        return (ResponseBody) genericHttpMessageConverter.read(responseBodyClass, responseBodyClass, gatewayHttpResponseWrapper);
                    }
                }
                if (!ObjectUtils.isEmpty(responseBodyClass)) {
                    if (httpMessageConverter.canRead(responseBodyClass, contentType)) {
                        return (ResponseBody) httpMessageConverter.read((Class) responseBodyClass, gatewayHttpResponseWrapper);
                    }
                }
            }
            GatewayHttpException.exception("没有匹配合适的HTTP消息转换器支持读取数据操作,内容类型:%s,响应实体类型:%s.", contentType, responseBodyClass);
        }
        return null;
    }

    @Override
    protected HttpStatus responseHttpStatusHandler(ClientHttpResponse clientHttpResponse) throws GatewayHttpException, IOException {
        return clientHttpResponse.getStatusCode();
    }

    @Override
    protected HttpHeaders responseHttpHeadersHandler(MediaType customResponseContentType, ClientHttpResponse clientHttpResponse) throws GatewayHttpException, IOException {
        HttpHeaders httpHeaders = clientHttpResponse.getHeaders();
        HttpHeaders newHttpHeaders = new HttpHeaders();
        newHttpHeaders.putAll(httpHeaders);
        if (!ObjectUtils.isEmpty(customResponseContentType)) {
            MediaType contentType = httpHeaders.getContentType();
            if (!ObjectUtils.isEmpty(contentType)) {
                newHttpHeaders.set(DEFAULT_RESPONSE_CONTENT_TYPE, contentType.toString());
            }
            newHttpHeaders.setContentType(customResponseContentType);
            newHttpHeaders.set(CUSTOM_RESPONSE_CONTENT_TYPE, customResponseContentType.toString());
        }
        return newHttpHeaders;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected <ResponseBody> ResponseEntity<ResponseBody> responseEntityHandler(HttpStatus httpStatus, HttpHeaders httpHeaders, ResponseBody responseBody) throws GatewayHttpException, IOException {
        return (ResponseEntity<ResponseBody>) GatewayHttpResponseEntityBuilder.newInstance().httpStatus(httpStatus).headers(httpHeaders).body(responseBody).build();
    }

}
