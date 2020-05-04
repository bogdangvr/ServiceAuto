package Main;

import Angajati.Angajat;
import Angajati.Asistent;
import Angajati.Director;
import Angajati.Mecanic;
import Serviciu.Serviciu;
import Utilitati.Cititor;
import Utilitati.Scriitor;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {

        Serviciu serviciulMeu = new Serviciu();

        Cititor cititor = Cititor.getInstance();

        String[][] continutFisierAngajati = cititor.citeste("angajati.csv");

        int nrAngajati = Integer.parseInt(continutFisierAngajati[0][0]);

        //stocarea in memodie a angajatilor
        List<Angajat> listaAngajati = new ArrayList<Angajat>();
        for (int i=1; i<=nrAngajati; i++){
            int tipAngajat = Integer.parseInt(continutFisierAngajati[i][0]); //1 pentru Director, 2 Mecanic, 3 Asistent
            String nume = continutFisierAngajati[i][1];
            String prenume = continutFisierAngajati[i][2];

            String[] dataNasteriiString = continutFisierAngajati[i][3].split("-");
            Calendar dataNasterii = new GregorianCalendar(Integer.parseInt(dataNasteriiString[2]),
                                                  Integer.parseInt(dataNasteriiString[1]),
                                                  Integer.parseInt(dataNasteriiString[0]));

            String[] dataAngajariiString = continutFisierAngajati[i][4].split("-");
            Calendar dataAngajarii = new GregorianCalendar(Integer.parseInt(dataAngajariiString[2]),
                                                           Integer.parseInt(dataAngajariiString[1]),
                                                           Integer.parseInt(dataAngajariiString[0]));
            if (tipAngajat == 1){
                listaAngajati.add(new Director(nume,prenume,dataNasterii,dataAngajarii));
            }
            if (tipAngajat == 2){
                listaAngajati.add(new Mecanic(nume,prenume,dataNasterii,dataAngajarii));
            }
            if (tipAngajat == 3){
                listaAngajati.add(new Asistent(nume,prenume,dataNasterii,dataAngajarii));
            }
        }


        serviciulMeu.afisareAngajati(listaAngajati);

        Scriitor scriitor = Scriitor.getInstance();

        serviciulMeu.editareAngajat(listaAngajati);

        scriitor.scrie(listaAngajati);

        serviciulMeu.afisareSalariu(listaAngajati, 1);
        serviciulMeu.afisareSalariu(listaAngajati, 2);
        serviciulMeu.afisareSalariu(listaAngajati, 3);
        serviciulMeu.afisareSalariu(listaAngajati, 4);
    }

}
