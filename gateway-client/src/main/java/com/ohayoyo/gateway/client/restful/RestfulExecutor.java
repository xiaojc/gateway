package com.ohayoyo.gateway.client.restful;

import com.ohayoyo.gateway.client.core.GatewayException;
import com.ohayoyo.gateway.client.core.GatewayExecutor;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.*;
import org.springframework.http.client.support.InterceptingHttpAccessor;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.ResponseExtractor;

import java.io.IOException;
import java.net.URI;

public class RestfulExecutor extends InterceptingHttpAccessor implements GatewayExecutor {

    private ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

    private ClientHttpRequestFactory delegateClientHttpRequestFactory;

    private boolean buffering = false;

    public RestfulExecutor() {
        this(new SimpleClientHttpRequestFactory(), false);
    }

    public RestfulExecutor(ClientHttpRequestFactory clientHttpRequestFactory, boolean isBuffering) {
        this.buffering = isBuffering;
        this.delegateClientHttpRequestFactory = clientHttpRequestFactory;
        if (this.isBuffering()) {
            this.enableBuffering();
        } else {
            this.disableBuffering();
        }
    }

    public static RestfulExecutor byHttpComponents() {
        return new RestfulExecutor(new HttpComponentsClientHttpRequestFactory(), false);
    }

    public static RestfulExecutor byHttpComponentsAsync() {
        return new RestfulExecutor(new HttpComponentsAsyncClientHttpRequestFactory(), false);
    }

    public static RestfulExecutor byNetty4() {
        return new RestfulExecutor(new Netty4ClientHttpRequestFactory(), false);
    }

    public static RestfulExecutor byOkHttp() {
        return new RestfulExecutor(new OkHttpClientHttpRequestFactory(), false);
    }

    public void enableBuffering() {
        BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory = new BufferingClientHttpRequestFactory(this.delegateClientHttpRequestFactory);
        this.setRequestFactory(bufferingClientHttpRequestFactory);
        this.buffering = true;
    }

    public void disableBuffering() {
        this.setRequestFactory(this.delegateClientHttpRequestFactory);
        this.buffering = false;
    }

    @Override
    public <T> T execute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws GatewayException {
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
            throw new GatewayException("I/O error on " + method.name() + " request for \"" + url + "\": " + ex.getMessage(), ex);
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

    public boolean isBuffering() {
        return buffering;
    }

    public ResponseErrorHandler getErrorHandler() {
        return errorHandler;
    }

    public RestfulExecutor setErrorHandler(ResponseErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
        return this;
    }

}
