package com.passer.api.web;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
* @Description: <p>
* @author: passer<p>
* @version：2019年5月18日 下午5:31:54<p>
*/
@Configuration
@EnableWebMvc
@ComponentScan("com.passer.api")
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    @Bean
    public SqlSessionFactory sqlSessionFactory() {
        try {
            return new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatis-config.xml"));
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public SqlSession sqlSession() {
        return sqlSessionFactory().openSession();
    }
}
