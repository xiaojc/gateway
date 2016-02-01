package com.ohayoyo.gateway.client.components;

import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseExtractor;

import java.io.IOException;

public class HeadersResponseExtractor implements ResponseExtractor<HttpHeaders> {
    /**
     * Extract data from the given {@code ClientHttpResponse} and return it.
     *
     * @param response the HTTP response
     * @return the extracted data
     * @throws IOException in case of I/O errors
     */
    @Override
    public HttpHeaders extractData(ClientHttpResponse response) throws IOException {
        return response.getHeaders();
    }

}
