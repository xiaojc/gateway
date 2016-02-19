package com.ohayoyo.gateway.client.core;

import com.ohayoyo.gateway.client.autofill.ClientAutofill;
import com.ohayoyo.gateway.client.autofill.GatewayAutofill;
import com.ohayoyo.gateway.client.restful.RestfulClient;
import com.ohayoyo.gateway.client.validator.*;
import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.define.memory.container.MemoryGatewayContainer;
import com.ohayoyo.gateway.http.converter.HttpGatewayMessageConverters;
import com.ohayoyo.gateway.http.core.HttpGateway;
import com.ohayoyo.gateway.http.core.HttpGatewayHandler;
import com.ohayoyo.gateway.http.core.HttpGatewayRequest;
import com.ohayoyo.gateway.http.core.HttpGatewayResponse;
import com.ohayoyo.gateway.http.request.HttpGatewayRequestHandler;
import com.ohayoyo.gateway.http.response.HttpGatewayResponseHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author 蓝明乐
 */
public class ConfigurableContext extends AbstractContext {

    public ConfigurableContext() {
    }

    protected void configDefaults() {
        configDefaultConversionService();
        configDefaultHttpMessageConverters();
        configDefaultHttpGatewayRequest();
        configDefaultHttpGatewayResponse();
        configDefaultClientHttpRequestInterceptors();
        configDefaultClientHttpRequestFactory();
        configDefaultHttpGateway();
        configDefaultGatewayContainer();
        configDefaultGatewayClient();
        configDefaultGatewayDefineValidator();
        configDefaultGatewayDataValidator();
        configDefaultGatewayResultValidator();
        configDefaultGatewayAutofill();
        configDefaultEnvironment();
    }

    protected ConfigurableContext configDefaultConversionService() {
        if (ObjectUtils.isEmpty(this.getConversionService())) {
            ConversionService conversionService = new DefaultFormattingConversionService();
            this.setConversionService(conversionService);
        }
        return this;
    }

    protected ConfigurableContext configDefaultHttpMessageConverters() {
        if (ObjectUtils.isEmpty(this.getHttpMessageConverters())) {
            List<HttpMessageConverter<?>> httpMessageConverters = new HttpGatewayMessageConverters();
            this.setHttpMessageConverters(httpMessageConverters);
        }
        return this;
    }

    protected ConfigurableContext configDefaultHttpGatewayRequest() {
        if (ObjectUtils.isEmpty(this.getHttpGatewayRequest())) {
            HttpGatewayRequest httpGatewayRequest = new HttpGatewayRequestHandler();
            this.setHttpGatewayRequest(httpGatewayRequest);
        }
        return this;
    }

    protected ConfigurableContext configDefaultHttpGatewayResponse() {
        if (ObjectUtils.isEmpty(this.getHttpGatewayResponse())) {
            HttpGatewayResponse httpGatewayResponse = new HttpGatewayResponseHandler();
            this.setHttpGatewayResponse(httpGatewayResponse);
        }
        return this;
    }

    protected ConfigurableContext configDefaultClientHttpRequestInterceptors() {
        if (ObjectUtils.isEmpty(this.getClientHttpRequestInterceptors())) {
            this.setClientHttpRequestInterceptors(null);
        }
        return this;
    }

    protected ConfigurableContext configDefaultClientHttpRequestFactory() {
        if (ObjectUtils.isEmpty(this.getClientHttpRequestFactory())) {
            ClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
            this.setClientHttpRequestFactory(clientHttpRequestFactory);
        }
        return this;
    }

    protected ConfigurableContext configDefaultHttpGateway() {
        if (ObjectUtils.isEmpty(this.getHttpGateway())) {
            HttpGateway httpGateway = new HttpGatewayHandler();
            this.setHttpGateway(httpGateway);
        }
        return this;
    }

    protected ConfigurableContext configDefaultGatewayContainer() {
        if (ObjectUtils.isEmpty(this.getGatewayContainer())) {
            GatewayContainer gatewayContainer = new MemoryGatewayContainer();
            this.setGatewayContainer(gatewayContainer);
        }
        return this;
    }

    protected ConfigurableContext configDefaultGatewayClient() {
        if (ObjectUtils.isEmpty(this.getGatewayClient())) {
            GatewayClient gatewayClient = new RestfulClient();
            gatewayClient.setGatewayContext(this);
            this.setGatewayClient(gatewayClient);
        }
        return this;
    }

    protected ConfigurableContext configDefaultGatewayDefineValidator() {
        if (ObjectUtils.isEmpty(this.getGatewayDefineValidator())) {
            GatewayDefineValidator gatewayDefineValidator = new ClientDefineValidator();
            gatewayDefineValidator.setGatewayContext(this);
            this.setGatewayDefineValidator(gatewayDefineValidator);
        }
        return this;
    }

    protected ConfigurableContext configDefaultGatewayDataValidator() {
        if (ObjectUtils.isEmpty(this.getGatewayDataValidator())) {
            GatewayDataValidator gatewayDataValidator = new ClientDataValidator();
            gatewayDataValidator.setGatewayContext(this);
            this.setGatewayDataValidator(gatewayDataValidator);
        }
        return this;
    }

    protected ConfigurableContext configDefaultGatewayResultValidator() {
        if (ObjectUtils.isEmpty(this.getGatewayResultValidator())) {
            GatewayResultValidator gatewayResultValidator = new ClientResultValidator();
            gatewayResultValidator.setGatewayContext(this);
            this.setGatewayResultValidator(gatewayResultValidator);
        }
        return this;
    }

    protected ConfigurableContext configDefaultGatewayAutofill() {
        if (ObjectUtils.isEmpty(this.getGatewayAutofill())) {
            GatewayAutofill gatewayAutofill = new ClientAutofill();
            gatewayAutofill.setGatewayContext(this);
            this.setGatewayAutofill(gatewayAutofill);
        }
        return this;
    }

    protected ConfigurableContext configDefaultEnvironment() {
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
        return this;
    }

}
