package Main;

import Angajati.Angajat;
import Angajati.Asistent;
import Angajati.Director;
import Angajati.Mecanic;
import Masini.Autobuz;
import Masini.Camion;
import Masini.Masina;
import Masini.Standard;
import Serviciu.Serviciu;
import Utilitati.Cititor;
import Utilitati.ScriitorAngajati;
import Utilitati.ScriitorMasini;

import java.io.IOException;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {

        Serviciu serviciulMeu = new Serviciu();

        Cititor cititor = Cititor.getInstance();

        Scanner scanner = new Scanner(System.in);

        String[][] continutFisierMasini = cititor.citeste("masini.csv");
        String[][] continutFisierAngajati = cititor.citeste("angajati.csv");
        List<Masina> listaMasini = new ArrayList<>();
        Queue<Masina> coadaMasini = new LinkedList<>();
        List<Angajat> listaAngajati = new ArrayList<>();

        int nrMasini = Integer.parseInt(continutFisierMasini[0][0]);
        if (nrMasini != 0){
            //stocarea in memorie a masinilor
            for (int i=1; i<=nrMasini; i++){
                int tipMasina = Integer.parseInt(continutFisierMasini[i][0]); //1 pentru Standard, 2 Autobuz, 3 Camion
                int nrkm = Integer.parseInt(continutFisierMasini[i][1]);
                int anFabricatie = Integer.parseInt(continutFisierMasini[i][2]);
                int timpReparatie = Integer.parseInt(continutFisierMasini[i][3]);
                int valDiesel = Integer.parseInt(continutFisierMasini[i][4]); //1 daca este diesel, 0 altfel
                boolean isDiesel=false;
                if (valDiesel==1){
                    isDiesel = true;
                }
                int status = Integer.parseInt(continutFisierMasini[i][6]); //-1 pentru nu se afla in coada, 1 altfel



                if (tipMasina == 1){
                    boolean isManuala = false;
                    if (Integer.parseInt(continutFisierMasini[i][5]) == 1){
                        isManuala=true;
                    }
                    Standard standard = new Standard(nrkm,anFabricatie,isDiesel,isManuala);
                    standard.setTimpReparatie(timpReparatie);
                    standard.setStatus(status);
                    listaMasini.add(standard);
                }
                if (tipMasina == 2){
                    int nrLocuri = Integer.parseInt(continutFisierMasini[i][5]);
                    Autobuz autobuz = new Autobuz(nrkm,anFabricatie,isDiesel,nrLocuri);
                    autobuz.setTimpReparatie(timpReparatie);
                    autobuz.setStatus(status);
                    listaMasini.add(autobuz);
                }
                if (tipMasina == 3){
                    int tonaj = Integer.parseInt(continutFisierMasini[i][5]);
                    Camion camion = new Camion(nrkm,anFabricatie,isDiesel,tonaj);
                    camion.setTimpReparatie(timpReparatie);
                    camion.setStatus(status);
                    listaMasini.add(camion);
                }
            }
        }

        int nrAngajati = Integer.parseInt(continutFisierAngajati[0][0]);
        if (nrAngajati==0){
            System.out.println("In momentul de fata nu exista angajati ai serviceului, deci este inchis.");
            while (true){
                int tasta = 0;
                while (true){
                    System.out.println("Apasati 1 pentru a adauga angajat sau 2 pentru exit.");
                    tasta = scanner.nextInt();
                    if (tasta != 1 && tasta !=2){
                        System.out.println("Tasta introdusa trebuie sa fie 1 sau 2!");
                    }
                    else{
                        break;
                    }
                }
                if (tasta==1){
                    serviciulMeu.adaugareAngajat(listaAngajati);
                    break;
                }
                else{
                    break;
                }
            }
        }


        //stocarea in memorie a angajatilor
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
            int standardInCoada = Integer.parseInt(continutFisierAngajati[i][5]);
            int autobuzeInCoada = Integer.parseInt(continutFisierAngajati[i][6]);
            int camioaneInCoada = Integer.parseInt(continutFisierAngajati[i][7]);
            int masiniReparate = Integer.parseInt(continutFisierAngajati[i][8]);
            int autobuzeNoi = Integer.parseInt(continutFisierAngajati[i][9]);
            int sumapolite = Integer.parseInt(continutFisierAngajati[i][10]);
            int bacsis = Integer.parseInt(continutFisierAngajati[i][11]);

            if (tipAngajat == 1){
                Director director = new Director(nume,prenume,dataNasterii,dataAngajarii);
                director.setNrStandardInCoada(standardInCoada);
                director.setNrAutobuzeInCoada(autobuzeInCoada);
                director.setNrCamioaneInCoada(camioaneInCoada);
                director.setNrMasiniReparate(masiniReparate);
                director.setNrAutobuzeNoi(autobuzeNoi);
                director.setSumaPolite(sumapolite);
                director.setBacsis(bacsis);
                listaAngajati.add(director);
                int nrMasiniInCoada = Integer.parseInt(continutFisierAngajati[i][12]);
                if (nrMasiniInCoada!=0){
                    for (int j=0; j<nrMasiniInCoada; j++){
                        director.getCoadaMasini().add(Integer.parseInt(continutFisierAngajati[i][12+j+1]));
                    }
                }
            }
            if (tipAngajat == 2){
                Mecanic mecanic = new Mecanic(nume,prenume,dataNasterii,dataAngajarii);
                mecanic.setNrStandardInCoada(standardInCoada);
                mecanic.setNrAutobuzeInCoada(autobuzeInCoada);
                mecanic.setNrCamioaneInCoada(camioaneInCoada);
                mecanic.setNrMasiniReparate(masiniReparate);
                mecanic.setNrAutobuzeNoi(autobuzeNoi);
                mecanic.setSumaPolite(sumapolite);
                mecanic.setBacsis(bacsis);
                listaAngajati.add(mecanic);
                int nrMasiniInCoada = Integer.parseInt(continutFisierAngajati[i][12]);
                if (nrMasiniInCoada!=0){
                    for (int j=0; j<nrMasiniInCoada; j++){
                        mecanic.getCoadaMasini().add(Integer.parseInt(continutFisierAngajati[i][12+j+1]));
                    }
                }
            }
            if (tipAngajat == 3){
                Asistent asistent = new Asistent(nume,prenume,dataNasterii,dataAngajarii);
                asistent.setNrStandardInCoada(standardInCoada);
                asistent.setNrAutobuzeInCoada(autobuzeInCoada);
                asistent.setNrCamioaneInCoada(camioaneInCoada);
                asistent.setNrMasiniReparate(masiniReparate);
                asistent.setNrAutobuzeNoi(autobuzeNoi);
                asistent.setSumaPolite(sumapolite);
                asistent.setBacsis(bacsis);
                listaAngajati.add(asistent);
                int nrMasiniInCoada = Integer.parseInt(continutFisierAngajati[i][12]);
                if (nrMasiniInCoada!=0){
                    for (int j=0; j<nrMasiniInCoada; j++){
                        asistent.getCoadaMasini().add(Integer.parseInt(continutFisierAngajati[i][12+j+1]));
                    }
                }
            }
        }

        // meniul principal
        while (true){
            System.out.println("Alegeti o tasta:");
            System.out.println("1. Operatii angajat (Interogari angajat, Updateaza timpul);");
            System.out.println("2. Operatii client;");
            System.out.println("3. Exit.");
            int tastaInitiala = scanner.nextInt();
            while (tastaInitiala<1 || tastaInitiala>3){
                System.out.println("Trebuie sa alegeti fie 1,2 sau 3!");
                System.out.println("Alegeti o tasta:");
                System.out.println("1. Operatii angajat (Interogari angajat, Updateaza timpul, ");
                System.out.println("2. Operatii client;");
                System.out.println("3. Exit.");
                tastaInitiala = scanner.nextInt();
            }

            if (tastaInitiala==1){
                while (true){
                    System.out.println("Alegeti o tasta:");
                    System.out.println("1. Afisare angajati;");
                    System.out.println("2. Adaugare angajat;");
                    System.out.println("3. Stergere angajat;");
                    System.out.println("4. Editare angajat;");
                    System.out.println("5. Calculare salariu angajat;");
                    System.out.println("6. Exit.");
                    int tasta = scanner.nextInt();
                    while (tasta<1 || tasta >6){
                        System.out.println("Nu ati ales o optiune valida!");
                        System.out.println("Alegeti o tasta:");
                        System.out.println("1. Afisare angajati;");
                        System.out.println("2. Adaugare angajat;");
                        System.out.println("3. Stergere angajat;");
                        System.out.println("4. Editare angajat;");
                        System.out.println("5. Calculare salariu angajat;");
                        System.out.println("6. Exit.");
                        tasta = scanner.nextInt();
                    }
                    if (tasta == 1){
                        serviciulMeu.afisareAngajati(listaAngajati);
                        break;
                    }
                    if (tasta == 2){
                        serviciulMeu.adaugareAngajat(listaAngajati);
                        break;
                    }
                    if (tasta == 3){
                        System.out.println("Introduceti id-ul angajatului ce urmeaza sa fie sters:");
                        int id = scanner.nextInt();
                        int indice = -1;
                        for (int i=0; i<listaAngajati.size(); i++){
                            if (listaAngajati.get(i).getId()==id){
                                indice = i;
                            }
                        }
                        while (indice == -1){
                            System.out.println("Id-ul introdus nu corespunde niciunui angajat! Introduceti alt id:");
                            id = scanner.nextInt();
                            for (int i=0; i<listaAngajati.size(); i++){
                                if (listaAngajati.get(i).getId()==id){
                                    indice = i;
                                }
                            }
                        }
                        serviciulMeu.stergereAngajat(listaAngajati, indice);
                        break;
                    }
                    if (tasta == 4){
                        serviciulMeu.editareAngajat(listaAngajati);
                        break;
                    }
                    if (tasta == 5){
                        System.out.println("Introduceti id-ul angajatului caruia urmeaza sa i se calculeze salariul:");
                        int id = scanner.nextInt();
                        int indice = -1;
                        for (int i=0; i<listaAngajati.size(); i++){
                            if (listaAngajati.get(i).getId()==id){
                                indice = i;
                            }
                        }
                        while (indice == -1){
                            System.out.println("Id-ul introdus nu corespunde niciunui angajat! Introduceti alt id:");
                            id = scanner.nextInt();
                            for (int i=0; i<listaAngajati.size(); i++){
                                if (listaAngajati.get(i).getId()==id){
                                    indice = i;
                                }
                            }
                        }
                        serviciulMeu.afisareSalariu(listaAngajati,indice);
                        break;
                    }
                    if (tasta == 6){
                        break;
                    }
                }
            }
            if (tastaInitiala==2){
                System.out.println("Beneficiati de discount? 1 pentru Da, 2 pentru Nu: ");
                int discount = scanner.nextInt();
                while (discount!=1 && discount !=2){
                    System.out.println("Valoarea introdusa trebuie sa fie 1 pentru Da, 2 pentru Nu: ");
                    discount = scanner.nextInt();
                }


                System.out.println("Introduceti datele masinii dumneavoastra:");

                System.out.println("Introduceti tipul: 1.Standard; 2.Autobuz; 3.Camion. ");
                int tip = scanner.nextInt();
                while (tip < 1 || tip > 3){
                    System.out.println("Valoarea introdusa este incorecta. Introduceti tipul: 1.Standard; 2.Autobuz; 3.Camion. ");
                    tip = scanner.nextInt();
                }
                System.out.println("Introduceti numarul de kilometri: ");
                int nrkm = scanner.nextInt();
                while (nrkm<0){
                    System.out.println("Numarul de kilometri nu poate fi negativ. Introduceti alta valoare: ");
                    nrkm = scanner.nextInt();
                }

                System.out.println("Introduceti anul fabricatiei: ");
                int anFabricatie = scanner.nextInt();
                Calendar ziCurenta = new GregorianCalendar();
                while (anFabricatie<0 || anFabricatie > ziCurenta.get(Calendar.YEAR)){
                    System.out.println("Anul introdus nu poate fi negativ sau in viitor. Introduceti alta valoare: ");
                    anFabricatie = scanner.nextInt();
                }

                System.out.println("Introduceti tipul motorizarii: 1 pentru Diesel, 2 pentru non Diesel: ");
                boolean diesel = false;
                int cifra = scanner.nextInt();
                while (cifra != 1 && cifra != 2){
                    System.out.println("Numarul introdus trebuie sa fie 1 sau 2. Introduceti alta valoare: ");
                    cifra = scanner.nextInt();
                }
                if (cifra == 1){
                    diesel = true;
                }

                if (tip == 1){
                    System.out.println("Introduceti tipul transmisiei: 1 pentru manuala, 2 pentru automata: ");
                    boolean manuala = false;
                    cifra = scanner.nextInt();
                    while (cifra != 1 && cifra != 2){
                        System.out.println("Numarul introdus trebuie sa fie 1 sau 2. Introduceti alta valoare: ");
                        cifra = scanner.nextInt();
                    }
                    if (cifra == 1){
                        manuala = true;
                    }
                    Standard standard = new Standard(nrkm,anFabricatie,diesel,manuala);

                    listaMasini.add(standard);

                    //generam random timpul reparatiei
                    int timp = (int)(Math.random() * 10);
                    standard.setTimpReparatie(timp);

                    int polita = 0;
                    if (discount == 1){
                        polita = standard.politaDiscout();
                    }
                    else{
                        polita = standard.polita();
                    }
                    System.out.println("Costul politei este:" + polita);

                    serviciulMeu.programareStandard(listaAngajati,coadaMasini,standard,scanner);
                }

                if (tip == 2){
                    System.out.println("Introduceti numarul de locuri: ");
                    int nrLocuri = scanner.nextInt();
                    while (nrLocuri < 0){
                        System.out.println("Numarul introdus trebuie sa fie pozitiv. Introduceti alta valoare: ");
                        nrLocuri = scanner.nextInt();
                    }
                    Autobuz autobuz = new Autobuz(nrkm,anFabricatie,diesel,nrLocuri);
                    listaMasini.add(autobuz);
                    int polita = 0;
                    if (discount == 1){
                        polita = autobuz.politaDiscout();
                    }
                    else{
                        polita = autobuz.polita();
                    }
                    System.out.println("Costul politei este:" + polita);

                    serviciulMeu.programareAutobuz(listaAngajati,coadaMasini,autobuz,scanner);
                }

                if (tip == 3){
                    System.out.println("Introduceti tonajul (in kg intregi): ");
                    int tonaj = scanner.nextInt();
                    while (tonaj < 0){
                        System.out.println("Tonajul introdus trebuie sa fie pozitiv. Introduceti alta valoare: ");
                        tonaj = scanner.nextInt();
                    }
                    Camion camion = new Camion(nrkm,anFabricatie,diesel,tonaj);
                    listaMasini.add(camion);
                    int polita = 0;
                    if (discount == 1){
                        polita = camion.politaDiscout();
                    }
                    else{
                        polita = camion.polita();
                    }
                    System.out.println("Costul politei este:" + polita);

                    serviciulMeu.programareCamion(listaAngajati,coadaMasini,camion,scanner);
                }
            }
            if (tastaInitiala==3){
                break;
            }
        }

        ScriitorAngajati scriitorAngajati = ScriitorAngajati.getInstance();
        scriitorAngajati.scrie(listaAngajati);

        ScriitorMasini scriitorMasini = ScriitorMasini.getInstance();
        scriitorMasini.scrie(listaMasini);

    }
}
