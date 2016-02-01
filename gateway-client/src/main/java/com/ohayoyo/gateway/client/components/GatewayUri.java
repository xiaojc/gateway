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

/**
 * 网关URI组件
 */
public class GatewayUri extends AbstractGatewayComponent<URI> {

    /**
     * 编译URI组件
     *
     * @return 返回URI组件
     */
    protected UriComponents compileUriComponents() {
        //获取网关请求
        GatewayRequest gatewayRequest = this.getGatewayRequest();
        //获取网关定义
        GatewayDefine gatewayDefine = this.getGatewayDefine();
        //获取选择定义的作用域
        String select = gatewayRequest.getSelect();
        //获取请求路径变量集合
        Map<String, String> requestPathVariables = gatewayRequest.getRequestPathVariables();
        //获取请求查询多值集合
        MultiValueMap<String, String> requestQueries = gatewayRequest.getRequestQueries();
        //获取请求定义
        RequestDefine requestDefine = gatewayDefine.getRequest();
        //获取协议定义集合
        Set<ProtocolDefine> protocolDefines = requestDefine.getProtocols();
        //获取主机定义集合
        Set<HostDefine> hostDefines = requestDefine.getHosts();
        //获取路径值定义
        PathDefine pathDefine = requestDefine.getPath();
        //获取片段
        String fragment = requestDefine.getFragment();
        //给定一个协议定义集合就会根据给定选择定义的作用域,选择合适的协议定义
        ProtocolDefine protocolDefine = SelectDefineUtil.selectProtocolDefine(select, protocolDefines);
        //给定一个主机定义集合就会根据给定选择定义的作用域,选择合适的主机定义
        HostDefine hostDefine = SelectDefineUtil.selectHostDefine(select, hostDefines);
        //获取协议定义的名称
        String scheme = protocolDefine.getName();
        //获取主机定义的主机名
        String host = hostDefine.getHostname();
        //选择主机定义的端口,如果没有定义会根据协议来进行选择默认的端口
        int port = SelectDefineUtil.selectHostDefinePort(protocolDefine, hostDefine);
        //把一个路径定义分片到一个字符串数组中
        String[] pathSegments = PathDefineUtil.pathSegments(pathDefine);
        //创建一个新的URI组件构建器
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        uriComponentsBuilder.scheme(scheme);
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

    /**
     * 获取网关URI组件
     *
     * @return 返回网关URI组件
     * @throws GatewayException 抛出网关异常
     */
    @Override
    public URI getComponent() throws GatewayException {
        UriComponents uriComponents = compileUriComponents();
        URI uri = uriComponents.encode().toUri();
        return uri;
    }

}
