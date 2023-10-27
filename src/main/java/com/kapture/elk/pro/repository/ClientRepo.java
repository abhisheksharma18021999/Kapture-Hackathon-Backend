package com.kapture.elk.pro.repository;


import com.kapture.elk.pro.dto.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepo extends CrudRepository<Client, Long> {

}
