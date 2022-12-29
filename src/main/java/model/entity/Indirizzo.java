package model.entity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;


/**
 *
 * @author leopoldotodisco
 * Creato il: 29/12/2022
 * Questa Java Persistence Entity rappresenta un indirizzo che sarà utilizzato dagli utenti registrati e dalla visita.
 * La classe ha un id autogenerato, una via, numero civico, città, CAP, provincia.
 * Si noti che grazie all'utilizzo di lombok non c'è bisogno di scrivere costruttori,getters,setters e toString.
 *
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "indirizzo")
public class Indirizzo implements Serializable {
    /**
     * Rappresenta l'id autogenerato.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    /**
     * Stringa che rappresenta la città dell'indirizzo
     */
    private String citta;
    /**
     * Numero Intero che rappresenta il numero civico dell'indirizzo
     */
    private Integer nCivico;
    /**
     * Numero Intero che rappresenta il CAP dell'indirizzo
     * Deve essere un numero intero di 5 cifre.
     */
    private Integer CAP;
    /**
     * Stringa che rappresenta la provincia dell'indirizzo
     */
    private String provincia;
    /**
     * Stringa che rappresenta la via dell'indirizzo
     */
    private String via;

    /**
     * Lista di tutti gli Utenti Registrati che hanno residenza in un determinato indirizzo.
     */
    @OneToMany(mappedBy = "indirizzoResidenza")
    @ToString.Exclude
    private List<UtenteRegistrato> elencoUtenti;
}