package com.wf.ew.common.generator;


import java.io.Serializable;

public class TemplatePathConstant implements Serializable {

    /**
     * 后台模板路径
     **/
    public static final String mapperXmlPath = "/templates/java_page/mapper.xml.ftl";

    public static final String mapperPath = "/templates/java_page/mapper.java.ftl";

    public static final String controllerPath = "/templates/java_page/controller.java.ftl";

    public static final String servicePath = "/templates/java_page/service.java.ftl";

    public static final String serviceImplPath = "/templates/java_page/serviceImpl.java.ftl";

    /**
     * 前台模板路径
     **/
    public static final String addHtmlPath = "/templates/html_page/add.html.ftl";

    public static final String listHtmlPath = "/templates/html_page/list.html.ftl";

    public static final String editHtmlPath = "/templates/html_page/edit.html.ftl";

    public static final String detailHtmlPath = "/templates/html_page/detail.html.ftl";

}
