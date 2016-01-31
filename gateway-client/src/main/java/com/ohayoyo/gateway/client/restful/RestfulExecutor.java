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

    private static final ThreadLocal<ClientHttpResponse> CLIENT_HTTP_RESPONSE_THREAD_LOCAL = new ThreadLocal<ClientHttpResponse>();

    private static final ThreadLocal<Boolean> EXECUTION_THREAD_LOCAL = new ThreadLocal<Boolean>();

    private final ClientHttpRequestFactory delegateClientHttpRequestFactory;

    private volatile boolean buffering = false;

    private volatile ResponseErrorHandler errorHandler = new DefaultResponseErrorHandler();

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


    @Override
    public void enableBuffering() {
        BufferingClientHttpRequestFactory bufferingClientHttpRequestFactory = new BufferingClientHttpRequestFactory(this.delegateClientHttpRequestFactory);
        this.setRequestFactory(bufferingClientHttpRequestFactory);
        this.buffering = true;
    }

    @Override
    public void disableBuffering() {
        this.setRequestFactory(this.delegateClientHttpRequestFactory);
        this.buffering = false;
    }

    @Override
    public void open() throws GatewayException {
        Boolean isExecution = EXECUTION_THREAD_LOCAL.get();
        if (null != isExecution && isExecution) {
            CLIENT_HTTP_RESPONSE_THREAD_LOCAL.set(null);
        }
    }

    @Override
    public <T> T execute(URI url, HttpMethod method, RequestCallback requestCallback, ResponseExtractor<T> responseExtractor) throws GatewayException {
        EXECUTION_THREAD_LOCAL.set(true);
        GatewayException gatewayException = null;
        T result = null;
        try {
            ClientHttpRequest clientHttpRequest = createRequest(url, method);
            requestCallback.doWithRequest(clientHttpRequest);
            ClientHttpResponse clientHttpResponse = clientHttpRequest.execute();
            CLIENT_HTTP_RESPONSE_THREAD_LOCAL.set(clientHttpResponse);
            responseErrorHandler(clientHttpResponse);
            result = responseExtractor.extractData(clientHttpResponse);

        } catch (IOException ex) {
            gatewayException = new GatewayException(ex);
        }
        EXECUTION_THREAD_LOCAL.set(false);
        if (null != gatewayException) {
            throw gatewayException;
        }
        return result;
    }

    @Override
    public void close() throws GatewayException {
        ClientHttpResponse clientHttpResponse = CLIENT_HTTP_RESPONSE_THREAD_LOCAL.get();
        if (null != clientHttpResponse) {
            clientHttpResponse.close();
        }
        EXECUTION_THREAD_LOCAL.set(null);
        CLIENT_HTTP_RESPONSE_THREAD_LOCAL.set(null);
    }

    protected void responseErrorHandler(ClientHttpResponse response) throws IOException {
        ResponseErrorHandler responseErrorHandler = getErrorHandler();
        boolean hasError = responseErrorHandler.hasError(response);
        if (hasError) {
            responseErrorHandler.handleError(response);
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
