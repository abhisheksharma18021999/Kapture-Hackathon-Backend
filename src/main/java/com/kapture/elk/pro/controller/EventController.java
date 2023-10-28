package com.kapture.elk.pro.controller;

import com.kapture.elk.pro.dto.Events;
import com.kapture.elk.pro.dto.Response;
import com.kapture.elk.pro.repository.EventRepo;
import com.kapture.elk.pro.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/e")
public class EventController {

    @Autowired
    EventRepo eventRepo;

    @Autowired
    EventService eventService;

    @GetMapping("/getall")
    public Response getAll() {
        Response response = Response.getFailedResponse();
        try {
            List<Events> events = (List<Events>) eventRepo.findAll();
            if (!CollectionUtils.isEmpty(events)) {
                response = Response.getSuccessResponse();
                response.setDataList(Collections.singletonList(events));
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
            Optional<Events> events = eventRepo.findById(id);
            if (events.isPresent()) {
                response = Response.getSuccessResponse();
                response.setData(events.get());
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
            List<Events> events = eventRepo.findEventsByClientId(id);
            if (!CollectionUtils.isEmpty(events)) {
                response = Response.getSuccessResponse();
                response.setDataList(Collections.singletonList(events));
            }
        } catch (Exception e) {
            response.setErrorReason(e.getMessage());
        }
        return response;
    }

    @GetMapping("/getbycustomerid")
    public Response getByCustomerId(@RequestParam Long id) {
        Response response = Response.getFailedResponse();
        try {
            List<Events> events = eventRepo.findEventsByCustomerId(id);
            if (!CollectionUtils.isEmpty(events)) {
                response = Response.getSuccessResponse();
                response.setDataList(Collections.singletonList(events));
            }
        } catch (Exception e) {
            response.setErrorReason(e.getMessage());
        }
        return response;
    }

    @PostMapping("/save")
    public Response save(@RequestBody Events events) {
        Response response = Response.getFailedResponse();
        try {
            Events event = eventRepo.save(events);
            response = Response.getSuccessResponse();
            response.setDataList(Collections.singletonList(events));
        } catch (Exception e) {
            response.setErrorReason(e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/delete")
    public Response delete(@RequestParam Long id) {
        Response response = Response.getFailedResponse();
        try {
            eventRepo.deleteById(id);
            response = Response.getSuccessResponse();
        } catch (Exception e) {
            response.setErrorReason(e.getMessage());
        }
        return response;
    }

}
