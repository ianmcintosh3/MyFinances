package com.example.myfinances;

public class Checking {
    private int accountNumber;
    private double currentBalance;

    public int getAccountNumber(){
        return accountNumber;
    }
    public void setAccountNumber(int i){
        accountNumber = i;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }
}
