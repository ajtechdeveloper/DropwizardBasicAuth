package com.aj.dropwizardbasicauth;

import com.aj.dropwizardbasicauth.domain.ApplicationUser;
import com.aj.dropwizardbasicauth.resource.DropwizardBasicAuthHealthCheckResource;
import com.aj.dropwizardbasicauth.resource.PingResource;
import com.aj.dropwizardbasicauth.util.SimpleAuthenticator;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DropwizardBasicAuthApplication extends Application<DropwizardBasicAuthConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(DropwizardBasicAuthApplication.class);

	public static void main(String[] args) throws Exception {
		new DropwizardBasicAuthApplication().run("server", args[0]);
	}

    @Override
    public void initialize(Bootstrap<DropwizardBasicAuthConfiguration> b) {
    }

	@Override
	public void run(DropwizardBasicAuthConfiguration config, Environment env)
			throws Exception {
        logger.info("Registering RESTful API resources");
		env.jersey().register(new PingResource());
		env.healthChecks().register("DropwizardBasicAuthHealthCheck",
				new DropwizardBasicAuthHealthCheckResource(config));
		env.jersey().register(AuthFactory.binder(
				new BasicAuthFactory<>(new SimpleAuthenticator(config),"AUTH REALM", ApplicationUser.class)));
	}
}
