package C15.dev.model.entity;

import C15.dev.model.entity.enumeration.Stato;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Set;

/**
 * @author Vincenzo Arnone
 * Creato il 30/12/2022
 * questa è la classe che rappresenta le notifiche

 */
@Entity
public class Notifica  implements Serializable {

    /**
     * Campo relativo all'id della nota generato automaticamente
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Campo relativo alla data di scadenza della notifica
     **/
    @Column(nullable = false)
    private GregorianCalendar data_scadenza;


    /**
     * Campo relativo all'oggetto della notifica
     **/

    @Column(nullable = false)
    private String oggetto;

    /**
     * Campo relativo al contenuto della notifica
     **/

    @Column(nullable = false)
    private String testo;

    /**
     * Campo relativo allo stato della notifica
     **/

    @Column(nullable = false)
    private Stato stato;

    /**
     * Chiave esterna che fa riferimento alla classe Medico
     **/
    @ManyToOne
    @JoinColumn(name = "id_medico",referencedColumnName = "id",nullable = false)
    private Medico notifica_medico;

    /**
     * Chiave esterna che fa riferimento alla classe Paziente
     **/
    @ManyToMany(mappedBy = "notifica",
                fetch = FetchType.EAGER)
     private Set<Paziente> pazienti;

    /**
     *
     * @return pazienti
     * metodo che restituisce l'insieme dei pazienti che hanno ricvuto una notifica
     */
    public Set<Paziente> getPazienti(){
        return pazienti;
    }

    /**
     * Costruttore vuoto per la classe Notifica
     **/
    public Notifica() {
    }

    /**
     * Costruttore per la classe Notifica
     *
     **/
    /**
     * @param data_scadenza rappresenta la data di scadenza della notifica
     * @param oggetto rappresenta l'oggetto della notifica
     * @param testo rappresenta il testo della notifica
     * @param stato rappresenta lo stato della notifica
     * @param notifica_medico rappresenta il medico che ha inviato la notifica
     * @param pazienti rappresenta la lista di pazienti che hanno ricevuto una notifica
     */
    public Notifica( GregorianCalendar data_scadenza, String oggetto, String testo, Stato stato, Medico notifica_medico, Set<Paziente> pazienti) {
        this.data_scadenza = data_scadenza;
        this.oggetto = oggetto;
        this.testo = testo;
        this.stato = stato;
        this.notifica_medico= notifica_medico;
        this.pazienti = pazienti;
    }
    /**
     *
     * @return id
     * metodo che restituisce l'id della notifica
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     * metodo che permette di definire l'id della notifica
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return data_scadenza
     * metodo che restituisce la data di scadenza della notifica
     */
    public GregorianCalendar getData_scadenza() {
        return data_scadenza;
    }

    /**
     *
     * @param data_scadenza
     * metodo che permette di definire la data di scadenza della notifica
     */
    public void setData_scadenza(GregorianCalendar data_scadenza) {
        this.data_scadenza = data_scadenza;
    }

    /**
     *
     * @return oggetto
     * metodo che restituisce l'oggetto della notifica
     */
    public String getOggetto() {
        return oggetto;
    }

    /**
     *
     * @param oggetto
     * metodo che permette di definire l'oggetto della notifica
     */
    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    /**
     *
     * @return testo
     * metodo che restituisce il testo della notifica
     */
    public String getTesto() {
        return testo;
    }

    /**
     *
     * @param testo
     * metodo che permette di definire il testo della notifica
     */
    public void setTesto(String testo) {
        this.testo = testo;
    }

    /**
     *
     * @return stato
     * metodo che restituisce lo stato della notifica
     */
    public Stato getStato() {
        return stato;
    }

    /**
     *
     * @param stato
     * metodo che permette di definire lo stato della notifica
     */
    public void setStato(Stato stato) {
        this.stato = stato;
    }

    /**
     *
     * @return notifica_medico
     * metodo che restituisce il medico che ha inviato la notifica
     */
    public Medico getNotifica_medico() {
        return notifica_medico;
    }

    /**
     *
     * @param notifica_medico
     * metodo che permette di definire il medico che ha inviato la notifica
     */
    public void setNotifica_medico(Medico notifica_medico) {
        this.notifica_medico = notifica_medico;
    }




    /**
     *
     * @param pazienti
     * metodo che permette di definire i pazienti che hanno ricevuto le notifiche
     */
    public void setPazienti(Set<Paziente> pazienti) {
        this.pazienti = pazienti;
    }
}
