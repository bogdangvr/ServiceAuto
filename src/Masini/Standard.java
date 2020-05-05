package Masini;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Standard extends Masina {
    boolean manuala;

    public Standard(int nrkm, int anFabricatie, boolean diesel, boolean manuala) {
        super(nrkm, anFabricatie, diesel);
        this.manuala = manuala;
    }

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

    public boolean isManuala() {
        return manuala;
    }
}
