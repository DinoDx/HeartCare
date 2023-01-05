package c15.dev.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;


import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author carlo.
 * Creato il: 29/12/2022.
 * Questa è la classe relativa a un Medico.
 */
@Entity
public class Medico extends UtenteRegistrato {
    /**
     * Lista che contiene l'elenco dei pazienti che sono stati
     * assegnati al medico in questione
     */
    @OneToMany(mappedBy = "medico")
    private List<Paziente> elencoPazienti;

    /**
     * Insieme delle note destinate al medico in questione.
     */
    @OneToMany(mappedBy = "medico", fetch = FetchType.EAGER)
    private Set<Nota> note;

    /**
     * Insieme delle notifiche destinate al medico in questione.
     */
    @OneToMany(mappedBy = "notificaMedico", fetch = FetchType.EAGER)
    private List<Notifica> notifica;

    /**
     * Questo campo indica la lista di visite in cui un medico è coinvolto.
     */
    @OneToMany(mappedBy = "medico", fetch = FetchType.EAGER)
    private List<Visita> visita;

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
    public Medico(final Date dataDiNascita,
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
                genere);
    }

    /**
     *
     * @return elencoPazienti
     * Metodo che restituisce l'elenco dei pazienti di un medico.
     */
    public List<Paziente> getElencoPazienti() {
        return elencoPazienti;
    }

    /**
     *
     * @param elencoPazienti
     * Metodo che permette di inserire una lista di paziente.
     */
    public void setElencoPazienti(List<Paziente> elencoPazienti) {
        this.elencoPazienti = elencoPazienti;
    }

    /**
     *
     * @return notifica
     * Metodo che restituisce l'elenco delle notifiche inviate da un medico.
     */
    public List<Notifica> getNotifica() {
        return notifica;
    }

    /**
     *
     * @param notifica
     * Metodo che permette di inserire una lista di notifiche.
     */
    public void setNotifica(List<Notifica> notifica) {
        this.notifica = notifica;
    }

    /**
     *
     * @return visita
     * Metodo che restituisce l'elenco delle visite di un medico.
     */
    public List<Visita> getVisita() {
        return visita;
    }

    /**
     *
     * @param visita
     * Metodo che permette di inserire una lista di visite.
     */
    public void setVisita(List<Visita> visita) {
        this.visita = visita;
    }
}
