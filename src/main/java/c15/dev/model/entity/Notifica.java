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
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.Set;

/**
 * @author Vincenzo Arnone.
 * Creato il 30/12/2022.
 * Questa è la classe che rappresenta le notifiche.
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
    @FutureOrPresent
    @NotNull
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
    @NotBlank
    private String testo;

    /**
     * Campo relativo allo statoNotifica della notifica.
     **/
    @Column(nullable = false)
    @NotNull
    private StatoNotifica statoNotifica;

    /**
     * Chiave esterna che fa riferimento alla classe Medico.
     **/
    @ManyToOne
    @JoinColumn(name = "id_medico",
                referencedColumnName = "id")
    private Medico notificaMedico;

    /**
     * Chiave esterna che fa riferimento alla classe Paziente.
     **/
    @ManyToMany(mappedBy = "notifica",
                fetch = FetchType.EAGER)
    private Set<Paziente> pazienti;

    /**
     *
     * @return pazienti.
     * Metodo che restituisce l'insieme dei pazienti che hanno ricevuto una notifica.
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
     * @param dataDiScadenza rappresenta la data di scadenza della notifica
     * @param oggettoNotifica rappresenta l'oggetto della notifica
     * @param testoNotifica rappresenta il testo della notifica
     * @param stato rappresenta lo statoNotifica della notifica
     * @param medico rappresenta il medico che ha inviato la notifica
     * @param elencoPazienti rappresenta la lista di pazienti che hanno ricevuto una notifica
     *
     * Costruttore per la classe Notifica.
     */
    public Notifica(final GregorianCalendar dataDiScadenza,
                    final String oggettoNotifica,
                    final String testoNotifica,
                    final StatoNotifica stato,
                    final Medico medico,
                    final Set<Paziente> elencoPazienti) {
        this.dataScadenza = dataDiScadenza;
        this.oggetto = oggettoNotifica;
        this.testo = testoNotifica;
        this.statoNotifica = stato;
        this.notificaMedico = medico;
        this.pazienti = elencoPazienti;
    }

    /**
     *
     * @return id
     * Metodo che restituisce l'id della notifica.
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @return dataScadenza
     * Metodo che restituisce la data di scadenza della notifica.
     */
    public GregorianCalendar getDataScadenza() {
        return dataScadenza;
    }

    /**
     *
     * @param dataDiScadenza
     * Metodo che permette di definire la data di scadenza della notifica.
     */
    public void setDataScadenza(final GregorianCalendar dataDiScadenza) {
        this.dataScadenza = dataDiScadenza;
    }

    /**
     *
     * @return oggetto.
     * Metodo che restituisce l'oggetto della notifica.
     */
    public String getOggetto() {
        return oggetto;
    }

    /**
     *
     * @param oggettoNotifica
     * metodo che permette di definire l'oggetto della notifica.
     */
    public void setOggetto(final String oggettoNotifica) {
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
     * @param testoNotifica
     * metodo che permette di definire il testo della notifica.
     */
    public void setTesto(final String testoNotifica) {
        this.testo = testoNotifica;
        return;
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
     * @param stato
     * metodo che permette di definire lo statoNotifica della notifica.
     */
    public void setStato(StatoNotifica stato) {
        this.statoNotifica = stato;
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
     * @param medico
     * metodo che permette di definire il medico che ha inviato la notifica.
     */
    public void setNotificaMedico(Medico medico) {
        this.notificaMedico = medico;
    }


    /**
     *
     * @param elencoPazienti
     * metodo che permette di definire i pazienti che hanno ricevuto le notifiche.
     */
    public void setPazienti(Set<Paziente> elencoPazienti) {
        this.pazienti = elencoPazienti;
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
