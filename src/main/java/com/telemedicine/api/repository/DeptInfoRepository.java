package com.telemedicine.api.repository;


import com.telemedicine.api.entity.CodeDept;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "deptinfo", path = "deptinfo")
public interface DeptInfoRepository extends PagingAndSortingRepository<CodeDept, String> {
    List<CodeDept> findByCodeId(@Param("codeid") String codeid);
}



