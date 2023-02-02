package c15.dev.model.listeners;

import c15.dev.gestioneComunicazione.service.GestioneComunicazioneService;
import c15.dev.gestioneMisurazione.misurazioneAdapter.ControlloMisurazioni;
import c15.dev.model.entity.Misurazione;
import jakarta.annotation.PostConstruct;
import org.hibernate.SessionFactory;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.EventType;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Leopoldo Todisco
 * Listener che si occupa di inviare email quando la misurazione è sballata.
 */
@Component
public class MisurazioneEventListener implements PostInsertEventListener {
    @Autowired
    private GestioneComunicazioneService comunicazioneService;
    @Autowired
    private SessionFactory session;

    @PostConstruct
    protected void init() {
        SessionFactoryImpl sF = session.unwrap(SessionFactoryImpl.class);
        var reg = sF
                                .getServiceRegistry()
                                .getService(EventListenerRegistry.class);
        reg.getEventListenerGroup(EventType.POST_INSERT).appendListener(this);
    }

    /**
     *
     * @param event
     */
    @Override
    public void onPostInsert(PostInsertEvent event) {
        var eventEntity = event.getEntity();
        var str = "La misurazione del tuo paziente è sballato";

        if(eventEntity instanceof Misurazione) {
            Misurazione misurazione = (Misurazione) eventEntity;
            if (ControlloMisurazioni.chiamaControllo(misurazione)) {
                comunicazioneService.
                        sendNotifica("Misurazione Sballata",
                        misurazione.getPaziente().getId());
                if(misurazione.getPaziente().getEmailCaregiver() != null) {
                    comunicazioneService.invioEmail(str, misurazione.getPaziente().getEmailCaregiver());
                }
            }
        }
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister persister) {
        return false;
    }
}
