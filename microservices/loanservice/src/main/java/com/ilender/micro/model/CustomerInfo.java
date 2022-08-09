package com.ilender.micro.model;

import java.util.Date;

public class CustomerInfo {

    private int id;

    private int lndUserId;
    private int lndFieldAgentId;
    private String orgId;
    private String orgName;
    private String address;
    private Date establishmentDate;
    private String founder1FirstName;
    private String founder1LastName;
    private String founder2FirstName;
    private String founder2LastName;
    private double AnnualRevenue;
    private double annualProfit;
    private String emailId;
    private String establishmentDateString;
    private String image;
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
        return AnnualRevenue;
    }

    public void setAnnualRevenue(double annualRevenue) {
        AnnualRevenue = annualRevenue;
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
}
