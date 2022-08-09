package com.ilender.micro.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;

@Table(name = "LNDBANKTRANSHISTORYGREAT")
@Entity
public class LndBankTransHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "lndcustomerid")
    private int lndCustomerId;

    @Column(name = "bankid")
    private int bankId;

    @Column(name = "bankname")
    private String bankName;

    @Column(name = "transdate")
    private Date transDate;

    @Column(name = "particulars")
    private String particulars;

    @Column(name = "withdrawl")
    private double withdrawl;

    @Column(name = "deposit")
    private double deposit;

    @Column(name = "balance")
    private double balance;

    @JsonInclude()
    @Transient
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