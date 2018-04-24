package com.telemedicine.api.repository;


import com.telemedicine.api.entity.CodeHosp;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "hospinfo", path = "hospinfo")
public interface HospInfoRepository extends PagingAndSortingRepository<CodeHosp, String> {
    List<CodeHosp> findByCodeId(@Param("codeid") String codeid);
}



