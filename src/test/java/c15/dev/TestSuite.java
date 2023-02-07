package c15.dev;


import c15.dev.gestioneUtente.service.GestioneUtenteServiceImplTest;
import c15.dev.gestioneVisita.service.GestioneVisitaServiceImplTesting;
import c15.dev.registrazione.service.RegistrazioneServiceImplTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({GestioneVisitaServiceImplTesting.class, GestioneUtenteServiceImplTest.class , RegistrazioneServiceImplTest.class})
public class TestSuite {

}