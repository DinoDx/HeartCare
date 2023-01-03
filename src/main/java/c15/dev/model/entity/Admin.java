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
     * @param dataNascita indica la data di nascita dell'admin
     * @param codFiscale
     * @param nTelefono è il campo del numero di telefono dell'admin
     * @param pass è la password dell'admin
     * @param indirizzoEmail è l'indirizzo email dell'admin
     * @param nome è il nome dell'admin
     * @param cog è il cognome dell'admin
     * @param sesso indica il genere dell'admin
     */
    public Admin(final Date dataNascita,
                 final String codFiscale,
                 final String nTelefono,
                 final String pass,
                 final String indirizzoEmail,
                 final String nome,
                 final String cog,
                 final char sesso) {
        super(dataNascita,
                codFiscale,
                nTelefono,
                pass,
                indirizzoEmail,
                nome,
                cog,
                sesso);
    }

}
