package C15.dev.model.entity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;


/**
 *
 * @author Leopoldo Todisco, Carlo Venditto
 * Creato il: 29/12/2022
 * Questa Java Persistence Entity rappresenta un indirizzo che sarà utilizzato dagli utenti registrati e dalla visita.
 * La classe ha un id autogenerato, una via, numero civico, città, CAP, provincia.
 * Si noti che grazie all'utilizzo di lombok non c'è bisogno di scrivere costruttori,getters,setters e toString.
 *
 */
@Entity
@Table(name = "indirizzo")
public class Indirizzo implements Serializable {
    /**
     * Rappresenta l'id autogenerato.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * Stringa che rappresenta la città dell'indirizzo
     */
    private String citta;
    /**
     * Numero Intero che rappresenta il numero civico dell'indirizzo
     */
    private Integer nCivico;
    /**
     * Numero Intero che rappresenta il CAP dell'indirizzo
     * Deve essere un numero intero di 5 cifre.
     */
    private Integer CAP;
    /**
     * Stringa che rappresenta la provincia dell'indirizzo
     */
    private String provincia;
    /**
     * Stringa che rappresenta la via dell'indirizzo
     */
    private String via;

    /**
     * Lista di tutti gli Utenti Registrati che hanno residenza in un determinato indirizzo.
     */
    @OneToMany(mappedBy = "indirizzoResidenza")
    private List<UtenteRegistrato> elencoUtenti;

    /**
     *
     * @return citta
     * Metodo che restituisce la citta di un Indirizzo
     */
    public String getCitta() {
        return citta;
    }

    /**
     *
     * @param citta
     * Metodo che permette di inserire una citta di un Indirizzo
     */
    public void setCitta(String citta) {
        this.citta = citta;
    }

    /**
     *
     * @return nCivico
     * Metodo che restituisce il numero civico di un Indirizzo
     */
    public Integer getnCivico() {
        return nCivico;
    }

    /**
     *
     * @param nCivico
     * Metodo che permette di inserire il numero civico di un Indirizzo
     */
    public void setnCivico(Integer nCivico) {
        this.nCivico = nCivico;
    }

    /**
     *
     * @return CAP
     * Metodo che restituisce il cap di un Indirizzo
     */
    public Integer getCAP() {
        return CAP;
    }

    /**
     *
     * @param CAP
     * Metodo che permette di inserire il CAP di un Indirizzo
     */
    public void setCAP(Integer CAP) {
        this.CAP = CAP;
    }

    /**
     *
     * @return provincia
     * Metodo che restitusice la provincia di un indirizzo
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     *
     * @param provincia
     * Metodo per inserire una provincia in un indirizzo
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     *
     * @return via
     * Metodo che restituisce la via di un indirizzo
     */
    public String getVia() {
        return via;
    }

    /**
     *
     * @param via
     * Metodo per inserire una via in un indirizzo
     */
    public void setVia(String via) {
        this.via = via;
    }

    /**
     *
     * @return elencoUtenti
     * Metodo per ottenere l'elenco di tutti gli utenti che vivono in un determinato indirizzo
     */
    public List<UtenteRegistrato> getElencoUtenti() {
        return elencoUtenti;
    }

    /**
     *
     * @param elencoUtenti
     * Metodo per aggiungere l'elenco di utenti che hanno residenza in un determinato indirizzo
     */
    public void setElencoUtenti(List<UtenteRegistrato> elencoUtenti) {
        this.elencoUtenti = elencoUtenti;
    }
}