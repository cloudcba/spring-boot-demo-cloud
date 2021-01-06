package com.neo.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class MysqlConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String userName;

    @Value("${spring.datasource.password}")
    private String passWd;

    @Bean(initMethod = "init", destroyMethod = "close")
    public DruidDataSource mysqlDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setName("mysqltesttwo");
        dataSource.setUrl(url);
        dataSource.setUsername(userName);
        dataSource.setPassword(passWd);
        dataSource.setValidationQuery("SELECT 1 ");

        return dataSource;
    }

    @Bean
    @Primary
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(mysqlDataSource());
    }


}
