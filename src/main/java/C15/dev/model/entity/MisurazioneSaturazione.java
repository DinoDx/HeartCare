package C15.dev.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.GregorianCalendar;

/**
 * @author Alessandro Zoccola
 * Creato il: 30/12/2022
 * Questa è la classe relativa ad una Misurazione Saturazione.
 * I campi sono: data della misurazione, valore battiti per minuto, valore percentuale saturazione
 */

@Entity
public class MisurazioneSaturazione extends Misurazione{

    @Column(name = "battiti_per_minuto", nullable = false)
    private int battitiPerMinuto;

    @Column(name = "percentuale_saturazione", nullable = false)
    private double percentualeSaturazione;

    /**
     * Costruttore senza parametri per MisurazioneSaturazione
     */
    public MisurazioneSaturazione(){
        super();
    }

    /**
     * @param dataMisurazione rappresenta la data della misurazione
     * @param paziente rappresenta il paziente coinvolto nella misurazione
     * @param dispositivoMedico rappresenta il dispositivo medico con cui è stata effettuata la misurazione
     * @param battitiPerMinuto rappresenta il valore dei battiti per minuto
     * @param percentualeSaturazione rappresenta il valore della percentuale di saturazione
     */
    public MisurazioneSaturazione(GregorianCalendar dataMisurazione, Paziente paziente, DispositivoMedico dispositivoMedico, int battitiPerMinuto, double percentualeSaturazione){
        super(dataMisurazione, paziente, dispositivoMedico);
        this.battitiPerMinuto = battitiPerMinuto;
        this.percentualeSaturazione = percentualeSaturazione;
    }


    /**
     *
     * @return battitiPerMinuto
     * metodo che restituisce il valore dei battiti per minuto
     */
    public int getBattitiPerMinuto() {
        return battitiPerMinuto;
    }

    /**
     *
     * @param battitiPerMinuto
     * metodo che permette settare i battiti per minuto di una misurazione
     *
     */
    public void setBattitiPerMinuto(int battitiPerMinuto) {
        this.battitiPerMinuto = battitiPerMinuto;
    }

    /**
     *
     * @return percentualeSaturazione
     * metodo che restituisce il valore della percentuale della saturazione
     */
    public double getPercentualeSaturazione() {
        return percentualeSaturazione;
    }

    /**
     *
     * @param percentualeSaturazione
     * metodo che permette settare la percentuale di saturazione di una misurazione
     *
     */
    public void setPercentualeSaturazione(double percentualeSaturazione) {
        this.percentualeSaturazione = percentualeSaturazione;
    }
}
