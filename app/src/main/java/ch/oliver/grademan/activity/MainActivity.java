package ch.oliver.grademan.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ch.oliver.grademan.R;
import ch.oliver.grademan.database.FachDAO;
import ch.oliver.grademan.database.KlasseDAO;
import ch.oliver.grademan.model.Fach;
import ch.oliver.grademan.model.Klasse;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewGradeDialogFragment dialogFragment = new NewGradeDialogFragment();
                dialogFragment.show(getFragmentManager(), "");
                System.out.println("Test");

            }
        });
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                KlasseDAO kdao = new KlasseDAO(getApplicationContext());
                final Menu menu = navigationView.getMenu();
                navigationView.invalidate();
                for (Klasse k : kdao.getAllKlassen()) {
                    if (menu.findItem(k.getId_klasse()) == null) {
                        menu.add(R.id.classes, k.getId_klasse(), 0, k.getKlassenname()).setIcon(R.drawable.ic_group_black);
                    }

                }

                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);

        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //------------------------------TESTZONE
        listView= (ListView) findViewById(R.id.listView);
        List<Fach> faecher = new ArrayList<Fach>();
        faecher.add(new Fach("Mathe", 1, 1, 1, "Test", 1));
        faecher.add(new Fach("English", 2, 1, 1, "Test", 1));
        faecher.add(new Fach("VBR", 3, 1, 1, "Test", 1));
        FachDAO fdao = new FachDAO(getApplicationContext());
        fdao.facherstellen(new Fach("Deutsch", 1, 1, 1, "df", 1));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_add) {
            final EditText editText = new EditText(this);

            new AlertDialog.Builder(this)
                    .setTitle("New class")
                    .setMessage("Enter the name of the class")
                    .setView(editText)
                    .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                            KlasseDAO kdao= new KlasseDAO(getApplicationContext());
                            Klasse klasse = new Klasse();
                            klasse.setKlassenname(editText.getText().toString());
                            kdao.klasseerstellen(klasse);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        }
                    })
                    .show();
        } else if (id == R.id.nav_send) {

        } else {

            Fragment classFragment = new ShowClassFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();

            Bundle args = new Bundle();
            args.putInt("class_id", item.getItemId());
            args.putString("classname", item.getTitle().toString());
            classFragment.setArguments(args);

            fragmentManager.beginTransaction().replace(R.id.flContent, classFragment).commit();
            System.out.println("fds");
            Toast.makeText(getApplicationContext(), item.getTitle() + " " + item.getItemId(),
                    Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
