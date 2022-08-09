package com.ilender.micro.controller;

import com.ilender.micro.common.ControllerUtil;
import com.ilender.micro.common.LoadUtil;
import com.ilender.micro.entity.LndCustomer;
import com.ilender.micro.service.LndCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/customer")
public class LndCustomerController {

    @Autowired
    LndCustomerService service;

    @CrossOrigin
    @GetMapping
    private List<LndCustomer> findAll() {
        LoadUtil.processLoad();
        return service.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @ResponseBody
    private LndCustomer findOne (@PathVariable("id") int id) {
        LoadUtil.processLoad();
        return service.find(id);
    }

    @CrossOrigin
    @PostMapping
    private ResponseEntity<?> create(@RequestBody LndCustomer entity) {
        LoadUtil.processLoad();
        Object result = service.create(entity);
        return ControllerUtil.getResponseEntityForCreate(result);
    }

    @CrossOrigin
    @PutMapping
    private LndCustomer update(@RequestBody LndCustomer entity) {
        LoadUtil.processLoad();
        return service.update(entity);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    private LndCustomer delete (@PathVariable("id") int id) {
        LoadUtil.processLoad();
        return service.delete(id);
    }

}
