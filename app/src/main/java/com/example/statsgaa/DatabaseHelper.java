package com.example.statsgaa;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.util.Log;

import static android.content.ContentValues.TAG;
import static android.os.Build.ID;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database information
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "test.db";
    private static final String TABLE_USER = "User";
    private static final String TABLE_PLAYERS = "Player";
    private static final String TABLE_FIXTURES = "Fixtures";
    private static final String TABLE_NOTES = "Notes";
    private DatabaseHelper myDBHelper;
    private SQLiteDatabase db;

    // Field names for Users
    private static final String COLUMN_USER_NAME = "User_name";
    private static final String COLUMN_USER_ID = "User_id";
    private static final String COLUMN_USER_EMAIL = "User_email";
    private static final String COLUMN_USER_PASSWORD = "User_password";


    // Field names for Players
    public static final String COLUMN_PLAYER_NAME = "Player_name";
    public static final String COLUMN_PLAYER_AGE = "Player_age";
    public static final String COLUMN_PLAYER_WEIGHT = "Player_weight";
    public static final String COLUMN_PLAYER_HEIGHT = "Player_height";
    public static final String COLUMN_PLAYER_ID = "Player_id";
    public static final String COLUMN_PLAYER_POSITION = "Player_position";
    public static final String FOREIGN_PLAYER_ID = COLUMN_USER_ID;
    // private static final Image COLUMN_PLAYER_IMAGE ;


    // Field names for Fixtures
    public static final String COLUMN_OPPONENT_NAME = "Opponent_name";
    public static final String COLUMN_FIXTURES_ID = "Fixtures_id";
    public static final String COLUMN_MATCH_DATE = "Match_date";
    public static final String COLUMN_HOME_SCORE = "Home_score";
    public static final String COLUMN_AWAY_SCORE = "Away_score";
    public static final String COLUMN_COMPETITION = "Competition";
    public static final String COLUMN_RESULT = "Result";
    public static final String FOREIGN_FIXTURE_ID = COLUMN_USER_ID;

    // Field names for Notes
    public static final String COLUMN_NOTE_DESC = "Note_desc";
    public static final String COLUMN_NOTE_ID = "Note_id";
    public static final String FOREIGN_NOTE_ID = COLUMN_USER_ID;


    public static final String TABLE_NAME = "mylist_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "ITEM1";


    public static final String[] ALL_KEYS = new String[]{COLUMN_PLAYER_NAME, COLUMN_PLAYER_AGE, COLUMN_PLAYER_HEIGHT, COLUMN_PLAYER_WEIGHT, COLUMN_PLAYER_POSITION};

    // Table 1 : Login/Register
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "(" + COLUMN_USER_NAME + " TEXT,"
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";


    // Table 2 : Adding players
    private String CREATE_PLAYER_TABLE = "CREATE TABLE " + TABLE_PLAYERS + "(" + COLUMN_PLAYER_NAME + " TEXT,"
            + COLUMN_PLAYER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_PLAYER_AGE + " INTEGER," + COLUMN_PLAYER_WEIGHT + " INTEGER," + COLUMN_PLAYER_POSITION + " TEXT," + COLUMN_PLAYER_HEIGHT + " INTEGER, " + FOREIGN_PLAYER_ID + " INTEGER," + "FOREIGN KEY(" + FOREIGN_PLAYER_ID + ") REFERENCES " + TABLE_USER + "(User_id) " + ")";


    // Table 3 : Adding fixtures
    private String CREATE_FIXTURES_TABLE = "CREATE TABLE " + TABLE_FIXTURES + "(" + COLUMN_OPPONENT_NAME + " TEXT,"
            + COLUMN_FIXTURES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_MATCH_DATE + " TEXT," + COLUMN_COMPETITION + " TEXT," + COLUMN_RESULT + " INTEGER," + COLUMN_HOME_SCORE + " TEXT," + COLUMN_AWAY_SCORE + " TEXT, " + FOREIGN_FIXTURE_ID + " INTEGER," + "FOREIGN KEY(" + FOREIGN_FIXTURE_ID + ") REFERENCES " + TABLE_USER + "(User_id) " + ")";


    // Table 4 : Adding Notes_model
    // private String CREATE_NOTES_TABLE = "CREATE TABLE " + TABLE_NOTES + "(" + COLUMN_NOTE_DESC + " TEXT,"
    // + COLUMN_NOTE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
    // + FOREIGN_NOTE_ID + " INTEGER," +  "FOREIGN KEY(" + FOREIGN_NOTE_ID + ") REFERENCES " + TABLE_USER + "(User_id) " + ")";

    private String CREATE_NOTES_TABLE = "CREATE TABLE " + TABLE_NOTES + " (Note_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NOTE_DESC +" TEXT)";


    // Drop tables

    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_PLAYER_TABLE = "DROP TABLE IF EXISTS " + TABLE_PLAYERS;
    private String DROP_FIXTURES_TABLE = "DROP TABLE IF EXISTS " + TABLE_FIXTURES;
    private String DROP_NOTES_TABLE = "DROP TABLE IF EXISTS " + TABLE_NOTES;


    public DatabaseHelper(Context context) {
        //String name,  SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON;");
        }
    }

    public DatabaseHelper open() {
        db = this.getReadableDatabase();
        return this;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_PLAYER_TABLE);
        db.execSQL(CREATE_FIXTURES_TABLE);
        db.execSQL(CREATE_NOTES_TABLE);
        // db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_PLAYER_TABLE);
        db.execSQL(DROP_FIXTURES_TABLE);
        db.execSQL(DROP_NOTES_TABLE);
        onCreate(db);
    }


    // Adding a user to Users table
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Table 1 : Add users info
        values.put(COLUMN_USER_NAME, user.getName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(FOREIGN_PLAYER_ID, user.getForeignID());

        db.insert(TABLE_USER, null, values);
        db.close();
    }

    // Adding a player to players table
    public void addPlayer(Player player) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        // Table 2 : Add players info
        values.put(COLUMN_PLAYER_NAME, player.getPlayerName());
        values.put(COLUMN_PLAYER_AGE, player.getPlayerAge());
        values.put(COLUMN_PLAYER_HEIGHT, player.getPlayerHeight());
        values.put(COLUMN_PLAYER_WEIGHT, player.getPlayerWeight());
        values.put(COLUMN_PLAYER_POSITION, player.getPlayerPosition());
        values.put(COLUMN_USER_ID, player.getForeignKey());


        db.insert(TABLE_PLAYERS, null, values);
        db.close();


    }

    // Adding fixtures to fixtures table
    public void addFixtures(Fixtures1 fixtures1) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        // Table 2 : Add fixtures info

        values.put(COLUMN_OPPONENT_NAME, fixtures1.getOpponentName());
        values.put(COLUMN_AWAY_SCORE, fixtures1.getAwayScore());
        values.put(COLUMN_HOME_SCORE, fixtures1.getHomeScore());
        values.put(COLUMN_MATCH_DATE, fixtures1.getMatchDate());
        values.put(COLUMN_RESULT, fixtures1.getResult());
        values.put(COLUMN_COMPETITION, fixtures1.getCompetition());
        values.put(COLUMN_USER_ID, fixtures1.getForeignKey());

        db.insert(TABLE_FIXTURES, null, values);
        db.close();

    }

    // Checking the users email
    public boolean checkUser(String email) {
        String[] columns = {
                COLUMN_USER_ID

        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }


    // Getting column user name
    public String getColumnUserName(String email) {

        String user = "";
        String[] columns = {
                COLUMN_USER_ID

        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        String[] b = cursor.getColumnNames();

        if (cursor.moveToFirst()) // data?{
            user = cursor.getString(cursor.getColumnIndex("User_id"));

        cursor.close(); // that's important too, otherwise you're gonna leak cursors
        db.close();

        if (cursorCount > 0) {
            return user;
        }
        return user;
    }

    // Checking the users email and password
    public boolean checkUser(String email, String password) {
        String[] columns = {
                COLUMN_USER_ID

        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " =?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_USER,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }
        return false;
    }


    // Get all Players
    public Cursor getAllRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        //String query = "SELECT * from "+TABLE_PLAYERS;
        Cursor cursor = db.rawQuery("select  Player_id _id, * from Player", null);


        return cursor;
    }

    // Get all fixtures
    public Cursor getFixtures() {
        SQLiteDatabase db = this.getReadableDatabase();
        //String query = "SELECT * from "+TABLE_PLAYERS;
        Cursor cursor = db.rawQuery("select  Fixtures_id _id, * from Fixtures", null);


        return cursor;
    }


    // Adding notes to the notes table
    public boolean addData(String item1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NOTE_DESC, item1);

        long result = db.insert(TABLE_NOTES, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getNoteID(String note){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COLUMN_NOTE_ID + " FROM " + TABLE_NOTES +
                " WHERE " + COLUMN_NOTE_DESC + " = '" + note + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NOTES;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    // Removing notes from database
    public void deleteNote(int id, String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NOTES + " WHERE "
                + COLUMN_NOTE_ID + " = '" + id + "'" +
                " AND " + COLUMN_NOTE_DESC + " = '" + note + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + note + " from database.");
        db.execSQL(query);
    }

    public void updateNote(String newNote, String id, String oldNote) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NOTES + " SET " + COLUMN_NOTE_DESC +
                " = '" + newNote + "' WHERE " + COLUMN_NOTE_ID + " = '" + id + "'" +
                " AND " + COLUMN_NOTE_DESC + " = '" + oldNote + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newNote);
        db.execSQL(query);

    }

}


