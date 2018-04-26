package com.telemedicine.config;

import com.telemedicine.api.entity.MemberInfo;
import com.telemedicine.api.entity.CodeDept;
import com.telemedicine.api.entity.TelemedicineList;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryRestConfig extends RepositoryRestConfigurerAdapter {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        // Spring Data Rest 기본값은 키가 되는 아이디를 Json 에 노출하지 않는다
        // 아이디가 필요한 엔티티를 여기서 설정해 준다
        config.exposeIdsFor(MemberInfo.class,TelemedicineList.class,CodeDept.class);
    }
}
