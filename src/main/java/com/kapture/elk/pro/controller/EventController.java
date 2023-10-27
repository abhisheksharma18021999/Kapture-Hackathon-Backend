package com.kapture.elk.pro.controller;

import com.kapture.elk.pro.dto.Events;
import com.kapture.elk.pro.repository.EventRepo;
import com.kapture.elk.pro.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/e")
public class EventController {

    @Autowired
    EventRepo eventRepo;

    @Autowired
    EventService eventService;

    @GetMapping("/getAll")
    public List<Events> getAll() {
        return (List<Events>) eventRepo.findAll();
    }

    @GetMapping("/getid")
    public Events getById(@RequestParam long id) {
        Optional<Events> optionalJourney = eventRepo.findById(id);
        return optionalJourney.get();
    }

    @GetMapping("/getbyclientid")
    public List<Events> getByClientId(@RequestParam Long id) {
        return eventRepo.findEventsByClientId(id);
    }

    @GetMapping("/getbycustomerid")
    public List<Events> getByCustomerId(@RequestParam Long id) {
        return eventRepo.findEventsByCustomerId(id);
    }

    @PostMapping("/save")
    public Events save(@RequestBody Events events) {
        return eventRepo.save(events);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        eventRepo.deleteById(id);
    }

}
