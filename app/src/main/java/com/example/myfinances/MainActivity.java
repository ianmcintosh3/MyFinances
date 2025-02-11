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
        RadioButton x = findViewById(R.id.checktext);
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
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                hideKeyboard();
                boolean wasSuccessful;
                FinanceDataSource ds = new FinanceDataSource(MainActivity.this);
                try {
                    ds.open();

                    if (currentContact.getAccountNumber() == -1) {
                        wasSuccessful = ds.insertContact(currentContact);

                        if (wasSuccessful) {
                            int newId = ds.getLastContactID();
                            currentContact.setContactID(newId);
                        }
                    } else {
                        wasSuccessful = ds.updateContact(currentContact);
                    }
                    ds.close();
                } catch (Exception e) {
                    wasSuccessful = false;
                }

                if (wasSuccessful) {
                    //clear method
                }
            }
        });
    }
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);

        EditText editAccountNumber = findViewById(R.id.editAccountNumber);
        imm.hideSoftInputFromWindow(editAccountNumber.getWindowToken(), 0);

        EditText editInitial = findViewById(R.id.editInitialB);
        imm.hideSoftInputFromWindow(editInitial.getWindowToken(), 0);

        EditText editCurrent = findViewById(R.id.editCurrentB);
        imm.hideSoftInputFromWindow(editCurrent.getWindowToken(), 0);

        EditText editInterest = findViewById(R.id.editRate);
        imm.hideSoftInputFromWindow(editInterest.getWindowToken(), 0);

        EditText editPayment = findViewById(R.id.editPayment);
        imm.hideSoftInputFromWindow(editPayment.getWindowToken(), 0);

    }
}
//save and cancel button