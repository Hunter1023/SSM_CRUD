package com.hunter.ssm_crud.utils;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MybatisGeneratorUtil {
    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<String>();

        //读取generatorConfig.xml的方式，待总结
        InputStream is = Thread.currentThread().getContextClassLoader()
                        .getResourceAsStream("generatorConfig.xml");

        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(is);
        is.close();
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
