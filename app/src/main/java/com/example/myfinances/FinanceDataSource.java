package com.example.myfinances;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

public class FinanceDataSource {
    private SQLiteDatabase database;
    private FinanceDBHelper dbHelper;

    public FinanceDataSource(Context context) {
        dbHelper = new FinanceDBHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }
    public boolean insertAccount(FinanceAccount c) {
        boolean didSucceed = false;
        try {
            ContentValues initialValues = new ContentValues();
            initialValues.put("accountNumber", c.getAccountNumber());
            initialValues.put("currentBalance", c.getCurrentBalance());
            if(c instanceof CDs){
            initialValues.put("interestRate", ((CDs) c).getInterestRate());
            initialValues.put("initialBalance", ((CDs) c).getInitialBalance());
            }else if(c instanceof Loans){
                initialValues.put("interestRate", ((Loans) c).getInterestRate());
                initialValues.put("initialBalance", ((Loans) c).getInitialBalance());
                initialValues.put("paymentAmount", ((Loans) c).getPaymentAmount());
            }


            didSucceed = database.insert("finance", null, initialValues) > 0;
        } catch (Exception e) {
            // Do nothing - will return false if there is an exception
        }
        return didSucceed;
    }
}
