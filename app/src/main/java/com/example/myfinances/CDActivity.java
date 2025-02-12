package com.example.myfinances;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CDActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cds);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.CD_activity), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initSaveButton();
        initCancelButton();
    }
    private void initSaveButton() {
        Button saveButton = findViewById(R.id.buttonSaveCD);

        if (saveButton != null) {
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hideKeyboard();
                    boolean wasSuccessful = false;
                    FinanceDataSource ds = new FinanceDataSource(CDActivity.this);

                    try {
                        ds.open();
                        CDs account = new CDs();
                        account.setAccountNumber(Integer.parseInt(((EditText) findViewById(R.id.editAccountNumber)).getText().toString()));
                        account.setInitialBalance(Double.parseDouble(((EditText) findViewById(R.id.editInitialB)).getText().toString()));
                        account.setCurrentBalance(Double.parseDouble(((EditText) findViewById(R.id.editCurrentB)).getText().toString()));
                        account.setInterestRate(Double.parseDouble(((EditText) findViewById(R.id.editRate)).getText().toString()));

                        wasSuccessful = ds.insertAccount(account);
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

    private void initCancelButton() {
        Button cancelButton = findViewById(R.id.buttonCancelCD);

        if (cancelButton != null) {
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hideKeyboard();
                    clearFields();
                }
            });
        }
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        EditText editAccountNumber = findViewById(R.id.editAccountNumber);
        EditText editInitial = findViewById(R.id.editInitialB);
        EditText editCurrent = findViewById(R.id.editCurrentB);
        EditText editInterest = findViewById(R.id.editRate);

        if (editAccountNumber != null) imm.hideSoftInputFromWindow(editAccountNumber.getWindowToken(), 0);
        if (editInitial != null) imm.hideSoftInputFromWindow(editInitial.getWindowToken(), 0);
        if (editCurrent != null) imm.hideSoftInputFromWindow(editCurrent.getWindowToken(), 0);
        if (editInterest != null) imm.hideSoftInputFromWindow(editInterest.getWindowToken(), 0);
    }

    private void clearFields() {
        EditText editAccountNumber = findViewById(R.id.editAccountNumber);
        EditText editInitial = findViewById(R.id.editInitialB);
        EditText editCurrent = findViewById(R.id.editCurrentB);
        EditText editInterest = findViewById(R.id.editRate);

        if (editAccountNumber != null) editAccountNumber.setText("");
        if (editInitial != null) editInitial.setText("");
        if (editCurrent != null) editCurrent.setText("");
        if (editInterest != null) editInterest.setText("");
    }
}
