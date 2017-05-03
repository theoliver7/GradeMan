package ch.oliver.grademan.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import ch.oliver.grademan.R;
import ch.oliver.grademan.adapter.KlassenSpinnerArrayAdapter;
import ch.oliver.grademan.database.FachDAO;
import ch.oliver.grademan.database.KlasseDAO;
import ch.oliver.grademan.model.Fach;
import ch.oliver.grademan.model.Klasse;

/**
 * Created by olive_000 on 2/7/2017.
 */

public class NewFachDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fach_dialog, null);


        final EditText fachnameedit = (EditText) view.findViewById(R.id.fachNameEdit);
        final EditText abkrzedit = (EditText) view.findViewById(R.id.abkürzungEdit);
        final EditText semesteredit = (EditText) view.findViewById(R.id.semesterEdit);
        final Spinner spinner = (Spinner) view.findViewById(R.id.klassespinner);

        KlasseDAO kdao = new KlasseDAO(getContext());
        List<Klasse> klassen = new ArrayList<Klasse>();
        klassen = kdao.getAllKlassen();
        Klasse[] klassenarray = klassen.toArray(new Klasse[klassen.size()]);

        KlassenSpinnerArrayAdapter spinnerArrayAdapter = new KlassenSpinnerArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_dropdown_item_1line, klassenarray);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setSelection(0);


        Button createButton = (Button) view.findViewById(R.id.buttoncreatefach);
        Button cancelButton = (Button) view.findViewById(R.id.buttoncancel);


        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FachDAO fdao = new FachDAO(getContext());
                Fach fach = new Fach();
                Klasse klasse = (Klasse) spinner.getSelectedItem();
                fach.setName(fachnameedit.getText().toString());
                fach.setAbkrz(abkrzedit.getText().toString());
                fach.setSemester(Integer.parseInt(semesteredit.getText().toString()));
                fach.setKlasse_id(klasse.getId_klasse());
                fdao.facherstellen(fach);

                getDialog().dismiss();
                Fragment classFragment = new ShowKlasseFragment();
                FragmentManager fragmentManager = getFragmentManager();

                Bundle args = new Bundle();
                args.putInt("class_id", klasse.getId_klasse());
                args.putString("classname", klasse.getKlassenname());
                classFragment.setArguments(args);

                fragmentManager.beginTransaction().replace(R.id.flContent, classFragment).commit();


            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
            }
        });
        builder.setView(view);
        return builder.create();
    }
}
