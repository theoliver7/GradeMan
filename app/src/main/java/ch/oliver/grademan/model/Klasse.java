package ch.oliver.grademan.model;

/**
 * Created by olive_000 on 1/29/2017.
 */

public class Klasse {
    private int id_klasse;
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

    public int getId_klasse() {
        return id_klasse;
    }

    public void setId_klasse(int id_klasse) {
        this.id_klasse = id_klasse;
    }
}
