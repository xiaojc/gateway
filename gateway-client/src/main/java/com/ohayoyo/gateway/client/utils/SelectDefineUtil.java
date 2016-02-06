package com.ohayoyo.gateway.client.utils;

import com.ohayoyo.gateway.define.HostDefine;
import com.ohayoyo.gateway.define.MethodDefine;
import com.ohayoyo.gateway.define.ProtocolDefine;
import com.ohayoyo.gateway.define.UserDefine;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Set;

public class SelectDefineUtil {

    private static boolean selectCompareRule(String select, String scope) {
        if (!StringUtils.isEmpty(select) && !StringUtils.isEmpty(scope)) {
            return select.equals(scope);
        }
        return false;
    }

    private static boolean hasSelectScopes(String select, Set<String> scopes) {
        boolean isScope = false;
        if (!CollectionUtils.isEmpty(scopes)) {
            for (String scope : scopes) {
                if (selectCompareRule(select, scope)) {
                    isScope = true;
                    break;
                }
            }
        }
        return isScope;
    }

    public static ProtocolDefine selectProtocolDefine(String select, Set<ProtocolDefine> protocolDefines) {
        ProtocolDefine selectProtocolDefine = null;
        if (!StringUtils.isEmpty(select) && (!CollectionUtils.isEmpty(protocolDefines))) {
            for (ProtocolDefine protocolDefine : protocolDefines) {
                Set<String> scopes = protocolDefine.getScopes();
                if (hasSelectScopes(select, scopes)) {
                    selectProtocolDefine = protocolDefine;
                    break;
                }
            }
        }
        if (null == selectProtocolDefine && (!CollectionUtils.isEmpty(protocolDefines))) {
            Iterator<ProtocolDefine> protocolDefineIterator = protocolDefines.iterator();
            if (protocolDefineIterator.hasNext()) {
                selectProtocolDefine = protocolDefineIterator.next();
            }
        }
        return selectProtocolDefine;
    }

    @Deprecated
    public static UserDefine selectUserDefine(String select, Set<UserDefine> userDefines) {
        UserDefine selectUserDefine = null;
        if (!StringUtils.isEmpty(select) && (!CollectionUtils.isEmpty(userDefines))) {
            for (UserDefine userDefine : userDefines) {
                Set<String> scopes = userDefine.getScopes();
                if (hasSelectScopes(select, scopes)) {
                    selectUserDefine = userDefine;
                    break;
                }
            }
        }
        if (null == selectUserDefine && (!CollectionUtils.isEmpty(userDefines))) {
            Iterator<UserDefine> userDefineIterator = userDefines.iterator();
            if (userDefineIterator.hasNext()) {
                selectUserDefine = userDefineIterator.next();
            }
        }
        return selectUserDefine;
    }

    public static MethodDefine selectMethodDefine(String select, Set<MethodDefine> methodDefines) {
        MethodDefine selectMethodDefine = null;
        if (!StringUtils.isEmpty(select) && (!CollectionUtils.isEmpty(methodDefines))) {
            for (MethodDefine methodDefine : methodDefines) {
                Set<String> scopes = methodDefine.getScopes();
                if (hasSelectScopes(select, scopes)) {
                    selectMethodDefine = methodDefine;
                    break;
                }
            }
        }
        if (null == selectMethodDefine && (!CollectionUtils.isEmpty(methodDefines))) {
            Iterator<MethodDefine> methodDefineIterator = methodDefines.iterator();
            if (methodDefineIterator.hasNext()) {
                selectMethodDefine = methodDefineIterator.next();
            }
        }
        return selectMethodDefine;
    }

    public static HostDefine selectHostDefine(String select, Set<HostDefine> hostDefines) {
        HostDefine selectHostDefine = null;
        if (!StringUtils.isEmpty(select) && (!CollectionUtils.isEmpty(hostDefines))) {
            for (HostDefine hostDefine : hostDefines) {
                String scope = hostDefine.getScope();
                if (selectCompareRule(select, scope)) {
                    selectHostDefine = hostDefine;
                    break;
                }
            }
        }
        if (null == selectHostDefine && (!CollectionUtils.isEmpty(hostDefines))) {
            Iterator<HostDefine> hostDefineIterator = hostDefines.iterator();
            if (hostDefineIterator.hasNext()) {
                selectHostDefine = hostDefineIterator.next();
            }
        }
        return selectHostDefine;
    }

    public static Integer selectHostDefinePort(ProtocolDefine protocolDefine, HostDefine hostDefine) {
        return (!(hostDefine.getPort() != null && hostDefine.getPort() <= 0)) ? (ProtocolDefine.HTTPS_NAME.equals(protocolDefine.getName()) ? HostDefine.DEFAULT_HTTPS_PORT : HostDefine.DEFAULT_HTTP_PORT) : hostDefine.getPort();
    }

}
