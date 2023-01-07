package c15.dev.model.dto;



import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe dto per la modifica di un paziente.
 * @author carlo.
 */
@Data
public class ModificaPazienteDTO implements Serializable {
    private static final int LENGTH_13 = 13;
    /**
     * Campo che indica il nome del caregiver.
     */
    @NotBlank
    private String nomeCaregiver;
    /**
     * Campo che indica il cognome del caregiver.
     */
    @NotBlank
    private String cognomeCaregiver;
    /**
     * Campo che indica email del caregiver.
     */
    @Email
    private String emailCaregiver;
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
    private byte[] password;
    /**
     * Campo che indica la password vecchia di un paziente.
     */
    private byte[] confermaPassword;

    public void setPassword(final String pass) throws Exception {
        String regexpPassword =
                "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])" +
                        "[A-Za-z\\d@$!%*?&]{8,16}$";

        System.out.println(pass);

        if(pass.matches(regexpPassword)) {
            try {
                MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
                this.password = msgDigest.digest(pass.getBytes());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            throw new Exception("La password non rispetta l'espressione regolare");
        }
    }


    public void setConfermaPassword(final String pass) throws Exception {
        String regexpPassword =
                "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])" +
                        "[A-Za-z\\d@$!%*?&]{8,16}$";

        if(pass.matches(regexpPassword)) {
            try {
                MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
                this.confermaPassword = msgDigest.digest(pass.getBytes());
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            throw new Exception("La password non rispetta l'espressione regolare");
        }
    }

}
