package com.ilender.micro.entity;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.util.Date;

@Table(name = "LNDCUSTOMER")
@Entity
public class LndCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "lnduserid")
    private int lndUserId;

    @Column(name = "lndfieldagentid")
    private int lndFieldAgentId;

    @Column(name = "orgid")
    private String orgId;

    @Column(name = "orgname")
    private String orgName;

    @Column(name = "address")
    private String address;

    @Column(name = "establishmentdate")
    private Date establishmentDate;

    @Column(name = "founder1firstname")
    private String founder1FirstName;

    @Column(name = "founder1lastname")
    private String founder1LastName;

    @Column(name = "founder2firstname")
    private String founder2FirstName;

    @Column(name = "founder2lastname")
    private String founder2LastName;

    @Column(name = "annualrevenue")
    private double annualRevenue;

    @Column(name = "annualprofit")
    private double annualProfit;

    @Column(name = "emailid")
    private String emailId;

    @JsonInclude()
    @Transient
    private String establishmentDateString;

    @JsonInclude()
    @Transient
    private String image;

    @JsonInclude()
    @Transient
    private String fieldAgentName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLndUserId() {
        return lndUserId;
    }

    public void setLndUserId(int lndUserId) {
        this.lndUserId = lndUserId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getEstablishmentDate() {
        return establishmentDate;
    }

    public void setEstablishmentDate(Date establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    public String getFounder1FirstName() {
        return founder1FirstName;
    }

    public void setFounder1FirstName(String founder1FirstName) {
        this.founder1FirstName = founder1FirstName;
    }

    public String getFounder1LastName() {
        return founder1LastName;
    }

    public void setFounder1LastName(String founder1LastName) {
        this.founder1LastName = founder1LastName;
    }

    public String getFounder2FirstName() {
        return founder2FirstName;
    }

    public void setFounder2FirstName(String founder2FirstName) {
        this.founder2FirstName = founder2FirstName;
    }

    public String getFounder2LastName() {
        return founder2LastName;
    }

    public void setFounder2LastName(String founder2LastName) {
        this.founder2LastName = founder2LastName;
    }

    public double getAnnualRevenue() {
        return annualRevenue;
    }

    public void setAnnualRevenue(double annualRevenue) {
        this.annualRevenue = annualRevenue;
    }

    public double getAnnualProfit() {
        return annualProfit;
    }

    public void setAnnualProfit(double annualProfit) {
        this.annualProfit = annualProfit;
    }

    public String getEstablishmentDateString() {
        return establishmentDateString;
    }

    public void setEstablishmentDateString(String establishmentDateString) {
        this.establishmentDateString = establishmentDateString;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLndFieldAgentId() {
        return lndFieldAgentId;
    }

    public void setLndFieldAgentId(int lndFieldAgentId) {
        this.lndFieldAgentId = lndFieldAgentId;
    }

    public String getFieldAgentName() {
        return fieldAgentName;
    }

    public void setFieldAgentName(String fieldAgentName) {
        this.fieldAgentName = fieldAgentName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }


    @Override
    public String toString() {
        return "LndCustomer{" +
                "id=" + id +
                ", lndUserId=" + lndUserId +
                ", lndFieldAgentId=" + lndFieldAgentId +
                ", orgId='" + orgId + '\'' +
                ", orgName='" + orgName + '\'' +
                ", address='" + address + '\'' +
                ", establishmentDate=" + establishmentDate +
                ", founder1FirstName='" + founder1FirstName + '\'' +
                ", founder1LastName='" + founder1LastName + '\'' +
                ", founder2FirstName='" + founder2FirstName + '\'' +
                ", founder2LastName='" + founder2LastName + '\'' +
                ", AnnualRevenue=" + annualRevenue +
                ", annualProfit=" + annualProfit +
                ", emailId='" + emailId + '\'' +
                ", establishmentDateString='" + establishmentDateString + '\'' +
                ", image='" + image + '\'' +
                ", fieldAgentName='" + fieldAgentName + '\'' +
                '}';
    }
}
