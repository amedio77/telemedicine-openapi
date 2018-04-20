package com.telemedicine.api.repository;


import com.telemedicine.api.entity.MemberInfo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "member", path = "member")
public interface MemberInfoRepository extends PagingAndSortingRepository<MemberInfo, String> {
    List<MemberInfo> findByName(@Param("name") String name);
    List<MemberInfo> findByUserId(@Param("userid") String userid);
}



