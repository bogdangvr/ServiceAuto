package Serviciu;

import Angajati.Angajat;
import Angajati.Asistent;
import Angajati.Director;
import Angajati.Mecanic;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

public class Serviciu {

    public void afisareAngajati(List<Angajat> listaAngajati){
        for (int i=0; i<listaAngajati.size(); i++){
            System.out.println(listaAngajati.get(i));
        }
    }

    public void adaugareAngajat(List<Angajat> listaAngajati){
        Calendar ziCurenta = new GregorianCalendar();

        Scanner scanner = new Scanner(System.in);


        System.out.println("Introduceti functia angajatului (1 pentru Director, 2 pentru Mecanic, 3 pentru Asistent: ");
        int functie = scanner.nextInt();
        scanner.nextLine();
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

    public void stergereAngajat(List<Angajat> listaAngajati, int id){
        int indiceAngajatDeSters = -1;
        for (int i=0; i<listaAngajati.size(); i++){
            if (listaAngajati.get(i).getId()==id){
                indiceAngajatDeSters=i;
                break;
            }
        }
        listaAngajati.remove(indiceAngajatDeSters);
    }

    public void afisareSalariu(List<Angajat> listaAngajati, int id){
        Calendar ziCurenta = new GregorianCalendar();
        for (int i=0; i<listaAngajati.size(); i++){
            if (listaAngajati.get(i).getId()==id){
                double coeficientSalariu = listaAngajati.get(i).getCoeficientSalariat();
                if (ziCurenta.get(Calendar.MONTH)<listaAngajati.get(i).getDataAngajarii().get(Calendar.MONTH)){
                    double salariu = (ziCurenta.get(Calendar.YEAR) - listaAngajati.get(i).getDataAngajarii().get(Calendar.YEAR) - 1)
                                      * coeficientSalariu * 1000;
                    System.out.println("Salariul angajatului cu id-ul " + id + " este: " + salariu);
                }
                else {
                    double salariu = (ziCurenta.get(Calendar.YEAR) - listaAngajati.get(i).getDataAngajarii().get(Calendar.YEAR))
                                      * coeficientSalariu * 1000;
                    System.out.println("Salariul angajatului cu id-ul " + id + " este: " + salariu);
                }
                break;
            }
        }

    }

    public void editareAngajat(List<Angajat> listaAngajati){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduceti ID-ul angajatului ce va fi editat (introduceti 0 pentru inapoi):");
        int id = scanner.nextInt();
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
        Angajat angajatDeEditat = listaAngajati.get(indiceAngajatDeEditat);

        System.out.println("Introduceti ce camp doriti sa editati:\n");
        System.out.println("1.Functie;\n");
        System.out.println("2.Nume;\n");
        System.out.println("3.Prenume;\n");
        System.out.println("4.Data nasterii;\n");
        System.out.println("5.Data angajarii;\n");
        System.out.println("6.Inapoi.\n");
        int numarCamp = scanner.nextInt();

        if (numarCamp == 1){
            System.out.println("Introduceti noua functie:\n");
            int nouaFunctie = scanner.nextInt();
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
}
