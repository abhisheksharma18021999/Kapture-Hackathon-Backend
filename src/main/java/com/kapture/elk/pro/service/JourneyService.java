package com.kapture.elk.pro.service;

import com.kapture.elk.pro.repository.JourneyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JourneyService {

    @Autowired
    JourneyRepo journeyRepo;



}
