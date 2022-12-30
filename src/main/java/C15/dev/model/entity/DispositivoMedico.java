package C15.dev.model.entity;

import C15.dev.model.entity.enumeration.Categoria;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.GregorianCalendar;

/**
 * @author Mario Cicalese
 * Creato il 30/12/2022
 * Questa è la classe relativa al dispositivo medico utilizzato da un paziente per effettuare delle misurazioni
 * I campi sono id (generato), dataRegistrazione, descrizione, numeroSeriale, disponibile, categoria,paziente
 */

@Entity
@Table(name = "Dispositivo_medico")
public class DispositivoMedico implements Serializable {
    /**
     * Campo relativo all'id del dispositivo medico generato automaticamente
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Campo relativo alla data di registrazione del dispositivo medico
     * Invariante : la data deve essere inferiore o uguale rispetto la data corrente
     */
    @Column(nullable = false)
    private GregorianCalendar dataRegistrazione;
    /**
     * Campo relativo alla descrizione del dispositivo medico
     */
    @Column(nullable = false,length = 100)
    private String descrizione;
    /**
     * Campo relativo al numero seriale del dispositivo medico
     * Invariante : deve essere composta da 30 caratteri
     */
    @Column(nullable = false,unique = true,length = 30)
    private String numeroSeriale;
    /**
     * Campo relativo a se il dispositivo medico è disponibile per essere assegnato a un paziente
     */
    @Column(nullable = false)
    private Boolean disponibile;
    /**
     * Campo relativo alla categoria di appartenenza del dispositivo medico
     */
    @Column(nullable = false)
    private Categoria categoria;
    /**
     * Campo (chiave esterna) relativo al paziente a cui è assegnato il paziente
     */
    @ManyToOne
    @JoinColumn(name = "id_paziente",referencedColumnName = "id", nullable = true)
    private Paziente paziente;

    /**
     * costruttre vuoto per la classe DispositivoMedico
     */
    public DispositivoMedico() {
    }

    /**
     *
     * @param dataRegistrazione rappresenta la data di registrazione del dispositivo medico
     * @param descrizione rappresenta la descrizione del dispositivo medico
     * @param numeroSeriale rappresenta il numero seriale del dispositivo medico
     * @param disponibile rappresenta se il dispositivo medico è disponibile o meno
     * @param categoria rappresenta la categoria di appartenenza del dispositivo medico
     * @param paziente rappresenta il paziente a cui è assegnato il dispositivo medico
     */
    public DispositivoMedico(GregorianCalendar dataRegistrazione, String descrizione, String numeroSeriale, Boolean disponibile, Categoria categoria, Paziente paziente) {
        this.dataRegistrazione = dataRegistrazione;
        this.descrizione = descrizione;
        this.numeroSeriale = numeroSeriale;
        this.disponibile = disponibile;
        this.categoria = categoria;
        this.paziente = paziente;
    }

    /**
     *
     * @return id
     * metodo che restituisce l'id del dispositivo medico
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     * metodo che permette di definire l'id del dispositivo medico
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return dataRegistrazione
     * metodo che restituisce la data di registrazione del dispositivo medico
     */
    public GregorianCalendar getDataRegistrazione() {
        return dataRegistrazione;
    }

    /**
     *
     * @param dataRegistrazione
     * metodo che permette di definire la data d registrazione del dispositivo medico
     */
    public void setDataRegistrazione(GregorianCalendar dataRegistrazione) {
        this.dataRegistrazione = dataRegistrazione;
    }

    /**
     *
     * @return descrizione
     * metodo che restituisce la descrizione del dispositivo medico
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     *
     * @param descrizione
     * metodo che permette di definire la descrizione del dispositivo medico
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     *
     * @return numeroSeriale
     * metodo che restituisce il numero seriale del dispositivo medico
     */
    public String getNumeroSeriale() {
        return numeroSeriale;
    }

    /**
     *
     * @param numeroSeriale
     * metodo che permette di definire il numero seriale del dispositivo medico
     */
    public void setNumeroSeriale(String numeroSeriale) {
        this.numeroSeriale = numeroSeriale;
    }

    /**
     *
     * @return disponibile
     * metodo che restituisce la disponibilità o meno del dispositivo medico
     */
    public Boolean getDisponibile() {
        return disponibile;
    }

    /**
     *
     * @param disponibile
     * metodo che permette di definire la disponibilità del dispositivo medico
     */
    public void setDisponibile(Boolean disponibile) {
        this.disponibile = disponibile;
    }

    /**
     *
     * @return categoria
     * metodo che restituisce la categoria del dispositivo medico
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     *
     * @param categoria
     * metodo che permette di definire la categoria del dispositivo medico
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     *
     * @return paziente
     * metodo che restituisce il paziente a cui è assegnato il dispositivo medico
     */
    public Paziente getPaziente() {
        return paziente;
    }

    /**
     *
     * @param paziente
     * metodo che permette di definire il paziente a cui è assegnato il dispositivo medico
     */
    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
    }

    @Override
    public String toString() {
        return "DispositivoMedico{" +
                "id=" + id +
                ", dataRegistrazione=" + dataRegistrazione +
                ", descrizione='" + descrizione + '\'' +
                ", numeroSeriale='" + numeroSeriale + '\'' +
                ", disponibile=" + disponibile +
                ", categoria=" + categoria +
                ", paziente=" + paziente +
                '}';
    }
}
