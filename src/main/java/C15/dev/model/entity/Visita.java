package C15.dev.model.entity;

import C15.dev.model.entity.enumeration.Stato_Visita;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * @author Vincenzo Arnone
 * Creato il 30/12/2022
 * questa è la classe che rappresenta le visite

 */
@Entity
public class Visita implements Serializable {

    /**
     * Campo relativo all'id della nota generato automaticamente
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Campo relativo alla data della visita
     **/
    @Column(nullable = false)
    private GregorianCalendar data;


    /**
     * Campo relativo allo stato della visita
     **/
    @Column(nullable = false)
    private Stato_Visita stato_visita;

    /**
     * Chiave esterna che fa riferimento alla classe Medico
     **/
    @ManyToOne
    @JoinColumn(name = "id_medico",referencedColumnName = "id",nullable = false)
    private Medico stato_medico;


    /**
     * Chiave esterna che fa riferimento alla classe Paziente
     **/
    @ManyToOne
    @JoinColumn(name = "id_paziente",referencedColumnName = "id", nullable = true)
    private Paziente stato_paziente;


    /**
     * Chiave esterna che fa riferimento alla classe Indirizzo
     **/
    @ManyToOne
    @JoinColumn(name="id_indirizzo", referencedColumnName = "id")
    private Indirizzo indirizzoVisita;


    /**
     * Costruttore vuoto
     **/
    public Visita() {
    }

    /**
     * @param data rappresenta la data  della visita
     * @param stato_visita rappresenta lo stato della visita
     * @param stato_medico rappresenta il medico con cui si fa la visita
     * @param stato_paziente rappresenta il paziente che [ coinvolto nella visita
     @param indirizzoVisita rappresenta l'indirizzo in cui si effetturà la visita
     */
    public Visita( GregorianCalendar data, Stato_Visita stato_visita, Medico stato_medico, Paziente stato_paziente, Indirizzo indirizzoVisita) {

        this.data = data;
        this.stato_visita = stato_visita;
        this.stato_medico = stato_medico;
        this.stato_paziente = stato_paziente;
        this.indirizzoVisita = indirizzoVisita;
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
     * @return data
     * metodo che restituisce la data della visita
     */
    public GregorianCalendar getData() {
        return data;
    }

    /**
     *
     * @param data
     * metodo che permette di definire la data della visita
     */
    public void setData(GregorianCalendar data) {
        this.data = data;
    }

    /**
     *
     * @return stato
     * metodo che restituisce lo stato della visita
     */
    public Stato_Visita getStato_visita() {
        return stato_visita;
    }

    /**
     *
     * @param stato_visita
     * metodo che permette di definire lo stato della visita
     */
    public void setStato_visita(Stato_Visita stato_visita) {
        this.stato_visita = stato_visita;
    }

    /**
     *
     * @return stato_medico
     * metodo che restituisce il medico che effettua la visita
     */
    public Medico getStato_medico() {
        return stato_medico;
    }

    /**
     *
     * @param stato_medico
     * metodo che permette di definire il medico che effettua la visita
     */
    public void setStato_medico(Medico stato_medico) {
        this.stato_medico = stato_medico;
    }

    /**
     *
     * @return stato_paziente
     * metodo che restituisce il paziente che effettua la visita
     */
    public Paziente getStato_paziente() {
        return stato_paziente;
    }

    /**
     *
     * @param stato_paziente
     * metodo che permette di definire il paziente coinvolto nella visita
     */
    public void setStato_paziente(Paziente stato_paziente) {
        this.stato_paziente = stato_paziente;
    }

    /**
     *
     * @return indirizzoVisita
     * metodo che restituisce l' indirizzo della visita
     */
    public Indirizzo getIndirizzoVisita() {
        return indirizzoVisita;
    }

    /**
     *
     * @param indirizzoVisita
     * metodo che permette di definire l'indirizzo della visita
     */
    public void setIndirizzoVisita(Indirizzo indirizzoVisita) {
        this.indirizzoVisita = indirizzoVisita;
    }
}
