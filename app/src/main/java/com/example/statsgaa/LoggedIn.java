package com.example.statsgaa;



import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;



public class LoggedIn extends AppCompatActivity {

    private ImageButton PlayersBtn;
    private ImageButton FixturesBtn;
    private TextView textViewName;
    private Button logOut;
    private ImageButton NotesBtn;
    String nameFromIntent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);


        // Open players section
        PlayersBtn = (ImageButton) findViewById(R.id.Players);
        PlayersBtn.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View v)

            {
                openPlayersActivity();


            }
        });



        // Open players section
        //FixturesBtn = (ImageButton) findViewById(R.id.fixtures);
        //FixturesBtn.setOnClickListener(new View.OnClickListener()

      //  {

           // @Override
           // public void onClick(View v)

            //{
            //    openFixturesActivity();


           // }
       // });

        // Open Notes_model section
        NotesBtn = (ImageButton) findViewById(R.id.notesModel);
        NotesBtn.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View v)

            {
                openNotesActivity();


            }
        });

        // LogOut
        logOut = (Button) findViewById(R.id.logOut);
        logOut.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View v)

            {
                openLoginActivity();


            }
        });


        textViewName = (TextView) findViewById(R.id.text1);
        nameFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText("Welcome " + nameFromIntent);
    }

    private void openNotesActivity() {

        Intent intent = new Intent(this, Notes.class);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        intent.putExtra(("EMAIL"), nameFromIntent);
        startActivity(intent);
    }

    private void openLoginActivity() {

        Intent intent = new Intent(this, LoginActivity.class);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        intent.putExtra(("EMAIL"), nameFromIntent);
        startActivity(intent);
    }

    public void openPlayersActivity() {

        // accountIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());

        Intent intent = new Intent(this, Players.class);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        intent.putExtra(("EMAIL"), nameFromIntent);
        startActivity(intent);
    }
    /*public void openFixturesActivity() {

        // accountIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());

        Intent intent = new Intent(this, Fixtures.class);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        intent.putExtra(("EMAIL"), nameFromIntent);
        startActivity(intent);
    }*/




}
