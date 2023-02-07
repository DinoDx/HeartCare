package c15.dev.gestioneComunicazione.service;

import c15.dev.gestioneUtente.service.GestioneUtenteService;
import c15.dev.model.dao.NotaDAO;
import c15.dev.model.dto.NotaDTO;
import c15.dev.model.dto.NotificaDTO;
import c15.dev.model.entity.Medico;
import c15.dev.model.entity.Nota;
import c15.dev.model.entity.Paziente;
import c15.dev.model.entity.enumeration.StatoNota;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

/**
 * @author: Leopoldo todisco, Carlo Venditto.
 *
 */
@Service
@Transactional
public class GestioneComunicazioneServiceImpl
        implements GestioneComunicazioneService {
    /**
     * Oggetto per invio notifiche.
     */
    @Autowired
    private SimpMessagingTemplate template;

    /**
     * Service per le operazioni legate all'utente.
     */
    @Autowired
    private GestioneUtenteService utenteService;

    /**
     * Provvede alle operazioni del db delle note.
     */
    @Autowired
    private NotaDAO notaDAO;

    /**
     * Provvede all'invio delle email.
     */
    @Autowired
    private JavaMailSender mailSender;


    /**
     * Metodo per inviare una email.
     * @param messaggio messaggio da inviare nell'email.
     * @param emailDestinatario mail del destinatario.
     */
    @Override
    @Async
    public void invioEmail(final String messaggio,
                           final String emailDestinatario) {
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
     * @param messaggio messaggio da inviare.
     * @param idDestinatario id del destinatario della mail.
     * @param idMittente id del mittente della mail.
     */
    @Override
    public void invioNota(final String messaggio,
                          final Long idDestinatario,
                          final Long idMittente) {
        if(utenteService.isMedico(idMittente)){
            Medico m =  utenteService.findMedicoById(idMittente);
            Paziente p = utenteService.findPazienteById(idDestinatario);
            Nota nota = new Nota(messaggio,
                    LocalDate.now(),
                    idMittente,
                    StatoNota.NON_LETTA,
                    m,
                    p);
            notaDAO.save(nota);
            return;
        }

        Medico m = (Medico) utenteService.findMedicoById(idDestinatario);
        Paziente p = (Paziente) utenteService.findPazienteById(idMittente);

        Nota nota =
                new Nota(messaggio,
                        LocalDate.now(),
                        idMittente,
                        StatoNota.NON_LETTA,
                        m,
                        p);
        notaDAO.save(nota);
        return;
    }

    /**
     * Implementazione del metodo che cerca tutte le note.
     * @return lista di tutte le note.
     */
    public List<Nota> findAllNote(){
        return notaDAO.findAll();
    }

    /**
     * Firma del metodo che ricerca tutte le note di un utente.
     * @param id identificativo dell'utente.
     * @return lista delle note dell'utente.
     */
    @Override
    public List<NotaDTO> findNoteByIdUtente(final long id) {
        System.out.println(id);
        List<Nota> note = notaDAO.findNoteByIdUtente(id);
        List<NotaDTO> dto = note
                .stream()
                .map(e->
                        new NotaDTO(utenteService
                                .findUtenteById(e.getAutore())
                                .getNome()
                                + " "
                                + utenteService
                                .findUtenteById(e.getAutore())
                                .getCognome(),
                e.getContenuto())).toList();

        return dto;
    }

    /**
     * Firma del metodo che restituisce tutte le note non lette di un utente.
     * @param email email dell'utente.
     * @return lista delle note non lette dell'utente.
     */
    @Override
    public List<Nota> findNoteNonLetteByUser(String email) {
        var user = utenteService.findUtenteByEmail(email);
        long idUser = user.getId();

        var list = notaDAO.findNoteByIdUtente(idUser);
        return list.stream().filter(n -> n.getStatoNota().equals(StatoNota.NON_LETTA)).toList();

    }


    /**
     * Metodo per inviare una notifica al frontend.
     * @param message è il messaggio che viene passato al frontend.
     */
    @Override
    @SendTo("/topic/notifica")
    public void sendNotifica(final String message, final Long idDest) {
        System.out.println("nel metodo sendNotifica");
        NotificaDTO n = NotificaDTO.builder()
                .messagio(message)
                .idPaziente(idDest)
                .build();

        System.out.println("notifica dto = " + n.toString());

        template.convertAndSend("/topic/notifica", n);
    }
}
