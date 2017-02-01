package ch.oliver.grademan.model;

import java.util.ArrayList;

/**
 * Created by olive_000 on 1/29/2017.
 */

public class Klasse {
    private int klasse_id;
    private String klassenname;
    private float gesamtschnitt;
    private long fachliste;

    public String getKlassenname() {
        return klassenname;
    }

    public void setKlassenname(String klassenname) {
        this.klassenname = klassenname;
    }

    public long getFachliste() {
        return fachliste;
    }

    public void setFachliste(long fachliste) {
        this.fachliste = fachliste;
    }

    public float getGesamtschnitt() {
        return gesamtschnitt;
    }

    public void setGesamtschnitt(float gesamtschnitt) {
        this.gesamtschnitt = gesamtschnitt;
    }

    public int getKlasse_id() {
        return klasse_id;
    }

    public void setKlasse_id(int klasse_id) {
        this.klasse_id = klasse_id;
    }
}
