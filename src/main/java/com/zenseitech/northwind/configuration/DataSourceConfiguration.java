package com.zenseitech.northwind.configuration;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
@ComponentScan("com.zenseitech.northwind")
@PropertySource("classpath:sql/jdbc.properties")
public class DataSourceConfiguration {

//    https://www.baeldung.com/spring-profiles
//    https://www.baeldung.com/properties-with-spring

    public static final String PROFILE_SQLITE = "sqlite";

    @Autowired
    Environment env;

    @Profile(PROFILE_SQLITE)
    @Bean
    public DataSource developmentDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(env.getProperty("sqlite.jdbc.driverClassName"));
        ds.setUrl(env.getProperty("sqlite.jdbc.url"));
        ds.setUsername(env.getProperty("sqlite.jdbc.username"));
        ds.setPassword(env.getProperty("sqlite.jdbc.password"));
        return ds;
    }

}
