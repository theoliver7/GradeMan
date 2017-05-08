package ch.oliver.grademan.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ch.oliver.grademan.R;
import ch.oliver.grademan.model.Note;

/**
 * Created by nilso on 08.05.2017.
 */

public class NotenArrayRecycleAdapter extends RecyclerView.Adapter<NotenArrayRecycleAdapter.ViewHolder> {

    private List<Note> items;
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textThema,textNote,textGewichtung;
        public ViewHolder(View view) {
            super(view);
            textThema = ((TextView) view.findViewById(R.id.notethematext));
            textNote = ((TextView) view.findViewById(R.id.notetext));
            textGewichtung = ((TextView) view.findViewById(R.id.gewichtungtext));
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public NotenArrayRecycleAdapter(List<Note> noten) {
        items = noten;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public NotenArrayRecycleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_adapter, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Note note = items.get(position);
        holder.textThema.setText(note.getThema());
        holder.textNote.setText("" + note.getNote());
        System.out.println("GEWICHTUNG: " + note.getGewichtung());
        holder.textGewichtung.setText(note.getGewichtung() + "x");


    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }
}