package com.ohayoyo.gateway.channel.support;

import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.memory.support.AutoScanMemoryGatewayContainer;
import com.ohayoyo.gateway.session.core.GatewayContext;
import org.springframework.util.ObjectUtils;

public class ScanMemoryGatewayChannel extends ScanContextGatewayChannel {

    @Override
    protected void configDefaultContainer() throws Exception {
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
