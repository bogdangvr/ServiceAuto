package Utilitati;

import Angajati.Angajat;
import Angajati.Asistent;
import Angajati.Director;
import Angajati.Mecanic;
import Masini.Autobuz;
import Masini.Camion;
import Masini.Masina;
import Masini.Standard;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ScriitorMasini {
    private static String BASE_PATH;
    private static ScriitorMasini single_instance = null;

    private ScriitorMasini(){
        BASE_PATH = "./src/Fisiere/";
    }

    public static ScriitorMasini getInstance(){
        if (single_instance == null)
            single_instance = new ScriitorMasini();
        return single_instance;
    }

    public void scrie(List<Masina> listaMasini) throws IOException {
        FileWriter fw;
        File file = new File(BASE_PATH + "masini_tmp.csv");
        if (file.exists()){
            fw = new FileWriter(file, true);
        }
        else{
            file.createNewFile();
            fw = new FileWriter(file, true);
        }
        fw.append(String.valueOf(listaMasini.size()));
        fw.append('\n');
        for (int i=0; i<listaMasini.size(); i++){
            Masina masinaCurenta = listaMasini.get(i);
            if (masinaCurenta instanceof Standard){
                fw.append("1,");
            }
            if (masinaCurenta instanceof Autobuz){
                fw.append("2,");
            }
            if (masinaCurenta instanceof Camion){
                fw.append("3,");
            }
            fw.append(String.valueOf(masinaCurenta.getNrkm()));
            fw.append(",");
            fw.append(String.valueOf(masinaCurenta.getAnFabricatie()));
            fw.append(",");
            fw.append(String.valueOf(masinaCurenta.getTimpReparatie()));
            fw.append(",");
            if (masinaCurenta.isDiesel()){
                fw.append("1,");
            }
            else{
                fw.append("0,");
            }
            if (masinaCurenta instanceof Standard){
                if ((((Standard) masinaCurenta).isManuala())){
                    fw.append("1");
                }
                else{
                    fw.append("0");
                }
            }
            if (masinaCurenta instanceof Autobuz){
                fw.append(String.valueOf(((Autobuz) masinaCurenta).getNumarLocuri()));
            }
            if (masinaCurenta instanceof Camion){
                fw.append(String.valueOf(((Camion) masinaCurenta).getTonaj()));
            }
            fw.append(",");
            fw.append(String.valueOf(masinaCurenta.getStatus()));
            fw.append("\n");
        }
        fw.flush();
        fw.close();

        File fisierVechi = new File(BASE_PATH + "masini.csv");
        fisierVechi.delete();
        File fisierNou = new File(BASE_PATH + "masini_tmp.csv");
        fisierNou.renameTo(fisierVechi);
    }
}
