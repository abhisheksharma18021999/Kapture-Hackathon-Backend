package com.kapture.elk.pro.controller;


import com.kapture.elk.pro.dto.Events;
import com.kapture.elk.pro.dto.Journey;
import com.kapture.elk.pro.repository.EventRepo;
import com.kapture.elk.pro.repository.JourneyRepo;
import com.kapture.elk.pro.service.JourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/j")
public class JourneyController {

    @Autowired
    JourneyService journeyService;

    @Autowired
    EventRepo eventRepo;

    @Autowired
    JourneyRepo journeyRepo;

    @GetMapping("/getAll")
    public List<Journey> getAll() {
        return (List<Journey>) journeyRepo.findAll();
    }

    @GetMapping("/getid")
    public Journey getById(@RequestParam long id) {
        Optional<Journey> optionalJourney = journeyRepo.findById(id);
        return optionalJourney.get();
    }

    @GetMapping("/getbyclientid")
    public List<Journey> getByClientId(@RequestParam Long id) {
        return journeyRepo.findJourneysByClientId(id);
    }

    @GetMapping("/getbycustomerid")
    public List<Events> getJourneyByCustomerId(@RequestParam Long cid, @RequestParam Long jid) {
        Optional<Journey> journey = journeyRepo.findById(jid);
        List<Events> finalList = new ArrayList<>();
        if (journey.isPresent()) {
            List<Events> events = eventRepo.findEventsByCustomerId(cid);
            String[] ar = journey.get().getEventNames().split(",");
            if (events != null && events.size() >= 1) {
                for (String s : ar) {
                    s = s.trim();
                    for (Events e : events) {
                        if (e.getEventName().equalsIgnoreCase(s)) {
                            finalList.add(e);
                        }
                    }
                }
            }
        }
        return finalList;
    }

    @PostMapping("/save")
    public Journey save(@RequestBody Journey journey) {
        return journeyRepo.save(journey);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        journeyRepo.deleteById(id);
    }

}
