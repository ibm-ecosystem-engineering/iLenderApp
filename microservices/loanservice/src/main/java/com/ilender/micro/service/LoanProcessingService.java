package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.common.DateUtil;
import com.ilender.micro.entity.LndLoan;
import com.ilender.micro.entity.LndLoanDetail;
import com.ilender.micro.entity.LndLoanOffer;
import com.ilender.micro.model.LoanProcessInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanProcessingService {

    @Autowired
    LndLoanDetailService loanDetailService;

    @Autowired
    LndLoanOfferService loanOfferService;

    public static String  STATUS_01_LOAN_REQUESTED_BM = "Get credit score using Watson Artificial Intelligence from IBM Cloud";
    public static String  STATUS_01_LOAN_REQUESTED_CUSTOMER = "Loan request is submitted to Fintech. Awaiting their approval.";

    public static String  STATUS_02_CREDIT_SCORE_RECEIVED_BM = "Credit score is ##CREDIT_SCORE##. Do you want to approve the request for SMB customer to get loans from registered banks?";
    public static String  STATUS_02_CREDIT_SCORE_RECEIVED_CUSTOMER = "Loan request is submitted to Fintech. Awaiting their approval.";

    public static String  STATUS_03_LOAN_REQUEST_REJECTED_BM = "Credit score is ##CREDIT_SCORE##. Loan request by SMB Customer is rejected by Business Manager";
    public static String  STATUS_03_LOAN_REQUEST_REJECTED_CUSTOMER = "Loan request is rejected by Business Manager.";

    public static String  STATUS_04_LOAN_REQUEST_SENT_TO_BANKS_BM = "Credit score is ##CREDIT_SCORE##. Loan request is sent to registered banks and awaiting their offers to SMB Customer.";
    public static String  STATUS_04_LOAN_REQUEST_SENT_TO_BANKS_CUSTOMER = "Loan request is submitted to 2 banks. Awaiting response from them.";

    public static String  STATUS_05_LOAN_OFFERES_SENT_TO_SMB_BM = "Credit score is ##CREDIT_SCORE##. SMB Customer is provided Offers by the bank.";
    public static String  STATUS_05_LOAN_OFFERES_SENT_TO_SMB_CUSTOMER = "##BANK## is offering Loan Amount of ##LOAN_AMOUNT## for  ##YEARS## years tenure and ##INTEREST##% interest ";

    public static String  STATUS_06_LOAN_ACCEPTED_BY_SMB_BM = "Credit score is ##CREDIT_SCORE##. SMB Customer chose to go with ##BANK## with ##YEARS## years tenure and ##INTEREST##% Interest rate.";
    public static String  STATUS_06_LOAN_ACCEPTED_BY_SMB_CUSTOMER = "";

    public void process(LndLoan lndLoan) {
        populateChild(lndLoan);

        lndLoan.setStartDateString(DateUtil.convertDDMMMYYY(lndLoan.getStartDate()));
        lndLoan.setLoanRequestDateString(DateUtil.convertDDMMMYYY(lndLoan.getLoanRequestDate()));

        populateStatus(lndLoan);
    }

    private void populateChild(LndLoan lndLoan) {
        lndLoan.setLoanOfferList(loanOfferService.findAllByLndLoanId(lndLoan.getId()));
        lndLoan.setLoanDetailList(loanDetailService.findAllByLndLoanId(lndLoan.getId()));
    }

    private void populateStatus(LndLoan lndLoan) {
       String statusCode = lndLoan.getStatusCode();
       if (CommonConstants.LOAN_STATUS_01_LOAN_REQUESTED.equalsIgnoreCase(statusCode)) {
           populateStatus01(lndLoan);
       } else if (CommonConstants.LOAN_STATUS_02_CREDIT_SCORE_RECEIVED.equalsIgnoreCase(statusCode)) {
           populateStatus02(lndLoan);
       } else if (CommonConstants.LOAN_STATUS_03_LOAN_REQUEST_REJECTED.equalsIgnoreCase(statusCode)) {
           populateStatus03(lndLoan);
       } else if (CommonConstants.LOAN_STATUS_04_LOAN_REQUEST_SENT_TO_BANKS.equalsIgnoreCase(statusCode)) {
           populateStatus04(lndLoan);
       } else if (CommonConstants.LOAN_STATUS_05_LOAN_OFFERES_SENT_TO_SMB.equalsIgnoreCase(statusCode)) {
           populateStatus05(lndLoan);
       } else if (CommonConstants.LOAN_STATUS_06_LOAN_ACCEPTED_BY_SMB.equalsIgnoreCase(statusCode)) {
           populateStatus06(lndLoan);
       }
    }

    public void populateStatus01 (LndLoan lndLoan) {
        lndLoan.setStatusTextBm(STATUS_01_LOAN_REQUESTED_BM);
        lndLoan.setStatusTextCustomer(STATUS_01_LOAN_REQUESTED_CUSTOMER);
    }

    public void populateStatus02 (LndLoan lndLoan) {
        lndLoan.setStatusTextBm(replaceCreditScore(lndLoan, STATUS_02_CREDIT_SCORE_RECEIVED_BM));
        lndLoan.setStatusTextCustomer(STATUS_02_CREDIT_SCORE_RECEIVED_CUSTOMER);
    }

    public void populateStatus03 (LndLoan lndLoan) {
        lndLoan.setStatusTextBm(replaceCreditScore(lndLoan, STATUS_03_LOAN_REQUEST_REJECTED_BM));
        lndLoan.setStatusTextCustomer(STATUS_03_LOAN_REQUEST_REJECTED_CUSTOMER);
    }

    public void populateStatus04 (LndLoan lndLoan) {
        lndLoan.setStatusTextBm(replaceCreditScore(lndLoan, STATUS_04_LOAN_REQUEST_SENT_TO_BANKS_BM));
        lndLoan.setStatusTextCustomer(STATUS_04_LOAN_REQUEST_SENT_TO_BANKS_CUSTOMER);
    }

    public void populateStatus05 (LndLoan lndLoan) {
        lndLoan.setStatusTextBm(replaceCreditScore(lndLoan, STATUS_05_LOAN_OFFERES_SENT_TO_SMB_BM));
        lndLoan.setStatusTextCustomer("");

        List<LoanProcessInfo> list = new ArrayList();
        if (lndLoan.getLoanOfferList() != null) {
            for (LndLoanOffer info :  lndLoan.getLoanOfferList()) {
                String text = STATUS_05_LOAN_OFFERES_SENT_TO_SMB_CUSTOMER;
                text = text.replace("##BANK##", info.getBankName());
                text = text.replace("##YEARS##", String.valueOf(info.getTenure()));
                text = text.replace("##INTEREST##", String.valueOf(info.getInterestRate()));
                text = text.replace("##LOAN_AMOUNT##", String.valueOf(info.getLoanAmount()));
                list.add(new LoanProcessInfo(info.getId(), text));
            }
        }
        lndLoan.setLoanProcessInfoList(list);
    }

    public void populateStatus06 (LndLoan lndLoan) {
        String text = STATUS_06_LOAN_ACCEPTED_BY_SMB_BM;
        if (lndLoan.getLoanDetailList() != null && lndLoan.getLoanDetailList().size() > 0) {
            LndLoanDetail info = lndLoan.getLoanDetailList().get(0);
            text = replaceCreditScore(lndLoan, text);
            text = text.replace("##BANK##", info.getBankName());
            text = text.replace("##YEARS##", String.valueOf(info.getTenure()));
            text = text.replace("##INTEREST##", String.valueOf(info.getInterestRate()));
        }
        lndLoan.setStatusTextBm(text);
        lndLoan.setStatusTextCustomer(STATUS_06_LOAN_ACCEPTED_BY_SMB_CUSTOMER);
    }

    public String replaceCreditScore (LndLoan lndLoan, String text) {
        if (lndLoan.getCreditScore() > 0) {
            text = text.replace("##CREDIT_SCORE##", String.valueOf(lndLoan.getCreditScore()));
        }
        return text;
    }
}
