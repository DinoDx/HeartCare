package c15.dev.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * @author Alessandro Zoccola.
 * Creato il: 30/12/2022.
 * Questa è la classe relativa ad una Misurazione Pressione.
 * I campi sono: data della misurazione,
 *  valore battiti per minuto,
 *  valore pressione massima,
 *  valore pressione media,
 *  valore pressione minima.
 */
@Entity
public class MisurazionePressione extends Misurazione implements Serializable {
    /**
     * Questo campo indica il numero di battiti per minuto.
     */
    @Column(name = "battiti_per_minuto", nullable = false)
    @NotNull
    private int battitiPerMinuto;

    /**
     * Questo campo indica il valore della pressione massima.
     */
    @Column(name = "pressione_massima", nullable = false)
    @NotNull
    private double pressioneMassima;

    /**
     * Questo campo indica il valore della pressione minima.
     */
    @Column(name = "pressione_minima", nullable = false)
    @NotNull
    private double pressioneMinima;

    /**
     * Questo campo indica il valore della pressione media.
     */
    @Column(name = "pressione_media", nullable = false)
    @NotNull
    private double pressioneMedia;

    /**
     * Costruttore senza parametri per MisurazionePressione.
     */
    public MisurazionePressione() {
        super();
    }

    /**
     * @param dataMisurazione rappresenta la data della misurazione
     * @param paziente rappresenta il paziente coinvolto nella misurazione
     * @param dispositivoMedico rappresenta il dispositivo medico con cui
     *                          è stata effettuata la misurazione
     * @param battitiPerMinuto rappresenta il valore dei battiti per minuto
     * @param pressioneMassima rappresenta il valore della pressione massima
     * @param pressioneMinima rappresenta il valore della pressione minima
     * @param pressioneMedia rappresenta il valore della pressione media
     */
    public MisurazionePressione(final GregorianCalendar dataMisurazione,
                                final Paziente paziente,
                                final DispositivoMedico dispositivoMedico,
                                final int battitiPerMinuto,
                                final double pressioneMassima,
                                final double pressioneMinima,
                                final double pressioneMedia) {
        super(dataMisurazione, paziente, dispositivoMedico);
        this.battitiPerMinuto = battitiPerMinuto;
        this.pressioneMassima = pressioneMassima;
        this.pressioneMinima = pressioneMinima;
        this.pressioneMedia = pressioneMedia;
    }

    /**
     *
     * @return battitiPerMinuto
     * Metodo che restituisce il valore dei battiti per minuto.
     */
    public int getBattitiPerMinuto() {
        return battitiPerMinuto;
    }

    /**
     *
     * @param battitiPerMinuto
     * Metodo che permette settare i battiti per minuto di una misurazione.
     *
     */
    public void setBattitiPerMinuto(final int battitiPerMinuto) {
        this.battitiPerMinuto = battitiPerMinuto;

    }

    /**
     *
     * @return pressioneMassima
     * Metodo che restituisce il valore della pressione massima.
     */
    public double getPressioneMassima() {
        return pressioneMassima;
    }
    /**
     *
     * @param pressioneMassima
     * Metodo che permette settare la pressione massima di una misurazione.
     *
     */
    public void setPressioneMassima(final double pressioneMassima) {
        this.pressioneMassima = pressioneMassima;
    }

    /**
     *
     * @return pressioneMinima
     * Metodo che restituisce il valore della pressione minima.
     */
    public double getPressioneMinima() {
        return pressioneMinima;
    }

    /**
     *
     * @param pressioneMinima
     * Metodo che permette settare la pressione minima di una misurazione.
     *
     */
    public void setPressioneMinima(final double pressioneMinima) {
        this.pressioneMinima = pressioneMinima;
    }

    /**
     *
     * @return pressioneMedia
     * Metodo che restituisce il valore della pressione media.
     */
    public double getPressioneMedia() {
        return pressioneMedia;
    }

    /**
     *
     * @param pressioneMedia
     * Metodo che permette settare la pressione media di una misurazione.
     *
     */
    public void setPressioneMedia(final double pressioneMedia) {
        this.pressioneMedia = pressioneMedia;
    }
}
