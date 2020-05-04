package Angajati;

import java.util.Calendar;

public abstract class Angajat {
    private static int contorAngajati;
    private int id;
    private String nume;
    private String prenume;
    private Calendar dataNasterii;
    private Calendar dataAngajarii;
    protected double coeficientSalariat;

    public Angajat(String nume, String prenume, Calendar dataNasterii, Calendar dataAngajarii) {
        this.nume = nume;
        this.prenume = prenume;
        this.dataNasterii = dataNasterii;
        this.dataAngajarii = dataAngajarii;
        this.contorAngajati++;
        this.id=contorAngajati;
    }

    public abstract double calculSalariu();

    //setteri
    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setDataNasterii(Calendar dataNasterii) {
        this.dataNasterii = dataNasterii;
    }

    public void setDataAngajarii(Calendar dataAngajarii) {
        this.dataAngajarii = dataAngajarii;
    }

    public static void setContorAngajati(int contorAngajati) {
        Angajat.contorAngajati = contorAngajati;
    }

    //getteri
    public int getId() {
        return id;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public Calendar getDataNasterii() {
        return dataNasterii;
    }

    public Calendar getDataAngajarii() {
        return dataAngajarii;
    }

    public double getCoeficientSalariat() {
        return coeficientSalariat;
    }


}
