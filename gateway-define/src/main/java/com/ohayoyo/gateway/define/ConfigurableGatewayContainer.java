package com.ohayoyo.gateway.define;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContextAware;

public interface ConfigurableGatewayContainer<Interface extends GatewayInterface> extends GatewayContainer<Interface>, InitializingBean, ApplicationContextAware {
}
