package com.ohayoyo.gateway.memory.support;

import com.ohayoyo.gateway.define.AbstractGatewayContainer;
import com.ohayoyo.gateway.define.ConfigurableGatewayContainer;
import com.ohayoyo.gateway.memory.MemoryGatewayInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Configuration
public class MemoryGatewayContainer extends AbstractGatewayContainer<MemoryGatewayInterface> implements ConfigurableGatewayContainer<MemoryGatewayInterface> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemoryGatewayContainer.class);

    private ApplicationContext applicationContext;

    private Map<String, MemoryGatewayInterface> memoryGatewayInterfaceMap;

    public void setMemoryGatewayInterfaceMap(Map<String, MemoryGatewayInterface> memoryGatewayInterfaceMap) {
        this.memoryGatewayInterfaceMap = memoryGatewayInterfaceMap;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (CollectionUtils.isEmpty(this.memoryGatewayInterfaceMap)) {
            this.memoryGatewayInterfaceMap = new HashMap<String, MemoryGatewayInterface>();
        }
        try {
            Map<String, MemoryGatewayInterface> memoryGatewayInterfaceMap = applicationContext.getBeansOfType(MemoryGatewayInterface.class);
            if (!CollectionUtils.isEmpty(memoryGatewayInterfaceMap)) {
                Set<Map.Entry<String, MemoryGatewayInterface>> entries = memoryGatewayInterfaceMap.entrySet();
                for (Map.Entry<String, MemoryGatewayInterface> entry : entries) {
                    MemoryGatewayInterface interfaces = entry.getValue();
                    if (!ObjectUtils.isEmpty(interfaces)) {
                        this.save(interfaces);
                    }
                }
            }
        } catch (Exception ex) {
            LOGGER.info("自动扫描错误,信息:{}", ex.getMessage());
        }
    }

    @Override
    public MemoryGatewayInterface query(String key) {
        if (!CollectionUtils.isEmpty(this.memoryGatewayInterfaceMap)) {
            if ((!StringUtils.isEmpty(key)) && this.memoryGatewayInterfaceMap.containsKey(key)) {
                return this.memoryGatewayInterfaceMap.get(key);
            }
        }
        return null;
    }

    public void save(MemoryGatewayInterface anInterface) {
        if (!ObjectUtils.isEmpty(anInterface)) {
            String key = anInterface.getKey();
            if (!StringUtils.isEmpty(key)) {
                this.memoryGatewayInterfaceMap.put(key, anInterface);
            }
        }
    }

    @Override
    public void remove(MemoryGatewayInterface anInterface) {
        if (!ObjectUtils.isEmpty(anInterface)) {
            String key = anInterface.getKey();
            if (!StringUtils.isEmpty(key)) {
                if (this.memoryGatewayInterfaceMap.containsKey(key)) {
                    this.memoryGatewayInterfaceMap.remove(key);
                }
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
