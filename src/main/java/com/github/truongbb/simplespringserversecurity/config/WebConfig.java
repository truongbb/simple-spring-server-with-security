package com.github.truongbb.simplespringserversecurity.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    private Logger log = LoggerFactory.getLogger(WebConfig.class);

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.addAllowedHeader("Accept,Accept-Encoding,Accept-Language,Access-Control-Request-Method,Access-Control-Request-Headers," +
                "Cache-Control,Connection,Content-Length,Content-Type,Host,Origin,Pragma,Referer,User-Agent,Authorization,StoreManagerNo");
        config.addAllowedMethod("GET,POST,DELETE,PUT,OPTIONS");
        config.addExposedHeader("Authorization");
        config.setMaxAge(3600L);

        if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
            log.debug("Registering CORS filter");
            source.registerCorsConfiguration("/demo-api/**", config);
        }
        return new CorsFilter(source);
    }

}
