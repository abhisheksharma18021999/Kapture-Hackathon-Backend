package com.kapture.elk.pro.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kt")
public class KaptureTraceController {

    @GetMapping("/")
    public String check() {
        return "Hello World";
    }

}