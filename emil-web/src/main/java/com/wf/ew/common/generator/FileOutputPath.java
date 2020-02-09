package com.wf.ew.common.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author emil
 */
public class FileOutputPath {

    /**
     * 首字母转小写
     *
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

    public static List<FileOutConfig> fileOutputPath(String projectPath, PackageConfig pc) {
        //java生成代码根路径
        String javaRootPath = projectPath + "/src/main/java/com/wf/ew/" + pc.getModuleName() + "/";
        //html生成代码根路径
        String htmlRootPath = projectPath + "/src/main/resources/frame/system/pages/" + pc.getModuleName();
        List<FileOutConfig> focList = new ArrayList<>();

        // Mapper.xml
        focList.add(new FileOutConfig(TemplatePathConstant.mapperXmlPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        //控制层
        focList.add(new FileOutConfig(TemplatePathConstant.controllerPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                String expand = javaRootPath + "controller";
                return String.format((expand + File.separator + "%s" + ".java"), tableInfo.getControllerName());
            }
        });


        //数据层
        focList.add(new FileOutConfig(TemplatePathConstant.mapperPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 + pc.getModuleName()
                String expand = javaRootPath + "mapper";
                return String.format((expand + File.separator + "%s" + ".java"), tableInfo.getMapperName());
            }
        });

        //业务层
        focList.add(new FileOutConfig(TemplatePathConstant.servicePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 + pc.getModuleName()
                String expand = javaRootPath + "service";
                return String.format((expand + File.separator + "%s" + ".java"), tableInfo.getServiceName());
            }
        });

        //实现层
        focList.add(new FileOutConfig(TemplatePathConstant.serviceImplPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 + pc.getModuleName()
                String expand = javaRootPath + "service/impl";
                return String.format((expand + File.separator + "%s" + ".java"), tableInfo.getServiceImplName());
            }
        });

        //添加页面
        focList.add(new FileOutConfig(TemplatePathConstant.addHtmlPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format((htmlRootPath + File.separator + "%s" + ".html"), toLowerCaseFirstOne(tableInfo.getEntityName()) + "Add");
            }
        });

        //修改页面
        focList.add(new FileOutConfig(TemplatePathConstant.editHtmlPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format((htmlRootPath + File.separator + "%s" + ".html"), toLowerCaseFirstOne(tableInfo.getEntityName()) + "Edit");
            }
        });

        //详情页面
        focList.add(new FileOutConfig(TemplatePathConstant.detailHtmlPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format((htmlRootPath + File.separator + "%s" + ".html"), toLowerCaseFirstOne(tableInfo.getEntityName()) + "Detail");
            }
        });

        //列表页面
        focList.add(new FileOutConfig(TemplatePathConstant.listHtmlPath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return String.format((htmlRootPath + File.separator + "%s" + ".html"), toLowerCaseFirstOne(tableInfo.getEntityName()) + "List");
            }
        });
        return focList;
    }
}
