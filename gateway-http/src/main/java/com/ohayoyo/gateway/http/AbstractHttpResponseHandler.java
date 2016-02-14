package com.ohayoyo.gateway.http;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.util.List;

public abstract class AbstractHttpResponseHandler implements HttpResponseHandler {

    private ResponseErrorHandler responseErrorHandler;

    public AbstractHttpResponseHandler() {
        this.responseErrorHandler = new DefaultResponseErrorHandler();
    }

    @Override
    public void responseErrorHandler(ClientHttpResponse clientHttpResponse) throws HttpClientException, IOException {
        if (null != this.responseErrorHandler) {
            boolean hasError = responseErrorHandler.hasError(clientHttpResponse);
            if (hasError) {
                responseErrorHandler.handleError(clientHttpResponse);
            }
        } else {
            throw new HttpClientException("response error handler is not");
        }
    }

    @Override
    public <ResponseBody> ResponseEntity<ResponseBody> responseHandler(MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, List<HttpMessageConverter<?>> httpMessageConverters, ClientHttpResponse clientHttpResponse) throws HttpClientException, IOException {
        ResponseBody responseBody = this.doResponseBodyHandler(customResponseContentType, responseBodyClass, httpMessageConverters, clientHttpResponse);
        HttpStatus httpStatus = this.doResponseHttpStatusHandler(clientHttpResponse);
        HttpHeaders httpHeaders = this.doResponseHttpHeadersHandler(customResponseContentType, clientHttpResponse);
        ResponseEntity<ResponseBody> responseEntity = this.doResponseEntityHandler(httpStatus, httpHeaders, responseBody);
        return responseEntity;
    }

    protected abstract HttpStatus doResponseHttpStatusHandler(ClientHttpResponse clientHttpResponse) throws HttpClientException, IOException;

    protected abstract HttpHeaders doResponseHttpHeadersHandler(MediaType customResponseContentType, ClientHttpResponse clientHttpResponse) throws HttpClientException, IOException;

    protected abstract <ResponseBody> ResponseBody doResponseBodyHandler(MediaType customResponseContentType, Class<ResponseBody> responseBodyClass, List<HttpMessageConverter<?>> httpMessageConverters, ClientHttpResponse clientHttpResponse) throws HttpClientException, IOException;

    protected abstract <ResponseBody> ResponseEntity<ResponseBody> doResponseEntityHandler(HttpStatus httpStatus, HttpHeaders httpHeaders, ResponseBody responseBody) throws HttpClientException, IOException;

    @Override
    public <ResponseBody> void responseCallback(MediaType customResponseContentType, ResponseEntity<ResponseBody> responseEntity, ClientHttpResponse clientHttpResponse) throws HttpClientException, IOException {
    }

    @Override
    public ResponseErrorHandler getResponseErrorHandler() {
        return responseErrorHandler;
    }

    @Override
    public AbstractHttpResponseHandler setResponseErrorHandler(ResponseErrorHandler responseErrorHandler) {
        Assert.notNull(responseErrorHandler);
        this.responseErrorHandler = responseErrorHandler;
        return this;
    }
}
