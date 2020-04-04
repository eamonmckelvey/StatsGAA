package com.example.statsgaa;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;


public class addFixtures extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = addFixtures.this;

    private Button goBackBtn;
    private DatabaseHelper databaseHelper;
    private EditText editTextOpponentName;
    private EditText editTextAwayScore;
    private EditText editTextHomeScore;
    private EditText editTextResult;
    private EditText editTextDate;
    private EditText editTextCompetition;
    private Fixtures1 fixtures;
    private Button save;
    private Inputvalidation inputvalidation;
    private User user;

    DatabaseHelper myDb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fixtures);

        goBackBtn = (Button) findViewById(R.id.goBack3);
        goBackBtn.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View v)

            {
                openFixturesActivity();


            }

        });

        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();

    }




    private void openFixturesActivity() {

        Intent intent = new Intent(this, com.example.statsgaa.Fixtures1.class);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        intent.putExtra(("EMAIL"), nameFromIntent);
        startActivity(intent);
    }

    private void initViews() {

        editTextOpponentName = (EditText) findViewById(R.id.opponentName);
        editTextAwayScore = (EditText) findViewById(R.id.awayScore);
        editTextHomeScore = (EditText) findViewById(R.id.homeScore);
        editTextResult = (EditText) findViewById(R.id.result);
        editTextDate = (EditText) findViewById(R.id.date);
        editTextCompetition = (EditText) findViewById(R.id.competition);
        save = (Button) findViewById(R.id.saveFixture);

    }
    private void initListeners() {

        save.setOnClickListener(this);

    }

    private void initObjects() {

        inputvalidation = new Inputvalidation(activity);
        databaseHelper = new DatabaseHelper(activity);
        fixtures = new Fixtures1();

    }



    // Table 3 : Add fixtures info
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveFixture:
                postDataToSQLite();
                break;

        }

    }

    private void postDataToSQLite() {


        if (!databaseHelper.checkUser(editTextOpponentName.getText().toString().trim()))
        //textInputEditTextPassword.getText().toString().trim()))
        {
            Bundle email = getIntent().getExtras();
            String a = databaseHelper.getColumnUserName(email.getString("EMAIL"));



            fixtures.setOpponentName(String.valueOf(editTextOpponentName.getText()));
            fixtures.setAwayScore(String.valueOf(editTextAwayScore.getText()));
            fixtures.setHomeScore(String.valueOf(editTextHomeScore.getText()));
            fixtures.setCompetition(String.valueOf(editTextCompetition.getText()));
            fixtures.setMatchDate(String.valueOf(editTextDate.getText()));
            fixtures.setResult(String.valueOf(editTextResult.getText()));
            fixtures.setForeignKey(Integer.parseInt(a));

            databaseHelper.addFixtures(fixtures);


            Intent accountIntent = new Intent(activity, com.example.statsgaa.Fixtures1.class);
            String nameFromIntent = getIntent().getStringExtra("EMAIL");
            accountIntent.putExtra(("EMAIL"), nameFromIntent);
            startActivity(accountIntent);




        }





    }


}

