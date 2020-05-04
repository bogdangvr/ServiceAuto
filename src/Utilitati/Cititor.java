package Utilitati;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Cititor {

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
                String[] persoana = linie.split(despartitor);
                dimensiune = dimensiune + 1;
                String[][] newvalues = new String[dimensiune][];
                System.arraycopy(values, 0, newvalues, 0, values.length);
                newvalues[dimensiune - 1] = persoana;
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
