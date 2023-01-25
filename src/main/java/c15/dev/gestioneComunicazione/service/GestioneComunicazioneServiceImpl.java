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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import java.time.LocalDate;

@Service
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

    @Override
    public Flux<ServerSentEvent<String>> invioNotifica(String messaggio,
                                                       Long idDestinatario) {
        Notifica n = new Notifica(
                LocalDate.of(2023, 11, 12),
                "Notifica Prova",
                "speriamo funzioni",
                StatoNotifica.NON_LETTA,
                utenteService.findPazienteById(1L)
        );
        daoNotifica.save(n);

        return Flux.just(ServerSentEvent.builder(n.getTesto()).build()).take(1);
    }

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

    @Override
    public void invioNota(String messaggio, Long idDestinatario, Long idMittente) {

        Medico m = (Medico) utenteService.findMedicoById(5L);
        Paziente p = (Paziente) utenteService.findPazienteById(1L);

        Nota nota = new Nota("sassi", LocalDate.of(2022,11,10),
                    Autore.M,StatoNotifica.NON_LETTA, m,p
                );
        notaDAO.save(nota);

    }
}
