package com.ohayoyo.gateway.client.spring;

import com.ohayoyo.gateway.client.autofill.GatewayAutofill;
import com.ohayoyo.gateway.client.channel.ClientChannel;
import com.ohayoyo.gateway.client.channel.GatewayChannel;
import com.ohayoyo.gateway.client.core.BehaviorClient;
import com.ohayoyo.gateway.client.core.GatewayClient;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.client.exception.GatewayException;
import com.ohayoyo.gateway.client.validator.GatewayDataValidator;
import com.ohayoyo.gateway.client.validator.GatewayDefineValidator;
import com.ohayoyo.gateway.client.validator.GatewayResultValidator;
import com.ohayoyo.gateway.define.container.GatewayContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public static final Logger LOGGER = LoggerFactory.getLogger(SpringClientChannel.class);

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

    private <Bean> Bean applicationContextLoadBean(Class<Bean> type) {
        try {
            return applicationContext.getBean(type);
        } catch (Exception ex) {
            LOGGER.info("加载失败 {} Bean .", type);
            return null;
        }
    }

    private <Bean> Bean applicationContextLoadBean(String name, Class<Bean> type) {
        try {
            return applicationContext.getBean(name, type);
        } catch (Exception ex) {
            LOGGER.info("加载失败 {} Bean .", type);
            return null;
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        LOGGER.debug("初始化 SpringClientChannel Bean .");

        if (ObjectUtils.isEmpty(this.gatewayContainer)) {
            LOGGER.debug("不存在引用网关容器对象 .");
            this.gatewayContainer = applicationContextLoadBean(SpringClientContainer.class);
            if (ObjectUtils.isEmpty(this.gatewayContainer)) {
                LOGGER.debug("手动初始化 SpringClientContainer 对象 .");
                SpringClientContainer springClientContainer = new SpringClientContainer();
                springClientContainer.setApplicationContext(this.applicationContext);
                springClientContainer.afterPropertiesSet();
                this.gatewayContainer = springClientContainer;
                LOGGER.debug("手动初始化 SpringClientContainer 完成 .");
            }

        }

        if (ObjectUtils.isEmpty(this.gatewayClient)) {
            LOGGER.debug("不存在引用网关客户端对象 .");
            this.gatewayClient = GatewayClient.DEFAULT_CLIENT;
            LOGGER.debug("使用默认的网关客户端对象 .");
        }

        if (ObjectUtils.isEmpty(this.delegateGatewayChannel)) {
            LOGGER.debug("不存在引用委托网关通道对象 .");
            this.delegateGatewayChannel = applicationContextLoadBean(OVERRIDE_DELEGATE_GATEWAY_CHANNEL_NAME, GatewayChannel.class);
        }

        if (ObjectUtils.isEmpty(this.delegateGatewayChannel)) {
            LOGGER.debug("手动初始化委托网关通道对象 .");
            this.delegateGatewayChannel = new ClientChannel(this.gatewayContainer, this.gatewayClient);
            LOGGER.debug("手动初始化委托网关通道完成 .");
        }

        if (this.gatewayClient instanceof ApplicationContextAware) {
            LOGGER.debug("网关客户端是实现了ApplicationContextAware接口,注入applicationContext .");
            ((ApplicationContextAware) this.gatewayClient).setApplicationContext(this.applicationContext);
        }

        if (this.gatewayClient instanceof BehaviorClient) {

            ((BehaviorClient) this.gatewayClient)
                    //加载定义验证器bean
                    .setGatewayDefineValidator(applicationContextLoadBean(GatewayDefineValidator.class))
                    //加载数据验证器bean
                    .setGatewayDataValidator(applicationContextLoadBean(GatewayDataValidator.class))
                    //加载结果验证器bean
                    .setGatewayResultValidator(applicationContextLoadBean(GatewayResultValidator.class))
                    //加载自动填充验证器bean
                    .setGatewayAutofill(applicationContextLoadBean(GatewayAutofill.class));

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
    public String channel(String interfaceDefineKey) throws GatewayException {
        return this.delegateGatewayChannel.channel(interfaceDefineKey);
    }

    @Override
    public String channel(String interfaceDefineKey, GatewayRequest<Object> gatewayRequest) throws GatewayException {
        return this.delegateGatewayChannel.channel(interfaceDefineKey, gatewayRequest);
    }

    @Override
    public <Result> Result channel(Class<Result> responseType, String interfaceDefineKey) throws GatewayException {
        return this.delegateGatewayChannel.channel(responseType, interfaceDefineKey);
    }

    @Override
    public <Result> Result channel(Class<Result> responseType, String interfaceDefineKey, GatewayRequest<Object> gatewayRequest) throws GatewayException {
        return this.delegateGatewayChannel.channel(responseType, interfaceDefineKey, gatewayRequest);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
