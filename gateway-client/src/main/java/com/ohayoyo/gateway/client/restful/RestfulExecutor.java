package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.core.GatewayException;
import com.ohayoyo.gateway.client.core.GatewayExecutor;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.InterceptingHttpAccessor;
import org.springframework.web.client.*;

import java.io.IOException;
import java.net.URI;

public class RestfulExecutor extends InterceptingHttpAccessor implements GatewayExecutor {

    private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

    public RestfulExecutor() {
    }

    public RestfulExecutor(ClientHttpRequestFactory clientHttpRequestFactory) {
        this.setRequestFactory(clientHttpRequestFactory);
    }

    @Override
    public <T> T execute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws GatewayException {
        return doExecute(url, method, requestCallback, responseExtractor);
    }

    protected <T> T doExecute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws RestClientException {
        ClientHttpResponse response = null;
        try {
            ClientHttpRequest request = createRequest(url, method);
            if (requestCallback != null) {
                requestCallback.doWithRequest(request);
            }
            response = request.execute();
            handleResponse(url, method, response);
            if (responseExtractor != null) {
                return responseExtractor.extractData(response);
            } else {
                return null;
            }
        } catch (IOException ex) {
            throw new ResourceAccessException("I/O error on " + method.name() + " request for \"" + url + "\": " + ex.getMessage(), ex);
        } finally {
            if (response != null) {
                response.close();
            }
        }
    }

    protected void handleResponse(URI url, HttpMethod method, ClientHttpResponse response) throws IOException {
        ResponseErrorHandler errorHandler = getErrorHandler();
        boolean hasError = errorHandler.hasError(response);
        if (hasError) {
            errorHandler.handleError(response);
        }
    }


    public ResponseErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public RestfulExecutor setErrorHandler(ResponseErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
        return this;
    }

}
