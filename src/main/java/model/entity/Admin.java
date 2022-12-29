package model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author leopoldotodisco
 * Creato il: 29/12/2022
 * Questa è la classe relativa a un Admin.
 */
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends UtenteRegistrato{


}
