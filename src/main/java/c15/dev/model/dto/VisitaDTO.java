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
    private String nomePaziente;
    private String cognomePaziente;
    private LocalDate data;
    private String viaIndirizzo;
    private String comune;
    private Integer nCivico;
    private String provincia;
    private String numeroTelefono;
    private String genere;


}
