package com.soso.config;

import com.soso.dto.ServiceInfoDto;
import com.soso.services.EventListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

/**
 * Created by Garik Kalashyan on 4/28/2017.
 */
@Configuration
@ComponentScan
@EnableWebMvc
@PropertySource(value = { "classpath:application.properties" })
public class EventListenerConfig  {


    @Bean
    public DataSource dataSource() {


        EventListenerService eventListenerService =  new EventListenerService(6);
        ServiceInfoDto serviceInfoDto = eventListenerService.getDestinationService();

        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(serviceInfoDto.getDbConnectionMetaData().getDriverClassName());
        ds.setUrl(serviceInfoDto.getDbConnectionMetaData().getUrl());
        ds.setUsername(serviceInfoDto.getDbConnectionMetaData().getUsername());
        ds.setPassword(serviceInfoDto.getDbConnectionMetaData().getPassword());
        return ds;
    }


    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }


}
