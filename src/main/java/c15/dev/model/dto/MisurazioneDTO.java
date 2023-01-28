package c15.dev.model.dto;

import c15.dev.model.entity.Misurazione;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MisurazioneDTO {
    private Misurazione misurazione;
    private String categoria;
}
