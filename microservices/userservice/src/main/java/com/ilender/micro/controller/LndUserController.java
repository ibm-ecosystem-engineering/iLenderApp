package com.ilender.micro.controller;

import com.ilender.micro.common.ControllerUtil;
import com.ilender.micro.common.LoadUtil;
import com.ilender.micro.entity.LndUser;
import com.ilender.micro.service.LndUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class LndUserController {

    public static final Logger logger = LoggerFactory.getLogger(LndUserController.class);

    @Autowired
    LndUserService service;

    @CrossOrigin
    @GetMapping
    private List<LndUser> findAll() {
        LoadUtil.processLoad();
        return service.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @ResponseBody
    private LndUser findOne (@PathVariable("id") int id) {
        LoadUtil.processLoad();
        return service.find(id);
    }

    @PostMapping
    private ResponseEntity<?>  create(@RequestBody LndUser entity) {
        LoadUtil.processLoad();
        Object createResult = service.create(entity);
        return ControllerUtil.getResponseEntityForCreate(createResult);
    }

    @CrossOrigin
    @PutMapping
    private LndUser update(@RequestBody LndUser entity) {
        LoadUtil.processLoad();
        return service.update(entity);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    private LndUser delete (@PathVariable("id") int id) {
        LoadUtil.processLoad();
        return service.delete(id);
    }

    @CrossOrigin
    @GetMapping("/delay/{time}")
    private List<LndUser> findAllWithDelay(@PathVariable("time") long time) {
        LoadUtil.processLoad();
        return service.findAllWithDelay(time);
    }


}
