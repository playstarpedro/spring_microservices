package com.psouza.online.sales.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.psouza.online.sales.repository")
public class MongoConfig {

}
