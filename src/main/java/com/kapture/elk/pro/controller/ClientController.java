package com.kapture.elk.pro.controller;


import com.kapture.elk.pro.dto.Client;
import com.kapture.elk.pro.repository.ClientRepo;
import com.kapture.elk.pro.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/c")
public class ClientController {

    @Autowired
    ClientRepo clientRepo;

    @Autowired
    ClientService clientService;

    @GetMapping("/getall")
    public List<Client> getAll() {
        return (List<Client>) clientRepo.findAll();
    }

    @GetMapping("/getid")
    public Client getById(@RequestParam long id) {
        Optional<Client> optionalJourney = clientRepo.findById(id);
        return optionalJourney.get();
    }

    @PostMapping("/save")
    public Client save(@RequestBody Client journey) {
        return clientRepo.save(journey);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        clientRepo.deleteById(id);
    }

}
