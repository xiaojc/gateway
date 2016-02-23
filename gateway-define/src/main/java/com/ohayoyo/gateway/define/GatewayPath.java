package com.ohayoyo.gateway.define;

/**
 * @author 蓝明乐
 */
public interface GatewayPath<Variables extends GatewayVariables> {

    String getProject();

    void setProject(String project);

    String getModule();

    void setModule(String module);

    String getOperate();

    void setOperate(String operate);

    String getResource();

    void setResource(String resource);

    Variables getVariables();

    void setVariables(Variables variables);

}
