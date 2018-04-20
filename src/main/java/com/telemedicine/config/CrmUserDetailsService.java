package com.telemedicine.config;

import com.telemedicine.user.entity.UserInfo;
import com.telemedicine.user.repositoty.UserInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CrmUserDetailsService implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserInfoRepository userInfoRepository;

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
       List<UserInfo> userList = userInfoRepository.findByUserId(userId);
        if(userList == null || userList.size() == 0){
            throw new UsernameNotFoundException("userId "+userId+" not found");
        }

        UserInfo userInfoResult = null;

        for (UserInfo userInfo : userList) {
            //logger.debug("loadUserByUsername="+memberInfo.getPass());
            userInfoResult = userInfo;
        };

        String password = encoder().encode(userInfoResult.getPass());
        userInfoResult.setPass(password);

        return new CrmUserDetails(userInfoResult);
    }


}
