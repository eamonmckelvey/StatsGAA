package com.example.statsgaa;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Register extends AppCompatActivity implements View.OnClickListener{

    private final AppCompatActivity activity = Register.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;
    private TextInputLayout textInputLayoutEmail;

    private TextInputEditText textInputEditTextName;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;
    private TextInputEditText textInputEditTextEmail;

    private Button appCompatButtonRegister;
    private TextView appCombatTextViewLoginLink;

    private Inputvalidation inputvalidation;
    private DatabaseHelper databaseHelper;
    private User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }





    private void initViews() {

        // nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInpuEditTextEmail);
        textInputLayoutName = (TextInputLayout) findViewById(R.id.textInputLayoutName);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.enterEmail);
        textInputEditTextName = (TextInputEditText) findViewById(R.id.enterName);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.confirmPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.confirmPassword);

        appCompatButtonRegister = (Button) findViewById(R.id.Register);
        appCombatTextViewLoginLink = (TextView) findViewById(R.id.appCombatTextViewLoginLink);
    }

    private void initListeners() {

        appCompatButtonRegister.setOnClickListener(this);
        appCombatTextViewLoginLink.setOnClickListener(this);
    }

    private void initObjects() {

        inputvalidation = new Inputvalidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        user = new User ();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Register:
                postDataToSQLite();
                break;

            case R.id.appCombatTextViewLoginLink:
                finish();
                break;
        }

    }

    private void postDataToSQLite() {

        /*if (!inputvalidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))){
            return;
        }

        if (!inputvalidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))){
            return;
        }

        if (!inputvalidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))){
            return;
        }

        if (!inputvalidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))){
            return;
        }

        if (!inputvalidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }*/

        if(!databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim()))
        //textInputEditTextPassword.getText().toString().trim()))
        {
            user.setName(textInputEditTextName.getText().toString().trim());
            user.setEmail(textInputEditTextEmail.getText().toString().trim());
            user.setPassword(textInputEditTextPassword.getText().toString().trim());



            databaseHelper.addUser(user);

            // Display successful success_message message upon registering
            //Snackbar.make(findViewById(R.id.Register), R.string.success_message,Snackbar.LENGTH_LONG).show();
            emptyInputEditText();

            Intent accountIntent = new Intent(activity, LoginActivity.class);
            accountIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
            emptyInputEditText();
            startActivity(accountIntent);



        } /*else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(findViewById(R.id.Register), getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }*/


    }

    private void emptyInputEditText(){
        textInputEditTextName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }
}



