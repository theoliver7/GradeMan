package ch.oliver.grademan.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.triggertrap.seekarc.SeekArc;

import java.util.ArrayList;
import java.util.List;

import ch.oliver.grademan.R;
import ch.oliver.grademan.adapter.SpinnerArrayAdapter;
import ch.oliver.grademan.database.FachDAO;
import ch.oliver.grademan.listener.AddGradeListener;
import ch.oliver.grademan.listener.SeekArcListener;
import ch.oliver.grademan.model.Fach;

public class NewNoteDialogFragment extends DialogFragment {
    SeekArc seekArc, seekArcW;
    private TextView seekArcText;
    private TextView seekArcTextW;
    private Spinner spinner;
    Button addButton, addButton2;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        grades = fillGrades();
        weightings = fillWeightnings();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.grade_dialog, null);
        TabHost tabHost = (TabHost) view.findViewById(R.id.gradeTabHost);
        tabHost.setup();

        TabHost.TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        tab1.setIndicator("Grade");
        tab1.setContent(R.id.linearLayout);
        tab2.setIndicator("Weighting");
        tab2.setContent(R.id.linearLayout2);
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        addButton = (Button) view.findViewById(R.id.addButtonTab1);
        addButton2 = (Button) view.findViewById(R.id.addButtonTab2);

        seekArc = (SeekArc) view.findViewById(R.id.seekArc);
        seekArcText = (TextView) view.findViewById(R.id.seekArcProgress);
        seekArcW = (SeekArc) view.findViewById(R.id.weightingArk);
        seekArcTextW = (TextView) view.findViewById(R.id.weightingProgress);
        spinner = (Spinner) view.findViewById(R.id.spinner2);
        seekArcText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekArcText.setCursorVisible(true);
                seekArcText.setFocusableInTouchMode(true);
                seekArcText.setInputType(InputType.TYPE_CLASS_NUMBER);
                seekArcText.requestFocus();
                Toast.makeText(getActivity().getApplicationContext(), "Text",
                        Toast.LENGTH_SHORT).show();
            }
        });

        FachDAO fdao = new FachDAO(getContext());
        List<Fach> faecher = fdao.getAllFaecher();

        SpinnerArrayAdapter spinnerArrayAdapter = new SpinnerArrayAdapter(getActivity().getApplicationContext(), android.R.layout.simple_dropdown_item_1line, faecher);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(spinnerArrayAdapter);

        seekArc.setOnSeekArcChangeListener(new SeekArcListener(seekArcText, grades));
        seekArcW.setOnSeekArcChangeListener(new SeekArcListener(seekArcTextW, weightings));
        addButton.setOnClickListener(new AddGradeListener(this, (Fach) spinner.getSelectedItem()));
        addButton2.setOnClickListener(new AddGradeListener(this, (Fach) spinner.getSelectedItem()));
        builder.setView(view);
        return builder.create();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public List<String> fillGrades() {
        List<String> list = new ArrayList<>();
        for (int i = 10; i < 61; i += 1) {
            list.add(String.valueOf(i).substring(0, 1) + "." + String.valueOf(i).substring(1, 2));
            if (String.valueOf(i).substring(1, 2).equals("2") || String.valueOf(i).substring(1, 2).equals("7")) {
                list.add(String.valueOf(i).substring(0, 1) + "." + String.valueOf(i).substring(1, 2) + "5");
            }
        }
        return list;
    }


    public List<String> fillWeightnings() {
        List<String> list = new ArrayList<>();
        double[] values = {0.25, 0.5, 1.0, 1.5, 2.0};
            for(double value : values){
                list.add(String.valueOf(value) + "x");
            }
        return list;
    }

    public TextView getSeekArcText() {
        return seekArcText;
    }

    public Spinner getSpinner() {
        return spinner;
    }

    List<String> grades, weightings;

    public TextView getSeekArcTextW() {
        return seekArcTextW;
    }
}
