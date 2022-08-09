package com.ilender.micro.controller;

import com.ilender.micro.common.LoadUtil;
import com.ilender.micro.entity.LndLoanDetail;
import com.ilender.micro.service.LndLoanDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/loanDetail")
public class LndLoanDetailController {

    @Autowired
    LndLoanDetailService service;

    @CrossOrigin
    @GetMapping
    private List<LndLoanDetail> findAll() {
        LoadUtil.processLoad();
        return service.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @ResponseBody
    private LndLoanDetail findOne (@PathVariable("id") int id) {
        LoadUtil.processLoad();
        return service.find(id);
    }

    @CrossOrigin
    @PostMapping
    private LndLoanDetail create(@RequestBody LndLoanDetail entity) {
        LoadUtil.processLoad();
        return service.create(entity);
    }

    @CrossOrigin
    @PutMapping
    private LndLoanDetail update(@RequestBody LndLoanDetail entity) {
        LoadUtil.processLoad();
        return service.update(entity);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    private LndLoanDetail delete (@PathVariable("id") int id) {
        LoadUtil.processLoad();
        return service.delete(id);
    }
}
