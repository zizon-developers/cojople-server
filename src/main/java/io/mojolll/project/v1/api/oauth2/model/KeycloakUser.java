package io.mojolll.project.v1.api.oauth2.model;

import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class KeycloakUser extends OAuth2ProviderUser{

    //사용자 정보를 가지고와서 인증을 받은 최종 객체 Oauth2User객체
    public KeycloakUser(OAuth2User oAuth2User, ClientRegistration clientRegistration){
        super(oAuth2User.getAttributes(), oAuth2User, clientRegistration);

    }

    //제공자 별로 차이가 나는 것들 Override
    @Override
    public String getId() {
        return (String) getAttributes().get("sub");
    }

    @Override
    public String getUsername() {
        return (String) getAttributes().get("preferred_username");
    }

}