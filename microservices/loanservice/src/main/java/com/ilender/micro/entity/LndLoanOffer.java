package com.ilender.micro.entity;

import javax.persistence.*;

@Table(name = "LNDLOANOFFER")
@Entity
public class LndLoanOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "lndloanid")
    private int lndLoanId;

    @Column(name = "bankid")
    private int bankId;

    @Column(name = "bankname")
    private String bankName;

    @Column(name = "loanamount")
    private double loanAmount;

    @Column(name = "tenure")
    private int tenure;

    @Column(name = "interestrate")
    private int interestRate;

    @Column(name = "offeraccepted")
    private int offerAccepted;

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

    public int getOfferAccepted() {
        return offerAccepted;
    }

    public void setOfferAccepted(int offerAccepted) {
        this.offerAccepted = offerAccepted;
    }
}
