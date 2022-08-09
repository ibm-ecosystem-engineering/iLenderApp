package com.ilender.micro.entity;


import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;

@Table(name = "LNDLOANDETAIL")
@Entity
public class LndLoanDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "lndloanid")
    private int lndLoanId;

    @Column(name = "bankid")
    private int bankId;

    @Column(name = "bankname")
    private String bankName;

    @Column(name = "loanreferenceno")
    private String loanReferenceNo;

    @Column(name = "loangranteddate")
    private Date loanGrantedDate;

    @Column(name = "emi")
    private double emi;

    @Column(name = "interestrate")
    private double interestRate;

    @Column(name = "loanstartdate")
    private Date loanStartDate;

    @Column(name = "loanenddate")
    private Date loanEndDate;

    @Column(name = "tenure")
    private int tenure;

    @Column(name = "noofinstallmentsremaining")
    private int noOfInstallmentsRemaining;

    @Column(name = "principalremaining")
    private double principalRemaining;

    @Column(name = "interestremaining")
    private double interestRemaining;

    @Column(name = "lastinstallmentamount")
    private double lastInstallmentAmount;

    @Column(name = "lastinstallmentdate")
    private Date lastInstallmentDate;

    @JsonInclude()
    @Transient
    private String loanGrantedDateString;

    @JsonInclude()
    @Transient
    private String lastInstallmentDateString;

    @JsonInclude()
    @Transient
    private String loanStartDateString;

    @JsonInclude()
    @Transient
    private String loanEndDateString;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLndLoanId() {
        return lndLoanId;
    }

    public void setLndLoanId(int lndLoanId) {
        this.lndLoanId = lndLoanId;
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

    public String getLoanReferenceNo() {
        return loanReferenceNo;
    }

    public void setLoanReferenceNo(String loanReferenceNo) {
        this.loanReferenceNo = loanReferenceNo;
    }

    public Date getLoanGrantedDate() {
        return loanGrantedDate;
    }

    public void setLoanGrantedDate(Date loanGrantedDate) {
        this.loanGrantedDate = loanGrantedDate;
    }

    public double getEmi() {
        return emi;
    }

    public void setEmi(double emi) {
        this.emi = emi;
    }

    public Date getLoanStartDate() {
        return loanStartDate;
    }

    public void setLoanStartDate(Date loanStartDate) {
        this.loanStartDate = loanStartDate;
    }

    public Date getLoanEndDate() {
        return loanEndDate;
    }

    public void setLoanEndDate(Date loanEndDate) {
        this.loanEndDate = loanEndDate;
    }

    public int getTenure() {
        return tenure;
    }

    public void setTenure(int tenure) {
        this.tenure = tenure;
    }

    public int getNoOfInstallmentsRemaining() {
        return noOfInstallmentsRemaining;
    }

    public void setNoOfInstallmentsRemaining(int noOfInstallmentsRemaining) {
        this.noOfInstallmentsRemaining = noOfInstallmentsRemaining;
    }

    public double getPrincipalRemaining() {
        return principalRemaining;
    }

    public void setPrincipalRemaining(double principalRemaining) {
        this.principalRemaining = principalRemaining;
    }

    public double getInterestRemaining() {
        return interestRemaining;
    }

    public void setInterestRemaining(double interestRemaining) {
        this.interestRemaining = interestRemaining;
    }

    public double getLastInstallmentAmount() {
        return lastInstallmentAmount;
    }

    public void setLastInstallmentAmount(double lastInstallmentAmount) {
        this.lastInstallmentAmount = lastInstallmentAmount;
    }

    public Date getLastInstallmentDate() {
        return lastInstallmentDate;
    }

    public void setLastInstallmentDate(Date lastInstallmentDate) {
        this.lastInstallmentDate = lastInstallmentDate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public String getLoanGrantedDateString() {
        return loanGrantedDateString;
    }

    public void setLoanGrantedDateString(String loanGrantedDateString) {
        this.loanGrantedDateString = loanGrantedDateString;
    }

    public String getLastInstallmentDateString() {
        return lastInstallmentDateString;
    }

    public void setLastInstallmentDateString(String lastInstallmentDateString) {
        this.lastInstallmentDateString = lastInstallmentDateString;
    }

    public String getLoanStartDateString() {
        return loanStartDateString;
    }

    public void setLoanStartDateString(String loanStartDateString) {
        this.loanStartDateString = loanStartDateString;
    }

    public String getLoanEndDateString() {
        return loanEndDateString;
    }

    public void setLoanEndDateString(String loanEndDateString) {
        this.loanEndDateString = loanEndDateString;
    }
}

