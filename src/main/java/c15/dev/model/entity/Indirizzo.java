package c15.dev.model.entity;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GenerationType;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;


/**
 *
 * @author Leopoldo Todisco, Carlo Venditto.
 * Creato il: 29/12/2022.
 * Questa Java Persistence Entity rappresenta un indirizzo,
 * esso sarà utilizzato dagli utenti registrati e dalla elencoVisite.
 * La classe ha un id autogenerato, una via, numero civico,
 * città, cap, provincia.
 *
 */
@Entity
@Table(name = "indirizzo")
public final class Indirizzo implements Serializable {
    /**
     * Rappresenta l'id autogenerato.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Stringa che rappresenta la città dell'indirizzo.
     */
    private String citta;

    /**
     * Numero Intero che rappresenta il numero civico dell'indirizzo.
     */
    private Integer nCivico;

    /**
     * Numero Intero che rappresenta il cap dell'indirizzo
     * deve essere un numero intero di 5 cifre.
     */
    private Integer cap;

    /**
     * Stringa che rappresenta la provincia dell'indirizzo.
     */
    private String provincia;

    /**
     * Stringa che rappresenta la via dell'indirizzo.
     */
    private String via;

    /**
     * Lista di tutti gli Utenti Registrati che hanno residenza
     * in un determinato indirizzo.
     */
    @OneToMany(mappedBy = "indirizzoResidenza")
    private List<UtenteRegistrato> elencoUtenti;

    /**
     * Lista di tutte le visite che hanno luogo in un determinato indirizzo.
     */
    @OneToMany(mappedBy = "indirizzoVisita")
    private List<Visita> elencoVisite;

    /**
     * Costruttore vuoto.
     */
    public Indirizzo() {
    }

    /**
     *
     * @param citta
     * @param nCivico
     * @param cap
     * @param provincia
     * @param via
     * Costruttore comprensivo di tutti i campi.
     */
    public Indirizzo(final String citta,
                     final Integer nCivico,
                     final Integer cap,
                     final String provincia,
                     final String via) {
        this.citta = citta;
        this.nCivico = nCivico;
        this.cap = cap;
        this.provincia = provincia;
        this.via = via;
    }

    /**
     *
     * @return citta
     * Metodo che restituisce la citta di un Indirizzo.
     */
    public String getCitta() {
        return citta;
    }

    /**
     *
     * @param citta
     * Metodo che permette di inserire una citta di un Indirizzo.
     */
    public void setCitta(final String citta) {
        this.citta = citta;
    }

    /**
     *
     * @return nCivico
     * Metodo che restituisce il numero civico di un Indirizzo.
     */
    public Integer getnCivico() {
        return nCivico;
    }

    /**
     *
     * @param nCivico
     * Metodo che permette di inserire il numero civico di un Indirizzo.
     */
    public void setnCivico(final Integer nCivico) {
        this.nCivico = nCivico;
    }

    /**
     *
     * @return cap
     * Metodo che restituisce il cap di un Indirizzo.
     */
    public Integer getCap() {
        return cap;
    }

    /**
     *
     * @param cap
     * Metodo che permette di inserire il cap di un Indirizzo.
     */
    public void setCap(final Integer cap) {
        this.cap = cap;
    }

    /**
     *
     * @return provincia
     * Metodo che restitusice la provincia di un indirizzo.
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     *
     * @param provincia
     * Metodo per inserire una provincia in un indirizzo.
     */
    public void setProvincia(final String provincia) {
        this.provincia = provincia;
    }

    /**
     *
     * @return via
     * Metodo che restituisce la via di un indirizzo.
     */
    public String getVia() {
        return via;
    }

    /**
     *
     * @param via
     * Metodo per inserire una via in un indirizzo.
     */
    public void setVia(final String via) {
        this.via = via;
    }

    /**
     *
     * @return elencoUtenti
     * Metodo per ottenere l'elenco di tutti gli utenti che vivono
     * in un determinato indirizzo.
     */
    public List<UtenteRegistrato> getElencoUtenti() {
        return elencoUtenti;
    }

    /**
     *
     * @param elencoUtenti
     * Metodo per aggiungere l'elenco di utenti che hanno residenza
     * in un determinato indirizzo.
     */
    public void setElencoUtenti(List<UtenteRegistrato> elencoUtenti) {
        this.elencoUtenti = elencoUtenti;
    }

    /**
     *
     * @return Visista
     * Metodo per ottenere l'elenco di tutte le visite che si effettuano
     * in un determinato indirizzo.
     */
    public List<Visita> getElencoVisite() {
        return elencoVisite;
    }

    /**
     *
     * @param elencoVisite
     * Metodo per aggiungere l'elenco delle visite che si effettuano
     * in un determinato indirizzo.
     */
    public void setElencoVisite(List<Visita> elencoVisite) {
        this.elencoVisite = elencoVisite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Indirizzo indirizzo)) {
            return false;
        }
        return Objects.equals(id, indirizzo.id)
                && Objects.equals(getCitta(), indirizzo.getCitta())
                && Objects.equals(getnCivico(), indirizzo.getnCivico())
                && Objects.equals(getCap(), indirizzo.getCap())
                && Objects.equals(getProvincia(), indirizzo.getProvincia())
                && Objects.equals(getVia(), indirizzo.getVia())
                && Objects.equals(getElencoUtenti(), indirizzo.getElencoUtenti())
                && Objects.equals(getElencoVisite(), indirizzo.getElencoVisite());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id,
                getCitta(),
                getnCivico(),
                getCap(),
                getProvincia(),
                getVia(),
                getElencoUtenti(),
                getElencoVisite());
    }

}
