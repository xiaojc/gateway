package com.ohayoyo.gateway.client.spring;

import com.ohayoyo.gateway.client.channel.ClientChannel;
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
public class ClientChannelBean extends ClientChannel implements InitializingBean, ApplicationContextAware {

    public static final Logger LOGGER = LoggerFactory.getLogger(ClientChannelBean.class);

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (ObjectUtils.isEmpty(this.getGatewayContext())) {
            ConfigurableContextBean configurableContextBean = new ConfigurableContextBean();
            configurableContextBean.setApplicationContext(this.applicationContext);
            configurableContextBean.afterPropertiesSet();
            this.setGatewayContext(configurableContextBean);
        }
        GatewayContainer gatewayContainer = this.getGatewayContext().getGatewayContainer();
        if (ObjectUtils.isEmpty(gatewayContainer) || (!(gatewayContainer instanceof ScanConfigurationContainerBean))) {
            ScanConfigurationContainerBean scanConfigurationContainerBean = new ScanConfigurationContainerBean();
            scanConfigurationContainerBean.setApplicationContext(this.applicationContext);
            scanConfigurationContainerBean.afterPropertiesSet();
            this.getGatewayContext().setGatewayContainer(scanConfigurationContainerBean);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
