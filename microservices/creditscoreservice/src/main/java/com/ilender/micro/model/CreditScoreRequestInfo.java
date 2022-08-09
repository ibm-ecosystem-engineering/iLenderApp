package com.ilender.micro.model;

import java.util.List;

public class CreditScoreRequestInfo {

    private int customerId;
    private List bankTransHistory;
    private List loanHistory;

    public CreditScoreRequestInfo(int customerId, List bankTransHistory, List loanHistory) {
        this.customerId = customerId;
        this.bankTransHistory = bankTransHistory;
        this.loanHistory = loanHistory;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List getBankTransHistory() {
        return bankTransHistory;
    }

    public void setBankTransHistory(List bankTransHistory) {
        this.bankTransHistory = bankTransHistory;
    }

    public List getLoanHistory() {
        return loanHistory;
    }

    public void setLoanHistory(List loanHistory) {
        this.loanHistory = loanHistory;
    }

    @Override
    public String toString() {
        return "CreditScoreRequestInfo{" +
                "customerId=" + customerId +
                ", bankTransHistory=" + bankTransHistory +
                ", loanHistory=" + loanHistory +
                '}';
    }
}
