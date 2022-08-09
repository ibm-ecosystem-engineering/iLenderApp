package com.ilender.micro.controller;

import com.ilender.micro.entity.LndLoanHistory;
import com.ilender.micro.service.LndLoanHistoryService;
import com.ilender.micro.common.LoadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/loanHistory")
public class LndLoanHistoryController {

    @Autowired
    LndLoanHistoryService service;

    @CrossOrigin
    @GetMapping
    private List<LndLoanHistory> findAll() {
        LoadUtil.processLoad();
        return service.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @ResponseBody
    private LndLoanHistory findOne (@PathVariable("id") int id) {
        LoadUtil.processLoad();
        return service.find(id);
    }

    @CrossOrigin
    @PostMapping
    private LndLoanHistory create(@RequestBody LndLoanHistory entity) {
        LoadUtil.processLoad();
        return service.create(entity);
    }

    @CrossOrigin
    @PutMapping
    private LndLoanHistory update(@RequestBody LndLoanHistory entity) {
        LoadUtil.processLoad();
        return service.update(entity);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    private LndLoanHistory delete (@PathVariable("id") int id) {
        LoadUtil.processLoad();
        return service.delete(id);
    }

    @CrossOrigin
    @GetMapping("/findAllByLndCustomerId/{lndCustomerId}")
    @ResponseBody
    private List<LndLoanHistory> findAllByLndCustomerId (@PathVariable("lndCustomerId") int lndCustomerId) {
        LoadUtil.processLoad();
        return service.findAllByLndCustomerId(lndCustomerId);
    }
}
