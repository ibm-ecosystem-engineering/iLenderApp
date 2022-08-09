package com.ilender.micro.model;

public class LoanOfferInfo {

    private int lndLoanId;
    private int bankId;
    private String bankName;
    private double loanAmount;
    private int tenure;
    private int interestRate;

    public LoanOfferInfo() {
    }

    public LoanOfferInfo(int lndLoanId, int bankId, String bankName, double loanAmount, int tenure, int interestRate) {
        this.lndLoanId = lndLoanId;
        this.bankId = bankId;
        this.bankName = bankName;
        this.loanAmount = loanAmount;
        this.tenure = tenure;
        this.interestRate = interestRate;
    }

    public int getLndLoanId() {
        return lndLoanId;
    }

    public void setLndLoanId(int loanId) {
        this.lndLoanId = loanId;
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

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount) {
        this.loanAmount = loanAmount;
    }

    public int getTenure() {
        return tenure;
    }

    public void setTenure(int tenure) {
        this.tenure = tenure;
    }

    public int getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public String toString() {
        return "LoanOfferInfo{" +
                "lndLoanId=" + lndLoanId +
                ", bankId=" + bankId +
                ", bankName='" + bankName + '\'' +
                ", loanAmount=" + loanAmount +
                ", tenure=" + tenure +
                ", interestRate=" + interestRate +
                '}';
    }
}
