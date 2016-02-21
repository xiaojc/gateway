package com.ohayoyo.gateway.channel.support;

import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.memory.support.AutoScanMemoryGatewayContainer;
import com.ohayoyo.gateway.session.core.GatewayContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

public class ScanMemoryGatewayChannel extends ScanContextGatewayChannel {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScanMemoryGatewayChannel.class);

    @Override
    protected void configDefaultGatewayContainer() throws Exception {
        GatewayContext gatewayContext = this.getGatewayContext();
        GatewayContainer containerDefine = gatewayContext.getGatewayContainer();
        if (ObjectUtils.isEmpty(containerDefine)) {
            AutoScanMemoryGatewayContainer autoScanContainer = new AutoScanMemoryGatewayContainer();
            autoScanContainer.setApplicationContext(this.getApplicationContext());
            autoScanContainer.afterPropertiesSet();
            containerDefine = autoScanContainer;
            gatewayContext.setGatewayContainer(containerDefine);
        }
    }

}
