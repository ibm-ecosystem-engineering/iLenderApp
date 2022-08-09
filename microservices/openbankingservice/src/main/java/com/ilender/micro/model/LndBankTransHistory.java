package com.ilender.micro.model;

import java.util.Date;

public class LndBankTransHistory {

    private int id;

    private int lndCustomerId;

    private int bankId;

    private String bankName;

    private Date transDate;

    private String particulars;

    private double withdrawl;

    private double deposit;

    private double balance;

    private String transDateString;

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

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public double getWithdrawl() {
        return withdrawl;
    }

    public void setWithdrawl(double withdrawl) {
        this.withdrawl = withdrawl;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getTransDateString() {
        return transDateString;
    }

    public void setTransDateString(String transDateString) {
        this.transDateString = transDateString;
    }
}