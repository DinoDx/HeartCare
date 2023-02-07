package c15.dev.model.entity;

import c15.dev.gestioneMisurazione.misurazioneAdapter.DispositivoMedicoStub;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * @author Mario Cicalese.
 * Creato il 30/12/2022.
 * Questa è la classe relativa al dispositivo medico utilizzato da un paziente.
 * per effettuare delle misurazioni.
 * I campi sono id (auto generato), dataRegistrazione.
 * descrizione, numeroSeriale, disponibile, categoria,paziente.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Dispositivo_medico")
public class DispositivoMedico {
    /**
     * Costante il cui valore è 30.
     * Viene usata per indicare la lunghezza massima di alcuni campi nel DB.
     * Necessaria a causa del checkstyle.
     */
    private static final int LENGTH_30 = 30;

    /**
     * Costante il cui valore è 10.
     * Viene usata per indicare la lunghezza massima di alcuni campi nel DB.
     * Necessaria a causa del checkstyle.
     */
    private static final int LENGTH_10 = 10;

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
    private LocalDate dataRegistrazione;
    /**
     * Campo relativo alla descrizione del dispositivo medico.
     */
    @NotNull
    private String descrizione;

    /**
     * Campo relativo al numero seriale del dispositivo medico.
     * Invariante: deve essere composta da 30 caratteri.
     */
    @Column(unique = true )
    @NotNull
    @Size(min = LENGTH_10, max = LENGTH_30)
    private String numeroSeriale;

    /**
     * Campo relativo a se il dispositivo medico è disponibile.
     * per essere assegnato a un paziente.
     */
    @NotNull
    private Boolean disponibile;
    /**
     * Campo relativo alla categoria di appartenenza del dispositivo medico.
     */
    @NotNull
    private String categoria;
    /**
     * Campo (chiave esterna) relativo al paziente a cui è assegnato.
     * il paziente.
     */
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_paziente",
            referencedColumnName = "id")
    @JsonBackReference("paziente-dispositivo")
    private Paziente paziente;

    /**
     * Campo che indica l'insieme delle misurazioni generate da un dispositivo.
     */
    @OneToMany(mappedBy = "dispositivoMedico", fetch = FetchType.LAZY)
    @JsonManagedReference("dispositivo-misurazione")
    private Set<Misurazione> misurazione;

    /**
     * Costruttre vuoto per la classe DispositivoMedico.
     */
    public DispositivoMedico() {
    }

    /**
     *
     * @param data rappresenta la data di registrazione.
     * @param desc rappresenta la descrizione.
     *                      del dispositivo medico.
     * @param nSeriale rappresenta il numero seriale.
     *                      del dispositivo medico.
     * @param disp indica se il dispositivo medico è disponibile o no.
     * @param cat rappresenta la categoria di appartenenza.
     *                  del dispositivo medico.
     * @param paz rappresenta il paziente a cui è assegnato.
     *                 il dispositivo medico.
     */
    public DispositivoMedico(final LocalDate data,
                             final String desc,
                             final String nSeriale,
                             final Boolean disp,
                             final String cat,
                             final Paziente paz) {
        this.dataRegistrazione = data;
        this.descrizione = desc;
        this.numeroSeriale = nSeriale;
        this.disponibile = true;
        this.categoria = cat;
        this.paziente = paz;
    }

    /**
     * Metodo per l'avvio di una misurazione.
     * @return stringa che contiene la misurazione.
     */

    public String avvioMisurazione() {
        var dispositivoMedicoStub = new DispositivoMedicoStub();
        String misurazione = " ";

        switch(categoria) {
            case "ECG": misurazione = dispositivoMedicoStub
                    .misurazioneHolterECGStub();
                break;
            case "Saturimetro" : misurazione = dispositivoMedicoStub
                    .misurazioneSaturazioneStub() ;
                break;
            case "Coagulometro" : misurazione =
                    dispositivoMedicoStub.misurazioneCoagulazioneStub();
                break;
            case "Misuratore glicemico" : misurazione = dispositivoMedicoStub
                    .misurazioneGlicemicaStub();
                break;
            case "Misuratore di pressione" : {
                LocalDate currentDate = new Date()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                LocalDate birthday = paziente.getDataDiNascita();
                misurazione = dispositivoMedicoStub.
                        misurazionePressioneStub(Period
                                .between(birthday, currentDate)
                                .getYears());
            }
                break;
            case "Enzimi cardiaci": misurazione = dispositivoMedicoStub
                    .misurazioneEnzimiCardiaciStub(paziente.getGenere());
                break;
        }
        return misurazione;
    }

    /**
     * Metodo equals.
     * @param o oggetto da confrontare.
     * @return booleano.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DispositivoMedico that)) {
            return false;
        }
        return Objects.equals(getId(),
                that.getId()) && Objects.equals(getDataRegistrazione(),
                that.getDataRegistrazione()) && Objects.equals(getDescrizione(),
                that.getDescrizione()) && Objects.equals(getNumeroSeriale(),
                that.getNumeroSeriale()) && Objects.equals(getDisponibile(),
                that.getDisponibile()) && Objects.equals(getCategoria(),
                that.getCategoria()) && Objects.equals(getPaziente(),
                that.getPaziente()) && Objects.equals(getMisurazione(),
                that.getMisurazione());
    }

    /**
     * Metodo hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getDataRegistrazione(),
                getDescrizione(),
                getNumeroSeriale(),
                getDisponibile(),
                getCategoria(),
                getPaziente(),
                getMisurazione());
    }
}
