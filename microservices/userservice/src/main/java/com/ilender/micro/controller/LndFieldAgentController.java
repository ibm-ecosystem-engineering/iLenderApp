package com.ilender.micro.controller;

import com.ilender.micro.common.ControllerUtil;
import com.ilender.micro.common.LoadUtil;
import com.ilender.micro.entity.LndFieldAgent;
import com.ilender.micro.service.LndFieldAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/fieldAgent")
public class LndFieldAgentController {

    @Autowired
    LndFieldAgentService service;

    @CrossOrigin
    @GetMapping
    private List<LndFieldAgent> findAll() {
        LoadUtil.processLoad();
        return service.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @ResponseBody
    private LndFieldAgent findOne (@PathVariable("id") int id) {
        LoadUtil.processLoad();
        return service.find(id);
    }

    @CrossOrigin
    @PostMapping
    private ResponseEntity<?> create(@RequestBody LndFieldAgent entity) {
        LoadUtil.processLoad();
        Object result = service.create(entity);
        return ControllerUtil.getResponseEntityForCreate(result);
    }

    @CrossOrigin
    @PutMapping
    private LndFieldAgent update(@RequestBody LndFieldAgent entity) {
        LoadUtil.processLoad();
        return service.update(entity);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    private LndFieldAgent delete (@PathVariable("id") int id) {
        LoadUtil.processLoad();
        return service.delete(id);
    }
}
