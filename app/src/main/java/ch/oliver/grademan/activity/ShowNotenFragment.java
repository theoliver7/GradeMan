package ch.oliver.grademan.activity;

import android.app.Fragment;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
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
        NoteDAO ndao = new NoteDAO(getContext());
        ArrayList<Note> noten = new ArrayList<Note>();
        Fach fach = new Fach();

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            Drawable background;
            Drawable xMark;
            int xMarkMargin;
            boolean initiated;

            private void init() {
                background = new ColorDrawable(Color.RED);
                xMark = ContextCompat.getDrawable(getActivity(), R.drawable.ic_add_black);
                //xMarkMargin = (int) getActivity().getResources().getDimension(R.dimen.ic_clear_margin);
                initiated = true;
            }
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {

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
                background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
                background.draw(c);

                // draw x mark
                int itemHeight = itemView.getBottom() - itemView.getTop();
//                int intrinsicWidth = xMark.getIntrinsicWidth();
//                int intrinsicHeight = xMark.getIntrinsicWidth();
//
//                int xMarkLeft = itemView.getRight() - xMarkMargin - intrinsicWidth;
//                int xMarkRight = itemView.getRight() - xMarkMargin;
//                int xMarkTop = itemView.getTop() + (itemHeight - intrinsicHeight)/2;
//                int xMarkBottom = xMarkTop + intrinsicHeight;
//                xMark.setBounds(xMarkLeft, xMarkTop, xMarkRight, xMarkBottom);
//
//                xMark.draw(c);

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recycleView);


        fach.setId_fach(args.getInt("fach_id", 0));
        fach.setName(args.getString("fachname"));
        noten = ndao.getAllNotefromFach(fach);
        textView.setText(args.getString("fachname"));
        for (Note n : noten) {
            System.out.println("Note" + n.getNote());
            System.out.println("N G" + n.getGewichtung());
        }
        NotenArrayAdapter notenArrayAdapter = new NotenArrayAdapter(getContext(), getActivity().getLayoutInflater(), noten);
        NotenArrayRecycleAdapter nara = new NotenArrayRecycleAdapter(noten);
        recycleView.setAdapter(nara);
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
