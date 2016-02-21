package com.ohayoyo.gateway.session.utils;

import com.ohayoyo.gateway.define.http.GatewayHost;
import com.ohayoyo.gateway.define.http.GatewayMethod;
import com.ohayoyo.gateway.define.http.GatewayProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Set;

/**
 * @author 蓝明乐
 */
public final class GatewaySelectorUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(GatewaySelectorUtils.class);

    private static boolean selectCompareRule(String select, String scope) {
        if (!StringUtils.isEmpty(select) && !StringUtils.isEmpty(scope)) {
            return select.equalsIgnoreCase(scope);
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

    public static GatewayProtocol selectProtocolDefine(String select, Set<GatewayProtocol> protocols) {
        GatewayProtocol selectProtocolDefine = null;
        if (!StringUtils.isEmpty(select) && (!CollectionUtils.isEmpty(protocols))) {
            for (GatewayProtocol protocolDefine : protocols) {
                Set<String> scopes = protocolDefine.getScopes();
                if (hasSelectScopes(select, scopes)) {
                    selectProtocolDefine = protocolDefine;
                    break;
                }
            }
        }
        if (ObjectUtils.isEmpty(selectProtocolDefine) && (!CollectionUtils.isEmpty(protocols))) {
            Iterator<GatewayProtocol> protocolDefineIterator = protocols.iterator();
            if (protocolDefineIterator.hasNext()) {
                selectProtocolDefine = protocolDefineIterator.next();
            }
        }
        return selectProtocolDefine;
    }

    public static GatewayMethod selectMethodDefine(String select, Set<GatewayMethod> methods) {
        GatewayMethod selectMethodDefine = null;
        if (!StringUtils.isEmpty(select) && (!CollectionUtils.isEmpty(methods))) {
            for (GatewayMethod methodDefine : methods) {
                Set<String> scopes = methodDefine.getScopes();
                if (hasSelectScopes(select, scopes)) {
                    selectMethodDefine = methodDefine;
                    break;
                }
            }
        }
        if (ObjectUtils.isEmpty(selectMethodDefine) && (!CollectionUtils.isEmpty(methods))) {
            Iterator<GatewayMethod> methodDefineIterator = methods.iterator();
            if (methodDefineIterator.hasNext()) {
                selectMethodDefine = methodDefineIterator.next();
            }
        }
        return selectMethodDefine;
    }

    public static GatewayHost selectHostDefine(String select, Set<GatewayHost> hosts) {
        GatewayHost selectHostDefine = null;
        if (!StringUtils.isEmpty(select) && (!CollectionUtils.isEmpty(hosts))) {
            for (GatewayHost hostDefine : hosts) {
                String scope = hostDefine.getScope();
                if (selectCompareRule(select, scope)) {
                    selectHostDefine = hostDefine;
                    break;
                }
            }
        }
        if (ObjectUtils.isEmpty(selectHostDefine) && (!CollectionUtils.isEmpty(hosts))) {
            Iterator<GatewayHost> hostDefineIterator = hosts.iterator();
            if (hostDefineIterator.hasNext()) {
                selectHostDefine = hostDefineIterator.next();
            }
        }
        return selectHostDefine;
    }

    public static Integer selectHostDefinePort(GatewayProtocol protocol, GatewayHost host) {
        Integer port = host.getPort();
        if ((!ObjectUtils.isEmpty(port)) && (port >= 0 && port <= 65535)) {
            return port;
        } else {
            if (GatewayProtocol.HTTPS_NAME.equalsIgnoreCase(protocol.getName())) {
                return GatewayHost.DEFAULT_HTTPS_PORT;
            } else {
                return GatewayHost.DEFAULT_HTTP_PORT;
            }
        }
    }

}
