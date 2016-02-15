package com.ohayoyo.gateway.channel;

import com.ohayoyo.gateway.client.GatewayClient;
import com.ohayoyo.gateway.client.GatewayRequest;
import com.ohayoyo.gateway.client.restful.RestfulGatewayClient;
import com.ohayoyo.gateway.client.restful.RestfulGatewayRequestBuilder;
import com.ohayoyo.gateway.container.GatewayContainer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringGatewayChannel implements GatewayChannel, InitializingBean, ApplicationContextAware {

    public static final String OVERRIDE_DELEGATE_GATEWAY_CHANNEL_NAME = "overrideDelegateGatewayChannel";

    @Autowired
    private ApplicationContext applicationContext;

    private GatewayChannel delegateGatewayChannel;

    private GatewayContainer gatewayContainer;

    private GatewayClient gatewayClient;

    public SpringGatewayChannel() {
    }

    public SpringGatewayChannel(GatewayContainer gatewayContainer) {
        this.gatewayContainer = gatewayContainer;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (null == this.gatewayContainer) {
            try {
                this.gatewayContainer = applicationContext.getBean(SpringGatewayContainer.class);
            } catch (Exception ex) {
                //node
            }
            if (null == this.gatewayContainer) {
                SpringGatewayContainer springGatewayContainer = new SpringGatewayContainer();
                springGatewayContainer.setApplicationContext(this.applicationContext);
                springGatewayContainer.afterPropertiesSet();
                this.gatewayContainer = springGatewayContainer;
            }
        }
        if (null == this.gatewayClient) {
            this.gatewayClient = RestfulGatewayClient.getDefaultGatewayClient();
        }
        if (null == this.delegateGatewayChannel) {
            try {
                this.delegateGatewayChannel = this.applicationContext.getBean(OVERRIDE_DELEGATE_GATEWAY_CHANNEL_NAME, GatewayChannel.class);
            } catch (Exception ex) {
                //node
            }
        }
        if (null == this.delegateGatewayChannel) {
            this.delegateGatewayChannel = new DefaultGatewayChannel(this.gatewayContainer, this.gatewayClient);
        }
    }

    @Override
    public GatewayContainer getGatewayContainer() {
        return gatewayContainer;
    }

    @Override
    public SpringGatewayChannel setGatewayContainer(GatewayContainer gatewayContainer) {
        this.gatewayContainer = gatewayContainer;
        return this;
    }

    @Override
    public GatewayClient getGatewayClient() {
        return gatewayClient;
    }

    @Override
    public SpringGatewayChannel setGatewayClient(GatewayClient gatewayClient) {
        this.gatewayClient = gatewayClient;
        return this;
    }

    @Override
    public RestfulGatewayRequestBuilder newRestfulGatewayRequestBuilder() {
        return this.delegateGatewayChannel.newRestfulGatewayRequestBuilder();
    }

    @Override
    public <Result> Result channel(Class<Result> gatewayResultClass, String interfaceDefineKey, GatewayRequest<Object> gatewayRequest) throws Exception {
        return this.delegateGatewayChannel.channel(gatewayResultClass, interfaceDefineKey, gatewayRequest);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
