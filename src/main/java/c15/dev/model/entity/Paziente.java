package c15.dev.model.entity;


import c15.dev.utils.Role;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyGroup;

import java.time.LocalDate;
import java.util.*;


/**
 * @author carlo.
 * Creato il: 29/12/2022
 * Questa è la classe relativa a un Paziente.
 */
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @JsonBackReference("paziente-medico")
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
                fetch = FetchType.LAZY)
    private Set<Nota> note;

    /**
     * Questo campo indica l'insieme dei dispositivi medici che un paziente si.
     * assegna.
     */
    @OneToMany(mappedBy = "paziente",
            fetch = FetchType.LAZY)
    @JsonManagedReference("paziente-dispositivo")
    @JsonIgnore
    private Set<DispositivoMedico> dispositivoMedico;

    /**
     * Campo che indica l'insieme delle misurazioni che un paziente esegue.
     */
    @JsonManagedReference("misurazione-paziente")
    @OneToMany(mappedBy = "paziente", fetch = FetchType.LAZY)
    private Set<Misurazione> misurazione;

    /**
     * Campo che inidica l'insieme delle visite a cui un paziente è stato.
     */

    @JsonIgnore
    @OneToMany(mappedBy = "paziente", fetch = FetchType.LAZY)
    @JsonManagedReference("paziente-visite")
    private Set<Visita> elencoVisite;


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
     * Metodo equals.
     * @param o oggetto da confrontare.
     * @return booleano.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paziente paziente)) return false;
        return Objects.equals(getNomeCaregiver(),
                paziente.getNomeCaregiver())
                && Objects.equals(getCognomeCaregiver(),
                paziente.getCognomeCaregiver())
                && Objects.equals(getEmailCaregiver(),
                paziente.getEmailCaregiver())
                && Objects.equals(getMedico(), paziente.getMedico())
                && Objects.equals(getNote(), paziente.getNote())
                && Objects.equals(getDispositivoMedico(),
                paziente.getDispositivoMedico())
                && Objects.equals(getMisurazione(),
                paziente.getMisurazione())
                && Objects.equals(getElencoVisite(),
                paziente.getElencoVisite());
    }

    /**
     * Metodo hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getNomeCaregiver(),
                getCognomeCaregiver(),
                getEmailCaregiver(),
                getMedico(),
                getNote(),
                getDispositivoMedico(),
                getMisurazione(),
                getElencoVisite());
    }
}
