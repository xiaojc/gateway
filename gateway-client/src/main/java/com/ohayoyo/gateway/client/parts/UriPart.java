package com.ohayoyo.gateway.client.parts;

import com.ohayoyo.gateway.client.core.GatewayConfig;
import com.ohayoyo.gateway.client.core.GatewayDefine;
import com.ohayoyo.gateway.client.core.GatewayException;
import com.ohayoyo.gateway.client.core.GatewayRequest;
import com.ohayoyo.gateway.client.utils.MapUtil;
import com.ohayoyo.gateway.client.utils.PathDefineUtil;
import com.ohayoyo.gateway.client.utils.SelectDefineUtil;
import com.ohayoyo.gateway.define.core.*;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Map;
import java.util.Set;

public class UriPart extends AbstractGatewayPart<URI> {

    public static final String USERNAME_AND_PASSWORD_DELIMITER = ":";

    @Override
    public URI getPart() throws GatewayException {
        UriComponents uriComponents = compileUriComponents();
        URI uri = uriComponents.encode().toUri();
        return uri;
    }

    private UriComponents compileUriComponents() {

        GatewayConfig gatewayConfig = this.getGatewayConfig();
        GatewayRequest gatewayRequest = this.getGatewayRequest();
        GatewayDefine gatewayDefine = this.getGatewayDefine();
        ClientHttpRequest clientHttpRequest = this.getClientHttpRequest();
        ClientHttpResponse clientHttpResponse = this.getClientHttpResponse();

        String select = gatewayRequest.getSelect();
        Map<String, Object> requestPathVariables = gatewayRequest.getRequestPathVariables();
        Map<String, Object> requestQueries = gatewayRequest.getRequestQueries();
        RequestDefine requestDefine = gatewayDefine.getRequest();

        Set<ProtocolDefine> protocolDefines = requestDefine.getProtocols();
        Set<HostDefine> hostDefines = requestDefine.getHosts();
        Set<UserDefine> userDefines = requestDefine.getUserDefines();
        PathDefine pathDefine = requestDefine.getPath();
        String fragment = requestDefine.getFragment();

        ProtocolDefine protocolDefine = SelectDefineUtil.selectProtocolDefine(select, protocolDefines);
        UserDefine userDefine = SelectDefineUtil.selectUserDefine(select, userDefines);
        HostDefine hostDefine = SelectDefineUtil.selectHostDefine(select, hostDefines);

        String scheme = protocolDefine.getName();
        String userInfo = buildUserInfo(userDefine);
        String host = hostDefine.getHostname();
        int port = SelectDefineUtil.selectHostDefinePort(protocolDefine, hostDefine);
        String[] pathSegments = PathDefineUtil.pathSegments(pathDefine);

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();

        uriComponentsBuilder.scheme(scheme);

        if (!StringUtils.isEmpty(userInfo)) {
            uriComponentsBuilder.userInfo(userInfo);
        }

        uriComponentsBuilder.host(host);

        uriComponentsBuilder.port(port);

        if (!ObjectUtils.isEmpty(pathSegments)) {
            uriComponentsBuilder.pathSegment(pathSegments);
        }

        if (!StringUtils.isEmpty(fragment)) {
            uriComponentsBuilder.fragment(fragment);
        }

        if (!CollectionUtils.isEmpty(requestQueries)) {
            //预留处理方案:是否提前转换可转变字符串对象,即toString方法后执行的结果
            MultiValueMap<String, String> params = MapUtil.stringAndObjectMapToStringAndStringMultiValueMap(requestQueries);
            uriComponentsBuilder.queryParams(params);
        }

        UriComponents uriComponents;
        if (!CollectionUtils.isEmpty(requestPathVariables)) {
            uriComponents = uriComponentsBuilder.buildAndExpand(requestPathVariables);
        } else {
            uriComponents = uriComponentsBuilder.build();
        }

        return uriComponents;
    }

    private static String buildUserInfo(UserDefine userDefine) {
        if (null != userDefine) {
            String username = userDefine.getUsername();
            if (StringUtils.isEmpty(username)) {
                return null;
            }
            String password = userDefine.getPassword();
            StringBuffer buffer = new StringBuffer();
            buffer.append(username).append(USERNAME_AND_PASSWORD_DELIMITER);
            if (!StringUtils.isEmpty(password)) {
                buffer.append(password);
            }
            return buffer.toString();
        }
        return null;
    }

}
