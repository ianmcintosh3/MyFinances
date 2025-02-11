package com.example.myfinances;

public class CDs implements FinanceAccount{
    private int accountNumber;
    private double initialBalance;
    private double currentBalance;
    private double interestRate;

    CDs(){
        this.accountNumber = 0;
        this.initialBalance = 0;
        this.currentBalance = 0;
        this.interestRate = 0;
    }
    @Override
    public int getAccountNumber() {
        return accountNumber;
    }
    @Override
    public void setAccountNumber(int i){
        accountNumber = i;
    }
    public double getInitialBalance(){
        return initialBalance;
    }
    public void setInitialBalance(double i){
        initialBalance = i;
    }
    @Override
    public double getCurrentBalance(){
        return currentBalance;
    }
    @Override
    public void setCurrentBalance(double i){
        currentBalance = i;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
