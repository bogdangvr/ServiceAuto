package Main;

import Masini.Masina;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Camion extends Masina {
    int tonaj; //va fi exprimat in kg

    public Camion(int id, int nrkm, int anFabricatie, boolean diesel, int tonaj) {
        super(id, nrkm, anFabricatie, diesel);
        this.tonaj = tonaj;
    }
    public int polita(){
        Calendar ziCurenta = new GregorianCalendar();
        int total = 0;

        total += 300*(ziCurenta.get(Calendar.YEAR)-this.getAnFabricatie());
        if (this.getNrkm() >= 800000){
            total+=700;
        }
        return total;
    }

    public int politaDiscout(){
        return this.polita()/100*85;
    }
}
