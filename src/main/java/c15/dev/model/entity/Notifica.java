package c15.dev.model.entity;

import c15.dev.model.entity.enumeration.StatoNotifica;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.Set;

/**
 * @author Vincenzo Arnone.
 * Creato il 30/12/2022.
 * Questa è la classe che rappresenta le notifiche.
 */
@Data
@Entity
@Setter
@NoArgsConstructor
public class Notifica implements Serializable {
    /**
     * Campo relativo all'id della nota generato automaticamente.
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Campo relativo alla data di scadenza della notifica.
     **/
    @Column(nullable = false)
    @FutureOrPresent
    @NotNull
    private LocalDate dataScadenza;


    /**
     * Campo relativo all'oggetto della notifica.
     **/
    @Column(nullable = false)
    @NotNull
    @NotBlank
    private String oggetto;

    /**
     * Campo relativo al contenuto della notifica.
     **/
    @Column(nullable = false)
    @NotBlank
    private String testo;

    /**
     * Campo relativo allo statoNotifica della notifica.
     **/
    @Column(nullable = false)
    @NotNull
    private StatoNotifica stato;

    /**
     * Campo relativo a tutti gli Utenti a cui una Notifica viene inviata.
     */
    @ManyToOne
    @JoinColumn(
            name = "id_destinatario",
            referencedColumnName = "id"
    )
    private UtenteRegistrato destinatario;

    public Notifica(LocalDate dataScadenza,
                    String oggetto,
                    String testo,
                    StatoNotifica statoNotifica,
                    UtenteRegistrato destinatario) {
        this.dataScadenza = dataScadenza;
        this.oggetto = oggetto;
        this.testo = testo;
        this.stato = statoNotifica;
        this.destinatario = destinatario;
    }
}
