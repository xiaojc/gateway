package com.ohayoyo.gateway.client.spring;

import com.ohayoyo.gateway.client.channel.ClientChannel;
import com.ohayoyo.gateway.client.channel.GatewayChannel;
import com.ohayoyo.gateway.client.core.GatewayClient;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.define.container.GatewayContainer;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

/**
 * @author 蓝明乐
 */
public class SpringClientChannel implements GatewayChannel, InitializingBean, ApplicationContextAware {

    public static final String OVERRIDE_DELEGATE_GATEWAY_CHANNEL_NAME = "overrideDelegateGatewayChannel";

    @Autowired
    private ApplicationContext applicationContext;

    private GatewayChannel delegateGatewayChannel;

    private GatewayContainer gatewayContainer;

    private GatewayClient gatewayClient;

    public SpringClientChannel() {
    }

    public SpringClientChannel(GatewayContainer gatewayContainer) {
        this(gatewayContainer, GatewayClient.DEFAULT_CLIENT);
    }

    public SpringClientChannel(GatewayContainer gatewayContainer, GatewayClient gatewayClient) {
        Assert.notNull(gatewayContainer);
        Assert.notNull(gatewayClient);
        this.gatewayContainer = gatewayContainer;
        this.gatewayClient = gatewayClient;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        if (ObjectUtils.isEmpty(this.gatewayContainer)) {

            try {
                this.gatewayContainer = applicationContext.getBean(SpringClientContainer.class);
            } catch (Exception ex) {
                //node
            }

            if (ObjectUtils.isEmpty(this.gatewayContainer)) {
                SpringClientContainer springClientContainer = new SpringClientContainer();
                springClientContainer.setApplicationContext(this.applicationContext);
                springClientContainer.afterPropertiesSet();
                this.gatewayContainer = springClientContainer;
            }

        }

        if (ObjectUtils.isEmpty(this.gatewayClient)) {
            this.gatewayClient = GatewayClient.DEFAULT_CLIENT;
        }

        if (ObjectUtils.isEmpty(this.delegateGatewayChannel)) {
            try {
                this.delegateGatewayChannel = this.applicationContext.getBean(OVERRIDE_DELEGATE_GATEWAY_CHANNEL_NAME, GatewayChannel.class);
            } catch (Exception ex) {
                //node
            }
        }

        if (ObjectUtils.isEmpty(this.delegateGatewayChannel)) {
            this.delegateGatewayChannel = new ClientChannel(this.gatewayContainer, this.gatewayClient);
        }

        if (this.gatewayClient instanceof ApplicationContextAware) {
            ((ApplicationContextAware) this.gatewayClient).setApplicationContext(this.applicationContext);
        }

    }

    @Override
    public GatewayContainer getGatewayContainer() {
        return gatewayContainer;
    }

    @Override
    public SpringClientChannel setGatewayContainer(GatewayContainer gatewayContainer) {
        this.gatewayContainer = gatewayContainer;
        return this;
    }

    @Override
    public GatewayClient getGatewayClient() {
        return gatewayClient;
    }

    @Override
    public SpringClientChannel setGatewayClient(GatewayClient gatewayClient) {
        this.gatewayClient = gatewayClient;
        return this;
    }

    @Override
    public <Result> Result channel(Class<Result> responseType, String interfaceDefineKey, GatewayRequest<Object> gatewayRequest) throws Exception {
        return this.delegateGatewayChannel.channel(responseType, interfaceDefineKey, gatewayRequest);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
