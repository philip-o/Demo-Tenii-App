package dtos;

import java.time.LocalDate;

public class TransactionBuilder {

    private String userId;
    private String provider;
    private String sortCode;
    private String accountNumber;
    private double amount;
    private String date;

    private LocalDate localDate = LocalDate.now();

    public TransactionBuilder withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public TransactionBuilder withProvider(String provider) {
        this.provider = provider;
        return this;
    }

    public TransactionBuilder withSortCode(String sortCode) {
        this.sortCode = sortCode;
        return this;
    }

    public TransactionBuilder withAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
        return this;
    }

    public TransactionBuilder withAmount(double amount) {
        this.amount = amount;
        return this;
    }

    public Transaction buildObject() {
        Transaction transaction = new Transaction();
        transaction.setUserId(this.userId);
        transaction.setProvider(this.provider);
        transaction.setSortCode(this.sortCode);
        transaction.setAccountNumber(this.accountNumber);
        transaction.setAmount(this.amount);
        transaction.setDate("" + localDate.getYear() + localDate.getMonthValue() + localDate.getYear());
        return transaction;
    }
}
