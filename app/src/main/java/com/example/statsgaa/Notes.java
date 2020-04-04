package com.example.statsgaa;

//package com.example.c5254992.myapplication.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;



public class Notes extends AppCompatActivity {


    DatabaseHelper myDB;
    Button btnAdd,btnView;
    EditText editText;
    Button BACK;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_main);
        editText = (EditText) findViewById(R.id.playerName);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnView = (Button) findViewById(R.id.btnView);
        BACK = (Button) findViewById(R.id.BACK);
        myDB = new DatabaseHelper(this);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = editText.getText().toString();
                if (editText.length() != 0) {
                    AddData(newEntry);
                    editText.setText("");
                } else {
                    toastMessage("DON'T LEAVE BLANK!!");
                }

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notes.this, ListDataActivity.class);
                startActivity(intent);
            }
        });


    }

    public void AddData(String newEntry) {
        boolean insertData = myDB.addData(newEntry);

        if (insertData) {
            toastMessage("NOTE ADDED SUCCESSFULLY!!");
        } else {
            toastMessage("SOMETHING WENT WRONG");
        }

        // Hide Keyboard
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();

    }


    private void openHomeActivity() {

        Intent intent = new Intent(this, LoggedIn.class);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        intent.putExtra(("EMAIL"), nameFromIntent);
        startActivity(intent);
    }
    private void openAddNotesActivity() {

        Intent intent = new Intent(this, Notes.class);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        intent.putExtra(("EMAIL"), nameFromIntent);
        startActivity(intent);
    }
}


/*


package com.example.c5254992.myapplication.activities;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.c5254992.myapplication.R;
import com.example.c5254992.myapplication.model.Notes_model;
import com.example.c5254992.myapplication.model.NotesRecyclerViewAdapter;
import com.example.c5254992.myapplication.model.SwipeController;
import com.example.c5254992.myapplication.model.SwipeControllerActions;
import com.example.c5254992.myapplication.sql.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class Notes extends AppCompatActivity {

    private Button addNotes;
    private Button back;
    DatabaseHelper myDb;
    RecyclerView recyclerView;
    SwipeController swipeController = null;
    private NotesRecyclerViewAdapter mAdapter;
    private String selectedName;
    private int selectedID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        myDb = new DatabaseHelper(this);
        recyclerView = (RecyclerView) findViewById(R.id.notesList);

        setPlayersDataAdapter();
        setupRecyclerView();


    }

    private void setPlayersDataAdapter(){
        Cursor data = myDb.getListContents();

        List<Notes_model> allNotes = new ArrayList<>();
        int i = 0;
        if(data.getCount() != 0){
            while(data.moveToNext()){
                Notes_model notes_model = new Notes_model(data.getString(0));
                allNotes.add(i,notes_model);
                i++;
            }
            NotesRecyclerViewAdapter adapter = new NotesRecyclerViewAdapter(allNotes);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager((new LinearLayoutManager(this)));
        }else{
            Toast.makeText(Notes.this, "There is no data to show!",Toast.LENGTH_LONG).show();
        }


        addNotes = (Button) findViewById(R.id.addNotes);
        addNotes.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View v)

            {
                openAddNotesActivity();


            }

        });


        back = (Button) findViewById(R.id.backButton01);
        back.setOnClickListener(new View.OnClickListener()

        {

            @Override
            public void onClick(View v)

            {
                openHomeActivity();


            }



        });

        mAdapter = new NotesRecyclerViewAdapter(allNotes);
        myDb = new DatabaseHelper(this);


    }

    private void setupRecyclerView() {
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.notesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(mAdapter);
        swipeController = new SwipeController(new SwipeControllerActions() {

            @Override
            public void onRightClicked(int position, String id) {

                myDb.deleteRecords(id);
                mAdapter.mNotes_model.remove(position);
                mAdapter.notifyItemRemoved(position);
                mAdapter.notifyItemRangeChanged(position, mAdapter.getItemCount());
            }
        }, "15");

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });
    }


    private void openHomeActivity() {

        Intent intent = new Intent(this, LoggedIn.class);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        intent.putExtra(("EMAIL"), nameFromIntent);
        startActivity(intent);
    }
    private void openAddNotesActivity() {

        Intent intent = new Intent(this, addNotes.class);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        intent.putExtra(("EMAIL"), nameFromIntent);
        startActivity(intent);
    }
}

 */