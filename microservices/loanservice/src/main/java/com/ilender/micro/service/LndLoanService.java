package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.common.LoadUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.entity.LndLoan;
import com.ilender.micro.jpa.LndLoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class LndLoanService {

    @Autowired
    LndLoanRepository repository;

    @Autowired
    LoanProcessingService loanProcessingService;

    public List<LndLoan> findAll() {
        List<LndLoan> list = new ArrayList();
        repository.findAll(Sort.by("id").descending()).forEach(listItem ->  {
                list.add(listItem);
                loanProcessingService.process(listItem);
            }
        );
        return list;
    }

    public LndLoan create(LndLoan lndLoan) {
        LogUtil.logDebug("LndLoanService : create :" + lndLoan.getPurpose());
        lndLoan.setStatusCode(CommonConstants.LOAN_STATUS_01_LOAN_REQUESTED);
        return repository.save(lndLoan);
    }

    public LndLoan update(LndLoan lndLoan) {
        LogUtil.logDebug("LndLoanService : update :" + lndLoan.getId());

        repository.save(lndLoan);
        lndLoan = find(lndLoan.getId());
        return lndLoan;
    }

    public LndLoan find(Integer id) {
        LogUtil.logDebug("LndLoanService : find :" + id);
        LndLoan lndLoan = repository.findById(id).get();
        loanProcessingService.process(lndLoan);
        return lndLoan;
    }

    public LndLoan loanByPurpose (String purpose) {
        LogUtil.logDebug("LndLoanService : loanByPurpose :" + purpose);
        LndLoan lndLoan = repository.findOneByPurpose(purpose);
        loanProcessingService.process(lndLoan);
        return lndLoan;
    }

    public LndLoan delete(int id) {
        LogUtil.logDebug("LndLoanService : delete :" + id);

        LndLoan lndLoan = find(id);
        if(lndLoan != null){
            repository.delete(lndLoan);
        }
        return lndLoan;
    }

    public LndLoan findOneByLndCustomerId(Integer lndCustomerId) {
        LndLoan lndLoan = repository.findOneByLndCustomerId(lndCustomerId);
        loanProcessingService.process(lndLoan);
        return lndLoan;
    }


    public List<LndLoan> findAllByLndCustomerId(Integer lndCustomerId) {
        List<LndLoan> list = (List<LndLoan>) repository.findAllByLndCustomerId(lndCustomerId, Sort.by("id").descending());

        for (LndLoan info : list) {
            loanProcessingService.process(info);
        }
        return list;
    }
}
