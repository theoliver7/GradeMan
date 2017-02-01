package ch.oliver.grademan.model;

/**
 * Created by olive_000 on 1/29/2017.
 */

public class Fach {
    private int id_fach;
    private String name;
    private long klasse_id;
    private int semester;
    private float schnitt;
    private String abkrz;

    public Fach(String name, long noten, int semester, float schnitt, String abkrz) {
        this.name = name;
        this.klasse_id = noten;
        this.semester = semester;
        this.schnitt = schnitt;
        this.abkrz = abkrz;
    }

    public Fach() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getKlasse_id() {
        return klasse_id;
    }

    public void setKlasse_id(long noten) {
        this.klasse_id = noten;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public float getSchnitt() {
        return schnitt;
    }

    public void setSchnitt(float schnitt) {
        this.schnitt = schnitt;
    }

    public String getAbkrz() {
        return abkrz;
    }

    public void setAbkrz(String abkrz) {
        this.abkrz = abkrz;
    }

    public int getId_fach() {
        return id_fach;
    }

    public void setId_fach(int id_fach) {
        this.id_fach = id_fach;
    }
}
