package com.ilender.micro.model;

public class LoanHistoryRequestInfo {

    int customerId;

    public LoanHistoryRequestInfo() {
    }

    public LoanHistoryRequestInfo(int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

}
