package com.ohayoyo.gateway.client.spring;

import com.ohayoyo.gateway.client.channel.ClientChannel;
import com.ohayoyo.gateway.client.core.GatewayContext;
import com.ohayoyo.gateway.client.utils.ApplicationContextUtils;
import com.ohayoyo.gateway.define.container.GatewayContainer;
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
public class ScanConfigurationChannel extends ClientChannel implements InitializingBean, ApplicationContextAware {

    public static final Logger LOGGER = LoggerFactory.getLogger(ScanConfigurationChannel.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        GatewayContext gatewayContext = this.getGatewayContext();
        if (ObjectUtils.isEmpty(gatewayContext)) {
            GatewayContext gatewayContextBean = ApplicationContextUtils.tryAdaptiveFirstBean(this.applicationContext, GatewayContext.class);
            if (!ObjectUtils.isEmpty(gatewayContextBean)) {
                gatewayContext = gatewayContextBean;
                this.setGatewayContext(gatewayContext);
            }
        }
        this.configDefaults(gatewayContext);
    }

    protected void configDefaults(GatewayContext gatewayContext) throws Exception {
        if (ObjectUtils.isEmpty(gatewayContext)) {
            ScanConfigurableContext scanConfigurableContext = new ScanConfigurableContext();
            scanConfigurableContext.setApplicationContext(this.applicationContext);
            scanConfigurableContext.afterPropertiesSet();
            gatewayContext = scanConfigurableContext;
        }
        GatewayContainer gatewayContainer = gatewayContext.getGatewayContainer();
        if (ObjectUtils.isEmpty(gatewayContainer)) {
            ScanConfigurationContainer scanConfigurationContainer = new ScanConfigurationContainer();
            scanConfigurationContainer.setApplicationContext(this.applicationContext);
            scanConfigurationContainer.afterPropertiesSet();
            gatewayContainer = scanConfigurationContainer;
            gatewayContext.setGatewayContainer(gatewayContainer);
        }
        this.setGatewayContext(gatewayContext);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    @Autowired(required = false)
    public void setGatewayContext(GatewayContext gatewayContext) {
        super.setGatewayContext(gatewayContext);
    }

}
