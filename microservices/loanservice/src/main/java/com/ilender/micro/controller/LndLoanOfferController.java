package com.ilender.micro.controller;

import com.ilender.micro.common.LoadUtil;
import com.ilender.micro.entity.LndLoanOffer;
import com.ilender.micro.service.LndLoanOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/loanOffer")
public class LndLoanOfferController {

    @Autowired
    LndLoanOfferService service;

    @CrossOrigin
    @GetMapping
    private List<LndLoanOffer> findAll() {
        LoadUtil.processLoad();
        return service.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @ResponseBody
    private LndLoanOffer findOne (@PathVariable("id") int id) {
        LoadUtil.processLoad();
        return service.find(id);
    }

    @CrossOrigin
    @PostMapping
    private LndLoanOffer create(@RequestBody LndLoanOffer entity) {
        LoadUtil.processLoad();
        return service.create(entity);
    }

    @CrossOrigin
    @PutMapping
    private LndLoanOffer update(@RequestBody LndLoanOffer entity) {
        LoadUtil.processLoad();
        return service.update(entity);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    private LndLoanOffer delete (@PathVariable("id") int id) {
        LoadUtil.processLoad();
        return service.delete(id);
    }
}
