package c15.dev.model.entity;

import c15.dev.utils.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


import java.time.LocalDate;
import java.util.*;

/**
 * @author carlo.
 * Creato il: 29/12/2022.
 * Questa è la classe relativa a un Medico.
 */
@Entity
@Setter
@SuperBuilder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
public class Medico extends UtenteRegistrato {
    /**
     * Lista che contiene l'elenco dei pazienti che sono stati
     * assegnati al medico in questione
     */
    @OneToMany(mappedBy = "medico", fetch = FetchType.LAZY)
    @JsonManagedReference("paziente-medico")
    private List<Paziente> elencoPazienti = new ArrayList<>();

    /**
     * Insieme delle note destinate al medico in questione.
     */
    @OneToMany(mappedBy = "medico", fetch = FetchType.LAZY)
    private Set<Nota> note;

    /**
     * Questo campo indica la lista di visite in cui un medico è coinvolto.
     */

    @JsonManagedReference
    @JsonIgnore
    @OneToMany(mappedBy = "medico", fetch = FetchType.LAZY)
    private Set<Visita> elencoVisite;

    /**
     * costruttore vuoto.
     */
    public Medico() throws Exception {
        super();
    }

    /**
     *
     * @param dataDiNascita
     * @param codiceFiscale
     * @param numeroTelefono
     * @param password
     * @param email
     * @param nome
     * @param cognome
     * @param genere
     *
     * costruttore per Medico.
     */
    public Medico(final LocalDate dataDiNascita,
                  final String codiceFiscale,
                  final String numeroTelefono,
                  final String password,
                  final String email,
                  final String nome,
                  final String cognome,
                  final String genere) throws Exception {
        super(dataDiNascita,
                codiceFiscale,
                numeroTelefono,
                password,
                email,
                nome,
                cognome,
                genere,
                Role.MEDICO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medico medico)) return false;
        return Objects.equals(getElencoPazienti(), medico.getElencoPazienti()) && Objects.equals(getNote(), medico.getNote()) && Objects.equals(getElencoVisite(), medico.getElencoVisite());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getElencoPazienti(), getNote(), getElencoVisite());
    }
}
