package C15.dev.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * @author Leopoldo Todisco
 * Creato il: 29/12/2022
 * Questa è la classe relativa a un Utente Registrato.
 * I campi sono: id autogenerato, data di nascita, codice fiscale, numero di telefono, password, email, cognome, nome, genere
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "utente_registrato")
public class UtenteRegistrato implements Serializable {
    /**
     * id generato in modo automatico.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * Campo relativo alla Data di nascita nel formato GG-MM-AAAA
     * Invariante: la data di nascita deve essere inferiore alla data corrente
     */

    @Column(nullable = false)
    private Date dataDiNascita;
    /**
     * Campo relativo al Codice Fiscale.
     */

    @Column(nullable = false, length = 16)
    private String codiceFiscale;
    /**
     * Campo relativo al numero di telefono
     * Invariante: deve avere dimensione pari a 10 caratteri
     */

    @Column(nullable = false, length = 10)
    private String numeroTelefono;
    /**
     * Rappresenta la password di un Utente Registrato.
     */

    @Column(nullable = false, length = 32)
    private byte[] password;
    /**
     * Rappresenta l'email di un Utente Registrato
     */

    @Column(nullable = false, length = 32)
    private String email;
    /**
     * Rappresenta il nome di un Utente Registrato
     */

    @Column(nullable = false, length = 32)
    private String nome;
    /**
     * Rappresenta il cognome di un Utente Registrato
     */

    @Column(nullable = false, length = 32)
    private String cognome;
    /**
     * Rappresenta il sesso di un Utente Registrato
     * Invariante: può essere o M o F
     */

    @Column(nullable = false, length = 1)
    private char genere;

    /**
     * Rappresenta l'indirizzo di residenza di un Utente
     */
    @ManyToOne
    @JoinColumn(name="id_indirizzo", referencedColumnName = "id")
    private Indirizzo indirizzoResidenza;

    /**
     * Costruttore vuoto per UtenteRegistrato
     */
    public UtenteRegistrato() {

    }

    /**
     * @param dataDiNascita rappresenta la data di nascita di un admin
     * @param codiceFiscale rappresenta il codice fiscale di un admin
     * @param numeroTelefono rappresenta il numero di telefono di un admin
     * @param password rappresenta la password in formato Stringa di un admin
     * @param email rappresenta l'email di un admin
     * @param nome rappresenta il nome di un admin
     * @param cognome rappresenta il cognome di un admin
     * @param genere rappresenta il genere di un admin
     */
    public UtenteRegistrato( Date dataDiNascita,
                  String codiceFiscale,
                  String numeroTelefono,
                  String password,
                  String email, String nome,
                  String cognome,
                  char genere) {
        this.dataDiNascita = dataDiNascita;
        this.codiceFiscale = codiceFiscale;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.genere = genere;

        /*
         * In questo blocco si converte la stringa "password" in un array di bytes
         * per poi applicare l'algoritmo di crittografia SHA-256
         * Per questo motivo il campo password è un array di bytes e non String
         */
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
            this.password = msgDigest.digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 
     * @return id 
     * metodo che restituisce l'id dell'utente 
     */
    public long getId(){
        return id;
    }

    /**
     * 
     * @return dataDiNascita 
     * metodo che restituisce la data di nascita dell'utente
     */
    public Date getDataDiNascita() {
        return dataDiNascita;
    }

    /**
     * 
     * @param dataDiNascita
     * metodo che permette di inserire la data di nascita
     * 
     */
    public void setDataDiNascita(Date dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    /**
     * 
     * @return codiceFiscale
     * Metodo che restituisce il codice fiscale
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * 
     * @param codiceFiscale 
     * metodo per inserire il codice fiscale
     */
    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    /**
     * 
     * @return numeroDiTelefono
     * Metodo che restitusice il numero di telefono
     */
    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    /**
     * 
     * @param numeroTelefono
     * Metodo che permette di inserire il numero di telefono
     */
    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    /**
     * 
     * @return password
     * Metodo che restituisce, come array di bytes, la password di un utente
     */
    public byte[] getPassword() {
        return password;
    }

    /**
     * Setter per la password implementando l'algoritmo SHA-256
     * @param password la password da settare
     */
    public void setPassword(String password) {
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("SHA-256");
            this.password = msgDigest.digest(password.getBytes());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @return email
     * Metodo che restituisce l'email di un utente
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * Metodo che permette di inserisce un'email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * @return nome
     * Metodo che restituisce il nome di un UtenteRegistrato
     */
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     * Metodo che permette di inserire il nome di un UtenteRegistrato
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return cognome
     * Metodo che restituisce il cognome di un UtenteRegistrato
     */
    public String getCognome() {
        return cognome;
    }

    /**
     *
     * @param cognome
     * Metodo che permette di inserire il cognome di un UtenteRegistrato
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     *
     * @return genere
     * Metodo che restituisce il genere di un UtenteRegistrato
     */
    public char getGenere() {
        return genere;
    }

    /**
     *
     * @param genere
     * Metodo che permette di inserisce il genere nel profilo utente.
     */
    public void setGenere(char genere) {
        this.genere = genere;
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
     * @param indirizzoResidenza
     * Metodo per collegare un indirizzo a un utente
     */
    public void setIndirizzoResidenza(Indirizzo indirizzoResidenza) {
        this.indirizzoResidenza = indirizzoResidenza;
    }
}