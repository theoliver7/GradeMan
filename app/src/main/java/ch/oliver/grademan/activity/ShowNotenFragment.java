package ch.oliver.grademan.activity;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
        noten = ndao.getAllNotefromFach(fach);
        textView.setText(args.getString("fachname"));
        for (Note n : noten) {
            System.out.println("Note" + n.getNote());
            System.out.println("N G" + n.getGewichtung());
        }
        NotenArrayAdapter notenArrayAdapter = new NotenArrayAdapter(getContext(), getActivity().getLayoutInflater(), noten);

        listView.setAdapter(notenArrayAdapter);
        GraphView graph = (GraphView) myView.findViewById(R.id.graph);


        ArrayList<DataPoint> dataPoints = new ArrayList<DataPoint>();

        for (int i = 0; i < noten.size(); i++) {
            dataPoints.add(new DataPoint(i + 1, noten.get(i).getNote()));
        }
        DataPoint[] dataPointsArray = dataPoints.toArray(new DataPoint[dataPoints.size()]);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPointsArray);
        series.setColor(Color.parseColor("#C5B358"));
        graph.addSeries(series);

        return myView;
    }
}
