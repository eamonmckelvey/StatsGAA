package com.example.statsgaa;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class Fixtures extends AppCompatActivity {

    private Button addFixtureBtn;
    private Button goBackBtn;
    DatabaseHelper myDb;

    ListView fixturesList;
    ArrayList<String> listItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixtures);

        openDB();
        getFixtures();

        goBackBtn = (Button) findViewById(R.id.goBack2);
        goBackBtn.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View v)

            {
                openHomePageActivity();

            }

        });

        myDb = new DatabaseHelper(this);

        //Open add fixtures section
        addFixtureBtn = (Button) findViewById(R.id.addFixtures);
        addFixtureBtn.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View v)

            {
                openAddFixturesActivity();


            }

        });

    }

    private void openDB() {
        myDb = new DatabaseHelper(this);
        myDb.open();

    }


    private void getFixtures() {
        Cursor cursor = myDb.getFixtures();

        // Allow activity to manage lifetime of cursor
        // Deprecated
        startManagingCursor(cursor);

        // Setup mapping from cursor to view fields
        String[] fromFieldNames = new String[]
                {DatabaseHelper.COLUMN_COMPETITION, DatabaseHelper.COLUMN_MATCH_DATE, DatabaseHelper.COLUMN_OPPONENT_NAME, DatabaseHelper.COLUMN_HOME_SCORE, DatabaseHelper.COLUMN_AWAY_SCORE, DatabaseHelper.COLUMN_RESULT};


        int[] toViewIDs = new int[]{R.id.textView13, R.id.textView12, R.id.textView11, R.id.textView10, R.id.textView12, R.id.textView11};

        // Create Adapter to map columns of DB onto elements in the UI
        SimpleCursorAdapter myCursorAdapter = new SimpleCursorAdapter(
                this, R.layout.fixture_layout, cursor , fromFieldNames , toViewIDs);


        // Set the adapter for the list view
        ListView myList = (ListView) findViewById(R.id.fixturesList);
        myList.setAdapter(myCursorAdapter);

    }

    private void openHomePageActivity() {

        Intent intent = new Intent(this, LoggedIn.class);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        intent.putExtra(("EMAIL"), nameFromIntent);
        startActivity(intent);
    }

    private void openAddFixturesActivity() {

        Intent intent = new Intent(this, addFixtures.class);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        intent.putExtra(("EMAIL"), nameFromIntent);
        startActivity(intent);
    }



}

