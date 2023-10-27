package com.kapture.elk.pro.repository;

import com.kapture.elk.pro.dto.ClientCustomer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientCustomerRepo extends CrudRepository<ClientCustomer, Long> {

    @Query(value = " SELECT * FROM client_customer WHERE client_id = :cid ", nativeQuery = true)
    List<ClientCustomer> findCustomerByClientId(@Param("cid") long cid);

}
