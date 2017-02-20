package ch.oliver.grademan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ch.oliver.grademan.model.Fach;

/**
 * Created by nilso on 10.09.2016.
 */
public class SpinnerArrayAdapter extends ArrayAdapter<Fach> {
    private Context context;
    private List<Fach> items;


    public SpinnerArrayAdapter(Context context, int resource, List<Fach> objects) {
        super(context, -1, objects);
        this.items = objects;
        this.context=context;
    }

    @Nullable
    @Override
    public Fach getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println(items.get(position).getName());
        System.out.println("ID FACH" + items.get(position).getId_fach());

        TextView label= new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(items.get(position).getName());

        return label;
    }
    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label= new TextView(context);
        label.setTextColor(Color.BLACK);
        label.setText(items.get(position).getName());

        return label;
    }
}

