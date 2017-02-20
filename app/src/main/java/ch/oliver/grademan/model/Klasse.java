package ch.oliver.grademan.model;

/**
 * Created by olive_000 on 1/29/2017.
 */

public class Klasse {
    private int id_klasse;
    private String klassenname;
    private float gesamtschnitt;

    public String getKlassenname() {
        return klassenname;
    }

    public void setKlassenname(String klassenname) {
        this.klassenname = klassenname;
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
