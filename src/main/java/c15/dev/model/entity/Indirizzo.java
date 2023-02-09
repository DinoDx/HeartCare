package c15.dev.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;


/**
 *
 * @author Leopoldo Todisco, Carlo Venditto.
 * Creato il: 29/12/2022.
 * Questa Java Persistence Entity rappresenta un indirizzo,
 * esso sarà utilizzato dagli utenti registrati e dalla elencoVisite.
 * La classe ha un id autogenerato, una via, numero civico,
 * città, cap, provincia.
 *
 */
@Entity
@Table(name = "indirizzo")
@Getter
@Setter
public final class Indirizzo implements Serializable {
    /**
     * Rappresenta l'id autogenerato.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Stringa che rappresenta la città dell'indirizzo.
     */
    @NotBlank
    @NotNull
    private String citta;

    /**
     * Numero Intero che rappresenta il numero civico dell'indirizzo.
     */
    @NotNull
    private String nCivico;

    /**
     * Numero Intero che rappresenta il cap dell'indirizzo.
     * deve essere un numero intero di 5 cifre.
     */
    @NotNull
    @Pattern(regexp = "^[0-9]*$")
    @Size(min = 5, max = 5)
    private String cap;

    /**
     * Stringa che rappresenta la provincia dell'indirizzo.
     */
    @NotBlank
    @NotNull
    private String provincia;

    /**
     * Stringa che rappresenta la via dell'indirizzo.
     */
    @NotNull
    @NotBlank
    private String via;

    /**
     * Lista di tutti gli Utenti Registrati che hanno residenza.
     * in un determinato indirizzo.
     */
    @OneToMany(mappedBy = "indirizzoResidenza")
    @JsonManagedReference
    private List<UtenteRegistrato> elencoUtenti;

    /**
     * Lista di tutte le visite che hanno luogo in un determinato indirizzo.
     */
    @OneToMany(mappedBy = "indirizzoVisita")
    @JsonManagedReference
    private List<Visita> elencoVisite;

    /**
     * Costruttore vuoto.
     */
    public Indirizzo() {
    }

    /**
     *
     * @param cit
     * @param numeroCivico
     * @param codiceAvviamentoPostale
     * @param prov
     * @param via
     * Costruttore comprensivo di tutti i campi.
     */
    public Indirizzo(final String cit,
                     final String numeroCivico,
                     final String codiceAvviamentoPostale,
                     final String prov,
                     final String via) {
        this.citta = cit;
        this.nCivico = numeroCivico;
        this.cap = codiceAvviamentoPostale;
        this.provincia = prov;
        this.via = via;
    }
    /**
     * Metodo equals.
     * @param o oggetto da confrontare.
     * @return booleano.
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Indirizzo indirizzo)) {
            return false;
        }
        return Objects.equals(id, indirizzo.id)
                && Objects.equals(getCitta(),
                indirizzo.getCitta()) && Objects.equals(getNCivico(),
                indirizzo.getNCivico()) && Objects.equals(getCap(),
                indirizzo.getCap()) && Objects.equals(getProvincia(),
                indirizzo.getProvincia()) && Objects.equals(getVia(),
                indirizzo.getVia()) && Objects.equals(getElencoUtenti(),
                indirizzo.getElencoUtenti())
                && Objects.equals(getElencoVisite(),
                indirizzo.getElencoVisite());
    }

    /**
     * Metodo hashCode.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id,
                getCitta(),
                getNCivico(),
                getCap(),
                getProvincia(),
                getVia(),
                getElencoUtenti(),
                getElencoVisite());
    }

}
