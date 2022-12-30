package C15.dev.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.GregorianCalendar;

/**
 * @author Paolo Carmine Valletta
 * Creato il: 30/12/2022
 * Questa è la classe relativa ad una Misurazione Holter ECG.
 * I campi sono: durata onda P, battiti per minuto, durata complesso QRS, intervallo PR, ondaT.
 *
 */

@Entity
public class MisurazioneHolterECG extends Misurazione{
    @Column(name = "durata_onda_p", nullable = false)
    private double durataOndaP;
    @Column(name = "battiti_per_minuto", nullable = false)
    private int battitiPerMinuto;
    @Column(name = "durata_complesso_qrs", nullable = false)
    private double durataComplessoQRS;
    @Column(name = "intervallo_pr", nullable = false)
    private double intervalloPR;
    @Column(name = "onda_t", nullable = false)
    private double ondaT;

    /**
     *
     * Costruttore senza parametri per misurazioneHolterECG
     */

    public MisurazioneHolterECG() {
        super();
    }

    /**
     * @param dataMisurazione rappresenta la data della misurazione Holter ECG
     * @param paziente rappresenta il paziente della misurazione Holter ECG
     * @param dispositivoMedico rappresenta il dispositivo medico della misurazione Holter ECG
     * @param durataOndaP rappresenta il valore della durata Onda P della misurazione Holter ECG
     * @param battitiPerMinuto rappresenta il valore dei battiti per minuto della misurazione Holter ECG
     * @param durataComplessoQRS rappresenta il valore della durata complesso QRS della misurazione Holter ECG
     * @param intervalloPR rappresenta il valore dell'intervallo PR della misurazione Holter ECG
     * @param ondaT rappresenta il valore dell'onda T della misurazione Holter ECG
     *
     */

    public MisurazioneHolterECG(GregorianCalendar dataMisurazione, Paziente paziente, DispositivoMedico dispositivoMedico, double durataOndaP, int battitiPerMinuto, double durataComplessoQRS, double intervalloPR, double ondaT) {
        super(dataMisurazione, paziente, dispositivoMedico);
        this.durataOndaP = durataOndaP;
        this.battitiPerMinuto = battitiPerMinuto;
        this.durataComplessoQRS = durataComplessoQRS;
        this.intervalloPR = intervalloPR;
        this.ondaT = ondaT;
    }

    /**
     *
     * @return durataOndaP
     * metodo che restituisce il valore della durata onda P
     */

    public double getDurataOndaP() {
        return durataOndaP;
    }

    /**
     *
     * @return battitiPerMinuto
     * metodo che restituisce il valore del battiti per minuto
     */

    public int getBattitiPerMinuto() {
        return battitiPerMinuto;
    }

    /**
     *
     * @return durataComplessoQRS
     * metodo che restituisce il valore della durata complesso QRS
     */

    public double getDurataComplessoQRS() {
        return durataComplessoQRS;
    }

    /**
     *
     * @return intervalloPR
     * metodo che restituisce il valore dell'intervallo PR
     */

    public double getIntervalloPR() {
        return intervalloPR;
    }

    /**
     *
     * @return ondaT
     * metodo che restituisce il valore dell'ondaT
     */

    public double getOndaT() {
        return ondaT;
    }

    /**
     *
     * @param durataOndaP
     * metodo che permette di impostare il valore dell'ondaP della misurazione Holter ECG
     */

    public void setDurataOndaP(double durataOndaP) {
        this.durataOndaP = durataOndaP;
    }

    /**
     *
     * @param battitiPerMinuto
     * metodo che permette di impostare il valore dei battiti per minuto della misurazione Holter ECG
     */

    public void setBattitiPerMinuto(int battitiPerMinuto) {
        this.battitiPerMinuto = battitiPerMinuto;
    }

    /**
     *
     * @param durataComplessoQRS
     * metodo che permette di impostare il valore della durata del complesso QRS della misurazione Holter ECG
     */

    public void setDurataComplessoQRS(double durataComplessoQRS) {
        this.durataComplessoQRS = durataComplessoQRS;
    }

    /**
     *
     * @param intervalloPR
     * metodo che permette di impostare il valore dell'intervallo PR della misurazione Holter ECG
     */

    public void setIntervalloPR(double intervalloPR) {
        this.intervalloPR = intervalloPR;
    }

    /**
     *
     * @param ondaT
     * metodo che permette di impostare il valore dell'ondaT della misurazione Holter ECG
     */

    public void setOndaT(double ondaT) {
        this.ondaT = ondaT;
    }
}
