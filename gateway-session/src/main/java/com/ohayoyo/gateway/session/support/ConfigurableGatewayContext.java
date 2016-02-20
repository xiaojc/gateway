package com.ohayoyo.gateway.session.support;

import com.ohayoyo.gateway.http.client.GatewayHttpClient;
import com.ohayoyo.gateway.http.client.GatewayHttpClientHandler;
import com.ohayoyo.gateway.http.client.GatewayHttpRequest;
import com.ohayoyo.gateway.http.client.GatewayHttpResponse;
import com.ohayoyo.gateway.http.converter.GatewayHttpMessageConverters;
import com.ohayoyo.gateway.http.converter.GatewayHttpMessageConvertersHandler;
import com.ohayoyo.gateway.http.request.GatewayHttpRequestHandler;
import com.ohayoyo.gateway.http.response.GatewayHttpResponseHandler;
import com.ohayoyo.gateway.session.autofill.GatewayDataAutofill;
import com.ohayoyo.gateway.session.autofill.SessionDataAutofill;
import com.ohayoyo.gateway.session.core.AbstractGatewayContext;
import com.ohayoyo.gateway.session.core.GatewaySession;
import com.ohayoyo.gateway.session.restful.RestfulGatewaySession;
import com.ohayoyo.gateway.session.validator.*;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.ObjectUtils;

/**
 * @author 蓝明乐
 */
class ConfigurableGatewayContext extends AbstractGatewayContext {

    public ConfigurableGatewayContext() {
    }

    protected void adaptiveDefaults() {
        configDefaultConversionService();
        configDefaultHttpMessageConverters();
        configDefaultHttpRequest();
        configDefaultHttpResponse();
        configDefaultClientHttpRequestFactory();
        configDefaultHttpClient();
        configDefaultGatewaySession();
        configDefaultGatewayInterfaceValidator();
        configDefaultGatewayDataValidator();
        configDefaultGatewayResultValidator();
        configDefaultGatewayDataAutofill();
        configDefaultEnvironment();
    }

    private void configDefaultConversionService() {
        if (ObjectUtils.isEmpty(this.getConversionService())) {
            ConversionService conversionService = new DefaultFormattingConversionService();
            this.setConversionService(conversionService);
        }
    }

    private void configDefaultHttpMessageConverters() {
        if (ObjectUtils.isEmpty(this.getGatewayHttpMessageConverters())) {
            GatewayHttpMessageConverters gatewayHttpMessageConverters = new GatewayHttpMessageConvertersHandler();
            this.setGatewayHttpMessageConverters(gatewayHttpMessageConverters);
        }
    }

    private void configDefaultHttpRequest() {
        if (ObjectUtils.isEmpty(this.getGatewayHttpRequest())) {
            GatewayHttpRequest gatewayHttpRequest = new GatewayHttpRequestHandler();
            this.setGatewayHttpRequest(gatewayHttpRequest);
        }
    }

    private void configDefaultHttpResponse() {
        if (ObjectUtils.isEmpty(this.getGatewayHttpResponse())) {
            GatewayHttpResponse gatewayHttpResponse = new GatewayHttpResponseHandler();
            this.setGatewayHttpResponse(gatewayHttpResponse);
        }
    }

    private void configDefaultClientHttpRequestFactory() {
        if (ObjectUtils.isEmpty(this.getClientHttpRequestFactory())) {
            ClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
            this.setClientHttpRequestFactory(clientHttpRequestFactory);
        }
    }

    private void configDefaultHttpClient() {
        if (ObjectUtils.isEmpty(this.getGatewayHttpClient())) {
            GatewayHttpClient gatewayHttpClient = new GatewayHttpClientHandler();
            this.setGatewayHttpClient(gatewayHttpClient);
        }
    }

    private void configDefaultGatewaySession() {
        if (ObjectUtils.isEmpty(this.getGatewaySession())) {
            GatewaySession gatewaySession = new RestfulGatewaySession();
            gatewaySession.setGatewayContext(this);
            this.setGatewaySession(gatewaySession);
        }
    }

    private void configDefaultGatewayInterfaceValidator() {
        if (ObjectUtils.isEmpty(this.getGatewayInterfaceValidator())) {
            GatewayInterfaceValidator gatewayInterfaceValidator = new SessionGatewayInterfaceValidator();
            gatewayInterfaceValidator.setGatewayContext(this);
            this.setGatewayInterfaceValidator(gatewayInterfaceValidator);
        }
    }

    private void configDefaultGatewayDataValidator() {
        if (ObjectUtils.isEmpty(this.getGatewayDataValidator())) {
            GatewayDataValidator gatewayDataValidator = new SessionGatewayDataValidator();
            gatewayDataValidator.setGatewayContext(this);
            this.setGatewayDataValidator(gatewayDataValidator);
        }
    }

    private void configDefaultGatewayResultValidator() {
        if (ObjectUtils.isEmpty(this.getGatewayResultValidator())) {
            GatewayResultValidator gatewayResultValidator = new SessionGatewayResultValidator();
            gatewayResultValidator.setGatewayContext(this);
            this.setGatewayResultValidator(gatewayResultValidator);
        }
    }

    private void configDefaultGatewayDataAutofill() {
        if (ObjectUtils.isEmpty(this.getGatewayDataAutofill())) {
            GatewayDataAutofill gatewayDataAutofill = new SessionDataAutofill();
            gatewayDataAutofill.setGatewayContext(this);
            this.setGatewayDataAutofill(gatewayDataAutofill);
        }
    }

    private void configDefaultEnvironment() {
        if (ObjectUtils.isEmpty(this.getEnvironment())) {
            ApplicationContext applicationContext = this.getApplicationContext();
            Environment environment = null;
            if (!ObjectUtils.isEmpty(this.getApplicationContext())) {
                applicationContext.getEnvironment();
            } else {
                environment = new StandardEnvironment();
            }
            this.setEnvironment(environment);
        }
    }

}
