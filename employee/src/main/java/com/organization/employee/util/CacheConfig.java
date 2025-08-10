package com.organization.employee.util;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Duration;
import java.util.Arrays;

@Configuration
@EnableCaching
public class CacheConfig {
    public static final String EMPLOYEE_CACHE = "employees";
    public static final String EMPLOYEE_BY_EMAIL_CACHE = "employeesByEmail";
    public static final String ALL_EMPLOYEES_CACHE = "allEmployees";

    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager(
                EMPLOYEE_CACHE,
                EMPLOYEE_BY_EMAIL_CACHE,
                ALL_EMPLOYEES_CACHE
        );

        cacheManager.setCaffeine(Caffeine.newBuilder()
                .maximumSize(500)
                .expireAfterWrite(Duration.ofMinutes(10))
                .recordStats()
        );

        return cacheManager;
    }
}
