package Angajati;

import java.util.Calendar;
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
                '}';
    }

    @Override
    public double calculSalariu() {
        return 0;
    }
}
