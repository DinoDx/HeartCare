package c15.dev.model.dto;

import lombok.*;

/**
 * @author carlo.
 * Classe dto per la modifica di una nota.
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NotaDTO {
    /**
     *  Parametro nome della nota.
     */
    private String nome;

    /**
     * Parametro che contiene il messaggio della nota.
     */
    private String messaggio;



}
