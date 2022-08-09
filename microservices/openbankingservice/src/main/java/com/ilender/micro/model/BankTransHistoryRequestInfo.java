package com.ilender.micro.model;

public class BankTransHistoryRequestInfo {

    int customerId;

    public BankTransHistoryRequestInfo() {
    }

    public BankTransHistoryRequestInfo( int customerId) {
        this.customerId = customerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

}
