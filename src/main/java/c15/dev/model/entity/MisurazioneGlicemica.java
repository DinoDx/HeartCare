package c15.dev.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


/**
 * @author Paolo Carmine Valletta
 * Creato il: 30/12/2022
 * Questa è la classe relativa ad una Misurazione Glicemica.
 * I campi sono: zuccheri nel sangue, colesterolo, trigliceridi.
 *
 */

@Getter
@Setter
@Entity
public class MisurazioneGlicemica extends Misurazione implements Serializable {
    /**
     * Questo campo intero indica la quantità di zuccheri nel sangue.
     */
    @Column(name = "zuccheri_nel_sangue", nullable = false)
    @NotNull
    private int zuccheriNelSangue;

    /**
     * questo campo indica il livello di colesterolo.
     */
    @Column(name = "colesterolo", nullable = false)
    @NotNull
    private int colesterolo;

    /**
     * questo campo indica il livello di trigliceridi.
     */
    @Column(name = "trigliceridi", nullable = false)
    @NotNull
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
    public MisurazioneGlicemica(final LocalDate dataMisurazione,
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
}
