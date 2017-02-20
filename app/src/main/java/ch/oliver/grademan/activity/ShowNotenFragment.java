package ch.oliver.grademan.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

import ch.oliver.grademan.R;
import ch.oliver.grademan.adapter.NotenArrayAdapter;
import ch.oliver.grademan.database.NoteDAO;
import ch.oliver.grademan.model.Fach;
import ch.oliver.grademan.model.Note;

/**
 * Created by olive_000 on 2/8/2017.
 */

public class ShowNotenFragment extends Fragment {
    private View myView;
    private TextView textView;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.myView = inflater.inflate(R.layout.noten_fragment, container, false);
        textView = (TextView) myView.findViewById(R.id.notenfach);
        listView = (ListView) myView.findViewById(R.id.notenliste);
        Bundle args = getArguments();
        NoteDAO ndao = new NoteDAO(getContext());
        ArrayList<Note> noten = new ArrayList<Note>();
        Fach fach = new Fach();

        fach.setId_fach(args.getInt("fach_id", 0));
        fach.setName(args.getString("fachname"));
        System.out.println(fach.getId_fach());
        noten = ndao.getAllNotefromFach(fach);
        textView.setText(args.getString("fachname"));

        NotenArrayAdapter notenArrayAdapter = new NotenArrayAdapter(getContext(), getActivity().getLayoutInflater(), noten);
        for (Note n : noten) {
            System.out.println("Note" + n.getNote());
        }
        listView.setAdapter(notenArrayAdapter);
        GraphView graph = (GraphView) myView.findViewById(R.id.graph);

        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[]{
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        series.setColor(Color.parseColor("#C5B358"));
        graph.addSeries(series);

        return myView;
    }
}
