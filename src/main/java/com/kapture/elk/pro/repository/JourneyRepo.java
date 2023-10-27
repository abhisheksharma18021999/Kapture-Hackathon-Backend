package com.kapture.elk.pro.repository;

import com.kapture.elk.pro.dto.Journey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JourneyRepo extends CrudRepository<Journey, Long> {

    @Query(value = " SELECT * FROM journey WHERE client_id = :cid ", nativeQuery = true)
    List<Journey> findJourneysByClientId(@Param("cid") Long cid);

}
