package com.kapture.elk.pro.controller;


import com.kapture.elk.pro.dto.Client;
import com.kapture.elk.pro.dto.Response;
import com.kapture.elk.pro.repository.ClientRepo;
import com.kapture.elk.pro.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
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
    public Response getAll() {
        Response response = Response.getFailedResponse();
        try {
            List<Client> list = (List<Client>) clientRepo.findAll();
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
            Optional<Client> optionalClient = clientRepo.findById(id);
            if (optionalClient.isPresent()) {
                response = Response.getSuccessResponse();
                response.setData(optionalClient.get());
            }
        } catch (Exception e) {
            response.setErrorReason(e.getMessage());
        }
        return response;
    }

    @PostMapping("/save")
    public Response save(@RequestBody Client client) {
        Response response = Response.getFailedResponse();
        try {
            Client result = clientRepo.save(client);
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
            clientRepo.deleteById(id);
            response = Response.getSuccessResponse();
        } catch (Exception e) {
            response.setErrorReason(e.getMessage());
        }
        return response;
    }

}
