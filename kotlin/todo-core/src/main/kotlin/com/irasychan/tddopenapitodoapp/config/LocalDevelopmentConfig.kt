package com.irasychan.tddopenapitodoapp.config

import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConditionalOnProperty(name = ["spring.profiles.active"], havingValue = "local")
class LocalDevelopmentConfig {
    @Bean
    fun httpExchangeRepository(): HttpExchangeRepository {
        return InMemoryHttpExchangeRepository()
    }
}