package com.ohayoyo.gateway.channel.support;

import com.ohayoyo.gateway.channel.AbstractGatewayChannel;
import com.ohayoyo.gateway.channel.GatewayChannelException;
import com.ohayoyo.gateway.define.GatewayContainer;
import com.ohayoyo.gateway.memory.support.MemoryGatewayContainer;
import com.ohayoyo.gateway.session.GatewayContext;
import com.ohayoyo.gateway.session.support.AutoScanGatewayContext;
import com.ohayoyo.gateway.utils.ApplicationContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ClassUtils;
import org.springframework.util.ObjectUtils;

/**
 * @author 蓝明乐
 */
@Configuration
public class AutoScanGatewayChannel extends AbstractGatewayChannel implements InitializingBean, ApplicationContextAware {

    private static final boolean AUTO_SCAN_MEMORY_GATEWAY_CONTAINER = ClassUtils.isPresent("com.ohayoyo.gateway.memory.config.MemoryGatewayContainer", AutoScanGatewayChannel.class.getClassLoader());

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoScanGatewayChannel.class);

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

    protected void configDefaultGatewayContext() throws Exception {
        GatewayContext gatewayContext = this.getGatewayContext();
        if (ObjectUtils.isEmpty(gatewayContext)) {
            AutoScanGatewayContext autoScanContext = new AutoScanGatewayContext();
            autoScanContext.setApplicationContext(this.getApplicationContext());
            autoScanContext.afterPropertiesSet();
            gatewayContext = autoScanContext;
        }
        this.setGatewayContext(gatewayContext);
    }

    protected void configDefaultGatewayContainer() throws Exception {
        if (AUTO_SCAN_MEMORY_GATEWAY_CONTAINER) {
            GatewayContext gatewayContext = this.getGatewayContext();
            GatewayContainer gatewayContainer = gatewayContext.getGatewayContainer();
            if (ObjectUtils.isEmpty(gatewayContainer)) {
                MemoryGatewayContainer autoScanContainer = new MemoryGatewayContainer();
                autoScanContainer.setApplicationContext(this.getApplicationContext());
                autoScanContainer.afterPropertiesSet();
                gatewayContainer = autoScanContainer;
                gatewayContext.setGatewayContainer(gatewayContainer);
            }
        } else {
            GatewayChannelException.exception("不能配置默认的网关容器,不存在支持网关内存模块在类路径中.");
        }
    }

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
