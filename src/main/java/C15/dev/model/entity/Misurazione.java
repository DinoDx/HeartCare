package C15.dev.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

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
     * Campo relativo alla Data di misurazione nel formato GG-MM-AAAA HH:mm
     * Invariante: la data della misurazione deve essere maggiore o uguale alla data corrente
     */

    @Column(nullable = false)
    private SimpleDateFormat dataMisurazione;

    @ManyToOne
    @JoinColumn(name = "id_paziente", referencedColumnName = "id", nullable = false)
    private Paziente paziente;

    @ManyToOne
    @JoinColumn(name = "id_dispositivo_medico", referencedColumnName = "id", nullable = false)
    private DispositivoMedico dispositivoMedico;

    /**
     * @param dataMisurazione rappresenta la data della misurazione
     */
    public Misurazione(SimpleDateFormat dataMisurazione, Paziente paziente, DispositivoMedico dispositivoMedico){
        this.dataMisurazione = dataMisurazione;
        this.paziente = paziente;
        this.dispositivoMedico = dispositivoMedico;
    }

    /**
     * Costruttore vuoto per Misurazione
     */
    public Misurazione(){

    }

    /**
     *
     * @return id
     * metodo che restituisce l'id della misurazione
     */
    public long getId(){
        return this.id;
    }

    /**
     *
     * @return dataMisurazione
     * metodo che restituisce la data della misurazione
     */
    public SimpleDateFormat getDataMisurazione() {
        return this.dataMisurazione;
    }

    /**
     *
     * @param dataMisurazione
     * metodo che permette di inserire la data della misurazione
     *
     */
    public void setDataMisurazione(SimpleDateFormat dataMisurazione) {
        this.dataMisurazione = dataMisurazione;
    }

    /**
     *
     * @return dataMisurazione
     * metodo che permette di restituire il paziente a cui afferisce la misurazione
     *
     */
    public Paziente getPaziente() {
        return paziente;
    }

    /**
     *
     * @param paziente
     * metodo che permette di impostare il paziente della misurazione
     *
     */
    public void setPaziente(Paziente paziente) {
        this.paziente = paziente;
    }

    /**
     *
     * @return dispositivoMedico
     * metodo che permette di restituire il dispositivo medico da cui  è stata effettuata la misurazione
     *
     */
    public DispositivoMedico getDispositivoMedico() {
        return dispositivoMedico;
    }

    /**
     *
     * @param dispositivoMedico
     * metodo che permette di impostare il dispositivo medico della misurazione
     *
     */
    public void setDispositivoMedico(DispositivoMedico dispositivoMedico) {
        this.dispositivoMedico = dispositivoMedico;
    }
}
