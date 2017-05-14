package ch.oliver.grademan.model;

import java.util.List;

public class Klasse {
    private int id_klasse;

    private String klassenname;
    private float gesamtschnitt;
    private int is_favorite_klasse;
    private List<Fach> faecher;

    public int getIs_favorite_klasse() {
        return is_favorite_klasse;
    }

    public void setIs_favorite_klasse(int is_favorite_klasse) {
        this.is_favorite_klasse = is_favorite_klasse;
    }

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

    public void setFaecher(List<Fach> faecher) {
        this.faecher = faecher;
    }

    public List<Fach> getFaecher() {
        return faecher;
    }


    @Override
    public String toString() {
        return "Klasse{" +
                "id_klasse=" + id_klasse +
                ", klassenname='" + klassenname + '\'' +
                ", gesamtschnitt=" + gesamtschnitt +
                ", is_favorite_klasse=" + is_favorite_klasse +
                '}';
    }
}
