package com.example.myfinances;

public class Checking implements FinanceAccount{
    private int accountNumber;
    private double currentBalance;

    @Override
    public int getAccountNumber(){
        return accountNumber;
    }
    @Override
    public void setAccountNumber(int i){
        accountNumber = i;
    }
    @Override
    public double getCurrentBalance() {
        return currentBalance;
    }

    @Override
    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
}
