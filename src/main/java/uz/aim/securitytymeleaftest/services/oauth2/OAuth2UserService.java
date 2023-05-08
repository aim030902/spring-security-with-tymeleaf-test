package uz.aim.securitytymeleaftest.services.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import uz.aim.securitytymeleaftest.dtos.oauth2.OAuth2UserDTO;
import uz.aim.securitytymeleaftest.repository.UserRepository;

@Service
public class OAuth2UserService extends DefaultOAuth2UserService  {
	@Autowired
	private UserRepository userRepository;
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User user =  super.loadUser(userRequest);
		return new OAuth2UserDTO(user);
	}

}
