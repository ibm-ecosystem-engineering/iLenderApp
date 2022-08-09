package com.ilender.micro.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;

@Table(name = "LNDLOANHISTORYGREAT")
@Entity
public class LndLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "lndcustomerid")
    private int lndCustomerId;

    @Column(name = "loandate")
    private Date loanDate;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "loanamount")
    private double loanAmount;

    @Column(name = "paidamount")
    private double paidAmount;

    @Column(name = "closedate")
    private Date closeDate;

    @JsonInclude()
    @Transient
    private String bankName;

    @JsonInclude()
    @Transient
    private String loanDateString;

    @JsonInclude()
    @Transient
    private String closeDateString;

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

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public String getLoanDateString() {
        return loanDateString;
    }

    public void setLoanDateString(String loanDateString) {
        this.loanDateString = loanDateString;
    }

    public String getCloseDateString() {
        return closeDateString;
    }

    public void setCloseDateString(String closeDateString) {
        this.closeDateString = closeDateString;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}