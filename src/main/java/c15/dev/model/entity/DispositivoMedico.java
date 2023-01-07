package c15.dev.model.entity;

import c15.dev.gestioneMisurazione.misurazioneAdapter.DispositivoMedicoStub;
import c15.dev.model.entity.enumeration.Categoria;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

/**
 * @author Mario Cicalese.
 * Creato il 30/12/2022.
 * Questa è la classe relativa al dispositivo medico utilizzato da un paziente
 * per effettuare delle misurazioni.
 * I campi sono id (auto generato), dataRegistrazione,
 * descrizione, numeroSeriale, disponibile, categoria,paziente.
 */
@Entity
@Table(name = "Dispositivo_medico")
public class DispositivoMedico implements Serializable {
    /**
     * Costante il cui valore è 30.
     * Viene usata per indicare la lunghezza massima di alcuni campi nel DB.
     * Necessaria a causa del checkstyle.
     */
    private static final int LENGTH_30 = 30;

    /**
     * Costante il cui valore è 100.
     * Viene usata per indicare la lunghezza massima di alcuni campi nel DB.
     * Necessaria a causa del checkstyle.
     */
    private static final int LENGTH_100 = 100;

    /**
     * Campo relativo all'id del dispositivo medico generato automaticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Campo relativo alla data di registrazione del dispositivo medico.
     * Invariante: la data deve essere <= rispetto la data corrente.
     */
    @NotNull
    private GregorianCalendar dataRegistrazione;
    /**
     * Campo relativo alla descrizione del dispositivo medico.
     */
    @NotNull
    @Max(100)
    private String descrizione;

    /**
     * Campo relativo al numero seriale del dispositivo medico.
     * Invariante: deve essere composta da 30 caratteri.
     */
    @Column(unique = true )
    @NotNull
    @Size(min = LENGTH_30, max = LENGTH_30)
    private String numeroSeriale;

    /**
     * Campo relativo a se il dispositivo medico è disponibile
     * per essere assegnato a un paziente.
     */
    @NotNull
    private Boolean disponibile;
    /**
     * Campo relativo alla categoria di appartenenza del dispositivo medico.
     */
    @NotNull
    private Categoria categoria;
    /**
     * Campo (chiave esterna) relativo al paziente a cui è assegnato
     * il paziente
     */
    @ManyToOne
    @JoinColumn(name = "id_paziente",
            referencedColumnName = "id",
            nullable = true)
    private Paziente paziente;

    /**
     * Campo che indica l'insieme delle misurazioni generate da un dispositivo.
     */
    @OneToMany(mappedBy = "dispositivoMedico", fetch = FetchType.EAGER)
    private Set<Misurazione> misurazione;

    /**
     * Costruttre vuoto per la classe DispositivoMedico.
     */
    public DispositivoMedico() {
    }

    /**
     *
     * @param dataRegistrazione rappresenta la data di registrazione.
     * @param descrizione rappresenta la descrizione
     *                      del dispositivo medico.
     * @param numeroSeriale rappresenta il numero seriale
     *                      del dispositivo medico.
     * @param disponibile indica se il dispositivo medico è disponibile o no
     * @param categoria rappresenta la categoria di appartenenza
     *                  del dispositivo medico.
     * @param paziente rappresenta il paziente a cui è assegnato
     *                 il dispositivo medico.
     */
    public DispositivoMedico(final GregorianCalendar dataRegistrazione,
                             final String descrizione,
                             final String numeroSeriale,
                             final Boolean disponibile,
                             final Categoria categoria,
                             final Paziente paziente) {
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
    public void setId(final Long id) {
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
     * metodo che permette di definire la data di registrazione
     * del dispositivo medico
     */
    public void setDataRegistrazione(final GregorianCalendar dataRegistrazione) {
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
    public void setDescrizione(final String descrizione) {
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
    public void setNumeroSeriale(final String numeroSeriale) {
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
    public void setDisponibile(final Boolean disponibile) {
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
    public void setCategoria(final Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     *
     * @return paziente
     * metodo che restituisce il paziente a cui è assegnato
     * il dispositivo medico
     */
    public Paziente getPaziente() {
        return paziente;
    }

    /**
     *
     * @param paziente
     * metodo che permette di definire il paziente a cui è assegnato
     * il dispositivo medico
     */
    public void setPaziente(final Paziente paziente) {
        this.paziente = paziente;
    }

    public String avvioMisurazione(){
        DispositivoMedicoStub dispositivoMedicoStub = new DispositivoMedicoStub();
        String misurazione = " ";

        switch(categoria.getDisplayName()){
            case "ECG" : misurazione = dispositivoMedicoStub.MisurazioneHolterECGStub();
                break;
            case "Saturimetro" : misurazione = dispositivoMedicoStub.MisurazioneSaturazioneStub() ;
                break;
            case "Coagulometro" : misurazione = dispositivoMedicoStub.MisurazioneCoagulazioneStub();
                break;
            case "Misuratore glicemico" : misurazione = dispositivoMedicoStub.MisurazioneGlicemicaStub();
                break;
            case "Misuratore di pressione" : {
                LocalDate currentDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate birthday = paziente.getDataDiNascita().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                misurazione = dispositivoMedicoStub.MisurazionePressioneStub(Period.between(birthday,currentDate).getYears());
            }
                break;
            case "Enzimi cardiaci" : dispositivoMedicoStub.MisurazioneEnzimiCardiaciStub(paziente.getGenere());
                break;
        }
        return misurazione;
    }

    /**
     * Metodo per ottenere una stringa comprensiva di tutti i campi
     * dell'oggetto.
     * @return String
     */
    @Override
    public String toString() {
        return "DispositivoMedico{"
                + "id=" + id
                + ", dataRegistrazione=" + dataRegistrazione
                + ", descrizione='" + descrizione + '\''
                + ", numeroSeriale='" + numeroSeriale + '\''
                + ", disponibile=" + disponibile
                + ", categoria=" + categoria
                + ", paziente=" + paziente
                + '}';
    }
}
