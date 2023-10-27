package com.kapture.elk.pro.controller;

import com.kapture.elk.pro.dto.ClientCustomer;
import com.kapture.elk.pro.repository.ClientCustomerRepo;
import com.kapture.elk.pro.service.ClientCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cc")
public class CustomerClientController {

    @Autowired
    ClientCustomerService clientCustomerService;

    @Autowired
    ClientCustomerRepo clientCustomerRepo;

    @GetMapping("/getAll")
    public List<ClientCustomer> getAll() {
        return (List<ClientCustomer>) clientCustomerRepo.findAll();
    }

    @GetMapping("/getid")
    public ClientCustomer getById(@RequestParam long id) {
        Optional<ClientCustomer> optionalJourney = clientCustomerRepo.findById(id);
        return optionalJourney.get();
    }

    @GetMapping("/getbyclientid")
    public List<ClientCustomer> getByClientId(@RequestParam Long id) {
        return clientCustomerRepo.findCustomerByClientId(id);
    }

    @PostMapping("/save")
    public ClientCustomer save(@RequestBody ClientCustomer journey) {
        return clientCustomerRepo.save(journey);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        clientCustomerRepo.deleteById(id);
    }


}
