package ch.oliver.grademan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import ch.oliver.grademan.model.Klasse;

/**
 * Created by nilso on 10.09.2016.
 */
public class KlassenSpinnerArrayAdapter extends ArrayAdapter<Klasse> {
    private Context context;
    private Klasse[] values;

    public KlassenSpinnerArrayAdapter(Context context, int resource, Klasse[] values) {
        super(context, resource, values);
        this.context = context;
        this.values = values;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(values[position].getKlassenname());

        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(values[position].getKlassenname());

        return label;
    }
}

