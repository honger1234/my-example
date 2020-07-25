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
public class MybatisPlusGenerator {

    public static void codeGenerator(String tableName){
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setActiveRecord(false)//是否支持AR模式
                    .setAuthor("zt")//作者
                    .setOpen(false)
                    .setOutputDir("F:\\idea-workspace\\HelloWorld\\boot-mybatis-plus-example\\src\\main\\java")//生成目录
                    .setFileOverride(false)//文件覆盖
                    .setIdType(IdType.AUTO)//主键策略
                    //自定义文件命名，注意 %s 会自动填充表实体属性！
//                   // 设置生成的service接口的名字的首字母是否为I，默认Service是以I开头的
//                  .setServiceName("%sService")
                    .setSwagger2(true)//实体属性 Swagger2 注解
                    .setEntityName("%sEntity")//自定义生成的实体类名字
                    .setMapperName("I%sDao")//自定义持久层接口名
                    .setBaseResultMap(true)//是否添加resultMap
                    .setBaseColumnList(true);
        //数据源配置
        DataSourceConfig dataSourceConfig=new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)//设置数据库类型
                        .setDriverName("com.mysql.jdbc.Driver")//设置驱动
                        .setUrl("jdbc:mysql://localhost:3306/mybatisplus?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC")//设置数据库URL
                        .setUsername("root")//用户名
                        .setPassword("honger1234");//密码
        //策略配置
        StrategyConfig strategyConfig=new StrategyConfig();
        strategyConfig.setCapitalMode(true)//全局大写命名
                      .setNaming(NamingStrategy.underline_to_camel)//数据库表映射到实体的命名策略
                      .setEntityLombokModel(true)//【实体】是否为lombok模型
                      .setTablePrefix("tb_")//表前缀
                      .setInclude(tableName);//生成的表

        //包名策略配置
        PackageConfig packageConfig=new PackageConfig();
        packageConfig
                     .setParent("com.honger1234.bootmybatisplusexample")
                     .setMapper("dao")
                     .setService("service")
                     .setController("controller")
                     .setEntity("entity")
                     .setXml("dao");
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
