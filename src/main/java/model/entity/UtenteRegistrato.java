package model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * @author leopoldotodisco
 * Creato il: 29/12/2022
 * Questa è la classe relativa a un Utente Registrato.
 * I campi sono: id autogenerato, data di nascita, codice fiscale, numero di telefono, password, email, cognome, nome, genere
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Table(name = "utente_registrato")
public class UtenteRegistrato implements Serializable {
    /**
     * id generato in modo automatico.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * Campo relativo alla Data di nascita nel formato GG-MM-AAAA
     * Invariante: la data di nascita deve essere inferiore alla data corrente
     */
    @NonNull
    @Column(nullable = false)
    private Date dataDiNascita;
    /**
     * Campo relativo al Codice Fiscale.
     */
    @NonNull
    @Column(nullable = false, length = 16)
    private String codiceFiscale;
    /**
     * Campo relativo al numero di telefono
     * Invariante: deve avere dimensione pari a 10 caratteri
     */
    @NonNull
    @Column(nullable = false, length = 10)
    private String numeroTelefono;
    /**
     * Rappresenta la password di un Utente Registrato.
     */
    @NonNull
    @Column(nullable = false, length = 32)
    private byte[] password;
    /**
     * Rappresenta l'email di un Utente Registrato
     */
    @NonNull
    @Column(nullable = false, length = 32)
    private String email;
    /**
     * Rappresenta il nome di un Utente Registrato
     */
    @NonNull
    @Column(nullable = false, length = 32)
    private String nome;
    /**
     * Rappresenta il cognome di un Utente Registrato
     */
    @NonNull
    @Column(nullable = false, length = 32)
    private String cognome;
    /**
     * Rappresenta il sesso di un Utente Registrato
     * Invariante: può essere o M o F
     */
    @NonNull
    @Column(nullable = false, length = 1)
    private char genere;

    /**
     * Rappresenta l'indirizzo di residenza di un Utente
     */
    @ManyToOne
    @JoinColumn(name="id_indirizzo", referencedColumnName = "id")
    private Indirizzo indirizzoResidenza;

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
    public UtenteRegistrato(@NonNull Date dataDiNascita,
                 @NonNull String codiceFiscale,
                 @NonNull String numeroTelefono,
                 @NonNull String password,
                 @NonNull String email,
                 @NonNull String nome,
                 @NonNull String cognome,
                 @NonNull char genere) {
        this.dataDiNascita = dataDiNascita;
        this.codiceFiscale = codiceFiscale;
        this.numeroTelefono = numeroTelefono;
        this.email = email;
        this.nome = nome;
        this.cognome = cognome;
        this.genere = genere;

        /*
         * In questo blocco si converte la stringa password in un array di bytes
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
}