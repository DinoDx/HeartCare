package c15.dev.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * @author Leopoldo Todisco.
 * Creato il: 29/12/2022.
 * Questa è la classe relativa a un Admin.
 */
@Entity
@Table(name = "admin")
public class Admin extends UtenteRegistrato implements Serializable {
    /**
     * Costruttore vuoto.
     */
    public Admin() {
    }

    /**
     * Costruttore comprensivo di tutti i parametri, richiama il costruttore
     * di "UtenteRegistrato".
     * @param dataDiNascita
     * @param codiceFiscale
     * @param numeroTelefono
     * @param password
     * @param email
     * @param nome
     * @param cognome
     * @param genere
     */
    public Admin(final Date dataDiNascita,
                 final String codiceFiscale,
                 final String numeroTelefono,
                 final String password,
                 final String email,
                 final String nome,
                 final String cognome,
                 final char genere) {
        super(dataDiNascita, codiceFiscale, numeroTelefono,
                password, email, nome, cognome, genere);
    }

}
