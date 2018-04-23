package com.telemedicine.user.repositoty;


import com.telemedicine.user.entity.UserInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "userinfo", path = "userinfo")
public interface UserInfoRepository extends PagingAndSortingRepository<UserInfo, String> {
    List<UserInfo> findByName(@Param("name") String name);
    List<UserInfo> findByUserId(@Param("userid") String userid);
}



