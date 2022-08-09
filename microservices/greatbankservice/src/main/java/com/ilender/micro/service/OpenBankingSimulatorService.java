package com.ilender.micro.service;

import com.ilender.micro.model.LoanDetailInfo;
import com.ilender.micro.util.SimulatorUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpenBankingSimulatorService {

    public List loadLoanOffer(int loanId, double loanAmount) {
        List result = SimulatorUtil.loadLoanOffer(loanId, loanAmount);
        return result;
    }

    public LoanDetailInfo loadLoanDetail(int loanId, double loanAmount) {
        LoanDetailInfo result = SimulatorUtil.loadLoanDetail(loanId, loanAmount);
        return result;
    }
}
