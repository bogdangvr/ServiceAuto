package Utilitati;

import Angajati.Angajat;
import Angajati.Asistent;
import Angajati.Director;
import Angajati.Mecanic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

public class ScriitorAngajati {
    //scriitor pentru angajati care va pune datele intr-un fisier de tip CSV
    //fiecare linie ca corespunde unui angajat
    //vom crea un nou fisier angajat_tmp.csv in care vom scrie noile date
    //apoi vom sterge fisierul vechi angajat.csv
    //pentru ca in final sa redenumim fisierul angajat_tmp.csv in angajat.csv

    private static String BASE_PATH;
    private static ScriitorAngajati single_instance = null;

    private ScriitorAngajati(){
        BASE_PATH = "./src/Fisiere/";
    }

    public static ScriitorAngajati getInstance(){
        if (single_instance == null)
            single_instance = new ScriitorAngajati();
        return single_instance;
    }

    public void scrie(List<Angajat> listaAngajati) throws IOException {
        FileWriter fw;
        File file = new File(BASE_PATH + "angajati_tmp.csv");
        if (file.exists()){
            fw = new FileWriter(file, true);
        }
        else{
            file.createNewFile();
            fw = new FileWriter(file, true);
        }
        fw.append(String.valueOf(listaAngajati.size()));
        fw.append('\n');
        for (int i=0; i<listaAngajati.size(); i++){
            Angajat angajatCurent = listaAngajati.get(i);
            if (angajatCurent instanceof Director){
                fw.append("1,");
            }
            if (angajatCurent instanceof Mecanic){
                fw.append("2,");
            }
            if (angajatCurent instanceof Asistent){
                fw.append("3,");
            }
            fw.append(angajatCurent.getNume());
            fw.append(",");
            fw.append(angajatCurent.getPrenume());
            fw.append(",");
            fw.append(String.valueOf(angajatCurent.getDataNasterii().get(Calendar.DAY_OF_MONTH)));
            fw.append("-");
            fw.append(String.valueOf(angajatCurent.getDataNasterii().get(Calendar.MONTH)));
            fw.append("-");
            fw.append(String.valueOf(angajatCurent.getDataNasterii().get(Calendar.YEAR)));
            fw.append(",");
            fw.append(String.valueOf(angajatCurent.getDataAngajarii().get(Calendar.DAY_OF_MONTH)));
            fw.append("-");
            fw.append(String.valueOf(angajatCurent.getDataAngajarii().get(Calendar.MONTH)));
            fw.append("-");
            fw.append(String.valueOf(angajatCurent.getDataAngajarii().get(Calendar.YEAR)));
            fw.append(",");
            fw.append(String.valueOf(angajatCurent.getNrStandardInCoada()));
            fw.append(",");
            fw.append(String.valueOf(angajatCurent.getNrAutobuzeInCoada()));
            fw.append(",");
            fw.append(String.valueOf(angajatCurent.getNrCamioaneInCoada()));
            fw.append(",");
            fw.append(String.valueOf(angajatCurent.getNrMasiniReparate()));
            fw.append(",");
            fw.append(String.valueOf(angajatCurent.getNrAutobuzeNoi()));
            fw.append(",");
            fw.append(String.valueOf(angajatCurent.getSumaPolite()));
            fw.append(",");
            fw.append(String.valueOf(angajatCurent.getBacsis()));
            fw.append(",");
            fw.append(String.valueOf(angajatCurent.getSolicitareSpeciala()));
            fw.append(",");

            fw.append(String.valueOf(angajatCurent.getCoadaMasini().size()));
            if (angajatCurent.getCoadaMasini().size()!=0) {
                fw.append(",");
                int dim = angajatCurent.getCoadaMasini().size();
                for (int j = 0; j < dim; j++) {
                    fw.append(String.valueOf( angajatCurent.getCoadaMasini().poll() ) );
                    if (j!=dim-1){
                        fw.append(",");
                    }
                }
            }
            fw.append("\n");
        }
        fw.flush();
        fw.close();

        File fisierVechi = new File(BASE_PATH + "angajati.csv");
        fisierVechi.delete();
        File fisierNou = new File(BASE_PATH + "angajati_tmp.csv");
        fisierNou.renameTo(fisierVechi);
    }
}
