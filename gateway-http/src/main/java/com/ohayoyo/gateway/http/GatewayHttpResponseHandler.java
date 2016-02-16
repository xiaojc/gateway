package com.ohayoyo.gateway.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * @author 蓝明乐
 */
public class GatewayHttpResponseHandler extends AbstractHttpResponseHandler {

    @Override
    protected <ResponseBody> ResponseBody doResponseBodyHandler(MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, List<HttpMessageConverter<?>> httpMessageConverters, ClientHttpResponse clientHttpResponse) throws HttpClientException, IOException {
        ClientHttpResponseWrapper clientHttpResponseWrapper = new ClientHttpResponseWrapper(clientHttpResponse);
        if (!clientHttpResponseWrapper.hasMessageBody() || clientHttpResponseWrapper.hasEmptyMessageBody()) {
            return null;
        }
        MediaType contentType = getCustomResponseContentType(customResponseContentType, clientHttpResponseWrapper);
        for (HttpMessageConverter<?> httpMessageConverter : httpMessageConverters) {
            if ((httpMessageConverter instanceof GenericHttpMessageConverter) && (null != responseBodyClass)) {
                GenericHttpMessageConverter<?> genericHttpMessageConverter = (GenericHttpMessageConverter<?>) httpMessageConverter;
                if (genericHttpMessageConverter.canRead((Type) responseBodyClass, responseBodyClass, contentType)) {
                    return (ResponseBody) genericHttpMessageConverter.read((Type) responseBodyClass, responseBodyClass, clientHttpResponseWrapper);
                }
            }
            if (null != responseBodyClass) {
                if (httpMessageConverter.canRead(responseBodyClass, contentType)) {
                    return (ResponseBody) httpMessageConverter.read((Class) responseBodyClass, clientHttpResponseWrapper);
                }
            }
        }
        throw new HttpClientException("no suitable HttpMessageConverter found for response type [" + (Type) responseBodyClass + "] and content type [" + contentType + "]");
    }

    @Override
    protected HttpStatus doResponseHttpStatusHandler(ClientHttpResponse clientHttpResponse) throws HttpClientException, IOException {
        return clientHttpResponse.getStatusCode();
    }

    @Override
    protected HttpHeaders doResponseHttpHeadersHandler(MediaType customResponseContentType, ClientHttpResponse clientHttpResponse) throws HttpClientException, IOException {
        HttpHeaders httpHeaders = clientHttpResponse.getHeaders();
        if (null != customResponseContentType) {
            MediaType contentType = httpHeaders.getContentType();
            if (null != contentType) {
                httpHeaders.set(DEFAULT_RESPONSE_CONTENT_TYPE, contentType.toString());
            }
            httpHeaders.setContentType(customResponseContentType);
            httpHeaders.set(CUSTOM_RESPONSE_CONTENT_TYPE, customResponseContentType.toString());
        }
        return httpHeaders;
    }

    private MediaType getCustomResponseContentType(MediaType customResponseContentType, ClientHttpResponse clientHttpResponse) {
        if (null != customResponseContentType) {
            return customResponseContentType;
        }
        MediaType contentType = clientHttpResponse.getHeaders().getContentType();
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM;
        }
        return contentType;
    }

    @Override
    protected <ResponseBody> ResponseEntity<ResponseBody> doResponseEntityHandler(HttpStatus httpStatus, HttpHeaders httpHeaders, ResponseBody responseBody) throws HttpClientException, IOException {
        return (ResponseEntity<ResponseBody>) GatewayResponseEntityBuilder.newInstance().httpStatus(httpStatus).headers(httpHeaders).body(responseBody).build();
    }

}
