package com.wf.ew.common.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.wf.ew.common.pojo.GenerateCode;

import java.util.Map;


/**
 * @author emil
 */
public class CodeGenerator {

    /**
     * 获取计算机用户名
     */
    private static String getHostName() {
        Map<String, String> map = System.getenv();
        return map.get("USERNAME");
    }

    public static void generateByTables(GenerateCode generateCode, String path) {
        String serviceName = generateCode.getServiceName();
        String dbName = generateCode.getDbName();
        String loginUser = generateCode.getLoginUser();
        String loginPwd = generateCode.getLoginPwd();
        String tableName = generateCode.getSqlTableName();
        String url = "jdbc:mysql://" + serviceName + "/" + dbName+"?useSSL=false";
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + "/emil-web";
        // 输出位置
        gc.setOutputDir(projectPath + "/src/main/java");
        //设置RowGuid为UUID
        gc.setIdType(IdType.UUID);
        //设置日期类型
        gc.setDateType(DateType.ONLY_DATE);
        // 覆盖文件
        gc.setFileOverride(true);
        //开发者
        gc.setAuthor(getHostName());
        //是否打开输出目录
        gc.setOpen(false);
        // 开启 BaseResultMap
        gc.setBaseResultMap(true);
        // 开启 baseColumnList
        gc.setBaseColumnList(true);
        // ActiveRecord 模式
        gc.setActiveRecord(false);
        // 二级缓存
        gc.setEnableCache(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl(url);
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername(loginUser);
        dsc.setPassword(loginPwd);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(path);
        pc.setParent("com.wf.ew");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        cfg.setFileOutConfigList(FileOutputPath.fileOutputPath(projectPath,pc));
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setXml(null);
        templateConfig.setController(null);
        templateConfig.setMapper(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 大写命名
        strategy.setCapitalMode(true);
        // lombok模型
        strategy.setEntityLombokModel(false);
        // 表名映射策略
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 字段映射策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setSuperControllerClass("com.wf.ew.common.BaseController");
        strategy.setSuperEntityColumns("id");
        // 表前缀
        strategy.setTablePrefix(pc.getModuleName() + "_");
        strategy.setInclude(tableName);
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}