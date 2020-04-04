package com.example.statsgaa;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;



import java.util.Deque;
import java.util.List;


public class NotesRecyclerViewAdapter extends RecyclerView.Adapter<NotesRecyclerViewAdapter.ViewHolder> {

    public List<Notes_model> mNotes_model;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView notesTextView;


        public ViewHolder(final View itemView) {
            super(itemView);
            notesTextView = (TextView) itemView.findViewById(R.id.notesTextView);

        }


    }



    public NotesRecyclerViewAdapter(List<Notes_model> notes_model) {

        this.mNotes_model = notes_model;

    }


    @Override
    public NotesRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_layout, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotesRecyclerViewAdapter.ViewHolder viewHolder, int position) {

        Notes_model notes_model = mNotes_model.get(position);
        TextView mNoteDesc = viewHolder.notesTextView;
        mNoteDesc.setText(notes_model.getNoteDesc());


    }




    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mNotes_model.size();
    }



}

