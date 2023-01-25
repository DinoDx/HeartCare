package c15.dev.model.entity;

import c15.dev.utils.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author carlo.
 * Creato il: 29/12/2022.
 * Questa è la classe relativa a un Medico.
 */
@Entity
@Setter
@Getter
public class Medico extends UtenteRegistrato {
    /**
     * Lista che contiene l'elenco dei pazienti che sono stati
     * assegnati al medico in questione
     */
    @OneToMany(mappedBy = "medico")
    private List<Paziente> elencoPazienti = new ArrayList<>();

    /**
     * Insieme delle note destinate al medico in questione.
     */
    @OneToMany(mappedBy = "medico", fetch = FetchType.EAGER)
    private Set<Nota> note;

    /**
     * Questo campo indica la lista di visite in cui un medico è coinvolto.
     */
    @OneToMany(mappedBy = "medico", fetch = FetchType.EAGER)
    private List<Visita> elencoVisite;

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


    /**
     * Metodo per inserire una singola elencoVisite alla lista.
     * @param visita
     */
    public void addSingolaVisita(Visita visita){
        this.elencoVisite.add(visita);
    }
}
