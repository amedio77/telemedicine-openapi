package com.telemedicine.api.repository;


import com.telemedicine.api.entity.LifelogBoryungBp;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "lifelogbb", path = "lifelogbb")
public interface LifelogBoryungBpRepository extends PagingAndSortingRepository<LifelogBoryungBp, String> {
/*
    @Query(value="select          SEQ_NO,\n" +
            "         USER_ID ,\n" +
            "         MODE_TYPE ,\n" +
            "         MS_DT ,\n" +
            "         SYSTOLIC ,\n" +
            "         DIASTOLIC ,\n" +
            "         PULSE ,\n" +
            "         APP_VERSION ,\n" +
            "         FIRMWARE ,\n" +
            "         REG_DT from lifelog_boryung_bp", nativeQuery=true)
*/
    List<LifelogBoryungBp> findByUserId(@Param("userid") String userid);
}



