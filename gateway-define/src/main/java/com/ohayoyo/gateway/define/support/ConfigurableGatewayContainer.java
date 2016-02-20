package com.ohayoyo.gateway.define.support;

import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.define.http.GatewayInterface;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContextAware;

public interface ConfigurableGatewayContainer<Interface extends GatewayInterface> extends GatewayContainer<Interface>, InitializingBean, ApplicationContextAware {
}
