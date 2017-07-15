package com.aj.dropwizardbasicauth.util;

import com.aj.dropwizardbasicauth.DropwizardBasicAuthConfiguration;
import com.aj.dropwizardbasicauth.domain.ApplicationUser;
import com.google.common.base.Optional;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

/**
 * Created by georgeninan on 5/12/16.
 */

public class SimpleAuthenticator implements Authenticator<BasicCredentials, ApplicationUser> {

    private static String apiUsername;
    private static String apiPassword;

    public SimpleAuthenticator(DropwizardBasicAuthConfiguration basicAuthConfiguration){
        this.apiUsername = basicAuthConfiguration.getApiUsername();
        this.apiPassword = basicAuthConfiguration.getApiPassword();
    }

    @Override
    public Optional<ApplicationUser> authenticate(BasicCredentials credentials) throws AuthenticationException {
        if (apiUsername.equals(credentials.getUsername()) && apiPassword.equals(credentials.getPassword())) {
            return Optional.of(new ApplicationUser());
        }
        return Optional.absent();
    }
}

