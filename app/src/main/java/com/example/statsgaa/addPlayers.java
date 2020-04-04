package com.example.statsgaa;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.textfield.TextInputEditText;


public class addPlayers extends AppCompatActivity implements View.OnClickListener {

    private Button insert;
    private Button back;


    private final AppCompatActivity activity = addPlayers.this;


    private EditText editTextPlayerName;
    private EditText editTextPlayerAge;
    private EditText editTextPlayerWeight;
    private EditText editTextPlayerHeight;
    private EditText editTextPlayerPosition;

    private TextInputEditText textInputEditTextEmail;
    private Inputvalidation inputvalidation;
    private DatabaseHelper databaseHelper;
    private Player player;
    private Button appCompatButtonRegister;
    private User user;

    DatabaseHelper myDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        back = (Button) findViewById(R.id.goBack);
        back.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View v)

            {
                openPlayersActivity();


            }

        });

        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();


    }

    private void openPlayersActivity() {

        Intent intent = new Intent(this, Players.class);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        intent.putExtra(("EMAIL"), nameFromIntent);
        startActivity(intent);
    }

    private void initViews() {


        editTextPlayerName = (EditText) findViewById(R.id.playerName);
        editTextPlayerAge = (EditText) findViewById(R.id.playerAge);
        editTextPlayerHeight = (EditText) findViewById(R.id.playerHeight);
        editTextPlayerWeight = (EditText) findViewById(R.id.playerWeight);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.enterEmail);
        editTextPlayerPosition = (EditText) findViewById(R.id.playerPosition) ;
        appCompatButtonRegister = (Button) findViewById(R.id.savePlayer);

    }

    private void initListeners() {

        appCompatButtonRegister.setOnClickListener(this);

    }

    private void initObjects() {

        inputvalidation = new Inputvalidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        player = new Player();

    }

    // Table 2 : Add players info

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.savePlayer:
                postDataToSQLite();
                break;

        }


    }


    private void postDataToSQLite() {


        if (!databaseHelper.checkUser(editTextPlayerName.getText().toString().trim()))
        //textInputEditTextPassword.getText().toString().trim()))
        {
            Bundle email = getIntent().getExtras();
            String a = "";
            try {
                a = databaseHelper.getColumnUserName(email.getString("EMAIL"));
            }catch (Exception e){
                e.printStackTrace();
            }

            player.setPlayerName(editTextPlayerName.getText().toString().trim());
            player.setPlayerAge(Integer.parseInt(editTextPlayerAge.getText().toString().trim()));
            player.setPlayerHeight(Integer.parseInt(editTextPlayerHeight.getText().toString().trim()));
            player.setPlayerWeight(Integer.parseInt(editTextPlayerWeight.getText().toString().trim()));
            player.setPlayerPosition(editTextPlayerPosition.getText().toString().trim());
            player.setForeignKey(Integer.parseInt(a));

            databaseHelper.addPlayer(player);


            Intent accountIntent = new Intent(activity, Players.class);
            String nameFromIntent = getIntent().getStringExtra("EMAIL");
            accountIntent.putExtra(("EMAIL"), nameFromIntent);
            startActivity(accountIntent);





        }





    }



}
