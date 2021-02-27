package cn.edu.xmu.auth.config;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @author summer
 * @date 2021/2/26 18:42
 */
public class MybatisplusGeneratorConfig {

    public static void main(String []args){

        AutoGenerator autoGenerator =
                new AutoGenerator();

        GlobalConfig globalConfig =
                new GlobalConfig();

        //String projectPath = System.getProperty("demo.super");
        //配置输出路径
        globalConfig.setOutputDir("E://springbootdemo/user-service/src/main/java");
        globalConfig.setAuthor("summer");
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(false);
        //globalConfig.setServiceName("%sService");
        globalConfig.setBaseResultMap(true);
        autoGenerator.setGlobalConfig(globalConfig);

        //配置数据源
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/crm?serverTimezone=GMT%2B8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("issyu153");
        autoGenerator.setDataSource(dsc);

        //配置包
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName("user-service");
        pc.setParent("cn.edu.xmu.auth");
        autoGenerator.setPackageInfo(pc);

        //策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperControllerClass("cn.edu.xmu.superdemo.controller.BaseController");
        strategy.setRestControllerStyle(true);
        strategy.setSuperEntityClass("cn.edu.xmu.auth.entity.BaseEntity");
        // strategy.setTablePrefix("t_");
        // 表名前缀
        strategy.setEntityLombokModel(true);
        //使用lombok
        //strategy.setInclude("user");
        // 逆向工程使用的表   如果要生成多个,这里可以传入String[]
        autoGenerator.setStrategy(strategy);

        autoGenerator.execute();
    }
}
