package Masini;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Standard extends Masina {
    boolean manuala; //camp aditional pentru clasa Masina

    public Standard(int nrkm, int anFabricatie, boolean diesel, boolean manuala) {
        super(nrkm, anFabricatie, diesel);
        this.manuala = manuala;
    }

    //suprascrierea metodelor abstracte
    @Override
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

    @Override
    public int politaDiscout() {
        return this.polita()/100*95;
    }

    //getter
    public boolean isManuala() {
        return manuala;
    }
}
