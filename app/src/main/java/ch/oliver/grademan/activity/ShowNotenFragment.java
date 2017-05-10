package ch.oliver.grademan.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;

import ch.oliver.grademan.R;
import ch.oliver.grademan.adapter.NotenArrayAdapter;
import ch.oliver.grademan.adapter.NotenArrayRecycleAdapter;
import ch.oliver.grademan.database.NoteDAO;
import ch.oliver.grademan.model.Fach;
import ch.oliver.grademan.model.Note;

/**
 * Created by olive_000 on 2/8/2017.
 */

public class ShowNotenFragment extends Fragment {
    private View myView;
    private TextView textView;
    private RecyclerView recycleView;
    private NoteDAO ndao;
    private ArrayList<Note> noten;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.myView = inflater.inflate(R.layout.noten_fragment, container, false);
        textView = (TextView) myView.findViewById(R.id.notenfach);
        recycleView = (RecyclerView) myView.findViewById(R.id.notenliste);
        recycleView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recycleView.setLayoutManager(layoutManager);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        Bundle args = getArguments();
        ndao = new NoteDAO(getContext());
        noten = new ArrayList<>();
        Fach fach = new Fach();

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            Drawable background;
            Drawable xMark;
            int xMarkMargin;
            boolean initiated;

            private void init() {
                background = new ColorDrawable(Color.RED);
                xMark = ContextCompat.getDrawable(getActivity(), R.mipmap.ic_delete);
                //xMarkMargin = (int) getActivity().getResources().getDimension(R.dimen.ic_clear_margin);
                initiated = true;
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                long noteId = noten.get(viewHolder.getPosition()).getId_note();
                //  ndao.noteloeschen(noteId);
                Toast.makeText(getActivity(), "Swiped", Toast.LENGTH_SHORT).show();
                Fragment frg = null;
                frg = getActivity().getFragmentManager().findFragmentByTag("noten_fragment");
                final FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
                ft.detach(frg);
                ft.attach(frg);
                ft.commit();
                Snackbar.make(getActivity().findViewById(android.R.id.content), "Had a snack at Snackbar", Snackbar.LENGTH_LONG)
                        .setAction("Undo", null)
                        .setActionTextColor(Color.RED)
                        .show();
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                View itemView = viewHolder.itemView;
                    if (viewHolder.getAdapterPosition() == -1) {
                        return;
                    }

                    if (!initiated) {
                        init();
                    }
                    if (dX > 0) {
                        background.setBounds(itemView.getLeft(), itemView.getTop(), (int) dX, itemView.getBottom());
                    } else {
                        background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
                    }
                    background.draw(c);

                    // draw x mark
                    int itemHeight = itemView.getBottom() - itemView.getTop();
                    int intrinsicWidth = xMark.getIntrinsicWidth();
                    int intrinsicHeight = xMark.getIntrinsicWidth();

                    int xMarkLeft = itemView.getRight() - xMarkMargin - intrinsicWidth;
                    int xMarkRight = itemView.getRight() - xMarkMargin;
                    int xMarkTop = itemView.getTop() + (itemHeight - intrinsicHeight) / 2;
                    int xMarkBottom = xMarkTop + intrinsicHeight;
                    xMark.setBounds(xMarkLeft, xMarkTop, xMarkRight, xMarkBottom);
                    xMark.draw(c);
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recycleView);


        fach.setId_fach(args.getInt("fach_id", 0));
        fach.setName(args.getString("fachname"));
        noten = ndao.getAllNotefromFach(fach);
        textView.setText(args.getString("fachname"));
        ArrayList<String> notenanzlabel = new ArrayList<>();

        ArrayList<DataPoint> dataPoints = new ArrayList<DataPoint>();
        for (int i = 0; i < noten.size(); i++) {
            notenanzlabel.add(Integer.toString(i + 1));
            dataPoints.add(new DataPoint(i + 1, noten.get(i).getNote()));
        }

        NotenArrayAdapter notenArrayAdapter = new NotenArrayAdapter(getContext(), getActivity().getLayoutInflater(), noten);
        NotenArrayRecycleAdapter nara = new NotenArrayRecycleAdapter(noten);
        recycleView.setAdapter(nara);
        GraphView graph = (GraphView) myView.findViewById(R.id.graph);
        if (dataPoints.size() > 0) {
            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(1);
            graph.getViewport().setMaxX(noten.size());

            // set manual Y bounds
            graph.getViewport().setYAxisBoundsManual(true);
            graph.getViewport().setMinY(1);
            graph.getViewport().setMaxY(6);

            StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
            staticLabelsFormatter.setVerticalLabels(new String[]{"1", "2", "3", "4", "5", "6"});
            if (notenanzlabel.size() <= 2) {
                staticLabelsFormatter.setHorizontalLabels(new String[]{"1", "2"});
            } else {
                staticLabelsFormatter.setHorizontalLabels(notenanzlabel.toArray(new String[0]));
            }
            graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);

        }



        DataPoint[] dataPointsArray = dataPoints.toArray(new DataPoint[dataPoints.size()]);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPointsArray);
        series.setColor(Color.parseColor("#C5B358"));
        series.setDrawDataPoints(true);



        graph.addSeries(series);

        return myView;
    }

}
