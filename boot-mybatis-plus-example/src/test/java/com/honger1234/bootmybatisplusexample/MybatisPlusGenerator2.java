package com.honger1234.bootmybatisplusexample;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @Description: MybatisPlus代码生成器
 * @author: zt
 * @date: 2020年3月26日
 */
public class MybatisPlusGenerator2 {

    //JDBC配置，请修改为你项目的实际配置
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/mybatisplus?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "honger1234";
    private static final String JDBC_DIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    private static final String AUTHOR = "zt";//@author

    //生成的文件路径配置
    private static final String OUTPUTDIR = "F:\\idea-workspace\\HelloWorld\\boot-mybatis-plus-example\\src\\main\\java";//文件生成的基本路径
    private static final String PACKAGE_PARENT ="com.honger1234.bootmybatisplusexample";//生成的父包名
    private static final String PACKAGE_ENTITY ="entity";//生成的实体类的包路径，最终为PACKAGE_PARENT+PACKAGE_ENTITY
    private static final String PACKAGE_CONTROLLER ="controller";//生成的controller包路径，最终为PACKAGE_PARENT+PACKAGE_CONTROLLER
    private static final String PACKAGE_SERVICE ="service";//生成的service接口包路径，实现类自动在创建在与接口同一包下的impl包下
    private static final String PACKAGE_DAO ="dao";//生成的持久层接口包路径
    private static final String PACKAGE_XML ="dao";//生成的XML文件的包路径

    private static final String TABLE_PREFIX ="tb_";//表前缀
    private static final boolean ENTITY_SWAGGER =true;//实体类是否添加swagger2注解
    private static final boolean ENTITY_Lombok =true;//实体是否为lombok模型


    public static void codeGenerator(String tableName){
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setActiveRecord(false)//是否支持AR模式
                    .setAuthor(AUTHOR)//作者
                    .setOpen(false)
                    .setOutputDir(OUTPUTDIR)//生成目录
                    .setFileOverride(false)//文件覆盖
                    .setIdType(IdType.AUTO)//主键策略
                    //自定义文件命名，注意 %s 会自动填充表实体属性！
//                   // 设置生成的service接口的名字的首字母是否为I，默认Service是以I开头的
//                  .setServiceName("%sService")
                    .setSwagger2(ENTITY_SWAGGER)//实体属性 Swagger2 注解
                    .setEntityName("%sEntity")//自定义生成的实体类名字
                    .setMapperName("I%sDao")//自定义持久层接口名
                    .setBaseResultMap(true)//是否添加resultMap
                    .setBaseColumnList(true);
        //数据源配置
        DataSourceConfig dataSourceConfig=new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)//设置数据库类型
                        .setDriverName(JDBC_DIVER_CLASS_NAME)//设置驱动
                        .setUrl(JDBC_URL)//设置数据库URL
                        .setUsername(JDBC_USERNAME)//用户名
                        .setPassword(JDBC_PASSWORD);//密码
        //策略配置
        StrategyConfig strategyConfig=new StrategyConfig();
        strategyConfig.setCapitalMode(true)//全局大写命名
                      .setNaming(NamingStrategy.underline_to_camel)//数据库表映射到实体的命名策略
                      .setEntityLombokModel(ENTITY_Lombok)//【实体】是否为lombok模型
                      .setTablePrefix(TABLE_PREFIX)//表前缀
                      .setInclude(tableName);//生成的表

        //包名策略配置
        PackageConfig packageConfig=new PackageConfig();
        packageConfig
                     .setParent(PACKAGE_PARENT)
                     .setMapper(PACKAGE_DAO)
                     .setService(PACKAGE_SERVICE)
                     .setController(PACKAGE_CONTROLLER)
                     .setEntity(PACKAGE_ENTITY)
                     .setXml(PACKAGE_XML);
        //整合配置
        AutoGenerator autoGenerator=new AutoGenerator();

        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setDataSource(dataSourceConfig);
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setPackageInfo(packageConfig);

        //执行
        autoGenerator.execute();
    }

    public static void main(String[] args) {
        String tableName="tb_user";
        codeGenerator(tableName);
    }
}
