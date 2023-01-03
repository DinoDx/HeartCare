package c15.dev.model.entity;

import c15.dev.model.entity.enumeration.StatoNotifica;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.Set;

/**
 * @author Vincenzo Arnone.
 * Creato il 30/12/2022.
 * questa è la classe che rappresenta le notifiche.
 */
@Entity
public class Notifica implements Serializable {

    /**
     * Campo relativo all'id della nota generato automaticamente.
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Campo relativo alla data di scadenza della notifica.
     **/
    @Column(nullable = false)
    private GregorianCalendar dataScadenza;


    /**
     * Campo relativo all'oggetto della notifica.
     **/
    @Column(nullable = false)
    private String oggetto;

    /**
     * Campo relativo al contenuto della notifica.
     **/
    @Column(nullable = false)
    private String testo;

    /**
     * Campo relativo allo statoNotifica della notifica.
     **/
    @Column(nullable = false)
    private StatoNotifica statoNotifica;

    /**
     * Chiave esterna che fa riferimento alla classe Medico.
     **/
    @ManyToOne
    @JoinColumn(name = "id_medico",
                referencedColumnName = "id",
                nullable = false)
    private Medico notificaMedico;

    /**
     * Chiave esterna che fa riferimento alla classe Paziente.
     **/
    @ManyToMany(mappedBy = "notifica",
                fetch = FetchType.EAGER)
    private Set<Paziente> pazienti;

    /**
     *
     * @return pazienti
     * metodo che restituisce l'insieme dei pazienti che hanno ricvuto una notifica.
     */
    public Set<Paziente> getPazienti(){
        return pazienti;
    }

    /**
     * Costruttore vuoto per la classe Notifica.
     **/
    public Notifica() {
    }

    /**
     * @param dataScadenza rappresenta la data di scadenza della notifica
     * @param oggetto rappresenta l'oggetto della notifica
     * @param testo rappresenta il testo della notifica
     * @param statoNotifica rappresenta lo statoNotifica della notifica
     * @param notificaMedico rappresenta il medico che ha inviato la notifica
     * @param pazienti rappresenta la lista di pazienti che hanno ricevuto una notifica
     *
     * Costruttore per la classe Notifica.
     */
    public Notifica(final GregorianCalendar dataScadenza,
                    final String oggetto,
                    final String testo,
                    final StatoNotifica statoNotifica,
                    final Medico notificaMedico,
                    final Set<Paziente> pazienti) {
        this.dataScadenza = dataScadenza;
        this.oggetto = oggetto;
        this.testo = testo;
        this.statoNotifica = statoNotifica;
        this.notificaMedico = notificaMedico;
        this.pazienti = pazienti;
    }

    /**
     *
     * @return id
     * metodo che restituisce l'id della notifica.
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     * metodo che permette di definire l'id della notifica.
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     *
     * @return dataScadenza
     * metodo che restituisce la data di scadenza della notifica.
     */
    public GregorianCalendar getDataScadenza() {
        return dataScadenza;
    }

    /**
     *
     * @param dataScadenza
     * metodo che permette di definire la data di scadenza della notifica.
     */
    public void setDataScadenza(final GregorianCalendar dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    /**
     *
     * @return oggetto
     * metodo che restituisce l'oggetto della notifica.
     */
    public String getOggetto() {
        return oggetto;
    }

    /**
     *
     * @param oggetto
     * metodo che permette di definire l'oggetto della notifica.
     */
    public void setOggetto(final String oggetto) {
        this.oggetto = oggetto;
    }

    /**
     *
     * @return testo
     * metodo che restituisce il testo della notifica.
     */
    public String getTesto() {
        return testo;
    }

    /**
     *
     * @param testo
     * metodo che permette di definire il testo della notifica.
     */
    public void setTesto(final String testo) {
        this.testo = testo;
    }

    /**
     *
     * @return statoNotifica
     * metodo che restituisce lo statoNotifica della notifica.
     */
    public StatoNotifica getStato() {
        return statoNotifica;
    }

    /**
     *
     * @param statoNotifica
     * metodo che permette di definire lo statoNotifica della notifica.
     */
    public void setStato(StatoNotifica statoNotifica) {
        this.statoNotifica = statoNotifica;
    }

    /**
     *
     * @return notificaMedico
     * metodo che restituisce il medico che ha inviato la notifica.
     */
    public Medico getNotificaMedico() {
        return notificaMedico;
    }

    /**
     *
     * @param notificaMedico
     * metodo che permette di definire il medico che ha inviato la notifica.
     */
    public void setNotificaMedico(Medico notificaMedico) {
        this.notificaMedico = notificaMedico;
    }


    /**
     *
     * @param pazienti
     * metodo che permette di definire i pazienti che hanno ricevuto le notifiche.
     */
    public void setPazienti(Set<Paziente> pazienti) {
        this.pazienti = pazienti;
    }

    /**
     *
     * @param otherNotifica
     * @return boolean
     * Metodo equals per vedere se due istanze della classe Notifica sono uguali.
     */
    @Override
    public boolean equals(Object otherNotifica) {
        if (this == otherNotifica) {
            return true;
        }
        if (!(otherNotifica instanceof Notifica notifica)) {
            return false;
        }
        return Objects.equals(getId(), notifica.getId())
                && Objects.equals(getDataScadenza(), notifica.getDataScadenza())
                && Objects.equals(getOggetto(), notifica.getOggetto())
                && Objects.equals(getTesto(), notifica.getTesto())
                && getStato() == notifica.getStato()
                && Objects.equals(getNotificaMedico(), notifica.getNotificaMedico())
                && Objects.equals(getPazienti(), notifica.getPazienti());
    }

}
