package C15.dev.model.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;


/**
 * @author carlo
 * Creato il: 29/12/2022
 * Questa è la classe relativa a un Paziente.
 */


@Entity
public class Paziente extends UtenteRegistrato{
    /**
     * Questo campo rappresenta il nome del caregiver
     */
    private String nomeCaregiver;

    /**
     * Questo campo rappresenta il cognome del caregiver
     */
    private String cognomeCaregiver;
    /**
     * Questo campo rappresenta l'email del caregiver
     */
    private String emailCaregiver;
    @ManyToOne
    @JoinColumn(name = "id_medico",referencedColumnName = "id")
    @Column(nullable = false)
    private Medico medico;

    @OneToMany(mappedBy = "paziente",fetch = FetchType.EAGER)
    private Set<Nota> note;

    @OneToMany(mappedBy = "paziente", fetch = FetchType.EAGER)
    private Set<DispositivoMedico> dispositivoMedico;

    @OneToMany(mappedBy = "paziente", fetch = FetchType.EAGER)
    private Set<Misurazione> misurazione;

    @OneToMany(mappedBy = "stato_paziente", fetch = FetchType.EAGER)
    private Set<Visita> visita;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "avviso",
            joinColumns = @JoinColumn(name = "paziente_id"),
            inverseJoinColumns = @JoinColumn(name = "notifica_id")
    )
    private Set<Notifica> notifica;
    public Set<Notifica> getNotifica(){
        return notifica;
    }
    public void setNotifca(Set<Notifica> notifica){
        this.notifica = notifica;
    }

    /**
     * costruttore vuoto
     */
    public Paziente() {
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
     * @param nomeCaregiver
     * @param cognomeCaregiver
     * @param emailCaregiver
     * @param medico
     *
     * costruttore per il Paziente
     */
    public Paziente(Date dataDiNascita, String codiceFiscale, String numeroTelefono, String password, String email,
                    String nome, String cognome, char genere, String nomeCaregiver, String cognomeCaregiver,
                    String emailCaregiver, Medico medico) {
        super(dataDiNascita, codiceFiscale, numeroTelefono, password, email, nome, cognome, genere);
        this.nomeCaregiver = nomeCaregiver;
        this.cognomeCaregiver = cognomeCaregiver;
        this.emailCaregiver = emailCaregiver;
        this.medico = medico;
    }

    /**
     * @return nomeCaregiver
     *  Metodo che restituisce il nome del caregiver
     */
    public String getNomeCaregiver() {
        return nomeCaregiver;
    }

    /**
     *
     * @param nomeCaregiver
     * Metodo che permette di inserire il nome di un caregiver
     */
    public void setNomeCaregiver(String nomeCaregiver) {
        this.nomeCaregiver = nomeCaregiver;
    }

    /**
     *
     * @return cognomeCaregiver
     * Metodo che restituisce il cognome del caregiver
     */
    public String getCognomeCaregiver() {
        return cognomeCaregiver;
    }

    /**
     *
     * @param cognomeCaregiver
     * Metodo che  permette di inserire il cognome del caregiver
     */
    public void setCognomeCaregiver(String cognomeCaregiver) {
        this.cognomeCaregiver = cognomeCaregiver;
    }

    /**
     *
     * @return emailCaregiver
     * Metodo che restituisce l'email del caregiver
     */
    public String getEmailCaregiver() {
        return emailCaregiver;
    }

    /**
     *
     * @param emailCaregiver
     * Metodo che permette di inserire l'email del caregiver
     */
    public void setEmailCaregiver(String emailCaregiver) {
        this.emailCaregiver = emailCaregiver;
    }

    /**
     *
     * @return medico
     * Metodo che restituisce il medico di un paziente
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     *
     * @param medico
     * Metodo che permette di inserire il medico ad un paziente
     */
    public void setMedico(Medico medico) {
        this.medico = medico;
    }


    /**
     *
     * @return visita
     * Metodo che restituisce le visite di un paziente
     */
    public Set<Visita> getVisita() {
        return visita;
    }

    /**
     *
     * @param visita
     * Metodo che permette di inserire le visite di un paziente
     */
    public void setVisita(Set<Visita> visita) {
        this.visita = visita;
    }
}
