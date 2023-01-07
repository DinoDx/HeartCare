package heartcare.model.entity;

import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

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
    @NotBlank
    @NotNull
    private String citta;

    /**
     * Numero Intero che rappresenta il numero civico dell'indirizzo.
     */
    @NotNull
    private Integer nCivico;

    /**
     * Numero Intero che rappresenta il cap dell'indirizzo
     * deve essere un numero intero di 5 cifre.
     */
    @NotNull
    private Integer cap;

    /**
     * Stringa che rappresenta la provincia dell'indirizzo.
     */
    @NotBlank
    @NotNull
    private String provincia;

    /**
     * Stringa che rappresenta la via dell'indirizzo.
     */
    @NotNull
    @NotBlank
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
     * @param cit
     * @param numeroCivico
     * @param codiceAvviamentoPostale
     * @param prov
     * @param via
     * Costruttore comprensivo di tutti i campi.
     */
    public Indirizzo(final String cit,
                     final Integer numeroCivico,
                     final Integer codiceAvviamentoPostale,
                     final String prov,
                     final String via) {
        this.citta = cit;
        this.nCivico = numeroCivico;
        this.cap = codiceAvviamentoPostale;
        this.provincia = prov;
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
     * @param cit
     * Metodo che permette di inserire una citta di un Indirizzo.
     */
    public void setCitta(final String cit) {
        this.citta = cit;
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
     * @param numeroCivico
     * Metodo che permette di inserire il numero civico di un Indirizzo.
     */
    public void setnCivico(final Integer numeroCivico) {
        this.nCivico = numeroCivico;
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
     * @param codiceAvviamentoPostale
     * Metodo che permette di inserire il cap di un Indirizzo.
     */
    public void setCap(final Integer codiceAvviamentoPostale) {
        this.cap = codiceAvviamentoPostale;
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
     * @param prov
     * Metodo per inserire una provincia in un indirizzo.
     */
    public void setProvincia(final String prov) {
        this.provincia = prov;
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
     * @param ListaUtenti
     * Metodo per aggiungere l'elenco di utenti che hanno residenza
     * in un determinato indirizzo.
     */
    public void setElencoUtenti(List<UtenteRegistrato> ListaUtenti) {
        this.elencoUtenti = ListaUtenti;
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
     * @param ListaVisite
     * Metodo per aggiungere l'elenco delle visite che si effettuano
     * in un determinato indirizzo.
     */
    public void setElencoVisite(List<Visita> ListaVisite) {
        this.elencoVisite = ListaVisite;
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
