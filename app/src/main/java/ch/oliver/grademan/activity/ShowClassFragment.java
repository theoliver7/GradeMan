package ch.oliver.grademan.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ch.oliver.grademan.R;
import ch.oliver.grademan.database.FachDAO;
import ch.oliver.grademan.model.Fach;
import ch.oliver.grademan.model.Klasse;

/**
 * Created by olive_000 on 2/7/2017.
 */

public class ShowClassFragment extends Fragment {
    private View myView;
    private TextView textView;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        System.out.println("Peace");
        this.myView = inflater.inflate(R.layout.class_fragment, container, false);
        textView = (TextView) myView.findViewById(R.id.classname);
        listView = (ListView) myView.findViewById(R.id.classliste);
        Bundle args = getArguments();
        FachDAO fdao = new FachDAO(getContext());
        Klasse klasse = new Klasse();
        klasse.setId_klasse(args.getInt("class_id", 0));
        ArrayList<Fach> facher = new ArrayList<Fach>();
        facher = fdao.getAllFacherfromClass(klasse);
        System.out.println(facher.get(0).getName());
        textView.setText(args.getString("classname"));
        listView.setAdapter(new ArrayAdapter<Fach>(getContext(), android.R.layout.simple_list_item_1, facher));
        return myView;
    }

}
