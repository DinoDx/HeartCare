package c15.dev.gestioneComunicazione.service;

import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.model.dao.NotaDAO;
import c15.dev.model.dao.NotificaDAO;
import c15.dev.model.entity.Medico;
import c15.dev.model.entity.Nota;
import c15.dev.model.entity.Notifica;
import c15.dev.model.entity.Paziente;
import c15.dev.model.entity.enumeration.Autore;
import c15.dev.model.entity.enumeration.StatoNotifica;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.time.LocalDate;

@Service
@Transactional
public class GestioneComunicazioneServiceImpl
        implements GestioneComunicazioneService {
    @Autowired
    private GestioneUtenteService utenteService;
    @Autowired
    private NotificaDAO daoNotifica;
    @Autowired
    private NotaDAO notaDAO;
    @Autowired
    private JavaMailSender mailSender;

    /**
     * Metodo per inviare una notifica.
     * @param messaggio
     * @param idDest
     * @return
     */
    @Override
    public Flux<ServerSentEvent<String>> invioNotifica(final String messaggio,
                                                       final Long idDest) {
        Notifica n = new Notifica(
                LocalDate.of(2023, 11, 12),
                "Notifica Prova",
                "speriamo funzioni",
                StatoNotifica.NON_LETTA,
                utenteService.findPazienteById(1L)
        );
        daoNotifica.save(n);

        return Flux
                .just(ServerSentEvent.builder(n.getTesto()).build())
                .take(1);
    }

    /**
     * Metodo per inviare una email.
     * @param messaggio
     * @param emailDestinatario
     */
    @Override
    public void invioEmail(String messaggio, String emailDestinatario) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("leopoldo.todiscozte@gmail.com");
        message.setTo("leopoldo.todiscozte@gmail.com");
        message.setSubject("PROVA");
        message.setText(messaggio);
        mailSender.send(message);
        System.out.println("email inviata");

        //jujdhvttecwbdgdn
    }

    /**
     * Metodo per inviare una nota.
     * @param messaggio
     * @param idDestinatario
     * @param idMittente
     * @return
     */
    @Override
    public Flux<ServerSentEvent<String>> invioNota(final String messaggio,
                                                   final Long idDestinatario,
                                                   final Long idMittente) {

        Medico m = (Medico) utenteService.findMedicoById(5L);
        Paziente p = (Paziente) utenteService.findPazienteById(2L);

        Nota nota = new Nota(messaggio, LocalDate.of(2022,11,10),
                    Autore.M, StatoNotifica.NON_LETTA, m, p
                );
        notaDAO.save(nota);
        System.out.println("ciao");
        System.out.println(nota.getContenuto());
        return Flux
                .just(ServerSentEvent.builder(nota.getContenuto()).build())
                .take(1);
    }
}
