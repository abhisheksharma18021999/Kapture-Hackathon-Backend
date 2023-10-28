package com.kapture.elk.pro.service;

import com.kapture.elk.pro.dto.ClientCustomer;
import com.kapture.elk.pro.dto.Events;
import com.kapture.elk.pro.repository.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    EventRepo eventRepo;

    public void saveEvent(String eventName, ClientCustomer clientCustomer) {
        Events event = new Events();
        event.setClientId(clientCustomer.getClientId());
        event.setEventName(eventName);
        event.setCustomerId(clientCustomer.getId());
        eventRepo.save(event);
    }


}
