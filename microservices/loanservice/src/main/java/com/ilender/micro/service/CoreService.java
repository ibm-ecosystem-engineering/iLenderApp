package com.ilender.micro.service;

import com.ilender.micro.common.CommonConstants;
import com.ilender.micro.common.LoadUtil;
import com.ilender.micro.common.LogUtil;
import com.ilender.micro.entity.LndLoan;
import com.ilender.micro.entity.LndLoanOffer;
import com.ilender.micro.model.LoanDetailInfo;
import com.ilender.micro.model.LoanOfferInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class CoreService {

    private static final Logger logger = LoggerFactory.getLogger(CommonConstants.LOGDNA_LOGGER);

    @Autowired
    LogService logService;

    @Autowired
    LndLoanService loanService;

    @Autowired
    LndLoanDetailService loanDetailService;

    @Autowired
    LndLoanOfferService loanOfferService;


    public LndLoan findOneByLndCustomerId(Integer lndCustomerId) {
        if (LoadUtil.isStartErrorFlag()) {
            logService.logError(logger, "Error occurred while finding customer");
            return null;
        } else {
            return findOneByLndCustomerId_Org(lndCustomerId);
        }
    }


    public LndLoan updateCreditScore(int lndLoanId, double creditScore) {
        if (LoadUtil.isStartErrorFlag()) {
            logService.logError(logger, "Error occurred while updating CreditScore");
            return null;
        } else {
            return updateCreditScore_Org(lndLoanId, creditScore);
        }
    }


    public LndLoan updateLoanRequestRejected(int lndLoanId) {
        if (LoadUtil.isStartErrorFlag()) {
            logService.logError(logger, "Error occurred while updating LoanRequest Rejected");
            return null;
        } else {
            return updateLoanRequestRejected_Org(lndLoanId);
        }
    }


    public LndLoan updateLoanRequestApproved(int lndLoanId) {
        if (LoadUtil.isStartErrorFlag()) {
            logService.logError(logger, "Error occurred while updating LoanRequest Approved");
            return null;
        } else {
            return updateLoanRequestApproved_Org(lndLoanId);
        }
    }

    public Boolean updateOfferFromBank(List<LoanOfferInfo> list) {
        if (LoadUtil.isStartErrorFlag()) {
            logService.logError(logger, "Error occurred while updating Offer FromBank");
            return null;
        } else {
            return updateOfferFromBank_Org(list);
        }
    }

    public LndLoan updateLoanOfferAccepted (int lndLoanOfferId) {
        if (LoadUtil.isStartErrorFlag()) {
            logService.logError(logger, "Error occurred while updating LoanOffer Accepted");
            return null;
        } else {
            return updateLoanOfferAccepted_Org(lndLoanOfferId);
        }
    }

    public Boolean updateDetailFromBank(LoanDetailInfo info) {
        if (LoadUtil.isStartErrorFlag()) {
            logService.logError(logger, "Error occurred while updating Detail FromBank");
            return false;
        } else {
            return updateDetailFromBank_Org(info);
        }
    }

    public LndLoan findOneByLndCustomerId_Org(Integer lndCustomerId) {
        LndLoan lndLoan = loanService.findOneByLndCustomerId(lndCustomerId);
        logService.logInfo(logger, "Loan Retrieved for Customer : " + lndCustomerId);
        return lndLoan;
    }

    public LndLoan updateCreditScore_Org(int lndLoanId, double creditScore) {
        LogUtil.logDebug("LndLoanService : updateCreditScore : " + lndLoanId);
        logService.logInfo(logger, "Update CreditScore  from bank started  ");

        LndLoan lndLoan = loanService.find(lndLoanId);
        if (lndLoan != null) {
            LogUtil.logDebug("LndLoanService : updateCreditScore : " + lndLoan);
            lndLoan.setStatusCode(CommonConstants.LOAN_STATUS_02_CREDIT_SCORE_RECEIVED);
            lndLoan.setCreditScore(creditScore);
            loanService.update (lndLoan);
        }
        LogUtil.logDebug("LndLoanService : updateCreditScore : completed");
        logService.logInfo(logger, "Credit Score updated for loan  : " + lndLoanId);
        return lndLoan;
    }


    public LndLoan updateLoanRequestRejected_Org(int lndLoanId) {
        LogUtil.logDebug("LndLoanService : updateLoanRequestRejected : " + lndLoanId);
        logService.logInfo(logger, "Update rejected Status from bank started  ");

        LndLoan lndLoan = loanService.find(lndLoanId);
        if (lndLoan != null) {
            LogUtil.logDebug("LndLoanService : updateLoanRequestRejected : " + lndLoan);
            lndLoan.setStatusCode(CommonConstants.LOAN_STATUS_03_LOAN_REQUEST_REJECTED);
            loanService.update (lndLoan);
        }

        logService.logInfo(logger, "Rejected Status updated for loan  : " + lndLoanId);

        LogUtil.logDebug("LndLoanService : updateLoanRequestRejected : completed");

        return lndLoan;
    }

    public LndLoan updateLoanRequestApproved_Org(int lndLoanId) {
        LogUtil.logDebug("LndLoanService : updateLoanRequestApproved : " + lndLoanId);
        logService.logInfo(logger, "Update Approved Status from bank started  ");

        LndLoan lndLoan = loanService.find(lndLoanId);
        if (lndLoan != null) {
            LogUtil.logDebug("LndLoanService : updateLoanRequestApproved : " + lndLoan);
            lndLoan.setStatusCode(CommonConstants.LOAN_STATUS_04_LOAN_REQUEST_SENT_TO_BANKS);
            loanService.update (lndLoan);
        }
        LogUtil.logDebug("LndLoanService : updateLoanRequestApproved : completed");
        logService.logInfo(logger, "Approved Status updated for loan  : " + lndLoanId);

        return lndLoan;
    }

    public Boolean updateOfferFromBank_Org(List<LoanOfferInfo> list) {
        LogUtil.logDebug("LndLoanService : updateOfferFromBank : " + list);
        logService.logInfo(logger, "Update Loan offer from bank started  ");

        LndLoan lndLoan = null;
        for (LoanOfferInfo info : list) {
            lndLoan = loanService.find(info.getLndLoanId());
            if (lndLoan == null) {
                LogUtil.logDebug("LndLoanService : updateOfferFromBank Loan NOT found for :" + info.getLndLoanId());
            } else {
                LogUtil.logDebug("LndLoanService : updateOfferFromBank Loan found for :" + info.getLndLoanId());
                lndLoan.setStatusCode(CommonConstants.LOAN_STATUS_05_LOAN_OFFERES_SENT_TO_SMB);
                loanService.update(lndLoan);
                loanOfferService.createFromInfo(info);
            }
        }
        if (lndLoan != null) {
            logService.logInfo(logger, "Offer from Bank updated for loan  : " + lndLoan.getId());
        }

        return true;
    }

    public LndLoan updateLoanOfferAccepted_Org (int lndLoanOfferId) {
        LogUtil.logDebug("LndLoanService : updateLoanOfferAccepted : " + lndLoanOfferId);
        logService.logInfo(logger, "Loan offer accept status update started  ");

        LndLoan lndLoan = null;

        //Find LoanOffer
        LndLoanOffer lndLoanOffer = loanOfferService.find(lndLoanOfferId);

        if (lndLoanOffer != null) {
            //Find Loan
            lndLoan = (LndLoan) loanService.find(lndLoanOffer.getLndLoanId());
            if (lndLoan != null) {
                LogUtil.logDebug("LndLoanService : updateLoanRequestApproved : LndLoan: " + lndLoan);

                //Update Loan status
                lndLoan.setStatusCode(CommonConstants.LOAN_STATUS_06_LOAN_ACCEPTED_BY_SMB);
                loanService.update(lndLoan);

                //Update LoanOffer status
                lndLoanOffer.setOfferAccepted(1);
                loanOfferService.update(lndLoanOffer);
            }
        }
        LogUtil.logDebug("LndLoanService : updateLoanOfferAccepted : completed");
        logService.logInfo(logger, "Loan Offer accepted Status updated for loan offer : " + lndLoanOfferId);
        return lndLoan;
    }

    public Boolean updateDetailFromBank_Org(LoanDetailInfo info) {
        LogUtil.logDebug("LndLoanService : updateDetailFromBank : " + info);
        logService.logInfo(logger, "Loan Detail update started  ");

        LndLoan lndLoan = loanService.find(info.getLndLoanId());
        loanService.update(lndLoan);

        loanDetailService.createFromInfo(info);

        logService.logInfo(logger, "Loan Detail updated for loan : " + info.getLndLoanId());

        return true;
    }

}
