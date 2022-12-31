package c15.dev.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * @author Paolo Carmine Valletta
 * Creato il: 30/12/2022
 * Questa è la classe relativa ad una Misurazione Glicemica.
 * I campi sono: zuccheri nel sangue, colesterolo, trigliceridi.
 *
 */


@Entity
public class MisurazioneGlicemica extends Misurazione implements Serializable {
    /**
     * Questo campo intero indica la quantità di zuccheri nel sangue.
     */
    @Column(name = "zuccheri_nel_sangue", nullable = false)
    private int zuccheriNelSangue;

    /**
     * questo campo indica il livello di colesterolo.
     */
    @Column(name = "colesterolo", nullable = false)
    private int colesterolo;

    /**
     * questo campo indica il livello di trigliceridi.
     */
    @Column(name = "trigliceridi", nullable = false)
    private int trigliceridi;

    /**
     *
     * Costruttore senza parametri per misurazioneGlicemica.
     */
    public MisurazioneGlicemica() {
        super();
    }

    /**
     * @param dataMisurazione rappresenta la data della misurazione glicemica
     * @param paziente rappresenta il paziente della misurazione glicemica
     * @param dispositivoMedico rappresenta il dispositivo medico
     *                          della misurazione glicemica
     * @param zuccheriNelSangue rappresenta il valore degli zuccheri nel sangue
     *                          della misurazione glicemica
     * @param colesterolo rappresenta il valore del colesterolo
     *                    della misurazione glicemica
     * @param trigliceridi rappresenta il valore dei trigliceridi
     *                     della misurazione glicemica
     *
     */
    public MisurazioneGlicemica(final GregorianCalendar dataMisurazione,
                                final Paziente paziente,
                                final DispositivoMedico dispositivoMedico,
                                final int zuccheriNelSangue,
                                final int colesterolo,
                                final int trigliceridi) {
        super(dataMisurazione, paziente, dispositivoMedico);
        this.zuccheriNelSangue = zuccheriNelSangue;
        this.colesterolo = colesterolo;
        this.trigliceridi = trigliceridi;
    }

    /**
     *
     * @return zuccheriNelSangue
     * metodo che restituisce il valore degli zuccheri nel sangue.
     */
    public int getZuccheriNelSangue() {
        return zuccheriNelSangue;
    }

    /**
     *
     * @return colesterolo
     * metodo che restituisce il valore del colesterolo.
     */
    public int getColesterolo() {
        return colesterolo;
    }

    /**
     *
     * @return trigliceridi
     * metodo che restituisce il valore dei trigliceridi.
     */
    public int getTrigliceridi() {
        return trigliceridi;
    }

    /**
     *
     * @param zuccheriNelSangue
     * metodo che permette di impostare il valore degli zuccheri nel sangue
     * della misurazione glicemica.
     */
    public void setZuccheriNelSangue(final int zuccheriNelSangue) {
        this.zuccheriNelSangue = zuccheriNelSangue;
    }

    /**
     *
     * @param colesterolo
     * metodo che permette di impostare il valore del colesterolo
     * della misurazione glicemica.
     */
    public void setColesterolo(final int colesterolo) {
        this.colesterolo = colesterolo;
    }

    /**
     *
     * @param trigliceridi
     * metodo che permette di impostare il valore dei trigliceridi
     * della misurazione glicemica.
     */
    public void setTrigliceridi(final int trigliceridi) {
        this.trigliceridi = trigliceridi;
    }
}
