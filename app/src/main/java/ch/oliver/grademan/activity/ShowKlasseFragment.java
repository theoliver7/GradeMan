package ch.oliver.grademan.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ch.oliver.grademan.R;
import ch.oliver.grademan.adapter.FachArrayAdapter;
import ch.oliver.grademan.database.FachDAO;
import ch.oliver.grademan.model.Fach;
import ch.oliver.grademan.model.Klasse;

/**
 * Created by olive_000 on 2/7/2017.
 */

public class ShowKlasseFragment extends Fragment {
    private View myView;
    private TextView textView;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        this.myView = inflater.inflate(R.layout.klassen_fragment, container, false);
        textView = (TextView) myView.findViewById(R.id.classname);
        listView = (ListView) myView.findViewById(R.id.classliste);
        Bundle args = getArguments();
        FachDAO fdao = new FachDAO(getContext());
        Klasse klasse = new Klasse();

        klasse.setId_klasse(args.getInt("class_id", 0));
        ArrayList<Fach> facher = new ArrayList<Fach>();
        facher = fdao.getAllFacherfromClass(klasse);
        textView.setText(args.getString("classname"));
        final FachArrayAdapter fachArrayAdapter = new FachArrayAdapter(getContext(), getActivity().getLayoutInflater(), facher);
        listView.setAdapter(fachArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fragment classFragment = new ShowNotenFragment();
                FragmentManager fragmentManager = getActivity().getFragmentManager();
                Fach fach = (Fach) fachArrayAdapter.getItem(position);
                System.out.println(fach.getName());
                Bundle args = new Bundle();
                args.putInt("fach_id", fach.getId_fach());
                args.putString("fachname", fach.getName());
                classFragment.setArguments(args);

                fragmentManager.beginTransaction().replace(R.id.flContent, classFragment).commit();
            }

        });
        return myView;
    }

}
