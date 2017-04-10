package com.hargeliya;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;


@SpringBootApplication
@ComponentScan
public class VotingApplication extends SpringBootServletInitializer {
    private static final Logger LOGGER = LogManager.getLogger(VotingApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(VotingApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(VotingApplication.class, args);
    }

    /**
     * Gets the sql statements from schema.sql
     **/
    @Value("classpath:schema.sql")
    private Resource initSqlScript;

    /**
     * Sql statements are executed
     **/
    @Bean
    public CommandLineRunner init(DataSource ds) {
        return args -> {
            ScriptUtils.executeSqlScript(ds.getConnection(), initSqlScript);
        };
    }
}
