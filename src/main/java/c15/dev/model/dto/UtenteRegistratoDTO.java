package c15.dev.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author carlo.
 * Classe dto per la modifica degli utenti.
 */
@Data
public class UtenteRegistratoDTO implements Serializable {

    private static final int LENGTH_13 = 13;
    /**
     * Campo che indica il nome del paziente.
     */
    @NotBlank
    private String nome;
    /**
     * Campo che indica il cognome del paziente.
     */
    @NotBlank
    private String cognome;
    /**
     * Campo che indica il numero di telefono di un paziente.
     */
    @NotNull
    @Pattern(regexp = "^((00|\\+)39[\\. ]??)??3\\d{2}[\\. ]??\\d{6,7}$")
    @Size(min=LENGTH_13, max=LENGTH_13)
    private String numeroTelefono;
    /**
     * Campo che indica la password nuova di un paziente.
     */
    private String password;
    /**
     * Campo che indica la password vecchia di un paziente.
     */
    private String confermaPassword;

    public void setPassword(final String pass) throws Exception {
        String regexpPassword =
                "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])" +
                        "[A-Za-z\\d@$!%*?&]{8,16}$";

        System.out.println(pass);

        if(pass.matches(regexpPassword)) {
            this.password = pass;
        }
        else {
            throw new Exception("La password non rispetta " +
                    "l'espressione regolare");
        }
    }




    public void setConfermaPassword(final String pass) throws Exception {
        String regexpPassword =
                "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])" +
                        "[A-Za-z\\d@$!%*?&]{8,16}$";

        if(pass.matches(regexpPassword)) {
            this.confermaPassword = pass;
        }
        else {
            throw new Exception("La password non rispetta " +
                    "l'espressione regolare");
        }
    }
}
