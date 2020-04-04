package com.example.statsgaa;



import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {


    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private Button appCompatButtonLogin;
    private TextView textViewLinkRegister;

    private Inputvalidation inputvalidation;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();

    }


    private void initViews() {

        textInputLayoutEmail = findViewById(R.id.textInpuEditTextEmail);
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = findViewById(R.id.enterEmail);
        textInputEditTextPassword = findViewById(R.id.enterPassword);

        appCompatButtonLogin = findViewById(R.id.Login1);
        textViewLinkRegister = findViewById(R.id.textViewLinkRegister);

    }

    private void initListeners() {

        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    private void initObjects() {

        databaseHelper = new DatabaseHelper(activity);
        inputvalidation = new Inputvalidation(activity);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.Login1:
                verifyFromSQLite();
                break;

            case R.id.textViewLinkRegister:
                Intent intentRegister = new Intent(getApplicationContext(), Register.class);
                startActivity(intentRegister);
                break;

        }
    }

    private void verifyFromSQLite() {

        /*if (!inputvalidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }

        if (!inputvalidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }

        if (!inputvalidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }*/

        if (databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim(), textInputEditTextPassword.getText().toString().trim())) {


            Intent accountIntent = new Intent(activity, LoggedIn.class);
            accountIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
           // emptyInputEditText();
            startActivity(accountIntent);


        }
        /*else {
            Snackbar.make(findViewById(R.id.Login), R.string.error_valid_email_password, Snackbar.LENGTH_LONG).show();

        }*/
    }


    //private void emptyInputEditText() {

        //textInputEditTextEmail.setText(null);
      //  textInputEditTextPassword.setText(null);

    //}
}
