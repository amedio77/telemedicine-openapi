package com.telemedicine.api.repository;


import com.telemedicine.api.entity.LifelogCroiseActivity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "lifelogca", path = "lifelogca")
public interface LifelogCroiseActivityRepository extends PagingAndSortingRepository<LifelogCroiseActivity, String> {
    List<LifelogCroiseActivity> findByUserId(@Param("userid") String userid);
}