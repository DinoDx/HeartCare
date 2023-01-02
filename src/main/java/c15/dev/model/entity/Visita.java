package c15.dev.model.entity;

import c15.dev.model.entity.enumeration.StatoVisita;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * @author Vincenzo Arnone.
 * Creato il 30/12/2022.
 * Questa è la classe che rappresenta le visite.
 */
@Entity
public class Visita implements Serializable {
    /**
     * Campo relativo all'id della nota generato automaticamente.
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Campo relativo alla data della visita.
     **/
    @Column(nullable = false)
    private GregorianCalendar data;

    /**
     * Campo relativo allo stato della visita.
     **/
    @Column(nullable = false)
    private StatoVisita statoVisita;

    /**
     * Chiave esterna che fa riferimento alla classe Medico.
     **/
    @ManyToOne
    @JoinColumn(name = "id_medico",
                referencedColumnName = "id",
                nullable = false)
    private Medico medico;


    /**
     * Chiave esterna che fa riferimento alla classe Paziente
     **/
    @ManyToOne
    @JoinColumn(name = "id_paziente",
                referencedColumnName = "id",
                nullable = true)
    private Paziente paziente;


    /**
     * Chiave esterna che fa riferimento alla classe Indirizzo
     **/
    @ManyToOne
    @JoinColumn(name="id_indirizzo", referencedColumnName = "id")
    private Indirizzo indirizzoVisita;


    /**
     * Costruttore vuoto.
     **/
    public Visita() {
    }

    /**
     * @param data rappresenta la data della visita
     * @param statoVisita rappresenta lo stato della visita
     * @param medico rappresenta il medico con cui si fa la visita
     * @param paziente rappresenta il paziente che è coinvolto nella visita
     * @param indirizzoVisita rappresenta l'indirizzo in cui si effettuerà la visita
     */
    public Visita(final GregorianCalendar data,
                  final StatoVisita statoVisita,
                  final Medico medico,
                  final Paziente paziente,
                  final Indirizzo indirizzoVisita) {
        this.data = data;
        this.statoVisita = statoVisita;
        this.medico = medico;
        this.paziente = paziente;
        this.indirizzoVisita = indirizzoVisita;
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
     * @return data
     * metodo che restituisce la data della visita.
     */
    public GregorianCalendar getData() {
        return data;
    }

    /**
     *
     * @param data
     * metodo che permette di definire la data della visita.
     */
    public void setData(final GregorianCalendar data) {
        this.data = data;
    }

    /**
     *
     * @return stato
     * metodo che restituisce lo stato della visita.
     */
    public StatoVisita getStatoVisita() {
        return statoVisita;
    }

    /**
     *
     * @param statoVisita
     * metodo che permette di definire lo stato della visita.
     */
    public void setStatoVisita(final StatoVisita statoVisita) {
        this.statoVisita = statoVisita;
    }

    /**
     *
     * @return medico
     * metodo che restituisce il medico che effettua la visita.
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     *
     * @param medico
     * metodo che permette di definire il medico che effettua la visita
     */
    public void setMedico(final Medico medico) {
        this.medico = medico;
    }

    /**
     *
     * @return paziente
     * metodo che restituisce il paziente che effettua la visita
     */
    public Paziente getPaziente() {
        return paziente;
    }

    /**
     *
     * @param paziente
     * metodo che permette di definire il paziente coinvolto nella visita
     */
    public void setPaziente(final Paziente paziente) {
        this.paziente = paziente;
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
    public void setIndirizzoVisita(final Indirizzo indirizzoVisita) {
        this.indirizzoVisita = indirizzoVisita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Visita visita)) {
            return false;
        }
        return Objects.equals(getId(), visita.getId())
                && Objects.equals(getData(), visita.getData())
                && getStatoVisita() == visita.getStatoVisita()
                && Objects.equals(getMedico(), visita.getMedico())
                && Objects.equals(getPaziente(), visita.getPaziente())
                && Objects.equals(getIndirizzoVisita(), visita.getIndirizzoVisita());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                            getData(),
                            getStatoVisita(),
                            getMedico(),
                            getPaziente(),
                            getIndirizzoVisita());
    }
}
