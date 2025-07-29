package com.example.fantasy.shared.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for Swagger/OpenAPI
 */
@Data
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerProperties {
    
    /**
     * API title
     */
    private String title = "Fantasy League API";
    
    /**
     * API description
     */
    private String description = "API for Fantasy League application with hexagonal architecture";
    
    /**
     * API version
     */
    private String version = "1.0";
    
    /**
     * Contact name
     */
    private String contactName = "Fantasy League Team";
    
    /**
     * Contact email
     */
    private String contactEmail = "contact@fantasyapp.com";
    
    /**
     * Contact URL
     */
    private String contactUrl = "https://fantasyapp.com";
    
    /**
     * License name
     */
    private String licenseName = "Apache 2.0";
    
    /**
     * License URL
     */
    private String licenseUrl = "https://www.apache.org/licenses/LICENSE-2.0.html";
}
