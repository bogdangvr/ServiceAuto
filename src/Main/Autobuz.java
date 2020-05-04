package Main;

import Masini.Masina;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Autobuz extends Masina {
    int numarLocuri;

    public Autobuz(int id, int nrkm, int anFabricatie, boolean diesel, int numarLocuri) {
        super(id, nrkm, anFabricatie, diesel);
        this.numarLocuri = numarLocuri;
    }

    public int polita(){
        Calendar ziCurenta = new GregorianCalendar();
        int total = 0;

        total += 200*(ziCurenta.get(Calendar.YEAR)-this.getAnFabricatie());
        if (this.isDiesel()){
            total+=1000;
        }
        if (this.getNrkm() >= 100000){
            if (this.getNrkm() >= 200000){
                total+=1000;
            }
            else{
                total+=500;
            }
        }
        return total;
    }

    public int politaDiscout(){
        return this.polita()/100*90;
    }
}
