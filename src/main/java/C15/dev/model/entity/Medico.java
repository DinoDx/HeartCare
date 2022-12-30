package C15.dev.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;


import java.util.List;
import java.util.Set;

/**
 * @author carlo
 * Creato il: 29/12/2022
 * Questa è la classe relativa a un Medico.
 */


@Entity
public class Medico extends UtenteRegistrato{
    @OneToMany(mappedBy = "medico")
    private List<Paziente> elencoPazienti;
    @OneToMany(mappedBy = "medico", fetch = FetchType.EAGER)
    private Set<Nota> note;

    /**
     *
     * @return elencoPazienti
     * Metodo che restituisce l'elenco dei pazienti di un medico
     */
    public List<Paziente> getElencoPazienti() {
        return elencoPazienti;
    }

    /**
     *
     * @param elencoPazienti
     * Metodo che permette di inserire una lista di paziente
     */
    public void setElencoPazienti(List<Paziente> elencoPazienti) {
        this.elencoPazienti = elencoPazienti;
    }
}
