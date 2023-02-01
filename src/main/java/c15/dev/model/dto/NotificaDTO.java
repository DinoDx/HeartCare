package c15.dev.model.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;
/**
 * @author carlo.
 * Classe dto per la notifica.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class NotificaDTO {
    /**
     * Messaggio da inviare.
     */
    private String messagio;
    /**
     * Id del paziente.
     */
    private Long idPaziente;


}
