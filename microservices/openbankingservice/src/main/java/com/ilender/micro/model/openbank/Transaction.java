package com.ilender.micro.model.openbank;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "TransactionId",
        "AccountId",
        "Amount",
        "CreditDebitIndicator",
        "ValueDateTime",
        "ProprietaryBankTransactionCode",
        "Balance"
})
public class Transaction implements Serializable
{

    @JsonProperty("TransactionId")
    private Integer transactionId;
    @JsonProperty("AccountId")
    private Integer accountId;
    @JsonProperty("Amount")
    private Amount amount;
    @JsonProperty("CreditDebitIndicator")
    private String creditDebitIndicator;
    @JsonProperty("ValueDateTime")
    private String valueDateTime;
    @JsonProperty("ProprietaryBankTransactionCode")
    private ProprietaryBankTransactionCode proprietaryBankTransactionCode;
    @JsonProperty("Balance")
    private Balance balance;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private final static long serialVersionUID = -8704641052556485255L;

    @JsonProperty("TransactionId")
    public Integer getTransactionId() {
        return transactionId;
    }

    @JsonProperty("TransactionId")
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    @JsonProperty("AccountId")
    public Integer getAccountId() {
        return accountId;
    }

    @JsonProperty("AccountId")
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    @JsonProperty("Amount")
    public Amount getAmount() {
        return amount;
    }

    @JsonProperty("Amount")
    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    @JsonProperty("CreditDebitIndicator")
    public String getCreditDebitIndicator() {
        return creditDebitIndicator;
    }

    @JsonProperty("CreditDebitIndicator")
    public void setCreditDebitIndicator(String creditDebitIndicator) {
        this.creditDebitIndicator = creditDebitIndicator;
    }

    @JsonProperty("ValueDateTime")
    public String getValueDateTime() {
        return valueDateTime;
    }

    @JsonProperty("ValueDateTime")
    public void setValueDateTime(String valueDateTime) {
        this.valueDateTime = valueDateTime;
    }

    @JsonProperty("ProprietaryBankTransactionCode")
    public ProprietaryBankTransactionCode getProprietaryBankTransactionCode() {
        return proprietaryBankTransactionCode;
    }

    @JsonProperty("ProprietaryBankTransactionCode")
    public void setProprietaryBankTransactionCode(ProprietaryBankTransactionCode proprietaryBankTransactionCode) {
        this.proprietaryBankTransactionCode = proprietaryBankTransactionCode;
    }

    @JsonProperty("Balance")
    public Balance getBalance() {
        return balance;
    }

    @JsonProperty("Balance")
    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
