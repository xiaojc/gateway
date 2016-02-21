package com.ohayoyo.gateway.channel.support;

import com.ohayoyo.gateway.channel.core.AbstractGatewayChannel;
import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.session.core.GatewayContext;
import com.ohayoyo.gateway.session.utils.ApplicationContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.ObjectUtils;

/**
 * @author 蓝明乐
 */
abstract class ConfigurableGatewayChannel extends AbstractGatewayChannel implements InitializingBean, ApplicationContextAware {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurableGatewayChannel.class);

    private ApplicationContext applicationContext;

    @Override
    public final void afterPropertiesSet() throws Exception {
        GatewayContext gatewayContext = this.getGatewayContext();
        if (ObjectUtils.isEmpty(gatewayContext)) {
            GatewayContext gatewayContextBean = ApplicationContextUtils.tryScanFirstBean(this.applicationContext, GatewayContext.class);
            if (!ObjectUtils.isEmpty(gatewayContextBean)) {
                gatewayContext = gatewayContextBean;
                this.setGatewayContext(gatewayContext);
            } else {
                configDefaultGatewayContext();
            }
        }
        GatewayContext configGatewayContext = this.getGatewayContext();
        GatewayContainer container = configGatewayContext.getGatewayContainer();
        if (ObjectUtils.isEmpty(container)) {
            configDefaultGatewayContainer();
        }
    }

    protected abstract void configDefaultGatewayContext() throws Exception;

    protected abstract void configDefaultGatewayContainer() throws Exception;

    @Override
    public final void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    @Autowired(required = false)
    public final void setGatewayContext(GatewayContext gatewayContext) {
        super.setGatewayContext(gatewayContext);
    }

    protected final ApplicationContext getApplicationContext() {
        return applicationContext;
    }

}
