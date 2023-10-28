package com.kapture.elk.pro.controller;


import com.kapture.elk.pro.dto.Events;
import com.kapture.elk.pro.dto.Journey;
import com.kapture.elk.pro.dto.Response;
import com.kapture.elk.pro.repository.EventRepo;
import com.kapture.elk.pro.repository.JourneyRepo;
import com.kapture.elk.pro.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/j")
public class JourneyController {

    @Autowired
    JourneyService journeyService;

    @Autowired
    EventRepo eventRepo;

    @Autowired
    JourneyRepo journeyRepo;

    @GetMapping("/getall")
    public Response getAll() {
        Response response = Response.getFailedResponse();
        try {
            List<Journey> list = (List<Journey>) journeyRepo.findAll();
            if (!CollectionUtils.isEmpty(list)) {
                response = Response.getSuccessResponse();
                response.setDataList(Collections.singletonList(list));
            }
        } catch (Exception e) {
            response.setErrorReason(e.getMessage());
        }
        return response;
    }

    @GetMapping("/getid")
    public Response getById(@RequestParam long id) {
        Response response = Response.getFailedResponse();
        try {
            Optional<Journey> optionalJourney = journeyRepo.findById(id);
            if (optionalJourney.isPresent()) {
                response = Response.getSuccessResponse();
                response.setData(optionalJourney.get());
            }
        } catch (Exception e) {
            response.setErrorReason(e.getMessage());
        }
        return response;
    }

    @GetMapping("/getbyclientid")
    public Response getByClientId(@RequestParam Long id) {
        Response response = Response.getFailedResponse();
        try {
            List<Journey> list = journeyRepo.findJourneysByClientId(id);
            if (!CollectionUtils.isEmpty(list)) {
                response = Response.getSuccessResponse();
                response.setDataList(Collections.singletonList(list));
            }
        } catch (Exception e) {
            response.setErrorReason(e.getMessage());
        }
        return response;
    }


    @PostMapping("/save")
    public Response save(@RequestBody Journey journey) {
        Response response = Response.getFailedResponse();
        try {
            Journey result = journeyRepo.save(journey);
            response = Response.getSuccessResponse();
            response.setData(result);
        } catch (Exception e) {
            response.setErrorReason(e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/delete")
    public Response delete(@RequestParam Long id) {
        Response response = Response.getFailedResponse();
        try {
            journeyRepo.deleteById(id);
            response = Response.getSuccessResponse();
        } catch (Exception e) {
            response.setErrorReason(e.getMessage());
        }
        return response;
    }


    @GetMapping("/getbycustomerid")
    public Response getJourneyByCustomerId(@RequestParam Long cid, @RequestParam Long jid) {
        Response response = Response.getFailedResponse();
        try {
            Optional<Journey> optionalJourney = journeyRepo.findById(jid);
            Set<Events> finalList = new HashSet<>();

            if (optionalJourney.isPresent()) {
                Journey journey = optionalJourney.get();

                List<Events> events = eventRepo.findEventsByCustomerId(cid);

                if (!CollectionUtils.isEmpty(events) && !StringUtils.isEmpty(journey.getEventNames())) {
                    String[] arr = journey.getEventNames().split(",");
                    for (String s : arr) {
                        s = s.trim();
                        for (Events e : events) {
                            if (e.getEventName().equalsIgnoreCase(s)) {
                                finalList.add(e);
                            }
                        }
                    }

                    response = Response.getSuccessResponse();
                    response.setData(journey);
                    response.setDataList(Collections.singletonList(events));
                }
            }

        } catch (Exception e) {
            response.setErrorReason(e.getMessage());
        }
        return response;
    }

}
