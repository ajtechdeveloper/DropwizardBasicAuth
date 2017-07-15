package com.aj.dropwizardbasicauth.resource;

import com.aj.dropwizardbasicauth.DropwizardBasicAuthConfiguration;
import com.codahale.metrics.health.HealthCheck;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DropwizardBasicAuthHealthCheckResource extends HealthCheck {

    private static final Logger logger = LoggerFactory.getLogger(DropwizardBasicAuthHealthCheckResource.class);

    private static String appName;

    public DropwizardBasicAuthHealthCheckResource(DropwizardBasicAuthConfiguration dropwizardBasicAuthConfiguration){
       this.appName = dropwizardBasicAuthConfiguration.getAppName();
    }

    @Override
    protected Result check() throws Exception {
        logger.info("App Name is: {}", appName);
        if("DropwizardBasicAuth".equalsIgnoreCase(appName)) {
            return Result.healthy();
        }
        return Result.unhealthy("Dropwizard Basic Auth Service is down");
    }
}