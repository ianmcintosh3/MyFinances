package com.example.myfinances;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CheckingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_checking);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.Checking), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initSaveButton();
        initCancelButton();
    }
    private void initSaveButton() {
        Button saveButton = findViewById(R.id.buttonSaveChecking);

        if (saveButton != null) {
            saveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hideKeyboard();
                    boolean wasSuccessful = false;
                    FinanceDataSource ds = new FinanceDataSource(CheckingActivity.this);

                    try {
                        ds.open();
                        Checking account = new Checking();
                        account.setAccountNumber(Integer.parseInt(((EditText) findViewById(R.id.editAccountNumber)).getText().toString()));
                        account.setCurrentBalance(Double.parseDouble(((EditText) findViewById(R.id.editCurrentB)).getText().toString()));

                        wasSuccessful = ds.insertAccount(account);
                        ds.close();
                    } catch (Exception e) {
                        wasSuccessful = false;
                    }

                    if (wasSuccessful) {
                        displaySavedButton();
                        clearFields();
                    }
                }
            });
        }
    }

    private void initCancelButton() {
        Button cancelButton = findViewById(R.id.buttonCancelChecking);

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
    private void displaySavedButton(){
        TextView saved = findViewById(R.id.textViewSavedChecking);
        saved.setVisibility(View.VISIBLE);
    }


    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        EditText editAccountNumber = findViewById(R.id.editAccountNumber);
        EditText editCurrent = findViewById(R.id.editCurrentB);

        if (editAccountNumber != null) imm.hideSoftInputFromWindow(editAccountNumber.getWindowToken(), 0);
        if (editCurrent != null) imm.hideSoftInputFromWindow(editCurrent.getWindowToken(), 0);
    }

    private void clearFields() {
        EditText editAccountNumber = findViewById(R.id.editAccountNumber);
        EditText editCurrent = findViewById(R.id.editCurrentB);

        if (editAccountNumber != null) editAccountNumber.setText("");
        if (editCurrent != null) editCurrent.setText("");
    }
}
