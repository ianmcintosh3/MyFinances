package com.example.myfinances;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initCDButton();
        initLoansButton();
        initCheckingButton();
        initSaveButton();


    }

    private void initCDButton() {
        RadioButton x = findViewById(R.id.CDs);
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CDActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initLoansButton() {
        RadioButton x = findViewById(R.id.Loans);
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoanActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initCheckingButton() {
        RadioButton x = findViewById(R.id.Checking);
        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CheckingActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private void initSaveButton() {
        Button saveButton = findViewById(R.id.buttonSave);

        if (saveButton != null) {
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hideKeyboard();
                    boolean wasSuccessful = false;
                    FinanceDataSource ds = new FinanceDataSource(MainActivity.this);

                    try {
                        ds.open();
                        FinanceAccount account = null;

                        RadioButton radioCD = findViewById(R.id.CDs);
                        RadioButton radioLoan = findViewById(R.id.Loans);
                        RadioButton radioChecking = findViewById(R.id.Checking);

                        if (radioCD.isChecked()) {
                            account = new CDs();
                            ((CDs) account).setInitialBalance(Double.parseDouble(((EditText) findViewById(R.id.editInitialB)).getText().toString()));
                            ((CDs) account).setInterestRate(Double.parseDouble(((EditText) findViewById(R.id.editRate)).getText().toString()));
                        } else if (radioLoan.isChecked()) {
                            account = new Loans();
                            ((Loans) account).setInitialBalance(Double.parseDouble(((EditText) findViewById(R.id.editInitialB)).getText().toString()));
                            ((Loans) account).setInterestRate(Double.parseDouble(((EditText) findViewById(R.id.editRate)).getText().toString()));
                            ((Loans) account).setPaymentAmount(Double.parseDouble(((EditText) findViewById(R.id.editPayment)).getText().toString()));
                        } else if (radioChecking.isChecked()) {
                            account = new Checking();
                        }

                        if (account != null) {
                            account.setAccountNumber(Integer.parseInt(((EditText) findViewById(R.id.editAccountNumber)).getText().toString()));
                            account.setCurrentBalance(Double.parseDouble(((EditText) findViewById(R.id.editCurrentB)).getText().toString()));

                            wasSuccessful = ds.insertAccount(account);
                        }
                        ds.close();
                    } catch (Exception e) {
                        wasSuccessful = false;
                    }

                    if (wasSuccessful) {
                        clearFields();
                    }
                }
            });
        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        EditText editAccountNumber = findViewById(R.id.editAccountNumber);
        EditText editInitial = findViewById(R.id.editInitialB);
        EditText editCurrent = findViewById(R.id.editCurrentB);
        EditText editInterest = findViewById(R.id.editRate);
        EditText editPayment = findViewById(R.id.editPayment);

        if (editAccountNumber != null) imm.hideSoftInputFromWindow(editAccountNumber.getWindowToken(), 0);
        if (editInitial != null) imm.hideSoftInputFromWindow(editInitial.getWindowToken(), 0);
        if (editCurrent != null) imm.hideSoftInputFromWindow(editCurrent.getWindowToken(), 0);
        if (editInterest != null) imm.hideSoftInputFromWindow(editInterest.getWindowToken(), 0);
        if (editPayment != null) imm.hideSoftInputFromWindow(editPayment.getWindowToken(), 0);
    }

    private void clearFields() {
        View mainLayout = findViewById(R.id.main);
        if (mainLayout != null) {
            mainLayout.requestFocus();
        }

        EditText editAccountNumber = findViewById(R.id.editAccountNumber);
        EditText editInitial = findViewById(R.id.editInitialB);
        EditText editCurrent = findViewById(R.id.editCurrentB);
        EditText editInterest = findViewById(R.id.editRate);
        EditText editPayment = findViewById(R.id.editPayment);

        if (editAccountNumber != null) editAccountNumber.setText("");
        if (editInitial != null) editInitial.setText("");
        if (editCurrent != null) editCurrent.setText("");
        if (editInterest != null) editInterest.setText("");
        if (editPayment != null) editPayment.setText("");
    }
}
//save and cancel button