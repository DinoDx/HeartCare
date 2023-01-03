package c15.dev.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Inheritance;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;


import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

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
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "utente_registrato")
public class UtenteRegistrato implements Serializable {
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
    @NotNull
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Campo relativo alla Data di nascita nel formato GG-MM-AAAA.
     * Invariante: la data di nascita deve essere inferiore o uguale alla data corrente.
     */
    @Column(nullable = false)
    @NotBlank
    @Past
    private Date dataDiNascita;

    /**
     * Campo relativo al Codice Fiscale.
     * Invariante: deve rispettare l'espressione regolare.
     */
    @Column(nullable = false, length = LENGTH_16)
    @NotNull
    @NotBlank
    @Pattern(regexp = "^[A-Z]{6}[A-Z0-9]{2}[A-Z][A-Z0-9]{2}[A-Z][A-Z0-9]{3}[A-Z]$",
            message = "regexp codice fiscale non rispettata")
    private String codiceFiscale;

    /**
     * Campo relativo al numero di telefono.
     * Invariante: deve avere dimensione pari a 10 caratteri.
     */
    @Column(nullable = false, length = LENGTH_10)
    @NotBlank
    @Pattern(regexp = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]\\d{3}[\\s.-]\\d{4}$")
    @Size(min=LENGTH_10, max=LENGTH_10)
    private String numeroTelefono;

    /**
     * Rappresenta la password di un Utente Registrato.
     * La sua espressione regolare richiede che ci siano:
     *  almeno 8 caratteri, massimo 16
     *  almeno una maiuscola
     *  almeno un numero
     *  almeno un carattere speciale.
     */
    @Column(nullable = false, length = LENGTH_32)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$",
            message = "Campo password in utente non rispetta la regexp")
    private byte[] password;

    /**
     * Rappresenta l'email di un Utente Registrato.
     */
    @Column(nullable = false, length = LENGTH_32)
    @NotNull
    @Email
    private String email;

    /**
     * Rappresenta il nome di un Utente Registrato.
     */
    @Column(nullable = false, length = LENGTH_32)
    @Size(min = LENGTH_1, max = LENGTH_32)
    private String nome;

    /**
     * Rappresenta il cognome di un Utente Registrato.
     */
    @Column(nullable = false, length = LENGTH_32)
    @Size(min = LENGTH_1, max = LENGTH_32)
    private String cognome;

    /**
     * Rappresenta il sesso di un Utente Registrato.
     * Invariante: può essere o M o F.
     */
    @Column(nullable = false, length = LENGTH_1)
    @Pattern(regexp = "^M$|^F$",
             message = "Genere deve rispettare l'espressione regolare")
    private char genere;

    /**
     * Rappresenta l'indirizzo di residenza di un Utente.
     */
    @ManyToOne
    @JoinColumn(name = "id_indirizzo",
                referencedColumnName = "id")
    @NotNull
    private Indirizzo indirizzoResidenza;

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
    public UtenteRegistrato(final Date dataNascita,
                  final String codFiscale,
                  final String nTelefono,
                  final String pass,
                  final String indirizzoEmail,
                  final String nome,
                  final String cognome,
                  final char sesso) {
        this.dataDiNascita = dataNascita;
        this.codiceFiscale = codFiscale;
        this.numeroTelefono = nTelefono;
        this.email = indirizzoEmail;
        this.nome = nome;
        this.cognome = cognome;
        this.genere = sesso;

        /*
         * In questo blocco si converte la stringa "password" in un array di bytes
         * per poi applicare l'algoritmo di crittografia SHA-256
         * Per questo motivo il campo password è un array di bytes e non String
         */
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
            this.password = msgDigest.digest(pass.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * @return id 
     * metodo che restituisce l'id dell'utente.
     */
    public long getId() {
        return id;
    }

    /**
     * 
     * @return dataDiNascita 
     * metodo che restituisce la data di nascita dell'utente.
     */
    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    /**
     * 
     * @param dataNascita
     * metodo che permette di inserire la data di nascita.
     * 
     */
    public void setDataDiNascita(final Date dataNascita) {
        this.dataDiNascita = dataNascita;
    }

    /**
     * 
     * @return codiceFiscale
     * Metodo che restituisce il codice fiscale.
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * 
     * @param codFiscale
     * metodo per inserire il codice fiscale.
     */
    public void setCodiceFiscale(final String codFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * @return numeroDiTelefono
     * Metodo che restitusice il numero di telefono.
     */
    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    /**
     * @param nTelefono
     * Metodo che permette di inserire il numero di telefono.
     */
    public void setNumeroTelefono(final String nTelefono) {
        this.numeroTelefono = nTelefono;
    }

    /**
     * 
     * @return password
     * Metodo che restituisce, come array di bytes, la password di un utente.
     */
    public byte[] getPassword() {
        return password;
    }

    /**
     * Setter per la password implementando l'algoritmo SHA-256.
     * @param pass la password da settare
     */
    public void setPassword(final String pass) {
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
            this.password = msgDigest.digest(pass.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @return email
     * Metodo che restituisce l'email di un utente.
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param indirizzoEmail
     * Metodo che permette di inserisce un'email.
     */
    public void setEmail(final String indirizzoEmail) {
        this.email = indirizzoEmail;
    }

    /**
     * 
     * @return nome
     * Metodo che restituisce il nome di un UtenteRegistrato.
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     * Metodo che permette di inserire il nome di un UtenteRegistrato.
     */
    public void setNome(final String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return cognome
     * Metodo che restituisce il cognome di un UtenteRegistrato.
     */
    public String getCognome() {
        return cognome;
    }

    /**
     *
     * @param cog
     * Metodo che permette di inserire il cognome di un UtenteRegistrato.
     */
    public void setCognome(final String cog) {
        this.cognome = cog;
    }

    /**
     *
     * @return genere
     * Metodo che restituisce il genere di un UtenteRegistrato.
     */
    public char getGenere() {
        return genere;
    }

    /**
     *
     * @param gen
     * Metodo che permette di inserisce il genere nel profilo utente.
     */
    public void setGenere(final char gen) {
        this.genere = gen;
    }

    /**
     *
     * @return indirizzoResidenza
     * Metodo che restituisce l'indirizzo di residenza dell'utente.
     */
    public Indirizzo getIndirizzoResidenza() {
        return indirizzoResidenza;
    }

    /**
     *
     * @param indirizzo
     * Metodo per collegare un indirizzo a un utente.
     */
    public void setIndirizzoResidenza(final Indirizzo indirizzo) {
        this.indirizzoResidenza = indirizzo;
    }
}
