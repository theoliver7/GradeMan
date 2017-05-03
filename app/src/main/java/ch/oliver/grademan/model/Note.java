package ch.oliver.grademan.model;

/**
 * Created by olive_000 on 1/29/2017.
 */

public class Note {
    private int id_note;
    private String thema;
    private float note;
    private float gewichtung;
    private int fach_id;
    private int semester;

    public Note(String thema, float note, float gewichtung, int fach_id) {
        this.thema = thema;
        this.note = note;
        this.gewichtung = gewichtung;
        this.fach_id=fach_id;
    }

    public Note() {
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public float getGewichtung() {
        return gewichtung;
    }

    public void setGewichtung(float gewichtung) {
        this.gewichtung = gewichtung;
    }

    public String getThema() {
        return thema;
    }

    public void setThema(String thema) {
        this.thema = thema;
    }

    public int getId_note() {
        return id_note;
    }

    public void setId_note(int id_note) {
        this.id_note = id_note;
    }

    public int getFach_id() {
        return fach_id;
    }

    public void setFach_id(int fach_id) {
        this.fach_id = fach_id;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
