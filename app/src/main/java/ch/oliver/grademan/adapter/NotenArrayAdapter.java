package ch.oliver.grademan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ch.oliver.grademan.R;
import ch.oliver.grademan.model.Note;

/**
 * Created by olive_000 on 2/8/2017.
 */

public class NotenArrayAdapter extends ArrayAdapter<Note> {

    private List<Note> items;
    private LayoutInflater inflater;

    public NotenArrayAdapter(Context context, LayoutInflater inflater, List<Note> objects) {
        super(context, -1, objects);
        this.items = objects;
        this.inflater = inflater;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        System.out.println(items.get(pos).getThema());

        convertView = inflater.inflate(R.layout.note_adapter, null);
        Note actualFach = items.get(pos);
        ((TextView) convertView.findViewById(R.id.notethematext)).setText(actualFach.getThema());
        ((TextView) convertView.findViewById(R.id.notetext)).setText("" + actualFach.getNote());
        ((TextView) convertView.findViewById(R.id.gewichtungtext)).setText(actualFach.getGewichtung() + "x");
        return convertView;
    }
}