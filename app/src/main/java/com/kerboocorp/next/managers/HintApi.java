package com.kerboocorp.next.managers;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

/**
 * Created by cgo on 9/03/2015.
 */
public class HintApi extends DefaultApi20 {

    @Override
    public String getAccessTokenEndpoint() {
        return "http://projectb.kerboocorp.com/oauth/token";
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig config) {
        return "http://projectb.kerboocorp.com/oauth/authorize";
    }

}
