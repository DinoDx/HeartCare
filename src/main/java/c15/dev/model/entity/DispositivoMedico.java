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
    @Size(min = LENGTH_30, max = LENGTH_30)
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
     * @param dataRegistrazione rappresenta la data di registrazione.
     * @param descrizione rappresenta la descrizione.
     *                      del dispositivo medico.
     * @param numeroSeriale rappresenta il numero seriale.
     *                      del dispositivo medico.
     * @param disponibile indica se il dispositivo medico è disponibile o no.
     * @param categoria rappresenta la categoria di appartenenza.
     *                  del dispositivo medico.
     * @param paziente rappresenta il paziente a cui è assegnato.
     *                 il dispositivo medico.
     */
    public DispositivoMedico(final LocalDate dataRegistrazione,
                             final String descrizione,
                             final String numeroSeriale,
                             final Boolean disponibile,
                             final String categoria,
                             final Paziente paziente) {
        this.dataRegistrazione = dataRegistrazione;
        this.descrizione = descrizione;
        this.numeroSeriale = numeroSeriale;
        this.disponibile = true;
        this.categoria = categoria;
        this.paziente = paziente;
    }

    /**
     * Metodo per l'avvio di una misurazione.
     * @return stringa che contiene la misurazione.
     */

    public String avvioMisurazione() {
        var dispositivoMedicoStub = new DispositivoMedicoStub();
        String misurazione = " ";

        switch(categoria){
            case "ECG" : misurazione = dispositivoMedicoStub
                    .MisurazioneHolterECGStub();
                break;
            case "Saturimetro" : misurazione = dispositivoMedicoStub
                    .MisurazioneSaturazioneStub() ;
                break;
            case "Coagulometro" : misurazione =
                    dispositivoMedicoStub.MisurazioneCoagulazioneStub();
                break;
            case "Misuratore glicemico" : misurazione = dispositivoMedicoStub
                    .MisurazioneGlicemicaStub();
                break;
            case "Misuratore di pressione" : {
                LocalDate currentDate = new Date()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                LocalDate birthday = paziente.getDataDiNascita();//toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                misurazione = dispositivoMedicoStub.
                        MisurazionePressioneStub(Period
                                .between(birthday,currentDate)
                                .getYears());
            }
                break;
            case "Enzimi cardiaci" : misurazione = dispositivoMedicoStub
                    .MisurazioneEnzimiCardiaciStub(paziente.getGenere());
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
        if (this == o) return true;
        if (!(o instanceof DispositivoMedico that)) return false;
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
