package c15.dev.utils;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: Leopoldo Todisco, Carlo Venditto.
 * Creato il: 24/01/2023.
 * Classe che indica la risposta a un login avvenuto con successo.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    /**
     * Token.
     */
    private String token;
}
