package c15.dev.model.entity;


import c15.dev.utils.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyGroup;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * @author carlo.
 * Creato il: 29/12/2022
 * Questa è la classe relativa a un Paziente.
 */
@Entity
@Data
@Setter
@Getter
@SuperBuilder
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
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_medico",
                referencedColumnName = "id",
                nullable = true)
    private Medico medico;

    /**
     * Questo campo indica l'insieme delle note che un paziente riceve.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "paziente",
                fetch = FetchType.EAGER)
    private Set<Nota> note;

    /**
     * Questo campo indica l'insieme dei dispositivi medici che un paziente si
     * assegna.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "paziente",
            fetch = FetchType.EAGER)
    private Set<DispositivoMedico> dispositivoMedico;

    /**
     * Campo che indica l'insieme delle misurazioni che un paziente esegue.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "paziente", fetch = FetchType.EAGER)
    private Set<Misurazione> misurazione;

    /**
     * Campo che inidica l'insieme delle visite a cui un paziente è stato.
     */
    @JsonIgnore
    @OneToMany(mappedBy = "paziente", fetch = FetchType.EAGER)
    private Set<Visita> elencoVisite = new HashSet<>();


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
    public Paziente(final LocalDate dataNascita,
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
                sesso,
        Role.PAZIENTE);

    }


    /**
     * Metodo che permette di aggiungere una singola elencoVisite al set.
     * @param visita
     */
    public void addSingolaVisita(Visita visita){
        this.elencoVisite.add(visita);

    }

}
