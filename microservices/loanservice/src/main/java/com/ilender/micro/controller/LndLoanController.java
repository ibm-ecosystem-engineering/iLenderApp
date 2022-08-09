package com.ilender.micro.controller;

import com.ilender.micro.common.LoadUtil;
import com.ilender.micro.entity.LndLoan;
import com.ilender.micro.service.KafkaService;
import com.ilender.micro.service.LndLoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/loan")
public class LndLoanController {

    @Autowired
    LndLoanService service;

    @Autowired
    KafkaService kafkaService;

    @CrossOrigin
    @GetMapping
    private List<LndLoan> findAll() {
        LoadUtil.processLoad();
        return service.findAll();
    }

    @CrossOrigin
    @GetMapping("/{id}")
    @ResponseBody
    private LndLoan findOne (@PathVariable("id") int id) {
        LoadUtil.processLoad();
        return service.find(id);
    }

    @CrossOrigin
    @GetMapping("/loanByPurpose/{purpose}")
    @ResponseBody
    private LndLoan loanByPurpose (@PathVariable("purpose") String purpose) {
        LoadUtil.processLoad();
        return service.loanByPurpose(purpose);
    }

    @CrossOrigin
    @PostMapping
    private LndLoan create(@RequestBody LndLoan entity) {
        LoadUtil.processLoad();

        LndLoan result = service.create(entity);

        //Send notification
        kafkaService.notifyLoanRequestCreated(entity);

        return result;

    }

    @CrossOrigin
    @PutMapping
    private LndLoan update(@RequestBody LndLoan entity) {
        LoadUtil.processLoad();
        return service.update(entity);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    private LndLoan delete (@PathVariable("id") int id) {
        LoadUtil.processLoad();
        return service.delete(id);
    }

    @CrossOrigin
    @GetMapping("/findOneByLndCustomerId/{lndCustomerId}")
    @ResponseBody
    private LndLoan findOneByLndCustomerId (@PathVariable("lndCustomerId") int lndCustomerId) {
        LoadUtil.processLoad();
        return service.findOneByLndCustomerId(lndCustomerId);
    }

    @CrossOrigin
    @GetMapping("/findAllByLndCustomerId/{lndCustomerId}")
    @ResponseBody
    private List findAllByLndCustomerId (@PathVariable("lndCustomerId") int lndCustomerId) {
        LoadUtil.processLoad();
        return service.findAllByLndCustomerId(lndCustomerId);
    }

}
