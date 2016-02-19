package com.ohayoyo.gateway.client.core;

import com.ohayoyo.gateway.client.autofill.ClientAutofill;
import com.ohayoyo.gateway.client.autofill.GatewayAutofill;
import com.ohayoyo.gateway.client.restful.RestfulClient;
import com.ohayoyo.gateway.client.validator.*;
import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.define.memory.container.MemoryGatewayContainer;
import com.ohayoyo.gateway.http.converter.HttpGatewayMessageConverters;
import com.ohayoyo.gateway.http.converter.HttpGatewayMessageConvertersHandler;
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
import org.springframework.util.ObjectUtils;

/**
 * @author 蓝明乐
 */
public class ConfigurableContext extends AbstractContext {

    public ConfigurableContext() {
    }

    protected void configDefaults() {
        configDefaultConversionService();
        configDefaultHttpGatewayMessageConverters();
        configDefaultHttpGatewayRequest();
        configDefaultHttpGatewayResponse();
        configDefaultHttpGatewayRequestIntercepting();
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

    private void configDefaultConversionService() {
        if (ObjectUtils.isEmpty(this.getConversionService())) {
            ConversionService conversionService = new DefaultFormattingConversionService();
            this.setConversionService(conversionService);
        }
    }

    private void configDefaultHttpGatewayMessageConverters() {
        if (ObjectUtils.isEmpty(this.getHttpGatewayMessageConverters())) {
            HttpGatewayMessageConverters httpGatewayMessageConverters = new HttpGatewayMessageConvertersHandler();
            this.setHttpGatewayMessageConverters(httpGatewayMessageConverters);
        }
    }

    private void configDefaultHttpGatewayRequest() {
        if (ObjectUtils.isEmpty(this.getHttpGatewayRequest())) {
            HttpGatewayRequest httpGatewayRequest = new HttpGatewayRequestHandler();
            this.setHttpGatewayRequest(httpGatewayRequest);
        }
    }

    private void configDefaultHttpGatewayResponse() {
        if (ObjectUtils.isEmpty(this.getHttpGatewayResponse())) {
            HttpGatewayResponse httpGatewayResponse = new HttpGatewayResponseHandler();
            this.setHttpGatewayResponse(httpGatewayResponse);
        }
    }

    protected void configDefaultHttpGatewayRequestIntercepting() {
    }

    private void configDefaultClientHttpRequestFactory() {
        if (ObjectUtils.isEmpty(this.getClientHttpRequestFactory())) {
            ClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
            this.setClientHttpRequestFactory(clientHttpRequestFactory);
        }
    }

    private void configDefaultHttpGateway() {
        if (ObjectUtils.isEmpty(this.getHttpGateway())) {
            HttpGateway httpGateway = new HttpGatewayHandler();
            this.setHttpGateway(httpGateway);
        }
    }

    private void configDefaultGatewayContainer() {
        if (ObjectUtils.isEmpty(this.getGatewayContainer())) {
            GatewayContainer gatewayContainer = new MemoryGatewayContainer();
            this.setGatewayContainer(gatewayContainer);
        }
    }

    private void configDefaultGatewayClient() {
        if (ObjectUtils.isEmpty(this.getGatewayClient())) {
            GatewayClient gatewayClient = new RestfulClient();
            gatewayClient.setGatewayContext(this);
            this.setGatewayClient(gatewayClient);
        }
    }

    private void configDefaultGatewayDefineValidator() {
        if (ObjectUtils.isEmpty(this.getGatewayDefineValidator())) {
            GatewayDefineValidator gatewayDefineValidator = new ClientDefineValidator();
            gatewayDefineValidator.setGatewayContext(this);
            this.setGatewayDefineValidator(gatewayDefineValidator);
        }
    }

    private void configDefaultGatewayDataValidator() {
        if (ObjectUtils.isEmpty(this.getGatewayDataValidator())) {
            GatewayDataValidator gatewayDataValidator = new ClientDataValidator();
            gatewayDataValidator.setGatewayContext(this);
            this.setGatewayDataValidator(gatewayDataValidator);
        }
    }

    private void configDefaultGatewayResultValidator() {
        if (ObjectUtils.isEmpty(this.getGatewayResultValidator())) {
            GatewayResultValidator gatewayResultValidator = new ClientResultValidator();
            gatewayResultValidator.setGatewayContext(this);
            this.setGatewayResultValidator(gatewayResultValidator);
        }
    }

    private void configDefaultGatewayAutofill() {
        if (ObjectUtils.isEmpty(this.getGatewayAutofill())) {
            GatewayAutofill gatewayAutofill = new ClientAutofill();
            gatewayAutofill.setGatewayContext(this);
            this.setGatewayAutofill(gatewayAutofill);
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
