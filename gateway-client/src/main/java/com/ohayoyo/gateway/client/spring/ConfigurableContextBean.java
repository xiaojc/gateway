package com.ohayoyo.gateway.client.spring;

import com.ohayoyo.gateway.client.core.ConfigurableContext;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author 蓝明乐
 */
public class ConfigurableContextBean extends ConfigurableContext implements InitializingBean {

    public ConfigurableContextBean() {
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.configDefaults();
    }

}
