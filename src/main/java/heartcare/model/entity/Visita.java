package heartcare.model.entity;

import heartcare.model.entity.enumeration.StatoVisita;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

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
     * Campo relativo alla data della elencoVisite.
     **/
    @Column(nullable = false)
    @NotNull
    @Future
    private GregorianCalendar data;

    /**
     * Campo relativo allo stato della elencoVisite.
     **/
    @Column(nullable = false)
    @NotNull
    private StatoVisita statoVisita;

    /**
     * Chiave esterna che fa riferimento alla classe Medico.
     **/
    @ManyToOne
    @JoinColumn(name = "id_medico",
                referencedColumnName = "id",
                nullable = false)
    @NotNull
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
    @NotNull
    private Indirizzo indirizzoVisita;


    /**
     * Costruttore vuoto.
     **/
    public Visita() {
    }

    /**
     * @param dataVisita rappresenta la data della elencoVisite
     * @param stato rappresenta lo stato della elencoVisite
     * @param med rappresenta il medico con cui si fa la elencoVisite
     * @param paz rappresenta il paziente che è coinvolto nella elencoVisite
     * @param indirizzo rappresenta l'indirizzo in cui si effettuerà la elencoVisite
     */
    public Visita(final GregorianCalendar dataVisita,
                  final StatoVisita stato,
                  final Medico med,
                  final Paziente paz,
                  final Indirizzo indirizzo) {
        this.data = dataVisita;
        this.statoVisita = stato;
        this.medico = med;
        this.paziente = paz;
        this.indirizzoVisita = indirizzo;
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
     * @return data
     * metodo che restituisce la data della elencoVisite.
     */
    public GregorianCalendar getData() {
        return data;
    }

    /**
     *
     * @param data
     * metodo che permette di definire la data della elencoVisite.
     */
    public void setData(final GregorianCalendar data) {
        this.data = data;
    }

    /**
     *
     * @return stato
     * metodo che restituisce lo stato della elencoVisite.
     */
    public StatoVisita getStatoVisita() {
        return statoVisita;
    }

    /**
     *
     * @param statoVisita
     * metodo che permette di definire lo stato della elencoVisite.
     */
    public void setStatoVisita(final StatoVisita statoVisita) {
        this.statoVisita = statoVisita;
    }

    /**
     *
     * @return medico
     * metodo che restituisce il medico che effettua la elencoVisite.
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     *
     * @param medico
     * metodo che permette di definire il medico che effettua la elencoVisite
     */
    public void setMedico(final Medico medico) {
        this.medico = medico;
    }

    /**
     *
     * @return paziente
     * metodo che restituisce il paziente che effettua la elencoVisite
     */
    public Paziente getPaziente() {
        return paziente;
    }

    /**
     *
     * @param paziente
     * metodo che permette di definire il paziente coinvolto nella elencoVisite
     */
    public void setPaziente(final Paziente paziente) {
        this.paziente = paziente;
    }

    /**
     *
     * @return indirizzoVisita
     * metodo che restituisce l' indirizzo della elencoVisite
     */
    public Indirizzo getIndirizzoVisita() {
        return indirizzoVisita;
    }

    /**
     *
     * @param indirizzoVisita
     * metodo che permette di definire l'indirizzo della elencoVisite
     */
    public void setIndirizzoVisita(final Indirizzo indirizzoVisita) {
        this.indirizzoVisita = indirizzoVisita;
    }

    /**
     * metodo equals
     * @param o
     * @return
     */
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

    /**
     * Metodo Hashcode.
     * @return
     */
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
