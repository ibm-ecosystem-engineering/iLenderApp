package com.ilender.micro.model;

import java.util.Date;

public class LoanInfo {

    private int id;

    private int lndCustomerId;

    private Date loanRequestDate;

    private double loanAmount;

    private String purpose;

    private String statusCode;

    private String statusTextCustomer;

    private String statusTextBm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLndCustomerId() {
        return lndCustomerId;
    }

    public void setLndCustomerId(int lndCustomerId) {
        this.lndCustomerId = lndCustomerId;
    }

    public Date getLoanRequestDate() {
        return loanRequestDate;
    }

    public void setLoanRequestDate(Date loanRequestDate) {
        this.loanRequestDate = loanRequestDate;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusTextCustomer() {
        return statusTextCustomer;
    }

    public void setStatusTextCustomer(String statusTextCustomer) {
        this.statusTextCustomer = statusTextCustomer;
    }

    public String getStatusTextBm() {
        return statusTextBm;
    }

    public void setStatusTextBm(String statusTextBm) {
        this.statusTextBm = statusTextBm;
    }
}