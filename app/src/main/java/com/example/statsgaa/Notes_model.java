package com.example.statsgaa;


import androidx.appcompat.app.AppCompatActivity;

public class Notes_model extends AppCompatActivity {

    private String NoteDesc;
    private int foreignKey;
    private String noteID;


    public Notes_model(String mNoteDesc){
        NoteDesc = mNoteDesc;

    }

    public String getNoteDesc() {
        return NoteDesc;
    }

    public void setNoteDesc(String mNoteDesc) {
        this.NoteDesc = mNoteDesc;
    }

    public int getForeignKey() {
        return foreignKey;
    }

    public void setForeignKey(int foreignKey) {
        this.foreignKey = foreignKey;
    }

    public String getNoteID() {
        return noteID;
    }

    public void setNoteID(String noteID) {
        this.noteID = noteID;
    }
}
