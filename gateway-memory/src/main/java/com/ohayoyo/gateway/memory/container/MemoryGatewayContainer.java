package com.ohayoyo.gateway.memory.container;

import com.ohayoyo.gateway.define.container.GatewayContainer;
import com.ohayoyo.gateway.define.resolver.GatewayTypeResolver;
import com.ohayoyo.gateway.memory.http.MemoryGatewayInterface;
import com.ohayoyo.gateway.memory.resolver.MemoryGatewayTypeResolver;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class MemoryGatewayContainer implements GatewayContainer<MemoryGatewayInterface> {

    private Map<String, MemoryGatewayInterface> memoryGatewayInterfaceMap;

    private MemoryGatewayTypeResolver memoryGatewayTypeResolver = new MemoryGatewayTypeResolver();

    public Map<String, MemoryGatewayInterface> getMemoryGatewayInterfaceMap() {
        return memoryGatewayInterfaceMap;
    }

    public void setMemoryGatewayInterfaceMap(Map<String, MemoryGatewayInterface> memoryGatewayInterfaceMap) {
        this.memoryGatewayInterfaceMap = memoryGatewayInterfaceMap;
    }

    protected void checkMemoryGatewayInterfaceMap() {
        if (CollectionUtils.isEmpty(this.memoryGatewayInterfaceMap)) {
            this.memoryGatewayInterfaceMap = new HashMap<String, MemoryGatewayInterface>();
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
        checkMemoryGatewayInterfaceMap();
        if (!ObjectUtils.isEmpty(anInterface)) {
            String key = anInterface.getKey();
            if (!StringUtils.isEmpty(key)) {
                this.memoryGatewayInterfaceMap.put(key, anInterface);
            }
        }
    }

    @Override
    public void remove(MemoryGatewayInterface anInterface) {
        checkMemoryGatewayInterfaceMap();
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
    public GatewayTypeResolver getTypeResolver() {
        return this.memoryGatewayTypeResolver;
    }

}
