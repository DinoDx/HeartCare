package C15.dev.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.GregorianCalendar;

/**
 * @author Alessandro Zoccola
 * Creato il: 30/12/2022
 * Questa è la classe relativa ad una Misurazione Pressione.
 * I campi sono: data della misurazione, valore battiti per minuto, valore pressione massima, valore pressione media e valore pressione minima
 */

@Entity
public class MisurazionePressione extends Misurazione{
    @Column(name = "battiti_per_minuto", nullable = false)
    private int battitiPerMinuto;
    @Column(name = "pressione_massima", nullable = false)
    private double pressioneMassima;

    @Column(name = "pressione_minima", nullable = false)
    private double pressioneMinima;

    @Column(name = "pressione_media", nullable = false)
    private double pressioneMedia;

    /**
     * Costruttore senza parametri per MisurazionePressione
     */
    public MisurazionePressione(){
        super();
    }

    /**
     * @param dataMisurazione rappresenta la data della misurazione
     * @param paziente rappresenta il paziente coinvolto nella misurazione
     * @param dispositivoMedico rappresenta il dispositivo medico con cui è stata effettuata la misurazione
     * @param battitiPerMinuto rappresenta il valore dei battiti per minuto
     * @param pressioneMassima rappresenta il valore della pressione massima
     * @param pressioneMinima rappresenta il valore della pressione minima
     * @param pressioneMedia rappresenta il valore della pressione media
     */
    public MisurazionePressione(GregorianCalendar dataMisurazione, Paziente paziente, DispositivoMedico dispositivoMedico, int battitiPerMinuto, double pressioneMassima, double pressioneMinima, double pressioneMedia) {
        super(dataMisurazione, paziente, dispositivoMedico);
        this.battitiPerMinuto = battitiPerMinuto;
        this.pressioneMassima = pressioneMassima;
        this.pressioneMinima = pressioneMinima;
        this.pressioneMedia = pressioneMedia;
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
     * @return pressioneMassima
     * metodo che restituisce il valore della pressione massima
     */
    public double getPressioneMassima() {
        return pressioneMassima;
    }
    /**
     *
     * @param pressioneMassima
     * metodo che permette settare la pressione massima di una misurazione
     *
     */
    public void setPressioneMassima(double pressioneMassima) {
        this.pressioneMassima = pressioneMassima;
    }

    /**
     *
     * @return pressioneMinima
     * metodo che restituisce il valore della pressione minima
     */
    public double getPressioneMinima() {
        return pressioneMinima;
    }

    /**
     *
     * @param pressioneMinima
     * metodo che permette settare la pressione minima di una misurazione
     *
     */
    public void setPressioneMinima(double pressioneMinima) {
        this.pressioneMinima = pressioneMinima;
    }

    /**
     *
     * @return pressioneMedia
     * metodo che restituisce il valore della pressione media
     */
    public double getPressioneMedia() {
        return pressioneMedia;
    }

    /**
     *
     * @param pressioneMedia
     * metodo che permette settare la pressione media di una misurazione
     *
     */
    public void setPressioneMedia(double pressioneMedia) {
        this.pressioneMedia = pressioneMedia;
    }
}
