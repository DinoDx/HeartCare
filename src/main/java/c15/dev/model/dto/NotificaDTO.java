package c15.dev.model.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class NotificaDTO {
    private String messagio;
    private Long idPaziente;


}
