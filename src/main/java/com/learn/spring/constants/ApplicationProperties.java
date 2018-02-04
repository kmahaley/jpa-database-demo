package com.learn.spring.constants;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Application properties
 *
 * @author km185223
 */
@Data
@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

    public String name;

    public String description;

    public String version;

    public String termsOfService;

    @Value("${application.license.name}")
    public String licenseName;

    @Value("${application.license.url}")
    public String licenseUrl;

    @Value("${application.contact.name}")
    public String contactName;

    @Value("${application.contact.url}")
    public String contactUrl;

    @Value("${application.contact.email}")
    public String contactEmail;
}
