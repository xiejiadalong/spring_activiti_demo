package com.longge.spring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.aspectj.util.FileUtil;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ResourceUtils;

import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.longge.spring.entity.Dictionary;
/**
 * 配置mybatis
 * @author ASUS
 *
 */
@Configuration    //该注解类似于spring配置文件
//@MapperScan(basePackages="huawei.purcher.mapper")
public class BeanConfig {
	
	Logger logger=Logger.getLogger(this.getClass());
	@Autowired
    private Environment env;
   
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource ds) throws Exception{
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(ds);
        fb.setTypeAliasesPackage(env.getProperty("mybatis.typeAliasesPackage"));//指定基包
        fb.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapperLocations")));//指定xml文件位置
        return fb.getObject();
    }

    @Bean
	public Dictionary initDictionary(){
    	Dictionary dictionary = new Dictionary();
		try {
			File file = ResourceUtils.getFile("classpath:Dictionary.json");
			String json = FileUtil.readAsString(file);
			Gson gson=new Gson();
			Map gsonData = gson.fromJson(json, Map.class);
			dictionary.setDictionary(gsonData);
			logger.info("初始化数据字典成功！");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return dictionary;
	}

}
