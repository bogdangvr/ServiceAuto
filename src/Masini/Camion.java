package Masini;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Camion extends Masina {
    int tonaj; //camp specific clasei Camion

    public Camion(int nrkm, int anFabricatie, boolean diesel, int tonaj) {
        super(nrkm, anFabricatie, diesel);
        this.tonaj = tonaj;
    }

    //suprascriere de metode abstracte
    @Override
    public int polita(){
        Calendar ziCurenta = new GregorianCalendar();
        int total = 0;

        total += 300*(ziCurenta.get(Calendar.YEAR)-this.getAnFabricatie());
        if (this.getNrkm() >= 800000){
            total+=700;
        }
        return total;
    }

    @Override
    public int politaDiscout(){
        return this.polita()/100*85;
    }

    //getter
    public int getTonaj() {
        return tonaj;
    }
}
