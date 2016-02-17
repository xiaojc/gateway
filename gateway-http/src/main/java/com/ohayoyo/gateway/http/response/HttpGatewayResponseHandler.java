package com.ohayoyo.gateway.http.response;

import com.ohayoyo.gateway.http.builder.HttpGatewayResponseEntityBuilder;
import com.ohayoyo.gateway.http.exception.HttpGatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

import java.io.IOException;
import java.util.List;

/**
 * @author 蓝明乐
 */
public class HttpGatewayResponseHandler extends AbstractHttpGatewayResponse {

    public static final Logger LOGGER = LoggerFactory.getLogger(HttpGatewayResponseHandler.class);

    @Override
    protected <ResponseBody> ResponseBody responseBodyHandler(MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, List<HttpMessageConverter<?>> httpMessageConverters, ClientHttpResponse clientHttpResponse) throws HttpGatewayException, IOException {
        HttpGatewayResponseWrapper httpGatewayResponseWrapper = new HttpGatewayResponseWrapper(clientHttpResponse);
        if (httpGatewayResponseWrapper.hasMessageBody() && (!httpGatewayResponseWrapper.hasEmptyMessageBody())) {
            MediaType contentType = resolveCustomResponseContentType(customResponseContentType, httpGatewayResponseWrapper);
            for (HttpMessageConverter<?> httpMessageConverter : httpMessageConverters) {
                if ((httpMessageConverter instanceof GenericHttpMessageConverter) && (null != responseBodyClass)) {
                    GenericHttpMessageConverter<?> genericHttpMessageConverter = (GenericHttpMessageConverter<?>) httpMessageConverter;
                    if (genericHttpMessageConverter.canRead(responseBodyClass, responseBodyClass, contentType)) {
                        return (ResponseBody) genericHttpMessageConverter.read(responseBodyClass, responseBodyClass, httpGatewayResponseWrapper);
                    }
                }
                if (null != responseBodyClass) {
                    if (httpMessageConverter.canRead(responseBodyClass, contentType)) {
                        return (ResponseBody) httpMessageConverter.read((Class) responseBodyClass, httpGatewayResponseWrapper);
                    }
                }
            }
            HttpGatewayException.exception("没有匹配合适的HTTP消息转换器支持读取数据操作,内容类型:%s,响应实体类型:%s.", contentType, responseBodyClass);
        }
        return null;
    }

    @Override
    protected HttpStatus responseHttpStatusHandler(ClientHttpResponse clientHttpResponse) throws HttpGatewayException, IOException {
        return clientHttpResponse.getStatusCode();
    }

    @Override
    protected HttpHeaders responseHttpHeadersHandler(MediaType customResponseContentType, ClientHttpResponse clientHttpResponse) throws HttpGatewayException, IOException {
        HttpHeaders httpHeaders = clientHttpResponse.getHeaders();
        HttpHeaders newHttpHeaders = new HttpHeaders();
        newHttpHeaders.putAll(httpHeaders);
        if (null != customResponseContentType) {
            MediaType contentType = httpHeaders.getContentType();
            if (null != contentType) {
                newHttpHeaders.set(DEFAULT_RESPONSE_CONTENT_TYPE, contentType.toString());
            }
            newHttpHeaders.setContentType(customResponseContentType);
            newHttpHeaders.set(CUSTOM_RESPONSE_CONTENT_TYPE, customResponseContentType.toString());
        }
        return newHttpHeaders;
    }

    private MediaType resolveCustomResponseContentType(MediaType customResponseContentType, ClientHttpResponse clientHttpResponse) {
        if (null != customResponseContentType) {
            return customResponseContentType;
        }
        HttpHeaders httpHeaders = clientHttpResponse.getHeaders();
        MediaType contentType = httpHeaders.getContentType();
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM;
        }
        return contentType;
    }

    @Override
    protected <ResponseBody> ResponseEntity<ResponseBody> responseEntityHandler(HttpStatus httpStatus, HttpHeaders httpHeaders, ResponseBody responseBody) throws HttpGatewayException, IOException {
        return (ResponseEntity<ResponseBody>) HttpGatewayResponseEntityBuilder.newInstance().httpStatus(httpStatus).headers(httpHeaders).body(responseBody).build();
    }

}
