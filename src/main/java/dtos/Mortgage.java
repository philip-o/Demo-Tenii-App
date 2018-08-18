package dtos;

import java.math.BigDecimal;

public class Mortgage {

    public Mortgage() {}

    private String userId;
    private String provider;
    private String mortgageType;
    private double monthlyPayment;
    private int accountNumber;
    private String productStarted;
    private double rate;
    private BigDecimal balanceAtYearStart;
    private String dateFixedProductEnds;
    private BigDecimal balance;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getMortgageType() {
        return mortgageType;
    }

    public void setMortgageType(String mortgageType) {
        this.mortgageType = mortgageType;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getProductStarted() {
        return productStarted;
    }

    public void setProductStarted(String productStarted) {
        this.productStarted = productStarted;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public BigDecimal getBalanceAtYearStart() {
        return balanceAtYearStart;
    }

    public void setBalanceAtYearStart(BigDecimal balanceAtYearStart) {
        this.balanceAtYearStart = balanceAtYearStart;
    }

    public String getDateFixedProductEnds() {
        return dateFixedProductEnds;
    }

    public void setDateFixedProductEnds(String dateFixedProductEnds) {
        this.dateFixedProductEnds = dateFixedProductEnds;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
