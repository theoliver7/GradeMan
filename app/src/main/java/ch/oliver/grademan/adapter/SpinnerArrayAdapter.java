package ch.oliver.grademan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import ch.oliver.grademan.model.Fach;

/**
 * Created by nilso on 10.09.2016.
 */
public class SpinnerArrayAdapter extends ArrayAdapter<Fach> {
    private Context context;
    private Fach[] values;

    public SpinnerArrayAdapter(Context context, int resource, Fach[] values) {
        super(context, resource, values);
        this.context=context;
        this.values=values;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label= new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(values[position].getName());

        return label;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label= new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(values[position].getName());

        return label;
    }
}

