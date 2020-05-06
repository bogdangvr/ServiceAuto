package Angajati;

import Masini.Masina;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Queue;

public abstract class Angajat {
    private static int contorAngajati;
    private int id;
    private String nume;
    private String prenume;
    private Calendar dataNasterii;
    private Calendar dataAngajarii;
    private Queue<Integer> coadaMasini; //coada de masini (contine id-urile acestora)
    private int nrStandardInCoada; //numarul de masini standard pe care urmeaza sa le repare (maxim 3)
    private int nrAutobuzeInCoada; //maxim 1
    private int nrCamioaneInCoada; //maxim 1
    private int nrMasiniReparate; //numarul de masini reparate de cand lucreaza la acest service
    private int nrAutobuzeNoi;
    private int sumaPolite; //suma politelor tuturor masinilor reparate
    private int solicitareSpeciala; //numarul de clienti care au solicitat expres sa fie ajutati de acest angajat
    private int bacsis; //suma bacsisului de-a lungul timpului
    protected double coeficientSalariat; //valoare prestabilita pentru Director, Mecanic si Asistent

    public Angajat(String nume, String prenume, Calendar dataNasterii, Calendar dataAngajarii) {
        this.nume = nume;
        this.prenume = prenume;
        this.dataNasterii = dataNasterii;
        this.dataAngajarii = dataAngajarii;
        this.contorAngajati++;
        this.id=contorAngajati;
        coadaMasini = new LinkedList<>();
    }

    //metoda abstracta ce va fi implementata in clasele ce mostenesc Angajat
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

    public void setNrMasiniReparate(int nrMasiniReparate) {
        this.nrMasiniReparate = nrMasiniReparate;
    }

    public void setNrAutobuzeNoi(int nrAutobuzeNoi) {
        this.nrAutobuzeNoi = nrAutobuzeNoi;
    }

    public void setSumaPolite(int sumaPolite) {
        this.sumaPolite = sumaPolite;
    }

    public void setSolicitareSpeciala(int solicitareSpeciala) {
        this.solicitareSpeciala = solicitareSpeciala;
    }

    public void setBacsis(int bacsis) {
        this.bacsis = bacsis;
    }

    public void setNrStandardInCoada(int nrStandardInCoada) {
        this.nrStandardInCoada = nrStandardInCoada;
    }

    public void setNrAutobuzeInCoada(int nrAutobuzeInCoada) {
        this.nrAutobuzeInCoada = nrAutobuzeInCoada;
    }

    public void setNrCamioaneInCoada(int nrCamioaneInCoada) {
        this.nrCamioaneInCoada = nrCamioaneInCoada;
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

    public Queue<Integer> getCoadaMasini() {
        return coadaMasini;
    }

    public int getNrMasiniReparate() {
        return nrMasiniReparate;
    }

    public int getNrAutobuzeNoi() {
        return nrAutobuzeNoi;
    }

    public int getSumaPolite() {
        return sumaPolite;
    }

    public int getSolicitareSpeciala() {
        return solicitareSpeciala;
    }

    public int getBacsis() {
        return bacsis;
    }

    public int getNrStandardInCoada() {
        return nrStandardInCoada;
    }

    public int getNrAutobuzeInCoada() {
        return nrAutobuzeInCoada;
    }

    public int getNrCamioaneInCoada() {
        return nrCamioaneInCoada;
    }
}
