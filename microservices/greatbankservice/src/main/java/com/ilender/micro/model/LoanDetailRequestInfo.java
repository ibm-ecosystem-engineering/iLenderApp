package com.ilender.micro.model;

public class LoanDetailRequestInfo {

    int loanId;
    int customerId;
    double loanAmount;

    public LoanDetailRequestInfo() {
    }

    public LoanDetailRequestInfo(int customerId, int loanId, double loanAmount) {
        this.customerId = customerId;
        this.loanId = loanId;
        this.loanAmount = loanAmount;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    @Override
    public String toString() {
        return "LoanDetailRequestInfo{" +
                "loanId=" + loanId +
                ", customerId=" + customerId +
                ", loanAmount=" + loanAmount +
                '}';
    }
}
