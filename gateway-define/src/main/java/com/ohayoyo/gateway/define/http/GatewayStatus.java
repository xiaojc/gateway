package com.ohayoyo.gateway.define.http;

/**
 * @author 蓝明乐
 */
public interface GatewayStatus {

    Integer getCode();

    void setCode(Integer code);

    String getPhrase();

    void setPhrase(String phrase);

}
