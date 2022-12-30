package C15.dev.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.util.GregorianCalendar;

/**
 * @author Paolo Carmine Valletta
 * Creato il: 30/12/2022
 * Questa è la classe relativa ad una Misurazione Coagulazione.
 * I campi sono: tempo di protrobina, INR.
 *
 */
@Entity
public class MisurazioneCoagulazione extends Misurazione{
    @Column(name = "tempo_di_protrobina", nullable = false)
    private double tempoDiProtrobina;
    @Column(name = "inr", nullable = false)
    private int INR;

    /**
     *
     * Costruttore senza parametri per misurazione Coagulazione
     */

    public MisurazioneCoagulazione() {
        super();
    }

    /**
     * @param dataMisurazione rappresenta la data della misurazione coagulazione
     * @param paziente rappresenta il paziente della misurazione coagulazione
     * @param dispositivoMedico rappresenta il dispositivo medico della misurazione coagulazione
     * @param tempoDiProtrobina rappresenta il valore del tempo di protrobina della misurazione coagulazione
     * @param INR rappresenta il valore dell'INR della misurazione coagulazione
     *
     */

    public MisurazioneCoagulazione(GregorianCalendar dataMisurazione, Paziente paziente, DispositivoMedico dispositivoMedico, double tempoDiProtrobina, int INR) {
        super(dataMisurazione, paziente, dispositivoMedico);
        this.tempoDiProtrobina = tempoDiProtrobina;
        this.INR = INR;
    }

    /**
     *
     * @return tempoDiProtrobina
     * metodo che restituisce il valore del tempo di protrobina
     */

    public double getTempoDiProtrobina() {
        return tempoDiProtrobina;
    }

    /**
     *
     * @return INR
     * metodo che restituisce il valore dell'INR
     */

    public int getINR() {
        return INR;
    }

    /**
     *
     * @param tempoDiProtrobina
     * metodo che permette di impostare il valore del tempo di protrobina della misurazione coagulazione
     */

    public void setTempoDiProtrobina(double tempoDiProtrobina) {
        this.tempoDiProtrobina = tempoDiProtrobina;
    }

    /**
     *
     * @param INR
     * metodo che permette di impostare il valore dell'INR della misurazione coagulazione
     */

    public void setINR(int INR) {
        this.INR = INR;
    }
}
