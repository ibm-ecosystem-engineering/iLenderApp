package com.ilender.micro.util;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.common.DateUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.model.LoanDetailInfo;
import com.ilender.micro.model.LoanOfferInfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SimulatorUtil {

    public static List loadLoanOffer(int lndLoanId, double loanAmount) {

        int tenure = 5;
        int interestRate = 12;

        if (loanAmount <= 2000000) {
            tenure = 5;
            interestRate = 12;
        } else if (loanAmount <= 3000000) {
            tenure = 6;
            interestRate = 13;
        } else {
            tenure = 7;
            interestRate = 14;
        }

        List list = new ArrayList();
        list.add(new LoanOfferInfo(lndLoanId, CommonConstants.BANK_ID, CommonConstants.BANK_NAME, loanAmount, tenure, interestRate));
        return list;
    }

    public static LoanDetailInfo loadLoanDetail(int lndLoanId, double loanAmount) {

        int tenure = 5;
        int interestRate = 12;

        if (loanAmount <= 2000000) {
            tenure = 5;
            interestRate = 12;
        } else if (loanAmount <= 3000000) {
            tenure = 6;
            interestRate = 13;
        } else {
            tenure = 7;
            interestRate = 14;
        }


        Date loanEndDate = DateUtil.addYearToDate(new Date(), tenure);
        int emi = (int) calcEMI(loanAmount, interestRate, tenure);
        int installmentRemaining = tenure *12;

        LoanDetailInfo info = new LoanDetailInfo();
        info.setLndLoanId(lndLoanId);
        info.setBankId(CommonConstants.BANK_ID);
        info.setBankName(CommonConstants.BANK_NAME);
        info.setInterestRate(interestRate);
        info.setLoanReferenceNo("LN-" + System.currentTimeMillis());
        info.setLoanGrantedDate(new Date());
        info.setInterestRate(interestRate);

        info.setEmi(emi);
        info.setLoanStartDate(new Date());
        info.setLoanEndDate(loanEndDate);

        info.setPrincipalRemaining(loanAmount);
        info.setLastInstallmentAmount(emi);
        info.setLastInstallmentDate(loanEndDate);
        info.setNoOfInstallmentsRemaining(installmentRemaining);
        info.setTenure(tenure);

        return info;
    }

    public static double calcEMI(double p, double r, int n) {
//        n = n * 12;
//        double result = (p * r * (1.0 + r) * n) / (((1 + r) * n) - 1);

        double interestAmount = (p * r * n);
        double result = (p + interestAmount) / (n * 12);

        LogUtil.logDebug("SimulatorUtil : calcEMI : result : " + result);
        return result;
    }
}
