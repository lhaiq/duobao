package com.hengsu.duobao.core;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.hengsu.duobao.core.model.AuthModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.TimeUnit;


@Configuration
@EnableAsync
public class CacheConfig {

    @Bean
    @Qualifier("sessionCache")
    public Cache<String, AuthModel> sessionCache() {
        Cache<String, AuthModel> cache = CacheBuilder.newBuilder()
                .expireAfterAccess(1, TimeUnit.DAYS).build();
        AuthModel authModel = new AuthModel(1L,1);
        cache.put("aaaaa",authModel);

        AuthModel authModel1 = new AuthModel(1L,3);
        cache.put("bbbbb",authModel1);
        return cache;
    }

    @Bean
    @Qualifier("validateCache")
    public Cache validateCache() {
        Cache<String,String> cache = CacheBuilder.newBuilder()
                .expireAfterAccess(5, TimeUnit.MINUTES).build();
        return cache;
    }
}
