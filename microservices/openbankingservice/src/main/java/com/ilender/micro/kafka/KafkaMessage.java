package com.ilender.micro.kafka;

public class KafkaMessage {

    int loanId;
    int loanOfferId;
    int customerId;
    double loanAmount;
    int bankId;


    public KafkaMessage() {
    }

    public KafkaMessage(int customerId, int loanId, int loanOfferId, double loanAmount, int bankId) {
        this.customerId = customerId;
        this.loanId = loanId;
        this.loanOfferId = loanOfferId;
        this.loanAmount = loanAmount;
        this.bankId = bankId;
    }

    public int getLoanId() {
        return loanId;
    }

    public void setLoanId(int loanId) {
        this.loanId = loanId;
    }

    public int getLoanOfferId() {
        return loanOfferId;
    }

    public void setLoanOfferId(int loanOfferId) {
        this.loanOfferId = loanOfferId;
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

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

}
