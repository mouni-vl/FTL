package com.example.fantasy.shared.config;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for Micrometer metrics
 */
@Configuration
public class MetricsConfig {

    /**
     * Customize the meter registry
     * @return a customizer for the meter registry
     */
    @Bean
    public MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> {
            // Add common tags to all metrics
            registry.config()
                    .commonTags("application", "fantasy-backend")
                    // Configure global filters
                    .meterFilter(MeterFilter.deny(id -> {
                        String uri = id.getTag("uri");
                        return uri != null && (
                                uri.startsWith("/actuator") ||
                                uri.startsWith("/swagger") ||
                                uri.startsWith("/v3/api-docs")
                        );
                    }));
        };
    }
}
