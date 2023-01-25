package c15.dev.model.entity;

import c15.dev.utils.Role;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Leopoldo Todisco.
 * Creato il: 29/12/2022.
 * Questa è la classe relativa a un Utente Registrato.
 * I campi sono: id autogenerato,
 *  data di nascita,
 *  codice fiscale,
 *  numero di telefono,
 *  password,
 *  email,
 *  cognome,
 *  nome,
 *  genere.
 */
@Entity
@SuperBuilder
@Setter
@Data
@Getter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "utente_registrato")
public class UtenteRegistrato implements Serializable, UserDetails {
    /**
     * Costante il cui valore è 30.
     * Viene usata per indicare la lunghezza massima di alcuni campi nel DB.
     * Necessaria a causa del checkstyle.
     */
    private static final int LENGTH_32 = 32;

    /**
     * Costante il cui valore è 100.
     * Viene usata per indicare la lunghezza massima di alcuni campi nel DB.
     * Necessaria a causa del checkstyle.
     */
    private static final int LENGTH_13 = 13;

    /**
     * Costante il cui valore è 100.
     * Viene usata per indicare la lunghezza massima di alcuni campi nel DB.
     * Necessaria a causa del checkstyle.
     */
    private static final int LENGTH_10 = 10;

    /**
     * Costante il cui valore è 100.
     * Viene usata per indicare la lunghezza massima di alcuni campi nel DB.
     * Necessaria a causa del checkstyle.
     */
    private static final int LENGTH_1 = 1;

    /**
     * Costante il cui valore è 100.
     * Viene usata per indicare la lunghezza massima di alcuni campi nel DB.
     * Necessaria a causa del checkstyle.
     */
    private static final int LENGTH_16 = 16;

    /**
     * id generato in modo automatico.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * Campo relativo alla Data di nascita nel formato GG-MM-AAAA.
     * Invariante: la data di nascita deve essere inferiore o uguale alla data corrente.
     */
    /*@NotNull
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataDiNascita;*/

    @NotNull
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataDiNascita;
    /**
     * Campo relativo al Codice Fiscale.
     * Invariante: deve rispettare l'espressione regolare.
     */
    @Column(length = LENGTH_16,unique = true)
    @NotNull
    @Pattern(regexp = "^[A-Z]{6}[A-Z0-9]{2}[A-Z][A-Z0-9]{2}[A-Z][A-Z0-9]{3}[A-Z]$",
            message = "regexp codice fiscale non rispettata")
    private String codiceFiscale;


    /**
     * Campo relativo al numero di telefono.
     * Invariante: deve avere dimensione pari a 10 caratteri.
     */
    @Column(unique = true)
    @NotNull
    @Pattern(regexp = "^((00|\\+)39[\\. ]??)??3\\d{2}[\\. ]??\\d{6,7}$")
    @Size(min=LENGTH_13, max=LENGTH_13)
    private String numeroTelefono;

    /**
     * Rappresenta la password di un Utente Registrato.
     * La sua espressione regolare richiede che ci siano:
     *  almeno 8 caratteri, massimo 16
     *  almeno una maiuscola
     *  almeno un numero
     *  almeno un carattere speciale.
     */
    @NotNull
    private String password;

    /**
     * Rappresenta l'email di un Utente Registrato.
     */
    @Column(unique = true)
    @NotNull
    @Email
    private String email;

    /**
     * Rappresenta il nome di un Utente Registrato.
     */
    @NotNull
    @Size(min = LENGTH_1, max = LENGTH_32)
    private String nome;

    /**
     * Rappresenta il cognome di un Utente Registrato.
     */
    @NotNull
    @Size(min = LENGTH_1, max = LENGTH_32)
    private String cognome;

    /**
     * Rappresenta il sesso di un Utente Registrato.
     * Invariante: può essere o M o F.
     */
    @NotNull
    @Size(min = 1, max = 1)
    @Pattern(regexp = "^M$|^F$")
    private String genere;

    /**
     * Rappresenta l'indirizzo di residenza di un Utente.
     */
    @ManyToOne
    @JoinColumn(name = "id_indirizzo",
                referencedColumnName = "id")
    private Indirizzo indirizzoResidenza;

    /**
     * Insieme delle notifiche relative a un utente.
     */
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "destinatario")
    private Set<Notifica> elencoNotifiche;

    @Enumerated(EnumType.STRING)
    private Role ruolo;

    /**
     * Costruttore vuoto per UtenteRegistrato.
     */
    public UtenteRegistrato() {

    }

    /**
     * @param dataNascita rappresenta la data di nascita di un utente
     * @param codFiscale rappresenta il codice fiscale di un utente
     * @param nTelefono rappresenta il numero di telefono di un utente
     * @param pass rappresenta la password in formato Stringa di un utente
     * @param indirizzoEmail rappresenta l'email di un utente
     * @param nome rappresenta il nome di un utente
     * @param cognome rappresenta il cognome di un utente
     * @param sesso rappresenta il genere di un utente
     */
    public UtenteRegistrato(final LocalDate dataNascita,
                  final String codFiscale,
                  final String nTelefono,
                  final String pass,
                  final String indirizzoEmail,
                  final String nome,
                  final String cognome,
                  final String sesso, final Role ruolo) throws Exception {
        this.dataDiNascita = dataNascita;
        this.codiceFiscale = codFiscale;
        this.numeroTelefono = nTelefono;
        this.email = indirizzoEmail;
        this.nome = nome;
        this.cognome = cognome;
        this.genere = sesso;
        this.ruolo = ruolo;

        String regexpPassword =
                "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])" +
                "[A-Za-z\\d@$!%*?&]{8,16}$";

        /**La password deve rispettare l'espressione regolare*/
        if(pass.matches(regexpPassword)) {
            this.password = pass;
        }
        else {
            throw new Exception("La password non rispetta l'espressione regolare");
        }
    }
    public void setPassword(final String pass) throws Exception {
            this.password = pass;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(ruolo.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
