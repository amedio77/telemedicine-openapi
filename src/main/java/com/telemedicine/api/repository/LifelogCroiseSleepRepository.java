package com.telemedicine.api.repository;


import com.telemedicine.api.entity.LifelogCroiseSleep;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "lifelogcs", path = "lifelogcs")
public interface LifelogCroiseSleepRepository extends PagingAndSortingRepository<LifelogCroiseSleep, String> {
    List<LifelogCroiseSleep> findByUserId(@Param("userid") String userid);
}



