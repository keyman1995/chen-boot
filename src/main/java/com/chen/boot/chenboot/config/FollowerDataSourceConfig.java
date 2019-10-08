package com.chen.boot.chenboot.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @ClassName FollowerDataSourceConfig
 * @Description: TODO
 * @Author chenjie
 * @Date 2019/10/8
 * @Version V1.0
 **/
@Configuration
@MapperScan(basePackages = "com.chen.boot.chenboot.follower", sqlSessionFactoryRef = "followerSqlSessionFactory")
public class FollowerDataSourceConfig {

    @Bean("followDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.follower")
    public DataSource setDataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "followerTransactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("followDataSource") DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean("followerSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("followDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/follower/*.xml"));
        return factoryBean.getObject();
    }

    @Bean("followerSqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("followerSqlSessionFactory") SqlSessionFactory factory) {
        return new SqlSessionTemplate(factory);
    }

}
