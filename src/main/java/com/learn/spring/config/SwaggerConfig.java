package com.learn.spring.config;

/**
 * Swagger configuration
 *
 * @author km185223
 */

import com.learn.spring.constants.ApplicationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
@EnableConfigurationProperties(ApplicationProperties.class)
public class SwaggerConfig {

    @Bean
    public Docket swaggerDocServiceApi(ApplicationProperties applicationProperties) {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.learn"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getMetaData(applicationProperties));
    }

    private ApiInfo getMetaData(ApplicationProperties applicationProperties) {

        return new ApiInfo(
                applicationProperties.getName(),
                applicationProperties.getDescription(),
                applicationProperties.getVersion(),
                applicationProperties.getTermsOfService(),
                new Contact(applicationProperties.getContactName(),
                        applicationProperties.getContactUrl(),
                        applicationProperties.getContactEmail()),
                applicationProperties.getLicenseName(),
                applicationProperties.getLicenseUrl());
    }
}

