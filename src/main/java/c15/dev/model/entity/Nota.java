package c15.dev.model.entity;

import c15.dev.model.entity.enumeration.Autore;

import c15.dev.model.entity.enumeration.StatoNotifica;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * @author Mario Cicalese
 * Creato il 30/12/2022
 * Questa è la classe relativa a una nota scambiata tra un paziente e medico e viceversa
 * I campi sono id(generato), contenuto, dataPubblicazione, autore, medico(chiave esterna), paziente(chiave esterna)
**/
@Entity
public class Nota implements Serializable {
    /**
     * Campo relativo all'id della nota generato automaticamente
     **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Campo relativo al contenuto della nota
     **/
    @NotNull
    private String contenuto;
    /**
     * Campo relativo alla data di pubblicazione della nota
     * Invariante: la data deve essere inferiore o uguale alla data corrente
     **/
    @NotNull
    @Past
    private GregorianCalendar dataPubblicazione;
    /**
     * Campo relativo all'autore della nota
     **/
    @NotNull
    private Autore autore;

    /**
     * Campo relativo allo stato della nota
     */

    @NotNull
    @Column(nullable = false)
    private StatoNotifica statoNota;

    /**
     * Campo (chiave esterna) relativo al medico che ha scritto/ricevuto la nota
     **/
    @ManyToOne
    @JoinColumn(name = "id_medico",
            referencedColumnName = "id")
    @NotBlank
    private Medico medico;
    /**
     * Campo (chiave esterna) relativo al paziente che ha scritto/ricevuto la nota
     **/
    @ManyToOne
    @JoinColumn(name = "id_paziente",
            referencedColumnName = "id")
    @NotBlank
    private Paziente paziente;

    /**
     * Costruttore vuoto per la classe nota
     **/
    public Nota() {
    }

    /**
     * @param contenuto rappresenta il contenuto della nota
     * @param dataPubblicazione rappresenta la data di pubblicazione della nota
     * @param autore rappresenta chi ha scritto la nota
     * @param stato rappresenta lo stato della nota
     * @param medico rappresenta il medico che ha scritto/ricevuto la nota
     * @param paziente rappresenta il paziente che ha scritto/ricevuto la nota
     **/
    public Nota(final String contenuto,
                final GregorianCalendar dataPubblicazione,
                final Autore autore,
                final StatoNotifica stato,
                final Medico medico,
                final Paziente paziente) {
        this.contenuto = contenuto;
        this.dataPubblicazione = dataPubblicazione;
        this.autore = autore;
        this.statoNota = stato;
        this.medico = medico;
        this.paziente = paziente;
    }

    /**
     *
     * @return id
     * metodo che restituisce l'id della nota
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     * metodo che permette di definire l'id della nota
     */
    public void setId(final Long id) {
        this.id = id;
    }

    /**
     *
     * @return contenuto
     * metodo che restituisce il contenuto della nota
     */
    public String getContenuto() {
        return contenuto;
    }

    /**
     *
     * @param contenuto
     * metodo che permette di definire il contenuto della nota
     */
    public void setContenuto(final String contenuto) {
        this.contenuto = contenuto;
    }

    /**
     *
     * @return dataPubblicazione
     * metodo che restituisce la data di pubblicazione della nota
     */
    public GregorianCalendar getDataPubblicazione() {
        return dataPubblicazione;
    }

    /**
     *
     * @param dataPubblicazione
     * metodo che permette di definire la data di pubblicazione della nota
     */
    public void setDataPubblicazione(final GregorianCalendar dataPubblicazione) {
        this.dataPubblicazione = dataPubblicazione;
    }

    /**
     *
     * @return autore
     * metodo che permette di restituire l'autore della nota
     */
    public Autore getAutore() {
        return autore;
    }

    /**
     *
     * @param autore
     * metodo che permette di definire l'autore della nota
     */
    public void setAutore(final Autore autore) {
        this.autore = autore;
    }

    /**
     *
     * @return statoNota
     * metodo che permette di restituire lo stato della nota
     */
    public StatoNotifica getStatoNota() {
        return statoNota;
    }

    /**
     *
     * @param statoNota
     * metodo che permette di definire lo stato della nota
     */
    public void setStatoNota(StatoNotifica statoNota) {
        this.statoNota = statoNota;
    }

    /**
     *
     * @return medico
     * metodo che permette di restituire il medico che ha scritto/ricevuto la nota
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     *
     * @param medico
     * metodo che permette di definire il medico che ha scritto/ricevuto la nota
     */
    public void setMedico(final Medico medico) {
        this.medico = medico;
    }

    /**
     *
     * @return paziente
     * metodo che permette di restituire il paziente che ha scritto/ricevuto la nota
     */
    public Paziente getPaziente() {
        return paziente;
    }

    /**
     *
     * @param paziente
     * metodo che permette di definire il paziente che ha scritto/ricevuto la nota
     */
    public void setPaziente(final Paziente paziente) {
        this.paziente = paziente;
    }

    /**
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Nota{"
                + "id=" + id
                + ", contenuto='" + contenuto + '\''
                + ", dataPubblicazione=" + dataPubblicazione
                + ", autore=" + autore
                + ", medico=" + medico
                + ", paziente=" + paziente
                + '}';
    }
}
