package com.ilender.micro.service;

import com.ilender.micro.entity.LndLoanOffer;
import com.ilender.micro.jpa.LndLoanOfferRepository;
import com.ilender.micro.model.LoanOfferInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class LndLoanOfferService {

    @Autowired
    LndLoanOfferRepository repository;

    public List<LndLoanOffer> findAll() {
        List<LndLoanOffer> list = new ArrayList();
        repository.findAll().forEach(listItem -> list.add(listItem));
        return list;
    }

    public LndLoanOffer create(LndLoanOffer lndLoan) {

        return repository.save(lndLoan);
    }

    public LndLoanOffer update(LndLoanOffer lndLoan) {

        repository.save(lndLoan);
        lndLoan = find(lndLoan.getId());
        return lndLoan;
    }

    public LndLoanOffer find(Integer id) {
        return repository.findById(id).get();
    }

    public LndLoanOffer delete(int id) {

        LndLoanOffer lndLoan = find(id);
        if(lndLoan != null){
            repository.delete(lndLoan);
        }
        return lndLoan;
    }

    public List<LndLoanOffer> findAllByLndLoanId(Integer lndloanId) {
        return repository.findAllByLndLoanId(lndloanId);
    }


    public LndLoanOffer createFromInfo(LoanOfferInfo info) {

        LndLoanOffer lndLoanOffer = new LndLoanOffer();

        lndLoanOffer.setLndLoanId(info.getLndLoanId());
        lndLoanOffer.setBankId(info.getBankId());
        lndLoanOffer.setBankName(info.getBankName());
        lndLoanOffer.setLoanAmount(info.getLoanAmount());

        lndLoanOffer.setTenure(info.getTenure());
        lndLoanOffer.setInterestRate(info.getInterestRate());
        lndLoanOffer.setOfferAccepted(0);

        return repository.save(lndLoanOffer);
    }

}
