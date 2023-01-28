package c15.dev.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

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
