package com.telemedicine.api.repository;


import com.telemedicine.api.entity.LifelogCroise250Sleep;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "lifelogcs250", path = "lifelogcs250")
public interface LifelogCroiseSleep250Repository extends PagingAndSortingRepository<LifelogCroise250Sleep, String> {
    List<LifelogCroise250Sleep> findByUserId(@Param("userid") String userid);
}



