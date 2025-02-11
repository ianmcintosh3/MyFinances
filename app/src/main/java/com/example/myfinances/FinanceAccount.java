package com.example.myfinances;

public interface FinanceAccount {
        int getAccountNumber();
        void setAccountNumber(int accountNumber);

        double getCurrentBalance();
        void setCurrentBalance(double currentBalance);
    }
