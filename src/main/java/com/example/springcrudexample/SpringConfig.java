package com.example.springcrudexample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class SpringConfig {

    JdbcTemplate template;


    @Autowired
    public SpringConfig(JdbcTemplate template) {
        this.template = template;
    }

    @Bean
    public EmpDAO empDAO(){
        return new EmpDAO(template);
    }
}
