package com.ilender.micro.controller;

import com.ilender.micro.common.LoadUtil;
import com.ilender.micro.entity.LndBankTransHistory;
import com.ilender.micro.service.LndBankTransHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/bankTransHistory")
public class LndBankTransHistoryController {

    @Autowired
    LndBankTransHistoryService service;

    @CrossOrigin
    @GetMapping
    private List<LndBankTransHistory> findAll() {
        LoadUtil.processLoad();
        return service.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @ResponseBody
    private LndBankTransHistory findOne (@PathVariable("id") int id) {
        LoadUtil.processLoad();
        return service.find(id);
    }

    @CrossOrigin
    @PostMapping
    private LndBankTransHistory create(@RequestBody LndBankTransHistory entity) {
        LoadUtil.processLoad();
        return service.create(entity);
    }

    @CrossOrigin
    @PutMapping
    private LndBankTransHistory update(@RequestBody LndBankTransHistory entity) {
        LoadUtil.processLoad();
        return service.update(entity);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    private LndBankTransHistory delete (@PathVariable("id") int id) {
        LoadUtil.processLoad();
        return service.delete(id);
    }

    @CrossOrigin
    @GetMapping("/findAllByLndCustomerId/{lndCustomerId}")
    @ResponseBody
    private List<LndBankTransHistory> findAllByLndCustomerId (@PathVariable("lndCustomerId") int lndCustomerId) {
        LoadUtil.processLoad();
        return service.findAllByLndCustomerId(lndCustomerId);
    }
}
