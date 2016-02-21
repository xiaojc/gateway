package com.ohayoyo.gateway.channel.support;

import com.ohayoyo.gateway.session.core.GatewayContext;
import com.ohayoyo.gateway.session.support.AutoScanGatewayContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

public abstract class ScanContextGatewayChannel extends ConfigurableGatewayChannel {

    private static final Logger LOGGER = LoggerFactory.getLogger(ScanContextGatewayChannel.class);

    @Override
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

}
