package com.telemedicine.api.repository;


import com.telemedicine.api.entity.LifelogCroise250Activity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "lifelogca250", path = "lifelogca250")
public interface LifelogCroiseActivity250Repository extends PagingAndSortingRepository<LifelogCroise250Activity, String> {
    List<LifelogCroise250Activity> findByUserId(@Param("userid") String userid);
}