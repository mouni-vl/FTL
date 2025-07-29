package com.example.fantasy.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    /**
     * Cache names
     */
    public static final String USER_CACHE = "userCache";
    public static final String TEAM_CACHE = "teamCache";
    public static final String PLAYER_CACHE = "playerCache";
    public static final String LEAGUE_CACHE = "leagueCache";
    public static final String SEASON_CACHE = "seasonCache";

    /**
     * Configure the cache manager
     * @return the cache manager
     */
//    @Bean
//    public CacheManager cacheManager() {
//        return new ConcurrentMapCacheManager(
//                USER_CACHE,
//                TEAM_CACHE,
//                PLAYER_CACHE,
//                LEAGUE_CACHE,
//                SEASON_CACHE
//        );
//    }
    @Bean
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(Caffeine.newBuilder()
                .expireAfterWrite(60, TimeUnit.MINUTES)
                .initialCapacity(100)
                .maximumSize(1000));
        return cacheManager;
    }
}
