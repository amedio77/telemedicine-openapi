package com.telemedicine.api.repository;


import com.telemedicine.api.entity.TelemedicineList;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "telemedicines", path = "telemedicines")
public interface TelemedicineListRepository extends PagingAndSortingRepository<TelemedicineList, String> {
    List<TelemedicineList> findByFromId(@Param("userid") String userid);
    List<TelemedicineList> findByToId(@Param("userid") String userid);
}



