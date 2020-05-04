package Main;

import Masini.Masina;

public class Standard extends Masina {
    boolean manuala;

    public Standard(int id, int nrkm, int anFabricatie, boolean diesel, boolean manuala) {
        super(id, nrkm, anFabricatie, diesel);
        this.manuala = manuala;
    }
}
