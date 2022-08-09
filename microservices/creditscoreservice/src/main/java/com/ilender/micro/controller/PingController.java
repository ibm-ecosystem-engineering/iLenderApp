package com.ilender.micro.controller;

import com.ilender.micro.common.LoadManager;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.service.LoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @Autowired
    LoadService service;


    @GetMapping("/ping")
    private String ping () {
        LogUtil.logDebug("LoadController ping ... ");
        return "true";
    }

    @GetMapping("/pingForMemoryAndSleep")
    private String pingForMemoryAndSleep () {
        LogUtil.logDebug("LoadController pingForMemoryAndSleep ... ");
        service.processLoad();
        return "true";
    }
}

