package com.kapture.elk.pro.repository;

import com.kapture.elk.pro.dto.Events;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepo extends CrudRepository<Events, Long> {


    @Query(value = " SELECT * FROM events WHERE client_id = :cid ", nativeQuery = true)
    List<Events> findEventsByClientId(@Param("cid")  long cid);

    @Query(value = " SELECT * FROM events WHERE customer_id = :cid ", nativeQuery = true)
    List<Events> findEventsByCustomerId(@Param("cid") long cid);


}
