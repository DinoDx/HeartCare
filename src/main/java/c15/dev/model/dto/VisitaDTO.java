package c15.dev.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

/**
 * @author Leopoldo Todisco.
 * Creato il: 28/01/2023.
 * Classe DTO che serve a passare solo i dati realmente necessari al frontend.
 */
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
public class VisitaDTO {
    /**
     *  Parametro nome del paziente.
     */
    private String nomePaziente;
    /**
     *  Parametro cognome del paziente.
     */
    private String cognomePaziente;
    /**
     *  Parametro data della visita.
     */
    private LocalDate data;
    /**
     *  Parametro indirizzo della visita.
     */
    private String viaIndirizzo;
    /**
     *  Paramentro comune della visita.
     */
    private String comune;
    /**
     *  Parametro nCivico della visita.
     */
    private String nCivico;
    /**
     *  Parametro provincia della visita.
     */
    private String provincia;
    /**
     *  Parametro numeroTelefono della visita.
     */
    private String numeroTelefono;
    /**
     *  Parametro genere.
     */
    private String genere;
    /**
     *  Parametro id paziente.
     */
    private Long idPaziente;
}
