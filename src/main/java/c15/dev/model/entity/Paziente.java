package c15.dev.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinTable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.Set;


/**
 * @author carlo.
 * Creato il: 29/12/2022
 * Questa è la classe relativa a un Paziente.
 */
@Entity
@Data
@SuperBuilder
@Setter
public class Paziente extends UtenteRegistrato {
    /**
     * Questo campo rappresenta il nome del caregiver.
     */
    //@NotEmpty
    private String nomeCaregiver;

    /**
     * Questo campo rappresenta il cognome del caregiver.
     */
    //@NotEmpty
    private String cognomeCaregiver;
    /**
     * Questo campo rappresenta l'email del caregiver.
     */
    //@Email
    private String emailCaregiver;

    /**
     * Questo campo indica il Medico che viene assegnato al paziente.
     */
    @ManyToOne
    @JoinColumn(name = "id_medico",
                referencedColumnName = "id",
                nullable = true)
    private Medico medico;

    /**
     * Questo campo indica l'insieme delle note che un paziente riceve.
     */
    @OneToMany(mappedBy = "paziente",
                fetch = FetchType.EAGER)
    private Set<Nota> note;

    /**
     * Questo campo indica l'insieme dei dispositivi medici che un paziente si
     * assegna.
     */
    @OneToMany(mappedBy = "paziente",
            fetch = FetchType.EAGER)
    private Set<DispositivoMedico> dispositivoMedico;

    /**
     * Campo che indica l'insieme delle misurazioni che un paziente esegue.
     */
    @OneToMany(mappedBy = "paziente", fetch = FetchType.EAGER)
    private Set<Misurazione> misurazione;

    /**
     * Campo che inidica l'insieme delle visite a cui un paziente è stato.
     */
    @OneToMany(mappedBy = "paziente", fetch = FetchType.EAGER)
    private Set<Visita> visita;

    /**
     * Insieme delle notifiche relative a un paziente.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "avviso",
            joinColumns = @JoinColumn(name = "paziente_id"),
            inverseJoinColumns = @JoinColumn(name = "notifica_id")
    )
    private Set<Notifica> notifica;

    /**
     * costruttore vuoto.
     */
    public Paziente() {
        super();
    }

    /**
     *
     * @param dataNascita
     * @param codFiscale
     * @param nTelefono
     * @param pass
     * @param indirizzoEmail
     * @param nome
     * @param cognome
     * @param sesso
     *
     * costruttore per il Paziente.
     */
    public Paziente(final Date dataNascita,
                    final String codFiscale,
                    final String nTelefono,
                    final String pass,
                    final String indirizzoEmail,
                    final String nome,
                    final String cognome,
                    final String sesso
                   ) throws Exception {
        super(dataNascita,
                codFiscale,
                nTelefono,
                pass,
                indirizzoEmail,
                nome,
                cognome,
                sesso);

    }


    @Override
    public String toString() {
        return super.toString();
    }
}
