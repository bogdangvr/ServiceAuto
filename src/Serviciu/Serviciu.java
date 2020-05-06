package Serviciu;

import Angajati.Angajat;
import Angajati.Asistent;
import Angajati.Director;
import Angajati.Mecanic;
import Masini.Autobuz;
import Masini.Camion;
import Masini.Masina;
import Masini.Standard;

import java.util.*;

public class Serviciu {
    //clasa ce contine solicitarile facute de un utilizator

    public void afisareAngajati(List<Angajat> listaAngajati){
        //parcurgem lista de angajati si il afisam pe fiecare
        for (int i=0; i<listaAngajati.size(); i++){
            System.out.println(listaAngajati.get(i));
        }
    }

    public void adaugareAngajat(List<Angajat> listaAngajati){
        //setam ziua curenta
        Calendar ziCurenta = new GregorianCalendar();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduceti functia angajatului (1 pentru Director, 2 pentru Mecanic, 3 pentru Asistent: ");
        int functie = scanner.nextInt();
        scanner.nextLine();
        //verificam ca valoarea introdusa sa fie 1,2 sau 3
        while (true){
            if (functie == 1 || functie == 3 || functie == 3){
                break;
            }
            else{
                System.out.println("Valoarea introdusa trebuie sa fie 1, 2 sau 3! Introduceti din nou:");
                functie = scanner.nextInt();
                scanner.nextLine();
            }
        }

        System.out.println("Introduceti numele angajatului: ");
        String nume = scanner.nextLine();
        while (true){
            if (nume.length()<=30 && nume.length()>0){
                break;
            }
            else{
                System.out.println("Numele trebuie sa fie nevid si lungumea sa cel mult 30! Introduceti din nou:");
            }
        }


        System.out.println("Introduceti prenumele angajatului: ");
        String prenume = scanner.nextLine();
        while (true){
            if (prenume.length()<=30 && prenume.length()>0){
                break;
            }
            else{
                System.out.println("Preumele trebuie sa fie nevid si lungumea sa cel mult 30! Introduceti din nou:");
            }
        }


        System.out.println("Introduceti anul nasterii angajatului: ");
        int anNastere = scanner.nextInt();
        System.out.println("Introduceti luna nasterii angajatului (indexarea se face de la 0): ");
        int lunaNastere = scanner.nextInt();
        System.out.println("Introduceti ziua nasterii angajatului: ");
        int ziuaNastere = scanner.nextInt();
        //testam cazurile in care angajatul introdus ar avea 18 ani, iar pentru restul spunem sa introduca alta data
        while(true){

            if (anNastere < ziCurenta.get(Calendar.YEAR) - 18 ||
                (anNastere == ziCurenta.get(Calendar.YEAR) - 18 &&
                lunaNastere <= ziCurenta.get(Calendar.MONTH) &&
                ziuaNastere <= ziCurenta.get(Calendar.DAY_OF_MONTH))){
                break;
            }
            else{
                System.out.println("Angajatul trebuie sa aiba minim 18 ani!");
                System.out.println("Introduceti anul nasterii angajatului: ");
                anNastere = scanner.nextInt();
                System.out.println("Introduceti luna nasterii angajatului (indexarea se face de la 0): ");
                lunaNastere = scanner.nextInt();
                System.out.println("Introduceti ziua nasterii angajatului: ");
                ziuaNastere = scanner.nextInt();
            }
        }
        Calendar dataNasterii = new GregorianCalendar(anNastere,lunaNastere,ziuaNastere);

        System.out.println("Introduceti anul angajarii angajatului: ");
        int anAngajare = scanner.nextInt();
        System.out.println("Introduceti luna angajarii angajatului (indexarea se face de la 0): ");
        int lunaAngajare = scanner.nextInt();
        System.out.println("Introduceti ziua angajarii angajatului: ");
        int ziuaAngajare = scanner.nextInt();
        while(true){
            //verificam situatiile in care este introdusa o data din viitor
            if (anAngajare > ziCurenta.get(Calendar.YEAR) ||
               (anAngajare == ziCurenta.get(Calendar.YEAR) &&
                lunaAngajare > ziCurenta.get(Calendar.MONTH)) ||
               (anAngajare == ziCurenta.get(Calendar.YEAR) - 18 &&
                lunaAngajare == ziCurenta.get(Calendar.MONTH) &&
                ziuaAngajare > ziCurenta.get(Calendar.DAY_OF_MONTH))){
                System.out.println("Data angajarii nu poate fi in viitor!");
                System.out.println("Introduceti anul angajarii angajatului: ");
                anAngajare = scanner.nextInt();
                System.out.println("Introduceti luna angajarii angajatului (indexarea se face de la 0): ");
                lunaAngajare = scanner.nextInt();
                System.out.println("Introduceti ziua angajarii angajatului: ");
                ziuaAngajare = scanner.nextInt();
            }
            else{
                break;
            }
        }
        Calendar dataAngajarii = new GregorianCalendar(anAngajare,lunaAngajare,ziuaAngajare);
        //in functie de tip, cream un obiect corespunzator
        if (functie == 1){
            Director director = new Director(nume,prenume,dataNasterii,dataAngajarii);
            listaAngajati.add(director);
        }
        if (functie == 2){
            Mecanic mecanic = new Mecanic(nume,prenume,dataNasterii,dataAngajarii);
            listaAngajati.add(mecanic);
        }
        if (functie == 3){
            Asistent asistent = new Asistent(nume,prenume,dataNasterii,dataAngajarii);
            listaAngajati.add(asistent);
        }
    }

    public void stergereAngajat(List<Angajat> listaAngajati, int indiceAngajatDeSters){
        listaAngajati.remove(indiceAngajatDeSters);
    }

    public void afisareSalariu(List<Angajat> listaAngajati, int indice){
        System.out.println("Salariul angajatului cu id-ul " + listaAngajati.get(indice).getId() + " este: " + listaAngajati.get(indice).calculSalariu());
    }

    public void editareAngajat(List<Angajat> listaAngajati){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti ID-ul angajatului ce va fi editat (introduceti 0 pentru inapoi):");
        int id = scanner.nextInt();
        scanner.nextLine();
        if (id == 0){
            return;
        }

        int indiceAngajatDeEditat = -1;
        for (int i=0; i<listaAngajati.size(); i++){
            if (listaAngajati.get(i).getId()==id){
                indiceAngajatDeEditat=i;
                break;
            }
        }
        while (indiceAngajatDeEditat == -1){
            System.out.println("Id-ul introdus nu corespunde niciunui angajat. Introduceti alt id:");
            id = scanner.nextInt();
            scanner.nextLine();
            for (int i=0; i<listaAngajati.size(); i++){
                if (listaAngajati.get(i).getId()==id){
                    indiceAngajatDeEditat=i;
                    break;
                }
            }
        }

        Angajat angajatDeEditat = listaAngajati.get(indiceAngajatDeEditat);

        System.out.println("Introduceti ce camp doriti sa editati:\n");
        System.out.println("1.Functie;\n");
        System.out.println("2.Nume;\n");
        System.out.println("3.Prenume;\n");
        System.out.println("4.Data nasterii;\n");
        System.out.println("5.Data angajarii;\n");
        System.out.println("6.Inapoi.\n");
        int numarCamp = scanner.nextInt();
        scanner.nextLine();

        if (numarCamp == 1){
            System.out.println("Introduceti noua functie:\n");
            int nouaFunctie = scanner.nextInt();
            //crearea unui noi obiect si stergerea celui vechi din lista
            if (nouaFunctie==1){
                Director director = new Director(angajatDeEditat.getNume(),angajatDeEditat.getPrenume(),
                                                 angajatDeEditat.getDataNasterii(),angajatDeEditat.getDataAngajarii());
                listaAngajati.add(director);
            }
            if (nouaFunctie==2){
                Mecanic mecanic = new Mecanic(angajatDeEditat.getNume(),angajatDeEditat.getPrenume(),
                        angajatDeEditat.getDataNasterii(),angajatDeEditat.getDataAngajarii());
                listaAngajati.add(mecanic);
            }
            if (nouaFunctie==3){
                Asistent asistent = new Asistent(angajatDeEditat.getNume(),angajatDeEditat.getPrenume(),
                        angajatDeEditat.getDataNasterii(),angajatDeEditat.getDataAngajarii());
                listaAngajati.add(asistent);
            }
            listaAngajati.remove(indiceAngajatDeEditat);
        }

        //pentru urmatoarele campuri testam exceptii ca la adaugareAngajat
        if (numarCamp == 2){
            System.out.println("Introduceti numele angajatului: ");
            String nume = scanner.nextLine();
            while (true){
                if (nume.length()<=30 && nume.length()>0){
                    break;
                }
                else{
                    System.out.println("Numele trebuie sa fie nevid si lungumea sa cel mult 30! Introduceti din nou:");
                }
            }
            angajatDeEditat.setNume(nume);
        }
        if (numarCamp == 3){
            System.out.println("Introduceti prenumele angajatului: ");
            String prenume = scanner.nextLine();
            while (true){
                if (prenume.length()<=30 && prenume.length()>0){
                    break;
                }
                else{
                    System.out.println("Preumele trebuie sa fie nevid si lungumea sa cel mult 30! Introduceti din nou:");
                }
            }
            angajatDeEditat.setPrenume(prenume);
        }
        if (numarCamp == 4){
            Calendar ziCurenta = new GregorianCalendar();
            System.out.println("Introduceti anul nasterii angajatului: ");
            int anNastere = scanner.nextInt();
            System.out.println("Introduceti luna nasterii angajatului (indexarea se face de la 0): ");
            int lunaNastere = scanner.nextInt();
            System.out.println("Introduceti ziua nasterii angajatului: ");
            int ziuaNastere = scanner.nextInt();
            while(true){
                if (anNastere < ziCurenta.get(Calendar.YEAR) - 18 ||
                        (anNastere == ziCurenta.get(Calendar.YEAR) - 18 &&
                                lunaNastere <= ziCurenta.get(Calendar.MONTH) &&
                                ziuaNastere <= ziCurenta.get(Calendar.DAY_OF_MONTH))){
                    break;
                }
                else{
                    System.out.println("Angajatul trebuie sa aiba minim 18 ani!");
                    System.out.println("Introduceti anul nasterii angajatului: ");
                    anNastere = scanner.nextInt();
                    System.out.println("Introduceti luna nasterii angajatului (indexarea se face de la 0): ");
                    lunaNastere = scanner.nextInt();
                    System.out.println("Introduceti ziua nasterii angajatului: ");
                    ziuaNastere = scanner.nextInt();
                }
            }
            Calendar dataNasterii = new GregorianCalendar(anNastere,lunaNastere,ziuaNastere);
            angajatDeEditat.setDataNasterii(dataNasterii);
        }
        if (numarCamp == 5){
            Calendar ziCurenta = new GregorianCalendar();
            System.out.println("Introduceti anul angajarii angajatului: ");
            int anAngajare = scanner.nextInt();
            System.out.println("Introduceti luna angajarii angajatului (indexarea se face de la 0): ");
            int lunaAngajare = scanner.nextInt();
            System.out.println("Introduceti ziua angajarii angajatului: ");
            int ziuaAngajare = scanner.nextInt();
            while(true){
                if (anAngajare > ziCurenta.get(Calendar.YEAR) ||
                        (anAngajare == ziCurenta.get(Calendar.YEAR) &&
                                lunaAngajare > ziCurenta.get(Calendar.MONTH)) ||
                        (anAngajare == ziCurenta.get(Calendar.YEAR) - 18 &&
                                lunaAngajare == ziCurenta.get(Calendar.MONTH) &&
                                ziuaAngajare > ziCurenta.get(Calendar.DAY_OF_MONTH))){
                    System.out.println("Data angajarii nu poate fi in viitor!");
                    System.out.println("Introduceti anul angajarii angajatului: ");
                    anAngajare = scanner.nextInt();
                    System.out.println("Introduceti luna angajarii angajatului (indexarea se face de la 0): ");
                    lunaAngajare = scanner.nextInt();
                    System.out.println("Introduceti ziua angajarii angajatului: ");
                    ziuaAngajare = scanner.nextInt();
                }
                else{
                    break;
                }
            }
            Calendar dataAngajarii = new GregorianCalendar(anAngajare,lunaAngajare,ziuaAngajare);
            angajatDeEditat.setDataAngajarii(dataAngajarii);
        }
        if (numarCamp == 6){
            return;
        }
    }

    public void programareStandard(List<Angajat> listaAngajati, Queue<Masina> coadaMasini, Masina masina, Scanner scanner){

        // acum clientul va alege daca vrea un anumit angajat sa il ajute
        System.out.println("Introduceti 1 pentru a fi distribuit la primul angajat liber sau 2 pentru a introduce id-ul unui anumit angajat: ");
        int alegereAngajat = scanner.nextInt();
        while (alegereAngajat!=1 && alegereAngajat!=2){
            System.out.println("Trebuie sa introduceti 1 pentru a fi distribuit la primul angajat liber sau 2 pentru a introduce id-ul unui anumit angajat: ");
            alegereAngajat = scanner.nextInt();
        }
        // il vom atribui primului angajat care nu este aglomerat
        if (alegereAngajat==1){
            // initializam un indice negativ si il updatam cand gasim un angajat care poate lua masina curenta
            int indice=-1;
            for (int i=0; i<listaAngajati.size(); i++){
                if (listaAngajati.get(i).getNrStandardInCoada()<3){
                    indice=i;
                    listaAngajati.get(indice).getCoadaMasini().add(masina.getId());
                    listaAngajati.get(indice).setNrStandardInCoada(listaAngajati.get(indice).getNrStandardInCoada()+1);
                    break;
                }
            }
        }
        // altfel, va trebui sa introduca id-ul angajatului
        else{
            System.out.println("Introduceti id-ul angajatului care doriti sa va ajute: ");
            int id = scanner.nextInt();
            int indice = -1;
            for (int i=0; i<listaAngajati.size(); i++){
                if (id == listaAngajati.get(i).getId()){
                    indice = i;
                }
            }
            while (indice==-1){
                System.out.println("Id-ul introdus nu corespunde niciunui angajat. Introduceti alt id: ");
                id = scanner.nextInt();
                for (int i=0; i<listaAngajati.size(); i++){
                    if (id == listaAngajati.get(i).getId()){
                        indice = i;
                        // incrementam numarul de solicitari al acestui angajat
                        listaAngajati.get(indice).setSolicitareSpeciala(listaAngajati.get(indice).getSolicitareSpeciala()+1);
                    }
                }
            }
            // daca poate fi atribuit angajatului dorit
            if (listaAngajati.get(indice).getNrStandardInCoada() < 3){
                listaAngajati.get(indice).getCoadaMasini().add(masina.getId());
                listaAngajati.get(indice).setNrStandardInCoada(listaAngajati.get(indice).getNrStandardInCoada()+1);
            }
            // altfel il intrebam daca doreste sa fie repartizat altui angajat sau sa plece
            else{
                System.out.println("Angajatul selectat este prea ocupat. Alegeti 1 pentru redistribuire la alt angajat sau 2 pentru a pleca: ");
                int optiune = scanner.nextInt();
                while (optiune!=1 && optiune!=2){
                    System.out.println("Alegere incorecta. Alegeti 1 pentru redistribuire la alt angajat sau 2 pentru a pleca: ");
                    optiune = scanner.nextInt();
                }
                if (optiune == 1){
                    int idx = -1;
                    for (int i=0; i<listaAngajati.size(); i++){
                        if (listaAngajati.get(i).getNrStandardInCoada()<3){
                            listaAngajati.get(i).getCoadaMasini().add(masina.getId());
                            listaAngajati.get(i).setNrStandardInCoada(listaAngajati.get(indice).getNrStandardInCoada()+1);
                            idx = i;
                            break;
                        }
                    }
                    //in cazul in care toti angajatii sunt ocupati, il intrebam daca doreste sa fie adaugat in coada de asteptare
                    if (idx == -1){
                        System.out.println("Toti angajatii sunt ocupati. Doriti sa ramaneti in coada de asteptare? 1 pentru Da, 2 pentru Nu.");
                        System.out.println("Coada de asteptare are acum masinile: " + coadaMasini);
                        int raspuns = scanner.nextInt();
                        while (raspuns!=1 && raspuns!=2){
                            System.out.println("Alegere incorecta. Alegeti 1 pentru a fi adaugat in coada sau 2 pentru a pleca: ");
                            raspuns = scanner.nextInt();
                        }
                        // il adaugam in coada de asteptare
                        if (raspuns == 1){
                            coadaMasini.add(masina);
                        }
                    }
                }
            }
        }
    }

    public void programareAutobuz(List<Angajat> listaAngajati, Queue<Masina> coadaMasini, Masina masina, Scanner scanner){

        // acum clientul va alege daca vrea un anumit angajat sa il ajute
        System.out.println("Introduceti 1 pentru a fi distribuit la primul angajat liber sau 2 pentru a introduce id-ul unui anumit angajat: ");
        int alegereAngajat = scanner.nextInt();
        while (alegereAngajat!=1 && alegereAngajat!=2){
            System.out.println("Trebuie sa introduceti 1 pentru a fi distribuit la primul angajat liber sau 2 pentru a introduce id-ul unui anumit angajat: ");
            alegereAngajat = scanner.nextInt();
        }
        // il vom atribui primului angajat care nu este aglomerat
        if (alegereAngajat==1){
            // initializam un indice negativ si il updatam cand gasim un angajat care poate lua masina curenta
            int indice=-1;
            for (int i=0; i<listaAngajati.size(); i++){
                if (listaAngajati.get(i).getNrAutobuzeInCoada()==0){
                    indice=i;
                    listaAngajati.get(indice).getCoadaMasini().add(masina.getId());
                    listaAngajati.get(indice).setNrAutobuzeInCoada(1);
                    break;
                }
            }
        }
        // altfel, va trebui sa introduca id-ul angajatului
        else{
            System.out.println("Introduceti id-ul angajatului care doriti sa va ajute: ");
            int id = scanner.nextInt();
            int indice = -1;
            for (int i=0; i<listaAngajati.size(); i++){
                if (id == listaAngajati.get(i).getId()){
                    indice = i;
                }
            }
            while (indice==-1){
                System.out.println("Id-ul introdus nu corespunde niciunui angajat. Introduceti alt id: ");
                id = scanner.nextInt();
                for (int i=0; i<listaAngajati.size(); i++){
                    if (id == listaAngajati.get(i).getId()){
                        indice = i;
                        // incrementam numarul de solicitari al acestui angajat
                        listaAngajati.get(indice).setSolicitareSpeciala(listaAngajati.get(indice).getSolicitareSpeciala()+1);
                    }
                }
            }
            // daca poate fi atribuit angajatului dorit
            if (listaAngajati.get(indice).getNrAutobuzeInCoada() == 0){
                listaAngajati.get(indice).getCoadaMasini().add(masina.getId());
                listaAngajati.get(indice).setNrAutobuzeInCoada(1);
            }
            // altfel il intrebam daca doreste sa fie repartizat altui angajat sau sa plece
            else{
                System.out.println("Angajatul selectat este prea ocupat. Alegeti 1 pentru redistribuire la alt angajat sau 2 pentru a pleca: ");
                int optiune = scanner.nextInt();
                while (optiune!=1 && optiune!=2){
                    System.out.println("Alegere incorecta. Alegeti 1 pentru redistribuire la alt angajat sau 2 pentru a pleca: ");
                    optiune = scanner.nextInt();
                }
                if (optiune == 1){
                    int idx = -1;
                    for (int i=0; i<listaAngajati.size(); i++){
                        if (listaAngajati.get(i).getNrAutobuzeInCoada() == 0){
                            listaAngajati.get(i).getCoadaMasini().add(masina.getId());
                            listaAngajati.get(i).setNrAutobuzeInCoada(1);
                            idx = i;
                            break;
                        }
                    }
                    if (idx == -1){
                        System.out.println("Toti angajatii sunt ocupati. Doriti sa ramaneti in coada de asteptare? 1 pentru Da, 2 pentru Nu.");
                        System.out.println("Coada de asteptare are acum masinile: " + coadaMasini);
                        int raspuns = scanner.nextInt();
                        while (raspuns!=1 && raspuns!=2){
                            System.out.println("Alegere incorecta. Alegeti 1 pentru a fi adaugat in coada sau 2 pentru a pleca: ");
                            raspuns = scanner.nextInt();
                        }
                        // il adaugam in coada de asteptare
                        if (raspuns == 1){
                            coadaMasini.add(masina);
                        }
                    }
                }
            }
        }
    }

    public void programareCamion(List<Angajat> listaAngajati, Queue<Masina> coadaMasini, Masina masina, Scanner scanner){

        // acum clientul va alege daca vrea un anumit angajat sa il ajute
        System.out.println("Introduceti 1 pentru a fi distribuit la primul angajat liber sau 2 pentru a introduce id-ul unui anumit angajat: ");
        int alegereAngajat = scanner.nextInt();
        while (alegereAngajat!=1 && alegereAngajat!=2){
            System.out.println("Trebuie sa introduceti 1 pentru a fi distribuit la primul angajat liber sau 2 pentru a introduce id-ul unui anumit angajat: ");
            alegereAngajat = scanner.nextInt();
        }
        // il vom atribui primului angajat care nu este aglomerat
        if (alegereAngajat==1){
            // initializam un indice negativ si il updatam cand gasim un angajat care poate lua masina curenta
            int indice=-1;
            for (int i=0; i<listaAngajati.size(); i++){
                if (listaAngajati.get(i).getNrCamioaneInCoada()==0){
                    indice=i;
                    listaAngajati.get(indice).getCoadaMasini().add(masina.getId());
                    listaAngajati.get(indice).setNrCamioaneInCoada(1);
                    break;
                }
            }
        }
        // altfel, va trebui sa introduca id-ul angajatului
        else{
            System.out.println("Introduceti id-ul angajatului care doriti sa va ajute: ");
            int id = scanner.nextInt();
            int indice = -1;
            for (int i=0; i<listaAngajati.size(); i++){
                if (id == listaAngajati.get(i).getId()){
                    indice = i;
                }
            }
            while (indice==-1){
                System.out.println("Id-ul introdus nu corespunde niciunui angajat. Introduceti alt id: ");
                id = scanner.nextInt();
                for (int i=0; i<listaAngajati.size(); i++){
                    if (id == listaAngajati.get(i).getId()){
                        indice = i;
                        // incrementam numarul de solicitari al acestui angajat
                        listaAngajati.get(indice).setSolicitareSpeciala(listaAngajati.get(indice).getSolicitareSpeciala()+1);
                    }
                }
            }
            // daca poate fi atribuit angajatului dorit
            if (listaAngajati.get(indice).getNrCamioaneInCoada() == 0){
                listaAngajati.get(indice).getCoadaMasini().add(masina.getId());
                listaAngajati.get(indice).setNrCamioaneInCoada(1);
            }
            // altfel il intrebam daca doreste sa fie repartizat altui angajat sau sa plece
            else{
                System.out.println("Angajatul selectat este prea ocupat. Alegeti 1 pentru redistribuire la alt angajat sau 2 pentru a pleca: ");
                int optiune = scanner.nextInt();
                while (optiune!=1 && optiune!=2){
                    System.out.println("Alegere incorecta. Alegeti 1 pentru redistribuire la alt angajat sau 2 pentru a pleca: ");
                    optiune = scanner.nextInt();
                }
                if (optiune == 1){
                    int idx = -1;
                    for (int i=0; i<listaAngajati.size(); i++){
                        if (listaAngajati.get(i).getNrCamioaneInCoada() == 0){
                            listaAngajati.get(i).getCoadaMasini().add(masina.getId());
                            listaAngajati.get(i).setNrCamioaneInCoada(1);
                            idx = i;
                            break;
                        }
                    }
                    if (idx == -1){
                        System.out.println("Toti angajatii sunt ocupati. Doriti sa ramaneti in coada de asteptare? 1 pentru Da, 2 pentru Nu.");
                        System.out.println("Coada de asteptare are acum masinile: " + coadaMasini);
                        int raspuns = scanner.nextInt();
                        while (raspuns!=1 && raspuns!=2){
                            System.out.println("Alegere incorecta. Alegeti 1 pentru a fi adaugat in coada sau 2 pentru a pleca: ");
                            raspuns = scanner.nextInt();
                        }
                        // il adaugam in coada de asteptare
                        if (raspuns == 1){
                            coadaMasini.add(masina);
                        }
                    }
                }
            }
        }
    }

    public void trecereaTimpului(List<Angajat> listaAngajati, List<Masina> listaMasini, Queue<Masina> coadaMasini){
        // vom lua prima masina din coada de reparare al fiecarui angajat si ii reducem timpul cu o unitate
        // daca ajunge la 0, o eliminam din coada fiecarui angajat si daca exista masini in coada de asteptare, o
        // vom lua pe prima si o atribuim angajatului curent
        for (int i=0; i<listaAngajati.size(); i++){

            Angajat angajatCurent = listaAngajati.get(i);
            // daca angajatul curent are masini in coada de reparare
            if (!angajatCurent.getCoadaMasini().isEmpty()){

                int idMasinaCurenta = angajatCurent.getCoadaMasini().peek();
                Masina masinaCurenta;

                for (int j=0; j<=listaMasini.size(); j++){

                    if (listaMasini.get(j).getId()==idMasinaCurenta){

                        masinaCurenta = listaMasini.get(j);
                        // daca mai are o singura unitate ramasa, o eliminam din coada si actualizam statusurile angajatului
                        if (masinaCurenta.getTimpReparatie() == 1){

                            masinaCurenta.setTimpReparatie(0);
                            angajatCurent.getCoadaMasini().poll();
                            if (masinaCurenta instanceof Standard){
                                angajatCurent.setNrStandardInCoada(angajatCurent.getNrStandardInCoada()-1);
                            }
                            if (masinaCurenta instanceof Autobuz){
                                angajatCurent.setNrAutobuzeInCoada(0);
                            }
                            if (masinaCurenta instanceof Camion){
                                angajatCurent.setNrCamioaneInCoada(0);
                            }
                            angajatCurent.setSumaPolite(angajatCurent.getSumaPolite()+masinaCurenta.polita());
                            Calendar ziCurenta = new GregorianCalendar();
                            if (masinaCurenta instanceof Autobuz && ziCurenta.get(Calendar.YEAR) - masinaCurenta.getAnFabricatie()<=5){
                                angajatCurent.setNrAutobuzeNoi(angajatCurent.getNrAutobuzeNoi()+1);
                            }
                            angajatCurent.setBacsis(angajatCurent.getBacsis() + (masinaCurenta.politaDiscout()/100));
                            //verificam daca exista masini in coada de asteptare
                            if (!coadaMasini.isEmpty()){
                                if (coadaMasini.peek() instanceof Standard){
                                    if (angajatCurent.getNrStandardInCoada()<3){
                                        angajatCurent.setNrStandardInCoada(angajatCurent.getNrStandardInCoada()+1);
                                        angajatCurent.getCoadaMasini().add(coadaMasini.peek().getId());
                                        coadaMasini.poll().setStatus(-1);
                                    }
                                }
                                if (coadaMasini.peek() instanceof Autobuz){
                                    if (angajatCurent.getNrAutobuzeInCoada()==0){
                                        angajatCurent.setNrAutobuzeInCoada(1);
                                        angajatCurent.getCoadaMasini().add(coadaMasini.peek().getId());
                                        coadaMasini.poll().setStatus(-1);
                                    }
                                }
                                if (coadaMasini.peek() instanceof Camion){
                                    if (angajatCurent.getNrCamioaneInCoada()==0){
                                        angajatCurent.setNrCamioaneInCoada(1);
                                        angajatCurent.getCoadaMasini().add(coadaMasini.peek().getId());
                                        coadaMasini.poll().setStatus(-1);
                                    }
                                }
                            }
                        }
                        else{
                            masinaCurenta.setTimpReparatie(masinaCurenta.getTimpReparatie()-1);
                        }
                        break;
                    }
                }
            }
        }
    }

    public void afisareIncarcaturaMaxima(List<Angajat> listaAngajati){
        Angajat raspuns = null;
        int maxim = -1;
        int incarcaturaCurenta;
        for (int i=0; i<listaAngajati.size(); i++){
            incarcaturaCurenta = listaAngajati.get(i).getNrStandardInCoada() +
                                     listaAngajati.get(i).getNrAutobuzeInCoada() +
                                     listaAngajati.get(i).getNrCamioaneInCoada();
            if (incarcaturaCurenta>maxim){
                maxim = incarcaturaCurenta;
                raspuns = listaAngajati.get(i);
            }
        }
        System.out.println(raspuns);
    }

    public void top3politeTotale(List<Angajat> listaAngajati){
        //maxim1 cea mai mare valoare, maxim2 a doua, maxim3 a treia
        int maxim1 = -1;
        Angajat angajat1 = null;
        int maxim2 = -1;
        Angajat angajat2 = null;
        int maxim3 = -1;
        Angajat angajat3 = null;
        int suma;
        for (int i=0; i<listaAngajati.size();i++){
            suma = listaAngajati.get(i).getSumaPolite();
            if (suma > maxim1){
                maxim3 = maxim2;
                angajat3 = angajat2;
                maxim2 = maxim1;
                angajat2 = angajat1;
                maxim1 = suma;
                angajat1 = listaAngajati.get(i);
            }
            else{
                if (suma > maxim2){
                    maxim3 = maxim2;
                    angajat3 = angajat2;
                    maxim2 = suma;
                    angajat2 = listaAngajati.get(i);
                }
                else{
                    if (suma > maxim3){
                        maxim3 = suma;
                        angajat3 = listaAngajati.get(i);
                    }
                }
            }
        }
        System.out.println("Top 3 angajati sunt:");
        System.out.println("1." + angajat1);
        System.out.println("2." + angajat2);
        System.out.println("3." + angajat3);
    }

    public void top3autobuzeNoi(List<Angajat> listaAngajati){
        //maxim1 cea mai mare valoare, maxim2 a doua, maxim3 a treia
        int maxim1 = -1;
        Angajat angajat1 = null;
        int maxim2 = -1;
        Angajat angajat2 = null;
        int maxim3 = -1;
        Angajat angajat3 = null;
        int nrAutobuze;
        for (int i=0; i<listaAngajati.size();i++){
            nrAutobuze = listaAngajati.get(i).getNrAutobuzeNoi();
            if (nrAutobuze > maxim1){
                maxim3 = maxim2;
                angajat3 = angajat2;
                maxim2 = maxim1;
                angajat2 = angajat1;
                maxim1 = nrAutobuze;
                angajat1 = listaAngajati.get(i);
            }
            else{
                if (nrAutobuze > maxim2){
                    maxim3 = maxim2;
                    angajat3 = angajat2;
                    maxim2 = nrAutobuze;
                    angajat2 = listaAngajati.get(i);
                }
                else{
                    if (nrAutobuze > maxim3){
                        maxim3 = nrAutobuze;
                        angajat3 = listaAngajati.get(i);
                    }
                }
            }
        }
        System.out.println("Top 3 angajati sunt:");
        System.out.println("1." + angajat1);
        System.out.println("2." + angajat2);
        System.out.println("3." + angajat3);
    }

    public void topSolicitati(List<Angajat> listaAngajati){
        List<Angajat> raspuns = new ArrayList<>();

        int maxim = -1;
        for (int i=0; i<listaAngajati.size(); i++){
            if (maxim<listaAngajati.get(i).getSolicitareSpeciala()){
                maxim=listaAngajati.get(i).getSolicitareSpeciala();
            }
        }
        for (int i=0; i<listaAngajati.size(); i++){
            if (maxim==listaAngajati.get(i).getSolicitareSpeciala()){
                raspuns.add(listaAngajati.get(i));
            }
        }
        System.out.println("Cei mai cautati angajati sunt:");
        for (int i=0; i<raspuns.size(); i++){
            System.out.println(raspuns);
        }
    }

    public void afisareBacsis(List<Angajat> listaAngajati){
        System.out.println("Bacsisurile angajatilor sunt:");
        for (int i=0; i<listaAngajati.size();i++){
            System.out.println("Pentru angajatul cu id-ul " + listaAngajati.get(i).getId() + ":" + listaAngajati.get(i).getBacsis());
        }
    }

}
