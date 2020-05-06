package Utilitati;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Cititor {
    //clasa universala de citire a fisierelor de tip CSV si salvarea lor intr-o matrice
    //de tip String. Am decis sa stochez datele folosind acest tip de fisier
    //prima linie a fisierelor CSV va contine cate linii va avea restul fisierului
    //in cazul in care nu avem angajati/masini, fisierul va contine 0

    private static String BASE_PATH;
    private static Cititor single_instance = null;

    private Cititor(){
        BASE_PATH = "./src/Fisiere/";
    }

    public static Cititor getInstance(){
        if (single_instance == null)
            single_instance = new Cititor();
        return single_instance;
    }

    public String[][] citeste(String fileName) {
        String linie = "";
        String despartitor = ",";
        int dimensiune = 0;
        String[][] values =  new String[dimensiune][];
        try {
            BufferedReader br = new BufferedReader(new FileReader(BASE_PATH + fileName));
            while ((linie = br.readLine()) != null)
            {
                String[] element = linie.split(despartitor);
                dimensiune = dimensiune + 1;
                String[][] newvalues = new String[dimensiune][];
                System.arraycopy(values, 0, newvalues, 0, values.length);
                newvalues[dimensiune - 1] = element;
                values = newvalues;
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return values;
    }

}
