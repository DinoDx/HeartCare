package c15.dev.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.checkerframework.checker.optional.qual.Present;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author Alessandro Zoccola
 * Creato il: 30/12/2022
 * Questa è la classe relativa ad una Misurazione.
 * I campi sono: id autogenerato, data della misurazione
 */

@Entity
@ToString
@Inheritance(strategy = InheritanceType.JOINED)
@SuperBuilder
@Getter
@Setter
@Table(name = "misurazione")
public class Misurazione implements Serializable {
    /**
     * l'id viene generato in modo automatico.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Campo relativo alla Data di misurazione nel formato GG-MM-AAAA HH:mm.
     * Invariante: la data della misurazione deve essere maggiore
     * o uguale alla data corrente.
     */
    @Column(name = "data_misurazione", nullable = false)
    @NotNull
    private LocalDate dataMisurazione;
    /**
     * Campo relativo alla relazione tra pazienti e misurazioni.
     */
    @ManyToOne
    @JsonIgnore
    @JsonBackReference("misurazione-paziente")
    @JoinColumn(name = "id_paziente",
            referencedColumnName = "id", nullable = false)
    private Paziente paziente;
    /**
     * Campo relativo alla relazione tra dispositivo e misurazioni.
     */
    @ManyToOne
    @JoinColumn(name = "id_dispositivo_medico",
            referencedColumnName = "id", nullable = false)
    @JsonIgnore
    @JsonBackReference("dispositivo-misurazione")
    private DispositivoMedico dispositivoMedico;

    /**
     * @param data rappresenta la data della misurazione
     * @param paziente rappresenta il paziente coinvolto nella misurazione
     * @param dispositivo rappresenta il dispositivo medico con cui è stata
     *effettuata la misurazione
     */
    public Misurazione(LocalDate data,
                       Paziente paziente,
                       DispositivoMedico dispositivo) {
        this.dataMisurazione = data;
        this.paziente = paziente;
        this.dispositivoMedico = dispositivo;
    }

    /**
     * Costruttore vuoto per Misurazione.
     */
    public Misurazione(){

    }
}
