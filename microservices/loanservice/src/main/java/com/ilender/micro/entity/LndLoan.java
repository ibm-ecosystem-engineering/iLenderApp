package com.ilender.micro.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ilender.micro.model.LoanProcessInfo;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Table(name = "lndloan")
@Entity
public class LndLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "lndcustomerid")
    private int lndCustomerId;

    @Column(name = "loanrequestdate")
    private Date loanRequestDate;

    @Column(name = "loanamount")
    private double loanAmount;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "statuscode")
    private String statusCode;

    @Column(name = "duration")
    private int duration;

    @Column(name = "startdate")
    private Date startDate;

    @Column(name = "creditscore")
    private double creditScore;

    @JsonInclude()
    @Transient
    private String statusTextCustomer;

    @JsonInclude()
    @Transient
    private String statusTextBm;

    @JsonInclude()
    @Transient
    private String statusCodeBm;

    @JsonInclude()
    @Transient
    private String statusCodeCustomer;

    @JsonInclude()
    @Transient
    private String loanRequestDateString;

    @JsonInclude()
    @Transient
    private String startDateString;

    @JsonInclude()
    @Transient
    private List<LndLoanOffer> loanOfferList;

    @JsonInclude()
    @Transient
    private List<LndLoanDetail> loanDetailList;

    @JsonInclude()
    @Transient
    private List<LoanProcessInfo> loanProcessInfoList;

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

    public List<LndLoanOffer> getLoanOfferList() {
        return loanOfferList;
    }

    public void setLoanOfferList(List<LndLoanOffer> loanOfferList) {
        this.loanOfferList = loanOfferList;
    }

    public List<LndLoanDetail> getLoanDetailList() {
        return loanDetailList;
    }

    public void setLoanDetailList(List<LndLoanDetail> loanDetailList) {
        this.loanDetailList = loanDetailList;
    }

    public String getStatusCodeBm() {
        return statusCodeBm;
    }

    public void setStatusCodeBm(String statusCodeBm) {
        this.statusCodeBm = statusCodeBm;
    }

    public String getStatusCodeCustomer() {
        return statusCodeCustomer;
    }

    public void setStatusCodeCustomer(String statusCodeCustomer) {
        this.statusCodeCustomer = statusCodeCustomer;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public double getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(double creditScore) {
        this.creditScore = creditScore;
    }

    public String getStartDateString() {
        return startDateString;
    }

    public void setStartDateString(String startDateString) {
        this.startDateString = startDateString;
    }

    public List<LoanProcessInfo> getLoanProcessInfoList() {
        return loanProcessInfoList;
    }

    public void setLoanProcessInfoList(List<LoanProcessInfo> loanProcessInfoList) {
        this.loanProcessInfoList = loanProcessInfoList;
    }

    public String getLoanRequestDateString() {
        return loanRequestDateString;
    }

    public void setLoanRequestDateString(String loanRequestDateString) {
        this.loanRequestDateString = loanRequestDateString;
    }
}