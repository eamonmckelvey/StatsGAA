package com.example.statsgaa;



import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {

    private static final String TAG = "ListDataActivity";

    DatabaseHelper mDatabaseHelper;

    //TEST

    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_layout);
        mListView = (ListView) findViewById(R.id.listView);
        mDatabaseHelper = new DatabaseHelper(this);

        populateListView();
    }

    private void populateListView() {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");

        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();
        ArrayList<String> listData = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            listData.add(data.getString(1));
        }
        //create the list adapter and set the adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        mListView.setAdapter(adapter);

        //set an onItemClickListener to the ListView
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String note = adapterView.getItemAtPosition(i).toString();
                Log.d(TAG, "onItemClick: You Clicked on " + note);

                Cursor data = mDatabaseHelper.getNoteID(note); //get the id associated with that name
                int itemID = -1;
                while(data.moveToNext()){
                    itemID = data.getInt(0);
                }
                if(itemID > -1){
                    Log.d(TAG, "onItemClick: The ID is: " + itemID);
                    Intent editScreenIntent = new Intent(ListDataActivity.this, EditDataActivity.class);
                    editScreenIntent.putExtra("id",itemID);
                    editScreenIntent.putExtra("noteDesc",note);
                    startActivity(editScreenIntent);
                }
                else{
                    toastMessage("NO ID FOUND FOR THAT RECORD!!");

                }
            }
        });
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
