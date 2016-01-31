package com.ohayoyo.gateway.client.components;

import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayException;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.client.utils.PathDefineUtil;
import com.ohayoyo.gateway.client.utils.SelectDefineUtil;
import com.ohayoyo.gateway.define.core.HostDefine;
import com.ohayoyo.gateway.define.core.PathDefine;
import com.ohayoyo.gateway.define.core.ProtocolDefine;
import com.ohayoyo.gateway.define.core.RequestDefine;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.Set;

public class GatewayUri extends AbstractGatewayComponent<URI> {

    private UriComponents compileUriComponents() {

        GatewayRequest gatewayRequest = this.getGatewayRequest();
        GatewayDefine gatewayDefine = this.getGatewayDefine();

        String select = gatewayRequest.getSelect();
        Map<String, String> requestPathVariables = gatewayRequest.getRequestPathVariables();
        MultiValueMap<String, String> requestQueries = gatewayRequest.getRequestQueries();
        RequestDefine requestDefine = gatewayDefine.getRequest();
        Set<ProtocolDefine> protocolDefines = requestDefine.getProtocols();
        Set<HostDefine> hostDefines = requestDefine.getHosts();
        //Set<UserDefine> userDefines = requestDefine.getUserDefines();
        PathDefine pathDefine = requestDefine.getPath();
        String fragment = requestDefine.getFragment();
        ProtocolDefine protocolDefine = SelectDefineUtil.selectProtocolDefine(select, protocolDefines);
        //UserDefine userDefine = SelectDefineUtil.selectUserDefine(select, userDefines);
        HostDefine hostDefine = SelectDefineUtil.selectHostDefine(select, hostDefines);
        String scheme = protocolDefine.getName();
        //String userInfo = buildUserInfo(userDefine);
        String host = hostDefine.getHostname();
        int port = SelectDefineUtil.selectHostDefinePort(protocolDefine, hostDefine);
        String[] pathSegments = PathDefineUtil.pathSegments(pathDefine);
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        uriComponentsBuilder.scheme(scheme);
        //if (!StringUtils.isEmpty(userInfo)) {
        //    uriComponentsBuilder.userInfo(userInfo);
        //}
        uriComponentsBuilder.host(host);
        uriComponentsBuilder.port(port);
        if (!ObjectUtils.isEmpty(pathSegments)) {
            uriComponentsBuilder.pathSegment(pathSegments);
        }
        if (!StringUtils.isEmpty(fragment)) {
            uriComponentsBuilder.fragment(fragment);
        }
        if (!CollectionUtils.isEmpty(requestQueries)) {
            uriComponentsBuilder.queryParams(requestQueries);
        }
        UriComponents uriComponents;
        if (!CollectionUtils.isEmpty(requestPathVariables)) {
            uriComponents = uriComponentsBuilder.buildAndExpand(requestPathVariables);
        } else {
            uriComponents = uriComponentsBuilder.build();
        }
        return uriComponents;
    }

    //private static String buildUserInfo(UserDefine userDefine) {
    //    if (null != userDefine) {
    //        String username = userDefine.getUsername();
    //        if (StringUtils.isEmpty(username)) {
    //            return null;
    //        }
    //        String password = userDefine.getPassword();
    //        StringBuffer buffer = new StringBuffer();
    //        buffer.append(username).append(":");
    //        if (!StringUtils.isEmpty(password)) {
    //            buffer.append(password);
    //        }
    //        return buffer.toString();
    //    }
    //    return null;
    //}

    @Override
    public URI getComponent() throws GatewayException {
        UriComponents uriComponents = compileUriComponents();
        URI uri = uriComponents.encode().toUri();
        return uri;
    }

}
