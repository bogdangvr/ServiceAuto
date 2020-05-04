package Masini;

import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Masina {
    private static int contorAngajati;
    private int id;
    int nrkm;
    int anFabricatie;
    boolean diesel;

    public Masina(int id, int nrkm, int anFabricatie, boolean diesel) {
        Masina.contorAngajati++;
        this.id = id;
        this.nrkm = nrkm;
        this.anFabricatie = anFabricatie;
        this.diesel = diesel;
    }

    public int polita(){
        Calendar ziCurenta = new GregorianCalendar();
        int total = 0;

        total += 100*(ziCurenta.get(Calendar.YEAR)-this.getAnFabricatie());
        if (this.isDiesel()){
            total+=500;
        }
        if (this.getNrkm() >= 200000){
            total+=500;
        }
        return total;
    }

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


}
