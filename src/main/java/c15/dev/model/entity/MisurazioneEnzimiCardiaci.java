package c15.dev.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;



/**
 * @author Alessandro Zoccola.
 * Creato il: 30/12/2022.
 * Questa è la classe relativa ad una Misurazione Enzimi Cardiaci.
 * I campi sono: data della misurazione,
 *               valore mioglobina,
 *               valore creatin Kinasi,
 *               troponina Cardiaca.
 */
@Getter
@Setter
@Entity
@SuperBuilder
public class MisurazioneEnzimiCardiaci
        extends Misurazione implements Serializable {
    /**
     * questo campo indica il valore della mioglobina.
     */
    @Column(name = "mioglobina", nullable = false)
    @NotNull
    private double mioglobina;

    /**
     * questo campo indica il valore della creatin Kinasi.
     */
    @Column(name = "creatin_kinasi", nullable = false)
    @NotNull
    private double creatinKinasi;

    /**
     * questo campo indica il valore della troponina cardiaca.
     */
    @Column(name = "troponina_cardiaca", nullable = false)
    @NotNull
    private double troponinaCardiaca;

    /**
     * Costruttore senza parametri per MisurazioneEnzimiCardiaci.
     */
    public MisurazioneEnzimiCardiaci() {
        super();
    }

    /**
     * @param data rappresenta la data della misurazione
     * @param paziente rappresenta il paziente coinvolto nella misurazione
     * @param dispositivo rappresenta il dispositivo medico con cui
     *                          è stata effettuata la misurazione
     * @param creatinKinasi rappresenta il valore della creatin Kinasi
     * @param mioglobina rappresenta il valore della mioglobina
     * @param troponinaCardiaca rappresenta il valore della troponina cardiaca
     */
    public MisurazioneEnzimiCardiaci(final LocalDate data,
                                     final Paziente paziente,
                                     final DispositivoMedico dispositivo,
                                     final double mioglobina,
                                     final double creatinKinasi,
                                     final double troponinaCardiaca) {
        super(data, paziente, dispositivo);
        this.creatinKinasi = creatinKinasi;
        this.mioglobina = mioglobina;
        this.troponinaCardiaca = troponinaCardiaca;
    }
}
