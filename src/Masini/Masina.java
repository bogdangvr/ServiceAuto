package Masini;

import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Masina {
    private static int contorMasini;
    private int id;
    int nrkm;
    int anFabricatie;
    int timpReparatie; //timpul de reparatie primit cand face cererea de reparare
    boolean diesel;
    int status; //-1 pentru nu se afla in coada, 1 altfel

    public Masina(int nrkm, int anFabricatie, boolean diesel) {
        Masina.contorMasini++;
        this.id = Masina.contorMasini;
        this.nrkm = nrkm;
        this.anFabricatie = anFabricatie;
        this.diesel = diesel;
        status=-1;
    }

    //metoda abstracta de calcul al politei
    public abstract int polita();

    //metoda abstracta de calcul a politei cu discount
    public abstract int politaDiscout();

    //getteri
    public int getId() {
        return id;
    }

    public int getNrkm() {
        return nrkm;
    }

    public int getAnFabricatie() {
        return anFabricatie;
    }

    public boolean isDiesel() {
        return diesel;
    }

    public int getTimpReparatie() {
        return timpReparatie;
    }

    public int getStatus() {
        return status;
    }

    //setteri
    public void setStatus(int status) {
        this.status = status;
    }

    public void setTimpReparatie(int timpReparatie) {
        this.timpReparatie = timpReparatie;
    }
}
