package com.kapture.elk.pro.controller;


import com.kapture.elk.pro.dto.ClientCustomer;
import com.kapture.elk.pro.dto.Response;
import com.kapture.elk.pro.repository.ClientCustomerRepo;
import com.kapture.elk.pro.service.ClientCustomerService;
import com.kapture.elk.pro.service.EventService;
import com.kapture.elk.pro.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cc")
public class CustomerClientController {

    @Autowired
    ClientCustomerService clientCustomerService;

    @Autowired
    ClientCustomerRepo clientCustomerRepo;

    @Autowired
    EventService eventService;

    @GetMapping("/getall")
    public Response getAll() {
        Response response = Response.getFailedResponse();
        try {
            List<ClientCustomer> list = (List<ClientCustomer>) clientCustomerRepo.findAll();
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
            Optional<ClientCustomer> optional = clientCustomerRepo.findById(id);
            if (optional.isPresent()) {
                response = Response.getSuccessResponse();
                response.setData(optional.get());
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
            List<ClientCustomer> list = clientCustomerRepo.findCustomerByClientId(id);
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
    public Response save(@RequestBody ClientCustomer clientCustomer) {
        Response response = Response.getFailedResponse();
        try {
            if (clientCustomer != null
                    && !StringUtils.isEmpty(clientCustomer.getEmail())
                    && !StringUtils.isEmpty(clientCustomer.getPassword())) {
                ClientCustomer result = clientCustomerRepo.save(clientCustomer);
                response = Response.getSuccessResponse();
                response.setData(result);

                // save event
                eventService.saveEvent(Constants.SIGN_UP_EVENT, clientCustomer);
            }
        } catch (Exception e) {
            response.setErrorReason(e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/delete")
    public Response delete(@RequestParam Long id) {
        Response response = Response.getFailedResponse();
        try {
            clientCustomerRepo.deleteById(id);
            response = Response.getSuccessResponse();
        } catch (Exception e) {
            response.setErrorReason(e.getMessage());
        }
        return response;
    }

    @GetMapping("/validate")
    public Response validate(@RequestParam String email, @RequestParam String password) {
        Response response = Response.getFailedResponse();
        try {
            ClientCustomer clientCustomer = clientCustomerRepo.getClientByEmail(email);
            if (clientCustomer != null
                    && clientCustomer.getPassword() != null
                    && clientCustomer.getPassword().length() > 0
                    && clientCustomer.getPassword().equalsIgnoreCase(password)) {
                // save event
                eventService.saveEvent(Constants.SIGN_IN_EVENT, clientCustomer);
                return Response.getSuccessResponse();
            }
            if (clientCustomer != null) {
                // save event
                eventService.saveEvent(Constants.SIGN_IN_FAILED_EVENT, clientCustomer);
            }
        } catch (Exception e) {
            response.setErrorReason(e.getMessage());
        }
        return response;
    }

}
