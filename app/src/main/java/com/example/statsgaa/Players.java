package com.example.statsgaa;



import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.provider.MediaStore;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.net.URI;
import java.sql.DataTruncation;
import java.util.ArrayList;

public class Players extends AppCompatActivity {

    private Button insert;
    private Button back;
    private static final int PICK_IMAGE = 100;
    private String nameFromIntent = "";
    DatabaseHelper myDb;

    ListView playersList;
    ArrayList<String> listItem;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        openDB();
        getAllRows();

        back = (Button) findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View v)

            {
                openHomePageActivity();


            }

        });

        myDb = new DatabaseHelper(this);

        //Open add players section
        insert = (Button) findViewById(R.id.addPlayer);
        insert.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View v)

            {
                openAddPlayersActivity();


            }

        });

    }

    private void openDB() {
        myDb = new DatabaseHelper(this);
        myDb.open();

    }

    private void closeDB() {
        myDb.close();
    }

    private void getAllRows() {
        Cursor cursor = myDb.getAllRows();

        // Allow activity to manage lifetime of cursor
        // Deprecated
        startManagingCursor(cursor);

        // Setup mapping from cursor to view fields
        String[] fromFieldNames = new String[]
                {DatabaseHelper.COLUMN_PLAYER_NAME, DatabaseHelper.COLUMN_PLAYER_AGE, DatabaseHelper.COLUMN_PLAYER_WEIGHT, DatabaseHelper.COLUMN_PLAYER_HEIGHT, DatabaseHelper.COLUMN_PLAYER_POSITION};


        int[] toViewIDs = new int[]{R.id.textView01, R.id.textView02, R.id.textView5, R.id.textView6, R.id.textView7};

        // Create Adapter to map columns of DB onto elements in the UI
        SimpleCursorAdapter myCursorAdapter = new SimpleCursorAdapter(
                this, R.layout.player_layout, cursor , fromFieldNames , toViewIDs);


        // Set the adapter for the list view
        ListView myList = (ListView) findViewById(R.id.playersList);
        myList.setAdapter(myCursorAdapter);



    }




    private void openAddPlayersActivity(){

        Intent intent = new Intent(this, addPlayers.class);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        intent.putExtra(("EMAIL"), nameFromIntent);
        startActivity(intent);


    }

    private void openHomePageActivity(){

        Intent intent = new Intent(this, LoggedIn.class);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        intent.putExtra(("EMAIL"), nameFromIntent);
        startActivity(intent);


    }






}



