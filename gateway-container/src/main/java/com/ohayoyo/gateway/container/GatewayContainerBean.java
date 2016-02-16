package com.ohayoyo.gateway.container;

import com.ohayoyo.gateway.define.http.InterfaceDefine;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 蓝明乐
 */
public class GatewayContainerBean implements GatewayContainer {

    private Map<String, InterfaceDefine> interfaceDefineMap;

    public Map<String, InterfaceDefine> getInterfaceDefineMap() {
        return this.interfaceDefineMap;
    }

    public GatewayContainerBean setInterfaceDefineMap(Map<String, InterfaceDefine> interfaceDefineMap) {
        this.doInterfaceDefineMap();
        this.interfaceDefineMap.putAll(interfaceDefineMap);
        return this;
    }

    protected void doInterfaceDefineMap() {
        if (null == this.interfaceDefineMap) {
            this.interfaceDefineMap = new HashMap<String, InterfaceDefine>();
        }
    }

    @Override
    public InterfaceDefine query(String interfaceDefineKey) {
        if (null != this.interfaceDefineMap && this.interfaceDefineMap.size() > 0) {
            if ((null != interfaceDefineKey && (!"".equals(interfaceDefineKey.trim()))) && this.interfaceDefineMap.containsKey(interfaceDefineKey)) {
                return this.interfaceDefineMap.get(interfaceDefineKey);
            }
        }
        return null;
    }

    public GatewayContainerBean add(InterfaceDefine interfaceDefine) {
        doInterfaceDefineMap();
        if (null != interfaceDefine) {
            String key = interfaceDefine.getKey();
            if (null != key && (!"".equals(key.trim()))) {
                this.interfaceDefineMap.put(key, interfaceDefine);
            }
        }
        return this;
    }

}
