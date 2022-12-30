package C15.dev.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;


/**
 * @author Alessandro Zoccola
 * Creato il: 30/12/2022
 * Questa è la classe relativa ad una Misurazione Enzimi Cardiaci.
 * I campi sono: data della misurazione, valore mioglobina, valore creatin Kinasi e troponina Cardiaca
 */

@Entity
public class MisurazioneEnzimiCardiaci extends Misurazione{
    @Column(name = "mioglobina", nullable = false)
    private double mioglobina;

    @Column(name = "creatin_kinasi", nullable = false)
    private double creatinKinasi;

    @Column(name = "troponina_cardiaca", nullable = false)
    private double troponinaCardiaca;

    /**
     * Costruttore senza parametri per MisurazioneEnzimiCardiaci
     */
    public MisurazioneEnzimiCardiaci(){
        super();
    }

    /**
     * @param dataMisurazione rappresenta la data della misurazione
     * @param paziente rappresenta il paziente coinvolto nella misurazione
     * @param dispositivoMedico rappresenta il dispositivo medico con cui è stata effettuata la misurazione
     * @param creatinKinasi rappresenta il valore della creatin Kinasi
     * @param mioglobina rappresenta il valore della mioglobina
     * @param troponinaCardiaca rappresenta il valore della troponina cardiaca
     */
    public MisurazioneEnzimiCardiaci(GregorianCalendar dataMisurazione, Paziente paziente, DispositivoMedico dispositivoMedico, double mioglobina, double creatinKinasi, double troponinaCardiaca){
        super(dataMisurazione, paziente, dispositivoMedico);
        this.creatinKinasi = creatinKinasi;
        this.mioglobina = mioglobina;
        this.troponinaCardiaca = troponinaCardiaca;
    }

    /**
     *
     * @return mioglobina
     * metodo che restituisce il valore della mioglobina
     */
    public double getMioglobina() {
        return mioglobina;
    }

    /**
     *
     * @param mioglobina
     * metodo che permette settare la mioglobina di una misurazione
     *
     */
    public void setMioglobina(double mioglobina) {
        this.mioglobina = mioglobina;
    }

    /**
     *
     * @return creatinKinasi
     * metodo che restituisce il valore della creatin kinasi
     */
    public double getCreatinKinasi() {
        return creatinKinasi;
    }

    /**
     *
     * @param creatinKinasi
     * metodo che permette settare la creatin kinasi di una misurazione
     *
     */
    public void setCreatinKinasi(double creatinKinasi) {
        this.creatinKinasi = creatinKinasi;
    }

    /**
     *
     * @return troponinaCardiaca
     * metodo che restituisce il valore della troponina Cardiaca
     */
    public double getTroponinaCardiaca() {
        return troponinaCardiaca;
    }


    /**
     *
     * @param troponinaCardiaca
     * metodo che permette settare la troponina cardiaca di una misurazione
     *
     */
    public void setTroponinaCardiaca(double troponinaCardiaca) {
        this.troponinaCardiaca = troponinaCardiaca;
    }
}
