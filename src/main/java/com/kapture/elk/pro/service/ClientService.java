package com.kapture.elk.pro.service;



import com.kapture.elk.pro.repository.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepo clientRepo;


}
