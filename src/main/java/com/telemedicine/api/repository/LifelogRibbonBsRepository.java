package com.telemedicine.api.repository;


import com.telemedicine.api.entity.LifelogRibbonBs;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "lifelogrb", path = "lifelogrb")
public interface LifelogRibbonBsRepository extends PagingAndSortingRepository<LifelogRibbonBs, String> {
    List<LifelogRibbonBs> findByUserId(@Param("userid") String userid);
}



