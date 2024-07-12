package com.example.fpi.service.user;


import com.example.fpi.domain.dto.user.UserDTO;
import com.example.fpi.domain.oauth.CustomOAuth2User;
import com.example.fpi.domain.vo.user.UserVO;
import com.example.fpi.mapper.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
    private final UserMapper userMapper;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest); //로그인 된 객체 담고있음
//        사용할 객체들 선언
        String userId=null;
        String userName = null;
        String userImg=null;


//        OAuth2로 부터받은 사용자 정보
        Map<String, Object> attribute = oAuth2User.getAttributes();
        userId = attribute.get("id").toString();

//        세부적으로 한 번 더 들어가야함
        Map<String, Object> account =(Map<String, Object>)attribute.get("kakao_account");
//        한번 더 들어감
        Map<String, Object> info = (Map<String, Object>)account.get("profile");

        userName = (String)info.get("nickname");
        userImg=(String)info.get("profile_image_url");

        UserDTO loginUser= new UserDTO();
        loginUser.setUserId(userId);
        loginUser.setUserName(userName);
        loginUser.setUserImg(userImg);
        loginUser.setProvider(userRequest.getClientRegistration().getRegistrationId()); //OAuth2 공급자의 이름




// DB저장 및 업데이트 로직
        UserDTO existingUser = userMapper.findByUserId(userId);

//        우리 애플리케이션에 가입한 적이 없는 OAuth계정
        if(existingUser == null){
//            insert
            loginUser.setRole("new");
            UserVO user = UserVO.toEntity(loginUser);
//            CategoryVO category = CategoryVO.toEntity()
            userMapper.saveUser(user);
//            userMapper.updateUser(user);
//            userMapper.insertCategory();
        }
//        가입한 이력이 있다면, 정보 업데이트
//        else{
//            UserVO user = UserVO.toEntity(loginUser);
//            userMapper.updateUser(user);
//
//        }


// 로그인이 제대로 되었다면 oAuth2User정보를 시큐리티로 넘겨줌
//        return oAuth2User;

//       oAuth2User안의 정보를  get을 써서 name, profilePic,providerId 편하게 꺼내옴
        return new CustomOAuth2User(oAuth2User,userId,userName,userImg);

    }
}
