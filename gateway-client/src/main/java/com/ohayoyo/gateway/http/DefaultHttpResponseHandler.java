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

public class DefaultHttpResponseHandler extends AbstractHttpResponseHandler {

    @Override
    protected HttpStatus doResponseHttpStatusHandler(ClientHttpResponse clientHttpResponse) throws HttpClientException, IOException {
        return clientHttpResponse.getStatusCode();
    }

    @Override
    protected HttpHeaders doResponseHttpHeadersHandler(MediaType customResponseContentType, ClientHttpResponse clientHttpResponse) throws HttpClientException, IOException {
        HttpHeaders httpHeaders = clientHttpResponse.getHeaders();
        if (null != customResponseContentType) {
            String headerValues = customResponseContentType.toString();
            httpHeaders.set(CUSTOM_RESPONSE_CONTENT_TYPE, headerValues);
        }
        return httpHeaders;
    }

    @Override
    protected <ResponseBody> ResponseBody doResponseBodyHandler(MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, List<HttpMessageConverter<?>> httpMessageConverters, ClientHttpResponse clientHttpResponse) throws HttpClientException, IOException {
        ClientHttpResponseMessageBodyWrapper clientHttpResponseMessageBodyWrapper = new ClientHttpResponseMessageBodyWrapper(clientHttpResponse);
        if (!clientHttpResponseMessageBodyWrapper.hasMessageBody() || clientHttpResponseMessageBodyWrapper.hasEmptyMessageBody()) {
            return null;
        }
        MediaType contentType = getCustomResponseContentType(customResponseContentType, clientHttpResponseMessageBodyWrapper);
        for (HttpMessageConverter<?> httpMessageConverter : httpMessageConverters) {
            if ((httpMessageConverter instanceof GenericHttpMessageConverter) && (null != responseBodyClass)) {
                GenericHttpMessageConverter<?> genericHttpMessageConverter = (GenericHttpMessageConverter<?>) httpMessageConverter;
                if (genericHttpMessageConverter.canRead((Type) responseBodyClass, responseBodyClass, contentType)) {
                    return (ResponseBody) genericHttpMessageConverter.read((Type) responseBodyClass, responseBodyClass, clientHttpResponseMessageBodyWrapper);
                }
            }
            if (null != responseBodyClass) {
                if (httpMessageConverter.canRead(responseBodyClass, contentType)) {
                    return (ResponseBody) httpMessageConverter.read((Class) responseBodyClass, clientHttpResponseMessageBodyWrapper);
                }
            }
        }
        throw new HttpClientException("no suitable HttpMessageConverter found for response type [" + (Type) responseBodyClass + "] and content type [" + contentType + "]");
    }

    private MediaType getCustomResponseContentType(MediaType customResponseContentType, ClientHttpResponse response) {
        if (null != customResponseContentType) {
            return customResponseContentType;
        }
        MediaType contentType = response.getHeaders().getContentType();
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM;
        }
        return contentType;
    }

    @Override
    protected <ResponseBody> ResponseEntity<ResponseBody> doResponseEntityHandler(HttpStatus httpStatus, HttpHeaders httpHeaders, ResponseBody responseBody) throws HttpClientException, IOException {
        HttpResponseEntityBuilder<ResponseBody> responseEntityBuilder = new DefaultResponseEntityBuilder<ResponseBody>();
        ResponseEntity<ResponseBody> responseEntity = responseEntityBuilder.httpStatus(httpStatus).headers(httpHeaders).body(responseBody).build();
        return responseEntity;
    }

}
