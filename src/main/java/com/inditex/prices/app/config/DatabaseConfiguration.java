package com.inditex.prices.app.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.inditex.prices.app.repository")
@EnableTransactionManagement
@EnableAutoConfiguration
public class DatabaseConfiguration {
}
