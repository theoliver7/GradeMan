package ch.oliver.grademan.activity;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import ch.oliver.grademan.R;
import ch.oliver.grademan.database.KlasseDAO;
import ch.oliver.grademan.model.Klasse;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Klasse favoriteKlasse;
    private Klasse currenKlasse;
    private KlasseDAO kdao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        kdao = new KlasseDAO(getApplicationContext());
        favoriteKlasse = kdao.getFavoriteKlasse();
        if(favoriteKlasse!=null){
            showKlasseFragment(favoriteKlasse);
        }
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currenKlasse ==null){
                    Toast.makeText(getApplicationContext(), "Wählen Sie zuerst eine Klasse aus",
                            Toast.LENGTH_SHORT).show();
                }else if(currenKlasse.getFaecher().size()==0){
                    Toast.makeText(getApplicationContext(), "Erstellen Sie zuerst ein Fach für diese Klasse",
                            Toast.LENGTH_SHORT).show();
                }else {
                    NewNoteDialogFragment dialogFragment = new NewNoteDialogFragment();
                    dialogFragment.show(getFragmentManager(), "");
                    System.out.println("Test");
                }

            }
        });
        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
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

        // Fragment classFragment = new ShowNotenFragment();
        //FragmentManager fragmentManager = getSupportFragmentManager();
        //fragmentManager.beginTransaction().replace(R.id.flContent, classFragment).commit();

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
        } else if (id == R.id.addfach) {
            NewFachDialogFragment dialogfragment = new NewFachDialogFragment();
            dialogfragment.show(getFragmentManager(), "");
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
                            klasse.setIs_favorite_klasse(0);
                            klasse.setKlassenname(editText.getText().toString());
                            long id= kdao.klasseerstellen(klasse);
                            if(id!=-1) {
                                //showKlasseFragment(klasse);
                            }
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {

                        }
                    })
                    .show();
        } else if (id == R.id.nav_send) {

        } else {
            Klasse klasse = new Klasse();
            klasse.setId_klasse(item.getItemId());
            klasse.setKlassenname(item.getTitle().toString());
            showKlasseFragment(klasse);
            Toast.makeText(getApplicationContext(), item.getTitle() + " " + item.getItemId(),
                    Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void showKlasseFragment(Klasse klasse){
        Fragment classFragment = new ShowKlasseFragment();
        FragmentManager fragmentManager = getFragmentManager();

        Bundle args = new Bundle();
        args.putInt("class_id", klasse.getId_klasse());
        args.putString("classname", klasse.getKlassenname());
        classFragment.setArguments(args);

        fragmentManager.beginTransaction().replace(R.id.flContent, classFragment).commit();
    }

    public Klasse getCurrenKlasse() {
        return currenKlasse;
    }

    public void setCurrenKlasse(Klasse currenKlasse) {
        this.currenKlasse = currenKlasse;
    }
}
