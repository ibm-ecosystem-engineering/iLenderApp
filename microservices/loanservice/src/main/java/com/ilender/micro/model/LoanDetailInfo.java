package com.ilender.micro.model;

import java.util.Date;

public class LoanDetailInfo {

    private int lndLoanId;
    private int bankId;
    private String bankName;
    private String loanReferenceNo;
    private Date loanGrantedDate;
    private double emi;
    private Date loanStartDate;
    private Date loanEndDate;
    private int tenure;
    private int noOfInstallmentsRemaining;
    private double principalRemaining;
    private double interestRemaining;
    private double lastInstallmentAmount;
    private Date lastInstallmentDate;

    public LoanDetailInfo() {
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

    @Override
    public String toString() {
        return "LoanDetailInfo{" +
                "lndLoanId=" + lndLoanId +
                ", bankId=" + bankId +
                ", bankName='" + bankName + '\'' +
                ", loanReferenceNo='" + loanReferenceNo + '\'' +
                ", loanGrantedDate=" + loanGrantedDate +
                ", emi=" + emi +
                ", loanStartDate=" + loanStartDate +
                ", loanEndDate=" + loanEndDate +
                ", tenure=" + tenure +
                ", noOfInstallmentsRemaining=" + noOfInstallmentsRemaining +
                ", principalRemaining=" + principalRemaining +
                ", interestRemaining=" + interestRemaining +
                ", lastInstallmentAmount=" + lastInstallmentAmount +
                ", lastInstallmentDate=" + lastInstallmentDate +
                '}';
    }
}
