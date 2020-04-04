package com.example.statsgaa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends Activity {

    // Get Current Time
    Time today = new Time(Time.getCurrentTimezone());
    DatabaseHelper myDB;


    // Log in
    private Button LoginBtn;
    // Register

    private Button RegisterBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_main);

        myDB = new DatabaseHelper(getApplicationContext());
        // Log in
        LoginBtn = (Button) findViewById(R.id.Login1);
        LoginBtn.setOnClickListener(new View.OnClickListener()


        {

            @Override
            public void onClick (View v)

            {
                openLoginActivity();


            }
        });

        // Register
        RegisterBtn = (Button) findViewById(R.id.Register1);
        RegisterBtn.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick (View v)

            {
                openRegisterActivity();


            }
        });



    }

    public void openLoginActivity(){

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openRegisterActivity(){

        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }



}


