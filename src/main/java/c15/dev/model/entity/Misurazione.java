package c15.dev.model.entity;

import jakarta.persistence.*;
import org.checkerframework.checker.optional.qual.Present;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Alessandro Zoccola
 * Creato il: 30/12/2022
 * Questa è la classe relativa ad una Misurazione.
 * I campi sono: id autogenerato, data della misurazione
 */

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "misurazione")
public class Misurazione implements Serializable {
    /**
     * l'id viene generato in modo automatico.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * Campo relativo alla Data di misurazione nel formato GG-MM-AAAA HH:mm.
     * Invariante: la data della misurazione deve essere maggiore
     * o uguale alla data corrente.
     */
    @Column(name = "data_misurazione", nullable = false)
    @Present
    private GregorianCalendar dataMisurazione;
    /**
     * Campo relativo alla relazione tra pazienti e misurazioni.
     */
    @ManyToOne
    @JoinColumn(name = "id_paziente",
            referencedColumnName = "id", nullable = false)
    private Paziente paziente;
    /**
     * Campo relativo alla relazione tra dispositivo e misurazioni.
     */
    @ManyToOne
    @JoinColumn(name = "id_dispositivo_medico",
            referencedColumnName = "id", nullable = false)
    private DispositivoMedico dispositivoMedico;

    /**
     * @param data rappresenta la data della misurazione
     * @param paziente rappresenta il paziente coinvolto nella misurazione
     * @param dispositivo rappresenta il dispositivo medico con cui è stata
     *effettuata la misurazione
     */
    public Misurazione(GregorianCalendar data, Paziente paziente,
                       DispositivoMedico dispositivo) {
        this.dataMisurazione = data;
        this.paziente = paziente;
        this.dispositivoMedico = dispositivo;
    }

    /**
     * Costruttore vuoto per Misurazione.
     */
    public Misurazione(){

    }

    /**
     *
     * @return id
     * metodo che restituisce l'id della misurazione.
     */
    public long getId(){
        return this.id;
    }

    /**
     *
     * @return dataMisurazione
     * metodo che restituisce la data della misurazione.
     */
    public GregorianCalendar getDataMisurazione() {
        return this.dataMisurazione;
    }

    /**
     *
     * @param data
     * Metodo che permette di inserire la data della misurazione.
     *
     */
    public void setDataMisurazione(GregorianCalendar data) {
        this.dataMisurazione = data;
    }

    /**
     *
     * @return dataMisurazione
     * Metodo che permette di restituire il paziente a cui afferisce
     * la misurazione.
     *
     */
    public Paziente getPaziente() {
        return paziente;
    }

    /**
     *
     * @param paziente
     * Metodo che permette di impostare il paziente della misurazione.
     *
     */
    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
    }

    /**
     *
     * @return dispositivoMedico
     * Metodo che permette di restituire il dispositivo medico da cui è stata
     * effettuata la misurazione.
     *
     */
    public DispositivoMedico getDispositivoMedico() {
        return dispositivoMedico;
    }

    /**
     *
     * @param dispositivo
     * Metodo che permette di impostare il dispositivo medico della
     * misurazione.
     *
     */
    public void setDispositivoMedico(DispositivoMedico dispositivo) {
        this.dispositivoMedico = dispositivo;
    }


}
