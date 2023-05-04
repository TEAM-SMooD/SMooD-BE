package yeinyeonha.SMooD.oauth;

import yeinyeonha.SMooD.oauth.info.GoogleOAuth2UserInfo;
import yeinyeonha.SMooD.oauth.info.KakaoOAuth2UserInfo;
import yeinyeonha.SMooD.oauth.info.NaverOAuth2UserInfo;
import yeinyeonha.SMooD.oauth.info.OAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(ProviderType providerType, Map<String, Object> attributes) {
        switch (providerType) {
            case GOOGLE: return new GoogleOAuth2UserInfo(attributes);
            case NAVER: return new NaverOAuth2UserInfo(attributes);
            case KAKAO: return new KakaoOAuth2UserInfo(attributes);
            default: throw new IllegalArgumentException("Invalid Provider Type.");
        }
    }
}
