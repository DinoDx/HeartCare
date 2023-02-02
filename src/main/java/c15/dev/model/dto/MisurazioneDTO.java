package c15.dev.model.dto;

import c15.dev.model.entity.Misurazione;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Mario Cicalese.
 * creato il: 25/01/2022.
 * Questo è una misurazione Data Transfer Protocol.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MisurazioneDTO {
    /**
     * Parametro misurazione che contiene tutti i dati di una misurazione.
     * @param misurazione.
     *
     */
    private Misurazione misurazione;

    /**
     * Parametro che contiene la categoria della misurazione.
     * @param categoria.
     */
    private String categoria;
}
