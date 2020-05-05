package Masini;

import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Masina {
    private static int contorMasini;
    private int id;
    int nrkm;
    int anFabricatie;
    int timpReparatie;
    boolean diesel;
    int status;

    public Masina(int nrkm, int anFabricatie, boolean diesel) {
        Masina.contorMasini++;
        this.id = Masina.contorMasini;
        this.nrkm = nrkm;
        this.anFabricatie = anFabricatie;
        this.diesel = diesel;
        status=-1;
    }

    public abstract int polita();

    public int politaDiscout(){
        return this.polita()/100*95;
    }

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

    public void setStatus(int status) {
        this.status = status;
    }

    public void setTimpReparatie(int timpReparatie) {
        this.timpReparatie = timpReparatie;
    }
}
