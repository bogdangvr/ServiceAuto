package Angajati;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Mecanic extends Angajat{

    public Mecanic(String nume, String prenume, Calendar dataNasterii, Calendar dataAngajarii) {
        super(nume, prenume, dataNasterii, dataAngajarii);
        this.coeficientSalariat = 1.5;
    }

    @Override
    public String toString() {
        return "Mecanic{" +
                "id=" + this.getId() +
                ", nume='" + this.getNume() + '\'' +
                ", prenume='" + this.getPrenume() + '\'' +
                ", dataNasterii=" + this.getDataNasterii().get(Calendar.DAY_OF_MONTH) + '-' +
                this.getDataNasterii().get(Calendar.MONTH) + '-' +
                this.getDataNasterii().get(Calendar.YEAR) +
                ", dataAngajarii=" + this.getDataAngajarii().get(Calendar.DAY_OF_MONTH) + '-' +
                this.getDataAngajarii().get(Calendar.MONTH) + '-' +
                this.getDataAngajarii().get(Calendar.YEAR) +
                ", coeficientSalariat=" + coeficientSalariat +
                ", nrStandardInCoada=" + this.getNrStandardInCoada() +
                ", nrAutobuzeInCoada=" + this.getNrAutobuzeInCoada() +
                ", nrCamioaneInCoada=" + this.getNrCamioaneInCoada() +
                ", nrStandardInCoada=" + this.getNrStandardInCoada() +
                ", nrMasiniReparate=" + this.getNrMasiniReparate() +
                ", nrAutobuzeNoi=" + this.getNrAutobuzeNoi() +
                ", salariu=" + this.calculSalariu() +
                ", sumaPolite=" + this.getSumaPolite() +
                ", solicitareSpeciala=" + this.getSolicitareSpeciala() +
                ", bacsis=" + this.getBacsis() +
                '}';
    }

    @Override
    public double calculSalariu() {
        Calendar ziCurenta = new GregorianCalendar();
        double coeficientSalariu = 1.5;
        double salariu;
        if (ziCurenta.get(Calendar.MONTH)<this.getDataAngajarii().get(Calendar.MONTH)){
            salariu = (ziCurenta.get(Calendar.YEAR) - this.getDataAngajarii().get(Calendar.YEAR) - 1)
                    * coeficientSalariu * 1000;
        }
        else {
            salariu = (ziCurenta.get(Calendar.YEAR) - this.getDataAngajarii().get(Calendar.YEAR))
                    * coeficientSalariu * 1000;
        }
        return salariu;
    }
}
